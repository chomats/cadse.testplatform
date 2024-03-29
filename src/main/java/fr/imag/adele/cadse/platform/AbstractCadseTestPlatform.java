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

import groovy.util.AntBuilder;

public abstract class AbstractCadseTestPlatform implements ICadseTestPlatform {

	public String testPlatformPath;
	public String wsTest;
	public String testEclipsePath;
	public String testReport;
	public AntBuilder ant;
	boolean debugFlag = false;
	public String _buildNumber = null;
	public final String defaultTimezone = "GMT+2";

	public int testPassed = 0;
	public int testFailed = 0;
	public int testCount = 0;
	public boolean collapseSuccess = true;

	final long startTime = System.currentTimeMillis();

	public CadseTest[] cadseTests;

	public CadseTestCollector cadseCollector = new CadseTestCollector();
	public IBuildManager buildManager;

	public void addBundle(BundleDescription b) {
		for (BundleDescription findB : buildManager.getBundlestoCompile()) {
			if (findB.equals(b))
				return;
		}
		buildManager.addBundle(b);
	}

	public void addBundelInM2(String name, String gId, String aId, String vId) {
		addBundle(new MavenBundleDescription(name, gId, aId, vId));
	}

	public void addBundle(String path, String name, String defaultSource) {
		addBundle(new BuildBundleDescription(path, name, defaultSource));
	}

	/** true if failed */
	public boolean compileAll() {
		for (BundleDescription b : buildManager.getBundlestoCompile()) {
			if (b.build(buildManager))
				return true;
		}
		return buildManager.deployMavenBundle();
	}

	/** true if failed */
	public boolean run(CadseTest ct, CadseTestPart tp) {
		tp.setCadseTestPlatform(this);
		for (String b : tp.bundlestoDelete)
			buildManager.deleteBundle(b);
		tp.addExtraBundle();
		buildManager.setBundlestoCompile(tp.bundlestoCompile);
		if (compileAll()) {
			ct.status = tp.status = 1;
			messageTestSuiteResults();
			return true;
		}

		/* Test preparation */
		long startTime = System.currentTimeMillis();
		messageStartTestSuite(tp.testName);

		/* Test running */
		boolean failed = tp.createRunner().run(tp.c);

		/* Test statistics */
		tp.timestamp = System.currentTimeMillis() - startTime;
		tp.status = failed ? 1 : 0;
		if (failed)
			ct.status = 1;
		messageTestSuiteResults();
		return failed;
	}

	// protected abstract RunJavaTestClass createRunner(CadseTestPart tp) ;

	/** true if failed */
	public boolean runTests(CadseTest... cadseTest) {
		this.cadseTests = cadseTest;

		this.testCount = cadseTest.length;
		for (CadseTest ct : cadseTest) {
			ct.setCadseTestPlatform(this);
		}
		for (CadseTest ct : cadseTest) {
			ct.init();
		}
		if (compileAll()) {
			messageTestSuiteResults();
			return true;
		}
		for (CadseTest ct : cadseTest) {
			try {
				if (!ct.runTest()) {
					this.testPassed++;
					ct.status = 0;
				} else {
					this.testFailed++;
					ct.status = 1;
				}
			} catch (Throwable e) {
				System.out.println("TEST Exception " + ct.getName());
				e.printStackTrace();
				this.testFailed++;
				ct.status = 1;
			}
			messageTestSuiteResults();
		}
		finishTest();

		return testPassed != testCount;
	}

	protected void finishTest() {

	}

	private void messageStartTestSuite(String testName) {
		System.out.println("");
		System.out.println("********************************************************");
		System.out.println("* Starting Test Suite : " + testName);
		System.out.println("********************************************************");
		System.out.println("");
	}

	private String toStringTime(long t) {
		t /= 1000; // seconds;
		long minutes = t / 60;
		long seconds = t % 60;

		return "" + minutes + " min " + seconds + " seconds.";
	}

	private void messageTestMethod(CadseTestPart tp) {
		for (CadseTestMethod tm : tp.tests) {
			if (tm.status == 1) {
				System.out.println("*         [ ERROR ] " + tm.name);
			} else if (tm.status == 0)
				System.out.println("*         [SUCCESS] " + tm.name);
			else
				System.out.println("*         [NOT RUN] " + tm.name);
		}
	}

	private void messageCadseTestPart(CadseTest ct) {
		for (CadseTestPart tp : ct.parts) {
			if (tp.status == 1) {
				System.out.println("*     [ ERROR ] " + tp.getName() + " (" + toStringTime(tp.timestamp) + " )");
				messageTestMethod(tp);
			} else if (tp.status == 0) {
				System.out.println("*     [SUCCESS] " + tp.getName() + " (" + toStringTime(tp.timestamp) + " )");
				if (!collapseSuccess)
					messageTestMethod(tp);
			} else
				System.out.println("*     [NOT RUN] " + tp.getName());
		}
	}

	private void messageTestSuiteResults() {
		long duration = System.currentTimeMillis() - startTime;
		String generalFlag = "[SUCCESS]";

		System.out.println("");
		System.out.println("********************************************************");
		System.out.println("* TEST RESULTS");
		System.out.println("********************************************************");
		System.out.println("*");

		for (CadseTest ct : cadseTests) {
			if (ct.status == 1) {
				System.out.println("* [ ERROR ] " + ct.getName() + " (" + toStringTime(ct.timestamp) + " )");
				generalFlag = "[ ERROR ]";
				messageCadseTestPart(ct);
			} else if (ct.status == 0) {
				System.out.println("* [SUCCESS] " + ct.getName() + " (" + toStringTime(ct.timestamp) + " )");
				if (!collapseSuccess)
					messageCadseTestPart(ct);
			} else if (ct.status == -2) {
				System.out.println("* [RUNNING] " + ct.getName());
				messageCadseTestPart(ct);
			} else
				System.out.println("* [NOT RUN] " + ct.getName());
		}

		System.out.println("*");
		System.out.println("* " + generalFlag + " TOTAL : " + toStringTime(duration));
		System.out.println("*");
		System.out.println("********************************************************");
		System.out.println("");
	}

	@Override
	public String getTestPlatformPath() {
		return testPlatformPath;
	}

	public CadseTestCollector getCadseTestCollector() {
		return cadseCollector;
	}

	public abstract void loadPropertyFile(String testProperties);

	public abstract void initRunJavaTest(RunJavaTestClass runJavaTestClass);
}