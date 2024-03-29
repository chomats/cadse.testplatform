package fr.imag.adele.cadse.platform.gr


import java.text.SimpleDateFormatimport java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest
import org.osgi.framework.Version

import fr.imag.adele.cadse.platform.*;

import java.io.File;
import java.lang.Throwable

public class BuildManager implements IBuildManager{
	
	String testPlatformPath
	String testEclipsePath
	AntBuilder ant
	boolean debugFlag = false
	String _buildNumber = null
	final String defaultTimezone = 'GMT+2'
	
	String plugins = "dropins" // plugins
	
	List<BundleDescription> bundlestoCompile = new ArrayList<BundleDescription>();
	
	public BuildManager(AntBuilder ant, String platformPath, String eclipsepath) {
		this.ant =  ant;
		
		testPlatformPath = platformPath
		testEclipsePath  = eclipsepath
		def localDebugFlag = ant.project.properties.get('build.debug')
		debugFlag = localDebugFlag == true || localDebugFlag == 'true'
	}
	
	
	String getBuildNumber() {
		if (_buildNumber == null) {
			_buildNumber = tstamp('yyyMMddHHmm', defaultTimezone, 'fr', 'fr', null)
		}
		
		return _buildNumber;
	}
	
	public void setDebugFlag(boolean value) {
		this.debugFlag = value;
	}
	
	private String tstamp(def format, def timezone, def language,def country, def d) {
		if (d == null)
			d = new Date();
		
		SimpleDateFormat f = new SimpleDateFormat (format, new Locale(language, country));
		if (timezone != null && timezone.length() != 0)
			f.setTimeZone(TimeZone.getTimeZone(timezone));
		return f.format(d);
	}
	
	
	public void changeVersion(def manifestfile, def manifestoutfile, def value) {
		Manifest mf = new Manifest(new FileInputStream(manifestfile));
		mf.getMainAttributes().putValue('Bundle-Version', value);
		mf.write(new FileOutputStream(manifestoutfile));
	}
	
	boolean createBundle(String pathWs, String name, String srcFolder) {
		File pom = new File("${pathWs}/$name/pom.xml");
		if (pom.exists()) {
			boolean ret=  callMaven("${pathWs}/$name", "clean install", "");
			if (ret)
				return true;
			ant.delete() {
				fileset(dir:"${testEclipsePath}/${plugins}/", includes:"${name}_*.jar")
			}
			ant.copy(todir:"${testEclipsePath}/${plugins}", overwrite:true)  {
				fileset(dir:"${pathWs}/$name/target", 
						includes:"${name}*.jar", 
						excludes:"${name}_*-sources.jar")
			}
			return false;
		} else
			return createBundle(pathWs, name, srcFolder, true)
	}
	boolean initMaven = false;
	
	boolean deployMavenBundle() {
		if (!initMaven) {
			ant.taskdef(name:"dependencies", classname:"fr.imag.adele.cadse.platform.DependenciesTask")
			initMaven = true;
		}
		
		ant.mkdir(dir:"${testEclipsePath}/${plugins}")
		ant.dependencies( filesetId:"dependency.fileset") {
  			for(BundleDescription mbd : bundlestoCompile) {
				  if (!(mbd instanceof MavenBundleDescription)) continue;
				  String gId = mbd.getgId();
				  String aId = mbd.getaId();
				  String vId = mbd.getvId();
				  dependency(groupId:"$gId", artifactId:"$aId", version:"$vId", scope:"compile")
			}
  		}
  		ant.copy(todir:"${testEclipsePath}/${plugins}", overwrite:true)  {
			fileset(refid:"dependency.fileset")
			mapper(type:"flatten")
		}
		return false;
	}
	
	
	String getVersion(String manifestfile) {
		Manifest mf = new Manifest(new FileInputStream(manifestfile));
		String v_str =  mf.getMainAttributes().getValue('Bundle-Version');
		
		String buildNumber = getBuildNumber();
		if (v_str.length() == 0) {
			println "[ERROR] bad version for ${ma.name} $v_str"
			v_str == '1.0.0'
		}
		def v = Version.emptyVersion;
		try {
			v = new Version(v_str)
		} catch (Throwable e) {
			println "[ERROR] bad osgi version for ${ma.name} $v_str"
		}
		def q = v.getQualifier();
		if (q.length() == 0 || q == 'dev-version' || q == 'qualifier') {
			q = "_$buildNumber";
		} else if (q.contains('dev-version')) {
			q = q.replaceFirst('dev-version', buildNumber)
		} else if (q.contains('qualifier')) {
			q = q.replaceFirst('qualifier', buildNumber)
		} else {
			q = "${q}_$buildNumber"
		}
		
		v= new Version(v.major, v.minor, v.micro, q)
		return v.toString()
	}
	
	/** true if failed */
	boolean createBundle(String pathWs, String name, String srcFolder, boolean excludeDot) {
		FileBuildModel fbm = new FileBuildModel("${pathWs}/$name/build.properties")
		fbm.setDefaultSourceFolder(srcFolder)
		return createBundle(fbm, pathWs, name, excludeDot)
	}
	
