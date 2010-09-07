/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.platform;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;
import groovy.util.AntBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.ExecuteStreamHandler;
import org.apache.tools.ant.taskdefs.ExecuteWatchdog;
import org.apache.tools.ant.taskdefs.PumpStreamHandler;
import org.apache.tools.ant.util.FileUtils;

abstract public class RunJavaTestClass {

	protected static final String DISPLAY_CST = "DISPLAY";
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
	protected ProcessBuilder processBuilder;
	protected List<String> params = new ArrayList<String>();
	protected List<String> paramsJVM = new ArrayList<String>();
	protected String exec;
	
	
	public void addParam(String ...param) {
		params.addAll(Arrays.asList(param));
	}
	
	public void addJvmParam(String ...param) {
		paramsJVM.addAll(Arrays.asList(param));
	}
	
	public void addJvmParam(String param) {
		paramsJVM.add(param);
	}
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
		
		display = getTestValue("display", testName, null);

		if (display == null)
			display = System.getenv(DISPLAY_CST);
		
		boolean cobertura = toBoolean(getTestValue("cobertura", testName, "false"));
		
		if (c != null) {
			c.setDelegate(this);
			c.call();
		}
		run.initRunJavaTest(this);

	    processBuilder = new ProcessBuilder();
		
	    addJvmParam("-Dfr.image.adele.cadse.test.path=" + testPlatformPath, 
				"-Dtest.resourcesPath=" + resourcesPath);
	    
		addParam("-testPluginName", testPluginName, 
				"-className", classname, 
				"-port", Integer.toString(junitPort), 
				"-loaderpluginname", loaderpluginname, 
				"-testloaderclass", "org.eclipse.jdt.internal.junit4.runner.JUnit4TestLoader");
		
		if (cadseToExecute != null) {
			addJvmParam("-Dfr.imag.adele.cadse.execute=" + cadseToExecute);
		}
		if (cobertura) {
			String coberturaOutput = converToPath(getTestValue("coberturaOutput", testName, testReport + "/" + testName + ".cobertura.ser"));
			addJvmParam("-Dnet.sourceforge.cobertura.datafile=" + coberturaOutput);
		}

		if (debugPort != 0) {
			String suspendString = suspend ? "y" : "n";
			addJvmParam("-Xdebug");
			addJvmParam(
					"-Xrunjdwp:transport=dt_socket,address=" + debugPort + ",server=y,suspend=" + suspendString);
		}

		Map<String, String> env = processBuilder.environment();

		addExtraParam();
		
		initEnv(env);
		
		constructCmdLine();

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

	protected void addExtraParam() {
	}

	protected void initEnv(Map<String, String> env) {
	}


	protected void constructCmdLine() {
		processBuilder.command().add(exec);
		for (String p : paramsJVM) {
			processBuilder.command().add(p);
		}
		
		for (String p : params) {
			processBuilder.command().add(p);
		}
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
		String ret = null;
		while(true) {
			ret = System.getProperty(realKey);
			if (ret != null) break;
			ret = (String) p.get(realKey);
			if (ret != null) break;
			
			realKey = "test." + key;
			ret = System.getProperty(realKey);
			if (ret != null) break;

			ret = (String) p.get(realKey);
			if (ret != null) break;
		
		
			ret = defaultValue;
			if (ret != null) break;
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
