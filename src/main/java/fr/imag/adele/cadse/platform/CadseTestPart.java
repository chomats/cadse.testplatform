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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import groovy.lang.Closure;

public class CadseTestPart {
	public AbstractCadseTestPlatform run;
	public String testProperties;
	public String testName;
	public String testPluginName;
	public String classname;
	public Closure c;
	public Closure e;
	public int status = -1; // 0 SUCESS, 1: FAILED; -1 NOT EXECUTED
	public long timestamp = 0;

	public List<BundleDescription> bundlestoCompile = new ArrayList<BundleDescription>();
	public List<String> bundlestoDelete = new ArrayList<String>();
	public List<CadseTestMethod> tests = new ArrayList<CadseTestMethod>();

	public CadseTestPart(String testProperties, String testName, String testPluginName, String classname, Closure c) {
		this.testProperties = testProperties;
		this.testName = testName;
		this.testPluginName = testPluginName;
		this.classname = classname;
		this.c = c;
	}

	public void setCadseTestPlatform(AbstractCadseTestPlatform lRun) {
		run = lRun;
	}

	public void addBundle(BundleDescription b) {
		for (BundleDescription findB : bundlestoCompile) {
			if (findB.equals(b)) {
				return;
			}
		}
		bundlestoCompile.add(b);
	}

	public void addBundle(String path, String name, String defaultSource) {
		if (path == null) {
			path = run.getTestPlatformPath() + "/test-ws";
		}

		addBundle(new BuildBundleDescription(path, name, defaultSource));
	}
	
	public void addBundle(String prefix, String defaultSource) {
		String path = run.getTestPlatformPath() + "/test-ws";
		File dir = new File(path);
		File[] files = dir.listFiles();
		if (files == null) return;
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (!f.isDirectory() || !f.exists()) continue;
			String n = f.getName();
			if (!n.startsWith(prefix))
				continue;
			addBundle(new BuildBundleDescription(path, n, defaultSource));
		}
		
	}

	public void deleteBundle(String name) {
		bundlestoDelete.add(name);
	}

	public void addTestResult(String name, long timeStamp, boolean success) {
		CadseTestMethod ctm = new CadseTestMethod();
		ctm.name = name;
		ctm.timestamp = timeStamp;
		ctm.status = success ? 0 : 1;
		tests.add(ctm);
	}

	public String getName() {
		return testName;
	}

	public CadseTestMethod findTestResult(String testName2) {
		for (CadseTestMethod ctm : tests) {
			if (ctm.name.equals(testName2)) {
				return ctm;
			}
		}
		return null;
	}

	public void report(RunJavaTestClass runJavaTestClass,
			AbstractCadseTestPlatform run2, boolean failed) {
	}
	
	public void setExtraBundleClosure(Closure e) {
		this.e = e;
	}
	

	public void addExtraBundle() {
		if (e != null) {
			e.setDelegate(this);
			e.call();
		}
			
	}

	public RunJavaTestClass createRunner() {
		return new RunEclipseTestClass(run, this);
	}

}
