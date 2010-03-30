package fr.imag.adele.cadse.platform;

import java.util.ArrayList;
import java.util.List;

import groovy.lang.Closure;


public abstract class CadseTest {
	public AbstractCadseTestPlatform	run;
	public List<CadseTestPart> 		parts = new ArrayList<CadseTestPart>();
	public int status = -1 ; // 0 SUCESS, 1: FAILED; -1 NOT EXECUTED, -2 RUNNING
	public long timestamp;
	
	public void setCadseTestPlatform(AbstractCadseTestPlatform lRun) {
		run = lRun;
	}
	
	/** true if failed */
	public CadseTestPart addTestPart(String testProperties, String testName, String testPluginName, String classname) {
		return addTestPart(testProperties, testName, testPluginName, classname, null);
	}
	
	/** true if failed */
	public CadseTestPart addTestPart(String testProperties, String testName, String testPluginName, String classname, Closure c) {
		CadseTestPart tp = new CadseTestPart(testProperties, testName, testPluginName, classname, c);
		parts.add(tp);
		tp.setCadseTestPlatform(run);
		return tp;
	}
	
	public void init() {
	}
	
	/** true if failed */
	public boolean runTest() {
		long startTime = System.currentTimeMillis();
		status = -2;
		boolean failed = false;
		for (CadseTestPart tp : parts) {
			if (run.run(this, tp)) {
				failed = true;
				break;
			}
		}
		timestamp = System.currentTimeMillis()-startTime;
		status = failed ? 1 : 0;
		return failed;
	}
	
	public static void main(CadseTest ...cadseTest) {
		try {
			((AbstractCadseTestPlatform) Class.forName("fr.imag.adele.cadse.platform.gr.CadseTestPlatform").newInstance()).runTests(cadseTest);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return getClass().getSimpleName();
	}
}