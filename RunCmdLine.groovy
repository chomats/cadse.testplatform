/**
 *
 */
package fr.imag.adele.cadse.platform

import groovy.util.AntBuilderimport java.io.File
import org.apache.tools.ant.types.Pathimport java.lang.IllegalArgumentExceptionimport groovy.lang.GroovyShell
import java.io.PrintWriterimport groovy.ui.GroovyMainimport java.lang.Throwable
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
/**
 * @author chomats
 *
 */
public class RunCmdLine{

	/**
     * Parse the command line.
     *
     * @param options the options parser.
     * @param args    the command line args.
     * @return parsed command line.
     * @throws ParseException if there was a problem.
     */
    private static CommandLine parseCommandLine(Options options, String[] args) throws ParseException {
        CommandLineParser parser = new PosixParser();
        return parser.parse(options, args, true);
    }

	static void processArgs(String[] args, final PrintStream out) {
        Options options = buildOptions();

        try {
            CommandLine cmd = parseCommandLine(options, args);

            if (cmd.hasOption('h')) {
                printHelp(out, options);
            } else if (cmd.hasOption('v')) {
                String version = '1.0';
                out.println("Cades test Version: " + version + " JVM: " + System.getProperty("java.version"));
            } else {
                // If we fail, then exit with an error so scripting frameworks can catch it
                // TODO: pass printstream(s) down through process
                if (!process(cmd)) {
                    System.exit(1);
                }
            }
        } catch (ParseException pe) {
            out.println("error: " + pe.getMessage());
            printHelp(out, options);
        }
    }

	/**
     * Build the options parser.  Has to be synchronized because of the way Options are constructed.
     *
     * @return an options parser.
     */
    private static synchronized Options buildOptions() {
        Options options = new Options();

        options.addOption(
            OptionBuilder.withLongOpt("define").
                withDescription("define a system property").
                hasArg(true).
                withArgName("name=value").
                create('D')
        );
        options.addOption(
            OptionBuilder.hasArg(false)
            .withDescription("usage information")
            .withLongOpt("help")
            .create('h'));
        options.addOption(
            OptionBuilder.hasArg(false)
            .withDescription("debug mode will print out full stack traces")
            .withLongOpt("debug")
            .create('d'));
        options.addOption(
            OptionBuilder.hasArg(false)
            .withDescription("display the Cadse test and JVM versions")
            .withLongOpt("version")
            .create('v'));
        options.addOption(
            OptionBuilder.withArgName("test")
            .hasArg()
            .withDescription("specify the test name")
            .withLongOpt("test")
            .create('t'));
        options.addOption(
            OptionBuilder.withArgName("testpath")
            .hasArg()
            .withDescription("specify a folder where is test.properties")
            .withLongOpt("testpath")
            .create('p'));
        options.addOption(
                OptionBuilder.withArgName("compilepath")
                .hasArg()
                .withDescription("specify a folder where is CadseTestPlatform to compile")
                .withLongOpt("compilepath")
                .create('c'));

        return options;
    }

	private static void printHelp(PrintStream out, Options options) {
        HelpFormatter formatter = new HelpFormatter();
        PrintWriter pw = new PrintWriter(out);

        formatter.printHelp(
            pw,
            80,
            "groovy [options] [args]",
            "options:",
            options,
            2,
            4,
            null, // footer
            false);

        pw.flush();
    }

