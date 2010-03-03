package fr.imag.adele.cadse.platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import junit.framework.TestResult;

import org.apache.tools.ant.taskdefs.optional.junit.JUnitTest;
import org.apache.tools.ant.taskdefs.optional.junit.XMLJUnitResultFormatter;
import org.eclipse.jdt.internal.junit.model.ITestRunListener2;


/**
 * The listener interface for receiving PDETest events.
 * The class that is interested in processing a PDETest
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addPDETestListener<code> method. When
 * the PDETest event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see PDETestEvent
 */
public class PDETestListener implements ITestRunListener2 {
	
	/** The total number of tests. */
	private int						totalNumberOfTests;
	
	/** The tests run count. */
	private int						testsRunCount;
	
	/** The number of tests passed. */
	private int						numberOfTestsPassed;
	
	/** The number of tests failed. */
	private int						numberOfTestsFailed;
	
	/** The number of tests with error. */
	private int						numberOfTestsWithError;
	
	/** The test run ended. */
	private boolean					testRunEnded	= false;
	
	/** The xml results formatter. */
	private XMLJUnitResultFormatter	xmlResultsFormatter;
	
	/** The output file. */
	private File					outputFile;
	
	/** The suite name. */
	private String					suiteName;
	
	/** The test name. */
	private String					testName;
	
	/** The junit test suite. */
	private JUnitTest				junitTestSuite;
	
	/** The current test. */
	private TestCase				currentTest;
	
	public CadseTestPart			currentCadseTest;
	
	/**
	 * Instantiates a new pDE test listener.
	 * 
	 * @param suite the suite
	 * @param testName the test name
	 */
	public PDETestListener(String suite, CadseTestPart testName) {
		suiteName = suite;
		junitTestSuite = new JUnitTest(suiteName);
		junitTestSuite.setProperties(System.getProperties());
		this.testName = testName.testName;
		this.currentCadseTest = testName;
	}

	/**
	 * Sets the output file.
	 * 
	 * @param filename the new output file
	 */
	public void setOutputFile(String filename) {
		outputFile = new File(filename);
	}

	/**
	 * Gets the output file.
	 * 
	 * @return the output file
	 */
	public File getOutputFile() {
		if (outputFile == null) {
			setOutputFile("TEST-" + suiteName + ".xml");
		}
		return outputFile;
	}

	/**
	 * Failed.
	 * 
	 * @return true, if successful
	 */
	public boolean failed() {
		return ((numberOfTestsFailed + numberOfTestsWithError) > 0) || (testRunEnded && (testsRunCount == 0));
	}

	/**
	 * Count.
	 * 
	 * @return the int
	 */
	public int count() {
		return testsRunCount;
	}

