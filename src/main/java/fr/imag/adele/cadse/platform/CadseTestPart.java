package fr.imag.adele.cadse.platform;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import groovy.lang.Closure;

public class CadseTestPart {
	public ICadseTestPlatform run;
	public String testProperties;
	public String testName;
	public String testPluginName;
	public String classname;
	public Closure c;
	public Closure e;
	public int status = -1; // 0 SUCESS, 1: FAILED; -1 NOT EXECUTED
	public long timestamp = 0;

	public List<BundleBuildDescription> bundlestoCompile = new ArrayList<BundleBuildDescription>();
	public List<String> bundlestoDelete = new ArrayList<String>();
	public List<CadseTestMethod> tests = new ArrayList<CadseTestMethod>();

	public CadseTestPart(String testProperties, String testName, String testPluginName, String classname, Closure c) {
		this.testProperties = testProperties;
		this.testName = testName;
		this.testPluginName = testPluginName;
		this.classname = classname;
		this.c = c;
	}

	public void setCadseTestPlatform(ICadseTestPlatform lRun) {
		run = lRun;
	}

	public void addBundle(BundleBuildDescription b) {
		for (BundleBuildDescription findB : bundlestoCompile) {
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

		addBundle(new BundleBuildDescription(path, name, defaultSource));
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
			addBundle(new BundleBuildDescription(path, n, defaultSource));
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

}