	/**
     * Process the users request.
     * -p path properties default .
     * -c path CadseTestPlatform
     * -
     *
     * @param line the parsed command line.
     * @throws ParseException if invalid options are chosen
     */
    private static boolean process(CommandLine line) throws ParseException {

    	String[] testClasses = line.getOptionValues('t');
    	String testPath = line.getOptionValue('p');

    	AntBuilder ant = new AntBuilder();
    	File f = new File('.')
		if (testPath != null)
			f = new File(testPath);
    	File testFile = new File(f, 'test.properties');
		ant.property(file:testFile.toString())
		String testPlatformPath = ant.project.properties.testPlatformPath




		String javaHome = ant.project.properties.get('java.home')
		File java_tools_jar = new File(new File(javaHome), 'lib/tools.jar')
		if (java_tools_jar.exists()) {
			throw new IllegalArgumentException("Cannot find tools.jar")
		}
		String testEclipsePluginPath  = "$testPlatformPath/eclipse/plugins"
		println testEclipsePluginPath
		Path runPath = ant.path( id:"run.class.path") {
			fileset (dir:testEclipsePluginPath) {
				include(name:'org.apache.xerces*.jar')
				include(name:'org.apache.ant_*/lib/*.jar')
				include(name:'org.eclipse.pde.junit.runtime_*.jar')
				include(name:'org.eclipse.equinox.app_*.jar')
				include(name:'org.eclipse.equinox.common_*.jar')
				include(name:'org.eclipse.equinox.http.jetty_*.jar')
				include(name:'org.eclipse.equinox.http.registry_*.jar')
				include(name:'org.eclipse.equinox.http.servlet_*.jar')
				include(name:'org.eclipse.equinox.jsp.jasper_*.jar')
				include(name:'org.eclipse.equinox.jsp.jasper.registry_*.jar')
				include(name:'org.eclipse.equinox.launcher_*.jar')
				include(name:'org.eclipse.equinox.preferences_*.jar')
				include(name:'org.eclipse.core.runtime.compatibility.registry_*/runtime_registry_compatibility.jar')
				include(name:'org.eclipse.equinox.registry_*.jar')
				include(name:'org.eclipse.jdt.junit_*.jar')
				include(name:'org.eclipse.jdt.junit.runtime_*.jar')
				include(name:'org.eclipse.jdt.junit4.runtime_*.jar')
				include(name:'org.eclipse.jdt.ui_*.jar')
				include(name:'org.eclipse.core.jobs_*.jar')
				include(name:'org.eclipse.core.resources_*.jar')
				include(name:'org.eclipse.core.runtime_*.jar')
				include(name:'org.eclipse.jface_*.jar')
				include(name:'org.eclipse.osgi_*.jar')
				include(name:'org.eclipse.pde.core_*.jar')
				include(name:'org.eclipse.swt_*.jar')
				include(name:'org.eclipse.swt.gtk.*.jar')
				include(name:'org.eclipse.ui.workbench_*.jar')
				include(name:'org.junit4_*/junit.jar')
   			}
		}


    	String compilePath = line.getOptionValue('c');
    	if (compilePath != null) {
    		String testClassesDirectory = "$testPlatformPath/target/CadseTestPlatform/classes"
    		ant.delete(dir:testClassesDirectory)
    		ant.mkdir(dir:testClassesDirectory)
    		ant.taskdef(name:'groovyc', classname:'org.codehaus.groovy.ant.Groovyc')

    		ant.groovyc(srcdir:"${compilePath}/src", destdir:testClassesDirectory) {
    		  classpath {
    		    pathelement(path:testClassesDirectory)
    		    path(refid:"run.class.path")
    		  }
    		  javac(source:"1.5", target:"1.5", debug:"on")
    		}
    		ant.jar( destfile:"$testPlatformPath/target/CadseTestPlatform.jar", manifest:"${compilePath}/META-INF/MANIFEST.MF") {
	  			// includes binary resources
	            fileset(dir :compilePath, excludes:"src/**/*, .svn, .project, .classpath, bin, bin-groovy, build.properties")
	            fileset(dir:"$compilePath/src",excludes:'*.java,*.aj,*.groovy,.svn')
				 // includes class dir
	            fileset(dir : testClassesDirectory)
	        }
    	}

		String[] listrunpath = runPath.list()
		def groovy_parent_classloader = GroovyShell.class.getClassLoader()
		println groovy_parent_classloader
		println "parent class loader"
		for(p in groovy_parent_classloader.getURLs()) {
			println " - $p"
		}
		GroovyShell shell = new GroovyShell()
		for(p in listrunpath ) {
			println p;
			shell.getClassLoader().addClasspath(p)
		}
		println "$testPlatformPath/target/CadseTestPlatform.jar"
		println java_tools_jar.toString()
		shell.getClassLoader().addClasspath("$testPlatformPath/target/CadseTestPlatform.jar")
		shell.getClassLoader().addClasspath(java_tools_jar.toString())

		if (testClasses == null) {
			testClasses = [ 'fr.imag.adele.cadse.platform.srciptTest.AllTestRun']
		}
		for(t in testClasses) {
			try {
				if (t.indexOf('.') == -1) {
					t = "fr.imag.adele.cadse.platform.srciptTest.$t"
				}
				println "Run test $t"
				def c = shell.getClassLoader().loadClass(t);
				c.main();
			} catch(Throwable e) {
				e.printStackTrace();
			}

		}

        return true;
    }

	public static void main(String[] args) {
		processArgs(args, System.out);
	}
}