	boolean callMaven(String basedir, String goals, String options) {
		String mavenHome = ant.project.properties.get('maven.home');
		if (mavenHome == null)
			mavenHome = ant.project.properties.get('maven-home');
		if (mavenHome == null)
			throw new RuntimeException("Cannot found maven home");
		String maxMem = ant.project.properties.get('maven.mx')
		if (maxMem == null)
			maxMem = "1024m"
		String profile = ant.project.properties.get('maven.profile')
		if (profile != null && profile.length() !=0)
			options = "-P ${profile} ${options}"
		try {
			ant.java(classname: 'org.codehaus.classworlds.Launcher', fork:true,
					dir:basedir, failonerror:true) {
						jvmarg( value:"-Xmx${maxMem}")
						classpath() {
							fileset(dir:"${mavenHome}/boot") { include( name:'*.jar') }
							fileset(dir:"${mavenHome}/lib") { include( name:'*.jar') }
						}
						sysproperty( key:'classworlds.conf', value:"${mavenHome}/bin/m2.conf")
						sysproperty( key:'maven.home', value: mavenHome)
						arg( line:"--batch-mode ${options} ${goals}")
					}
			return false;
		} catch (Throwable e) {
			println 'Error : ';
			e.printStackTrace();
			return true;
		}
	}
	
	boolean createBundleWithMaven(String name) {
		return callMaven("${pathWs}/$name", "clean install", "");
	}
	
	/** true if failed */
	boolean createBundle(FileBuildModel fbm, String pathWs, String name, boolean excludeDot) {
		try {
			String srcFolder = fbm.getDefaultSourceFolder();
			
			ant.echo(message:"Compile and create bundle $name, debug $debugFlag")
			String classesDir = "$testPlatformPath/target/$name/classes"
			ant.mkdir(dir : classesDir)
			ant.path( id:"build.class.path") {
				fileset (dir:testEclipsePath) {
					include(name:"plugins/**/*.jar")
					include(name:"dropins/**/*.jar")
				}
			}
			// compile source folder
			String[] srcFolders = fbm.getTokensEntry('source..')
			ant.property(name:'build.compiler', value:'org.eclipse.jdt.core.JDTCompilerAdapter');
			
			ant.javac(destdir:classesDir, debug:debugFlag, debuglevel:'lines,vars,source', classpathref:'build.class.path', 
					source:'1.5',
					target:'1.5',
					includeantruntime:false,
					nowarn:true) {
						for(s in srcFolders) {
							src(path:"$pathWs/$name/$s")
						}
					}
			
			// build extra jars
			String[] jars = fbm.getTokensEntry('jars')
			if (jars != null) {
				for(j in jars) {
					srcFolders = fbm.getTokensEntry("source.$j")
					ant.javac(destdir:classesDir+"-$j", debug:debugFlag, classpathref:'build.class.path', includeantruntime:false) {
						for(src in srcFolders) {
							src(path:"$pathWs/$name/$src")
						}
					}
					ant.zip(destfile:"${testPlatformPath}/target/${name}/$j") {
						zipfileset(dir : classesDir+"-$j")
					}
				}
			}
			
			// get and change version  in the manifset
			String version = getVersion("${pathWs}/$name/META-INF/MANIFEST.MF")
			
			changeVersion("${pathWs}/$name/META-INF/MANIFEST.MF",
					"${testPlatformPath}/target/${name}/MANIFEST.MF",
					version
					)
			
			// delete old version in target
			String bundle_file_jar = "${testPlatformPath}/target/${name}_${version}.jar"
			ant.delete() {
				fileset(dir:"${testPlatformPath}/target/", includes:"${name}_*.jar")
			}
			// create jar files
			ant.jar( destfile:bundle_file_jar, manifest:"${testPlatformPath}/target/${name}/MANIFEST.MF") {
				// includes binary resources
				String[] binIncludes = fbm.getTokensEntry('bin.includes')
				if (binIncludes != null) {
					for(bi in binIncludes) {
						if (bi.equals('.')){
							if (!excludeDot) {
								fileset(dir :"${pathWs}/$name", includes:bi, excludes:"$srcFolder/**/*.java,$srcFolder/**/*.aj,$srcFolder/**/*.groovy,.svn")
							}
						} else {
							fileset(dir :"${pathWs}/$name", includes:bi, excludes:'.svn')
						}
					}
				}
				else {
					fileset(dir :"${pathWs}/$name", excludes:"$srcFolder/**/*.java,$srcFolder/**/*.aj,$srcFolder/**/*.groovy, .svn, .project, .classpath")
				}
				// includes extra jars
				if (jars != null) {
					for(j in jars) {
						fileset(dir : "${testPlatformPath}/target/$name", includes:"$j")
					}
				}
				// includes resources in sources folder
				for(src in srcFolders) {
					fileset(dir:"$pathWs/$name/$src",excludes:'*.java,*.aj,*.groovy,.svn')
				}
				// includes class dir
				fileset(dir : classesDir)
			}
			ant.delete() {
				fileset(dir:"${testEclipsePath}/${plugins}/", includes:"${name}_*.jar")
			}
			ant.copy(todir:"${testEclipsePath}/${plugins}", file:bundle_file_jar, overwrite:true)
			return false;
		} catch (Throwable e) {
			println 'Error : ';
			e.printStackTrace();
			return true;
		}
	}
	
	
	/** true if failed */
	public boolean deleteBundle( String name) {
		try {
			
			ant.delete() {
				fileset(dir:"${testEclipsePath}/${plugins}/", includes:"${name}_*.jar")
			}
			return false;
		} catch (Throwable e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public void addBundle(BundleDescription b) {
		for(BundleDescription findB : bundlestoCompile) {
			if (findB.equals(b)) return;
		}
		bundlestoCompile.add( b);
	}
	
	@Override
	public List<BundleDescription> getBundlestoCompile() {
		return bundlestoCompile;
	}
	
	@Override
	public void setBundlestoCompile(List<BundleDescription> bundlestoCompile) {
		this.bundlestoCompile = bundlestoCompile;
	}
}
