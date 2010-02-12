/**
 *
 */
package fr.imag.adele.cadse.platform

import java.util.jar.Manifest
import java.util.Locale
import java.text.SimpleDateFormat
import java.lang.System
import java.io.FileInputStream
import java.util.Properties
import groovy.util.AntBuilder
import org.osgi.framework.Version
import org.junit.Assert
import java.lang.Long
import java.lang.Boolean
import groovy.lang.Closure
import java.lang.NullPointerException
import groovy.util.Eval
import org.eclipse.jdt.internal.junit.model.ITestRunListener2;
import org.eclipse.jdt.internal.junit.model.RemoteTestRunnerClient;

import junit.framework.TestListener
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;

import org.eclipse.jdt.internal.junit.runner.MessageIds;
import org.eclipse.jdt.internal.junit.ui.JUnitPlugin;

/**
 * @author chomats
 *
 */
public class RunJavaTestClass{
	String testPlatformPath;
	String wsTest ;
	String testEclipsePath;
	String testReport;
	AntBuilder ant ;

	long timeout
	int debugPort
	boolean suspend
	
	Binding b = new Binding();

	String cadseToExecute
	boolean deleteWsDir
	String resourcesPath
	String outputFile
	String errorFile
	String testOutput
	String screenshots
	String memArgs
	String wsDir
	String testProperties
	String testName
	String testPluginName
	String classname
	String wsDirFolder
	String application
	String jvm
	String execEclipse
	String display
	
	CadseTestPlatform run

	public RunJavaTestClass(CadseTestPlatform run, String testProperties, String testName,String testPluginName, String classname ) {
		this.testProperties = testProperties
		this.testName = testName;
		this.testPluginName =testPluginName
		this.classname = classname
		this.run = run;

		testPlatformPath = run.testPlatformPath;
		wsTest = run.wsTest;
		testEclipsePath = run.testEclipsePath;
		testReport = run.testReport;
		ant = run.ant;
		if (testPluginName == null) {
			throw new NullPointerException();
		}
		if (classname == null) {
			throw new NullPointerException();
		}
	}

