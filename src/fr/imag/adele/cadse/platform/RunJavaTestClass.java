/**
 *
 */
package fr.imag.adele.cadse.platform;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;
import groovy.util.AntBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.ExecuteStreamHandler;
import org.apache.tools.ant.taskdefs.ExecuteWatchdog;
import org.apache.tools.ant.taskdefs.PumpStreamHandler;
import org.apache.tools.ant.util.FileUtils;

public class RunJavaTestClass {

	public String testPlatformPath;
	public String wsTest;
	public String testEclipsePath;
	public String testReport;
	AntBuilder ant;

	public long timeout;
	public int debugPort;
	public int junitPort;
	public boolean suspend;
	public boolean redirectErrorStream;

	Binding b = new Binding();

	public String cadseToExecute;
	public boolean deleteWsDir;
	public String resourcesPath;
	public String outputFile;
	public String errorFile;
	public String testOutput;
	public String screenshots;
	public String memArgs;
	public String wsDir;
	public String testProperties;
	public String testName;
	public String testPluginName;
	public String classname;
	public String wsDirFolder;
	public String application;
	public String loaderpluginname;
	public String jvm;
	public String execEclipse;
	public String display;

	AbstractCadseTestPlatform run;
	CadseTestPart tp;

	public RunJavaTestClass(AbstractCadseTestPlatform run, CadseTestPart tp) {
		this.testProperties = tp.testProperties;
		this.testName = tp.testName;
		this.testPluginName = tp.testPluginName;
		this.classname = tp.classname;
		this.run = run;
		this.tp = tp;

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

	public boolean run(Closure c) {

		if (testProperties != null) {
			run.loadPropertyFile(testProperties);
		}
		wsDirFolder = converToPath(getTestValue("wsdir", testName, "test-ws"));
		application = getTestValue("application", testName, "org.eclipse.pde.junit.runtime.nonuithreadtestapplication");
		loaderpluginname = getTestValue("loaderpluginname", testName, "org.eclipse.jdt.junit4.runtime");
		timeout = toLong(getTestValue("timeout", testName, "03600000"));
		if (timeout <= 0) {
			timeout = 03600000L;
		}

		debugPort = toInt(getTestValue("debug.port", testName, "0"));
		junitPort = toInt(getTestValue("junit.port", testName, "2566"));
		suspend = toBoolean(getTestValue("debug.suspend", testName, "false"));

		cadseToExecute = getTestValue("cadseToExecute", testName, null);
		deleteWsDir = toBoolean(getTestValue("deleteWsDir", testName, "true"));
		redirectErrorStream = toBoolean(getTestValue("redirectErrorStream", testName, "true"));

		int waitCollector = toInt(getTestValue(":w", testName, "0"));
		resourcesPath = converToPath(getTestValue("resourcesPath", testName, wsTest + "/" + testPluginName
				+ "/resources/"));
		outputFile = converToPath(getTestValue("outputFile", testName, testReport + "/" + testName + ".txt"));
		errorFile = converToPath(getTestValue("errorFile", testName, testReport + "/" + testName + ".txt"));
		testOutput = converToPath(getTestValue("testOutput", testName, testReport + "/" + testName + ".xml"));
		screenshots = converToPath(getTestValue("screenshots", testName, testReport + "/" + testName + "/screenshots/"));
		memArgs = getTestValue("mem.args", testName, "-XX:MaxPermSize=128m -Xms256m -Xmx1024m");
		wsDir = converToPath(testPlatformPath + "/" + wsDirFolder);
		if (((String) System.getProperties().get("os.name")).startsWith("Windows")) {
			execEclipse = converToPath(getTestValue("execEclipse", testName, testEclipsePath + "/eclipse.exe"));
		}
		else {
			execEclipse = converToPath(getTestValue("execEclipse", testName, testEclipsePath + "/eclipse"));
		}
		display = getTestValue("display", testName, null);

		//		
		//		
		// // set default jvm to use for testing-->
		// jvm = getTestValue("jvm", testName, System.getProperty("java.home").replace ("\\","/")+"/bin/java");

		if (c != null) {
			c.setDelegate(this);
			c.call();
		}
		run.initRunJavaTest(this);

		// /home/chomats/eclipse-apps/platform-test-galileo/test-ws
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command(execEclipse, "-application", application, "-data", wsDir, "-testPluginName",
				testPluginName, "-className", classname, "-consoleLog", "-console", "-clean", "-port", Integer
						.toString(junitPort), "-loaderpluginname", loaderpluginname, "-testloaderclass",
				"org.eclipse.jdt.internal.junit4.runner.JUnit4TestLoader", "-vmargs",
				"-Dfr.image.adele.cadse.test.path=" + testPlatformPath, "-Dorg.eclipse.swtbot.screenshots.dir="
						+ screenshots, "-Dtest.resourcesPath=" + resourcesPath,
				"-Dorg.eclipse.swtbot.keyboard.strategy=org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy",
				"-Dorg.eclipse.swtbot.search.timeout='30000'");
		if (cadseToExecute != null) {
			processBuilder.command().add("-Dfr.image.adele.addcadse=" + cadseToExecute);
		}

		if (debugPort != 0) {
			String suspendString = suspend ? "y" : "n";
			processBuilder.command().add("-Xdebug");
			processBuilder.command().add(
					"-Xrunjdwp:transport=dt_socket,address=" + debugPort + ",server=y,suspend=" + suspendString);
		}

		Map<String, String> env = processBuilder.environment();

		env.put("PLUGIN_PATH", testEclipsePath + "/plugins");
		if (display != null) {
			env.put("DISPLAY", display);
		}

		processBuilder.directory(new File(testEclipsePath));
		processBuilder.redirectErrorStream(redirectErrorStream);
		Process p;
		try {
			p = processBuilder.start();
		}
		catch (IOException e1) {
			e1.printStackTrace();
			return true;
		}
		ExecuteStreamHandler streamHandler = new PumpStreamHandler();

		try {
			streamHandler.setProcessInputStream(p.getOutputStream());
			streamHandler.setProcessOutputStream(p.getInputStream());
			streamHandler.setProcessErrorStream(p.getErrorStream());
		}
		catch (IOException e) {
			p.destroy();
			e.printStackTrace();
			return true;
		}
		try {
			streamHandler.start();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ExecuteWatchdog watchdog = null;

		watchdog = new ExecuteWatchdog(timeout);
		watchdog.start(p);
		if (waitCollector != 0) {
			System.out.println("Regiser wait " + waitCollector);
			try {
				Thread.sleep(waitCollector);
			}
			catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		System.out.println("Regiser run test runner client");
		run.getCadseTestCollector().runTestRunnerClient(tp, classname, junitPort, testOutput);

		try {
			p.waitFor();
		}
		catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (true) {
			if (run.getCadseTestCollector().isTerminated()) {
				break;
			}
			try {
				Thread.sleep(20);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		watchdog.stop();

		streamHandler.stop();
		closeStreams(p);

		tp.report(this, run, run.cadseCollector.failed());
		try {
			watchdog.checkException();
		}
		catch (BuildException e) {
			e.printStackTrace();
			return true;
		}
		return run.cadseCollector.failed();
	}

	/**
	 * Close the streams belonging to the given Process.
	 * 
	 * @param process
	 *            the <code>Process</code>.
	 */
	public static void closeStreams(Process process) {
		FileUtils.close(process.getInputStream());
		FileUtils.close(process.getOutputStream());
		FileUtils.close(process.getErrorStream());
	}

	// if (true) { //System.getProperties().get("os.name").startsWith("Windows")
	// // Windows
	// ant.exec( executable:execEclipse,
	// logError:true,
	// dir:testEclipsePath) {
	// arg(line:"-application "+application)
	// arg(line:"-data ""+wsDir+""" )
	// arg(line:"-testPluginName "+testPluginName+"" )
	// arg(line:"-className "+classname+"" )
	// arg(line:"-consoleLog")
	// arg(line:"-console")
	// arg(line:"-clean" )
	// arg(line:"-port 2566")
	// arg(line:"-loaderpluginname org.eclipse.swtbot.eclipse.junit4.headless")
	// arg(line:"-testloaderclass org.eclipse.jdt.internal.junit4.runner.JUnit4TestLoader")
	// arg(line:"-vmargs" )
	// arg(line:memArgs)
	// if (cadseToExecute != null) {
	// arg(line:"-Dfr.image.adele.addcadse="$cadseToExecute"")
	// }
	// arg(line:"-Dfr.image.adele.cadse.test.path="$testPlatformPath"")
	// arg(line:"-Dorg.eclipse.swtbot.screenshots.dir=""+screenshots+""")
	// arg(line:"-Dtest.resourcesPath=""+resourcesPath+""")
	// arg(line:"-Dorg.eclipse.swtbot.keyboard.strategy=org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy")
	// arg(line:"-Dorg.eclipse.swtbot.search.timeout="30000"")
	//			
	// if (debugPort != 0) {
	// String suspendString = suspend?"y":"n"
	// arg( line:"-Xdebug -Xrunjdwp:transport=dt_socket,address="+debugPort+",server=y,suspend=$suspendString")
	// }
	// env( key:"PLUGIN_PATH", path:""+testEclipsePath+"/plugins")
	// if (display != null)
	// env(key:"DISPLAY", value:""+display+"")
	// }
	// } else {
	// // Linux
	// ant.java(fork:true, dir:testEclipsePath, jvm:jvm,
	// classname:"org.eclipse.core.launcher.Main",
	// output:outputFile) {
	//
	// classpath() { fileset(dir:""+testEclipsePath+"/plugins", includes:"org.eclipse.equinox.launcher_*.jar") }
	// arg(line:"-application "+application+"")
	// arg(line:"-data "+wsDir+"" )
	// arg(line:"-testPluginName "+testPluginName+"" )
	// arg(line:"-className "+classname+"" )
	// arg(line:"-consoleLog")
	// arg(line:"-console")
	// arg(line:"-clean" )
	// arg(line:"-port 2566")
	// arg(line:"-loaderpluginname org.eclipse.swtbot.eclipse.junit4.headless")
	// arg(line:"-testloaderclass org.eclipse.jdt.internal.junit4.runner.JUnit4TestLoader")
	// //jvmarg(line:"-Dosgi.bundles="org.eclipse.equinox.common@2:start, org.eclipse.update.configurator@3:start,
	// org.eclipse.core.runtime@4:start, org.apache.felix.ipojo@4:start, fr.imag.adele.ipojo.autostart@4:start"")
	// jvmarg(line:memArgs)
	// if (cadseToExecute != null) {
	// jvmarg(line:"-Dfr.image.adele.addcadse="$cadseToExecute"")
	// }
	// jvmarg(line:"-Dfr.image.adele.cadse.test.path="$testPlatformPath"")
	// jvmarg(line:"-Dorg.eclipse.swtbot.screenshots.dir=""+screenshots+""")
	// jvmarg(line:"-Dtest.resourcesPath=""+resourcesPath+""")
	// jvmarg(line:"-Dorg.eclipse.swtbot.keyboard.strategy=org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy")
	// jvmarg(line:"-Dorg.eclipse.swtbot.search.timeout="30000"")
	//			
	//
	// if (debugPort != 0) {
	// String suspendString = suspend?"y":"n"
	// jvmarg( line:"-Xdebug -Xrunjdwp:transport=dt_socket,address="+debugPort+",server=y,suspend=$suspendString")
	// }
	// env( key:"PLUGIN_PATH", value:""+testEclipsePath+"/plugins")
	// if (display != null)
	// env(key:"DISPLAY", value:""+display+"")
	// }
	// }

	public long toLong(String value) {
		return Long.parseLong(value);
	}

	public int toInt(String value) {
		return Integer.parseInt(value);
	}

	public boolean toBoolean(String value) {
		return Boolean.parseBoolean(value);
	}

	public String converToPath(String value) {
		return new java.io.File(value).getPath();
	}

	public String getTestValue(String key, String testName, String defaultValue) {
		String realKey = "test." + testName + "." + key;
		Hashtable<?, ?> p = ant.getProject().getProperties();
		String ret = (String) p.get(realKey);
		if (ret == null) {
			realKey = "test." + key;
			ret = (String) p.get(realKey);
		}
		if (ret == null) {
			ret = defaultValue;
		}
		if (ret == null) {
			return null;
		}
		// eval
		GroovyShell sh = new GroovyShell(b);
		ret = (String) sh.evaluate("'" + ret + "'");
		// binding
		b.setVariable(key, ret);
		return ret;
	}
}