	/**
	 * Gets the xMLJ unit result formatter.
	 * 
	 * @return the xMLJ unit result formatter
	 */
	private XMLJUnitResultFormatter getXMLJUnitResultFormatter() {
		if (xmlResultsFormatter == null) {
			xmlResultsFormatter = new XMLJUnitResultFormatter();
			try {
				xmlResultsFormatter.setOutput(new FileOutputStream(getOutputFile()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return xmlResultsFormatter;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.junit.model.ITestRunListener2#testRunStarted(int)
	 */
	public synchronized void testRunStarted(int testCount) {
		totalNumberOfTests = testCount;
		testsRunCount = 0;
		numberOfTestsPassed = 0;
		numberOfTestsFailed = 0;
		numberOfTestsWithError = 0;
		testRunEnded = false;
		getXMLJUnitResultFormatter().startTestSuite(junitTestSuite);
		printMessage("Starting test suite : " + testName + " (" + totalNumberOfTests + " tests)");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.junit.model.ITestRunListener2#testRunEnded(long)
	 */
	public synchronized void testRunEnded(long elapsedTime) {
		testRunEnded = true;
		junitTestSuite.setCounts(testsRunCount, numberOfTestsFailed, numberOfTestsWithError);
		junitTestSuite.setRunTime(elapsedTime);
		getXMLJUnitResultFormatter().endTestSuite(junitTestSuite);
		String flag = (failed() ? "[ ERROR ]" : "[SUCCESS]");
		printMessage(flag + " Test suite ended " + " - Total: " + totalNumberOfTests
				+ " (Errors: " + numberOfTestsWithError + ", Failed: " + numberOfTestsFailed + ", Passed: "
				+ numberOfTestsPassed + "), duration " + elapsedTime/1000 + "s.");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.junit.model.ITestRunListener2#testRunStopped(long)
	 */
	public synchronized void testRunStopped(long elapsedTime) {
		testRunEnded(elapsedTime);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.junit.model.ITestRunListener2#testRunTerminated()
	 */
	public synchronized void testRunTerminated() {
		testRunEnded(0);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.junit.model.ITestRunListener2#testStarted(java.lang.String, java.lang.String)
	 */
	public synchronized void testStarted(String testId, String testName) {
		testsRunCount++;
		currentTest = new WrapperTestCase(testName);
		getXMLJUnitResultFormatter().startTest(currentTest);
		printMessage("Test n°" + count() + " started : " + testName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.junit.model.ITestRunListener2#testEnded(java.lang.String, java.lang.String)
	 */
	public synchronized void testEnded(String testId, String testName) {
		numberOfTestsPassed = count() - (numberOfTestsFailed + numberOfTestsWithError);
		getXMLJUnitResultFormatter().endTest(currentTest);
		printMessage("[SUCCESS] Test n°" + count() + " ended : " + testName);
		currentCadseTest.addTestResult(testName, 0, true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.junit.model.ITestRunListener2#testFailed(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public synchronized void testFailed(int status, String testId, String testName, String trace, String expected,
			String actual) {
		String statusMessage = String.valueOf(status);
		if (status == ITestRunListener2.STATUS_OK) {
			numberOfTestsPassed++;
			statusMessage = "OK";
		} else if (status == ITestRunListener2.STATUS_FAILURE) {
			numberOfTestsFailed++;
			statusMessage = "FAILED";
			getXMLJUnitResultFormatter().addFailure(currentTest, new AssertionFailedError(trace));
		} else if (status == ITestRunListener2.STATUS_ERROR) {
			numberOfTestsWithError++;
			statusMessage = "ERROR";
			getXMLJUnitResultFormatter().addError(currentTest, new Exception(trace));
		}
		printMessage("[ ERROR ] Test n°" + count() + " ended : " + testName + " - status: " + statusMessage	+ ", expected: " + expected + ", actual: " + actual, trace);
		currentCadseTest.addTestResult(testName, 0, false);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.junit.model.ITestRunListener2#testReran(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	public synchronized void testReran(String testId, String testClass, String testName, int status, String trace,
			String expected, String actual) {
		String statusMessage = String.valueOf(status);
		if (status == ITestRunListener2.STATUS_OK) {
			statusMessage = "OK";
		} else if (status == ITestRunListener2.STATUS_FAILURE) {
			statusMessage = "FAILED";
		} else if (status == ITestRunListener2.STATUS_ERROR) {
			statusMessage = "ERROR";
		}

		printMessage("[ INFO ] Test ReRan   - " + testName + " - test class: " + testClass + ", status: "
				+ statusMessage + ", trace: " + trace + ", expected: " + expected + ", actual: " + actual);
	}

	private synchronized void printMessage(String message) {
		printMessage(message, null);
	}
	
	private synchronized void printMessage(String message, String trace) {
		System.out.println("");
		System.out.println("********************************************************");
		System.out.println("* " + message);
		System.out.println("********************************************************");
		if (trace != null)
			System.out.println(trace);
		System.out.println("");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.junit.model.ITestRunListener2#testTreeEntry(java.lang.String)
	 */
	public synchronized void testTreeEntry(String description) {
		System.out.println("Test Tree Entry - Description: " + description);
	}

	/**
	 * The Class WrapperTestCase.
	 */
	class WrapperTestCase extends TestCase {

		/**
		 * Instantiates a new wrapper test case.
		 * 
		 * @param name the name
		 */
		public WrapperTestCase(String name) {
			super(name);
		}

		/* (non-Javadoc)
		 * @see junit.framework.TestCase#countTestCases()
		 */
		@Override
		public int countTestCases() {
			return 1;
		}

		/* (non-Javadoc)
		 * @see junit.framework.TestCase#run(junit.framework.TestResult)
		 */
		@Override
		public void run(TestResult result) {
		}
	}
}