	public boolean run(Closure c ) {

		if (testProperties != null) {
			ant.property(file:testProperties)
		}
		wsDirFolder = converToPath(getTestValue('wsdir', testName, 'test-ws'));
		application = getTestValue('application', testName, 'org.eclipse.swtbot.eclipse.junit4.headless.swtbottestapplication');


		timeout 		= toLong(getTestValue('timeout', testName, '7200000'));
		debugPort 		= toInt(getTestValue('debug.port', testName, '0'))
		suspend 		= toBoolean(getTestValue('debug.suspend', testName, 'false'))

		cadseToExecute 	= getTestValue('cadseToExecute', testName, null)
		deleteWsDir 	= toBoolean(getTestValue('deleteWsDir', testName, 'true'))
		resourcesPath 	= converToPath(getTestValue('resourcesPath', testName, "${wsTest}/${testPluginName}/resources/"))
		outputFile 		= converToPath(getTestValue('outputFile', testName, "${testReport}/${testName}.txt"))
		errorFile  		= converToPath(getTestValue('errorFile', testName, "${testReport}/${testName}.txt"))
		testOutput		= converToPath(getTestValue('testOutput', testName, "${testReport}/${testName}.xml"))
		screenshots		= converToPath(getTestValue('screenshots', testName, "${testReport}/${testName}/screenshots/"))
		memArgs			= getTestValue('mem.args', testName, "-XX:MaxPermSize=128m -Xms400m -Xmx920m")
		wsDir 			= converToPath("${testPlatformPath}/${wsDirFolder}")
		if (System.getProperties().get("os.name").startsWith("Windows"))
			execEclipse 	= converToPath(getTestValue('execEclipse', testName, "${testEclipsePath}/eclipse.exe"))
		else
			execEclipse 	= converToPath(getTestValue('execEclipse', testName, "${testEclipsePath}/eclipse"))
		display 		= getTestValue('display',testName, null)
		
		
		
		// set default jvm to use for testing-->
		jvm 			= getTestValue('jvm', testName, System.getProperty('java.home').replace ('\\','/')+"/bin/java")

		if (c != null) {
			c.setDelegate(this);
			c.call()
		}
		if (deleteWsDir)
			ant.delete(dir : wsDir)
		ant.mkdir(dir: testReport)
		ant.mkdir(dir: screenshots)

		ant.delete(file: testOutput)
		if (outputFile == "")
			outputFile = null;
		if (outputFile != null)
			ant.delete(file: outputFile)

		ant.echo(message:"Running ${testName}. Result file: ${testOutput}")
		run.cadseCollector.runTestRunnerClient(testName, classname, 2566, testOutput)

		if (true) { //System.getProperties().get("os.name").startsWith("Windows")
			// Windows
			ant.exec(executable:execEclipse, 
					 logError:true,
					 dir:testEclipsePath) {
				arg(line:"-application ${application}")
				arg(line:"-data '${wsDir}'" )
				arg(line:"-testPluginName ${testPluginName}" )
				arg(line:"-className ${classname}" )
				arg(line:"-consoleLog")
				arg(line:"-console")
				arg(line:"-clean" )
				arg(line:"-port 2566")
				arg(line:'-loaderpluginname org.eclipse.swtbot.eclipse.junit4.headless')
				arg(line:'-testloaderclass org.eclipse.jdt.internal.junit4.runner.JUnit4TestLoader')
				arg(line:"-vmargs" )
				//arg(line:"-Dosgi.bundles='org.eclipse.equinox.common@2:start, org.eclipse.update.configurator@3:start, org.eclipse.core.runtime@4:start, org.apache.felix.ipojo@4:start, fr.imag.adele.ipojo.autostart@4:start'")
				arg(line:memArgs)
				if (cadseToExecute != null) {
					arg(line:"-Dfr.image.adele.addcadse='$cadseToExecute'")
				}
				arg(line:"-Dfr.image.adele.cadse.test.path='$testPlatformPath'")
				arg(line:"-Dorg.eclipse.swtbot.screenshots.dir='${screenshots}'")
				arg(line:"-Dtest.resourcesPath='${resourcesPath}'")
				arg(line:"-Dorg.eclipse.swtbot.keyboard.strategy=org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy")
				arg(line:"-Dorg.eclipse.swtbot.search.timeout='30000'")
					
				if (debugPort != 0) {
					String suspendString = suspend?'y':'n'
					arg( line:"-Xdebug -Xrunjdwp:transport=dt_socket,address=${debugPort},server=y,suspend=$suspendString")
				}
				env( key:"PLUGIN_PATH", path:"${testEclipsePath}/plugins")
				if (display != null)
						env(key:"DISPLAY", value:"${display}")
			}
		} else {
			// Linux
			ant.java(fork:true, dir:testEclipsePath, jvm:jvm,
				classname:'org.eclipse.core.launcher.Main',
				output:outputFile) {

					classpath() { fileset(dir:"${testEclipsePath}/plugins", includes:'org.eclipse.equinox.launcher_*.jar') }
					arg(line:"-application ${application}")
					arg(line:"-data ${wsDir}" )
					arg(line:"-testPluginName ${testPluginName}" )
					arg(line:"-className ${classname}" )
					arg(line:"-consoleLog")
					arg(line:"-console")
					arg(line:"-clean" )
					arg(line:"-port 2566")
					arg(line:'-loaderpluginname org.eclipse.swtbot.eclipse.junit4.headless')
					arg(line:'-testloaderclass org.eclipse.jdt.internal.junit4.runner.JUnit4TestLoader')
					//jvmarg(line:"-Dosgi.bundles='org.eclipse.equinox.common@2:start, org.eclipse.update.configurator@3:start, org.eclipse.core.runtime@4:start, org.apache.felix.ipojo@4:start, fr.imag.adele.ipojo.autostart@4:start'")
					jvmarg(line:memArgs)
					if (cadseToExecute != null) {
						jvmarg(line:"-Dfr.image.adele.addcadse='$cadseToExecute'")
					}
					jvmarg(line:"-Dfr.image.adele.cadse.test.path='$testPlatformPath'")
					jvmarg(line:"-Dorg.eclipse.swtbot.screenshots.dir='${screenshots}'")
					jvmarg(line:"-Dtest.resourcesPath='${resourcesPath}'")
					jvmarg(line:"-Dorg.eclipse.swtbot.keyboard.strategy=org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy")
					jvmarg(line:"-Dorg.eclipse.swtbot.search.timeout='30000'")
					

					if (debugPort != 0) {
						String suspendString = suspend?'y':'n'
						jvmarg( line:"-Xdebug -Xrunjdwp:transport=dt_socket,address=${debugPort},server=y,suspend=$suspendString")
					}
					env( key:"PLUGIN_PATH", value:"${testEclipsePath}/plugins")
					if (display != null)
						env(key:"DISPLAY", value:"${display}")
				}
		}
		
		return run.cadseCollector.failed();

	}

	private toLong(String value) {
		return Long.parseLong(value);
	}

	private toInt(String value) {
		return Integer.parseInt(value);
	}

	private toBoolean(String value) {
		return Boolean.parseBoolean(value);
	}
	
	private converToPath(String value) {
		return new java.io.File(value).getPath();
	}

	private String getTestValue(String key, String testName, String defaultValue) {
		String realKey = "test.${testName}.${key}"
		def p = ant.project.properties;
		String ret = p.get(realKey)
		if (ret == null) {
			realKey = "test.${key}"
			ret = p.get(realKey)
		}
		if (ret == null)
			ret = defaultValue
			
		// eval
		GroovyShell sh = new GroovyShell(b);
        ret =  sh.evaluate('"'+ret+'"');
		//binding
		b.setVariable(key, ret)
		return ret;
	}
}

