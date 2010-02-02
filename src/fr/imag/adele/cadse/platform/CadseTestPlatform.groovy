package fr.imag.adele.cadse.platform;import java.util.jar.Manifestimport java.util.Localeimport java.text.SimpleDateFormatimport java.lang.Systemimport java.io.FileInputStreamimport java.util.Propertiesimport groovy.util.AntBuilderimport org.osgi.framework.Versionimport org.junit.Assertimport java.lang.Longimport java.lang.Booleanimport groovy.lang.Closureimport java.lang.NullPointerExceptionimport java.lang.Throwableimport java.lang.Throwableimport junit.framework.TestFailureimport org.eclipse.ui.internal.misc.TestPartListenerimport org.eclipse.jdt.internal.junit.model.ITestRunListener2;import org.eclipse.jdt.internal.junit.model.RemoteTestRunnerClient;

/**
 * @author etienne
 *
 */
public class CadseTestPlatform{

	String testPlatformPath	String wsTest
	String testEclipsePath	String testReport
	AntBuilder ant
	boolean debugFlag = false	String _buildNumber = null	final String defaultTimezone = 'GMT+2'	int testPassed	int testFailed	int testCount	CadseTestCollector cadseCollector = new CadseTestCollector()	BuildManager buildManager;	public CadseTestPlatform() {		ant =  new AntBuilder();		ant.property(file:"test.properties");		testPlatformPath = ant.project.properties.testPlatformPath		testReport ="${testPlatformPath}/report"		testEclipsePath  = "$testPlatformPath/eclipse"		buildManager = new BuildManager(ant, testPlatformPath)		wsTest = ant.project.properties.wsTest	}	public void addBundle(BundleBuildDescription b) {		for(findB in buildManager.bundlestoCompile) {			if (findB.equals(b)) return;		}		buildManager.bundlestoCompile.add( b);	}	public void addBundle(String path, String name, String defaultSource) {		addBundle(new BundleBuildDescription(path, name, defaultSource));	}	/** true if failed*/	public boolean compileAll() {		for(b in buildManager.bundlestoCompile) {			if (buildManager.createBundle(b.path, b.name, b.defaultSource))				return true;		}		return false;	}	/** true if failed*/	public boolean runJavaTest(String testProperties, String testName,String testPluginName, String classname ) {		return new RunJavaTestClass(this, testProperties, testName,testPluginName, classname).run(null);	}	/** true if failed*/	public boolean runJavaTest(String testProperties, String testName,String testPluginName, String classname, Closure c ) {		return new RunJavaTestClass(this, testProperties, testName,testPluginName, classname).run(c);	}	/** true if failed*/	public boolean runTests(CadseTest... cadseTest) {		this.testCount = cadseTest.length;		for(ct in cadseTest) {			ct.setCadseTestPlatform(this);		}		for(ct in cadseTest) {			ct.addBundleToCompile();		}		if (compileAll()) {			showResult();			return true;		}		for(ct in cadseTest) {			try {				if (!ct.runTest()) {					this.testPassed++;				} else {					this.testFailed++;				}			} catch(Throwable e) {				e.printStackTrace()				this.testFailed++;			}		}		showResult();		return testPassed != testCount;	}	public void showResult() {		boolean failed = testPassed != testCount;		println("Test Run Ended   - " + (failed? "FAILED" : "PASSED")				+ " - Total: " + cadseCollector.count()				+ " (Errors: " + cadseCollector.getNumberOfTestsWithError()				+ ", Failed: " + cadseCollector.getNumberOfTestsFailed()				+ ", Passed: " + cadseCollector.getNumberOfTestsPassed() + "), duration " + cadseCollector.getElapsedTime() + "ms.");		println("Test Run Ended   - " + (failed? "FAILED" : "PASSED") + " - Total: " + testCount				+ " (Failed: " + testFailed + ", Passed: "+ testPassed + ")" );	}
}