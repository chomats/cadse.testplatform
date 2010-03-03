package fr.imag.adele.cadse.platform

import java.util.List;

import groovy.lang.Closure;


public abstract class CadseTest {
	public CadseTestPlatform	run;
	public List<CadseTestPart> 		parts = new ArrayList<CadseTestPart>();
	int status = -1 ; // 0 SUCESS, 1: FAILED; -1 NOT EXECUTED
	public long timestamp;
	
	public void setCadseTestPlatform(CadseTestPlatform lRun) {
		run = lRun;
	}
	
	/** true if failed */
	public CadseTestPart addTestPart(String testProperties, String testName, String testPluginName, String classname) {
		return addTestPart(testProperties, testName, testPluginName, classname, null);
	}

	/** true if failed */
	public CadseTestPart addTestPart(String testProperties, String testName, String testPluginName, String classname, Closure c) {
		CadseTestPart tp = new CadseTestPart(testProperties, testName, testPluginName, classname, c);
		parts.add(tp)
		return tp;
	}

	public void init() {
	}

	/** true if failed */
	public boolean runTest() {
		long startTime = System.currentTimeMillis();
		status = 1;
		boolean failed = false;
		for (CadseTestPart tp : parts) {
			if (run.run(tp)) {
				failed = true;
				break;
			}
		}
		timestamp = System.currentTimeMillis()-startTime
		status = failed ? 1 : 0;
		return failed;
	}

	public static void main(CadseTest ...cadseTest) {
		new CadseTestPlatform().runTests(cadseTest);
	}
	
	public String getName() {
		return getClass().getSimpleName();
	}
}
