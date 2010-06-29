package fr.imag.adele.cadse.platform.gr

import java.io.File;

import fr.imag.adele.cadse.platform.CadseTestPart;
import fr.imag.adele.cadse.platform.*;
import groovy.util.AntBuilder;
import org.apache.tools.ant.types.FileSet;

public class FelixTestPart extends CadseTestPart {
	
	public FelixTestPart(String testProperties, String testName,
			String testPluginName, String classname, Closure c) {
		super(testProperties, testName, testPluginName, classname, c);
	}
	
	public RunJavaTestClass createRunner() {
		String felixHome = run.ant.project.properties.get('felix.home');
		if (felixHome == null)
			throw new RuntimeException("Cannot found felix home");
		
		String maxMem = run.ant.project.properties.get('felix.mx')
		if (maxMem == null)
			maxMem = "1024m"
		String felixClassName = run.ant.project.properties.get('felix.mainClass')
		if (felixClassName == null)
			felixClassName = "fr.imag.adele.felix.junit.runtime.JunitMain";
		
		RunJavaFelixTestClass rjftc = new RunJavaFelixTestClass(run, this);
		rjftc.setClassName(felixClassName);
		rjftc.setBasedir(felixHome);
		FileSet fs = run.ant.fileset(dir:"${felixHome}/lib") { include( name:'*.jar') }
		rjftc.setClassPath(fs)
		run.ant.delete(dir:"$felixHome/$testName");
		
		//String configFelix = run.ant.project.properties.get('felix.config.properties');
		//if (configFelix != null)
		//	rjftc.addJvmParam((String) "-Dfelix.config.properties=$configFelix");
		for (String key in run.ant.project.properties.keySet() )
		   {
			   if (key.startsWith("felix.") || key.startsWith("org.osgi.framework."))
			   {
				   String value = run.ant.project.properties.get(key)
				   rjftc.addJvmParam((String) "-D${key}=${value}");
			   }
		   }
		   
		rjftc.addParam((String)"--bundles-dir", (String)"$felixHome/bundles");
		rjftc.addParam((String)"--bundles-dir", (String)"$felixHome/dropins");
		rjftc.addParam((String)"--cache-dir" , (String)"$felixHome/$testName");
		return rjftc;
	}
	
	
}
