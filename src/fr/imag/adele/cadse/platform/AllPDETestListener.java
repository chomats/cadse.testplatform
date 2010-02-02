package fr.imag.adele.cadse.platform;

import org.eclipse.jdt.internal.junit.model.ITestRunListener2;

public class AllPDETestListener implements ITestRunListener2 {
	private int		totalNumberOfTests;
	private int		testsRunCount;
	private int		numberOfTestsPassed;
	private int		numberOfTestsFailed;
	private int		numberOfTestsWithError;
	private boolean	testRunEnded	= false;
	long			elapsedTime		= 0;

	public AllPDETestListener() {
	}

	public boolean failed() {
		return ((numberOfTestsFailed + numberOfTestsWithError) > 0) || (testRunEnded && (testsRunCount == 0));
	}

	public int count() {
		return testsRunCount;
	}

	public synchronized void testRunStarted(int testCount) {
		totalNumberOfTests = testCount;
		testsRunCount = 0;
		numberOfTestsPassed = 0;
		numberOfTestsFailed = 0;
		numberOfTestsWithError = 0;
		testRunEnded = false;
	}

	public synchronized void testRunEnded(long elapsedTime) {
		testRunEnded = true;
		this.elapsedTime += elapsedTime;
	}

	public synchronized void testRunStopped(long elapsedTime) {
		testRunEnded(elapsedTime);
	}

	public synchronized void testRunTerminated() {
		testRunEnded(0);
	}

	public synchronized void testStarted(String testId, String testName) {
		testsRunCount++;
	}

	public synchronized void testEnded(String testId, String testName) {
		// numberOfTestsPassed = count() - (numberOfTestsFailed +
		// numberOfTestsWithError);
	}

	public synchronized void testFailed(int status, String testId, String testName, String trace, String expected,
			String actual) {
		if (status == ITestRunListener2.STATUS_OK) {
			numberOfTestsPassed++;
		} else if (status == ITestRunListener2.STATUS_FAILURE) {
			numberOfTestsFailed++;
		} else if (status == ITestRunListener2.STATUS_ERROR) {
			numberOfTestsWithError++;
		}
	}

	public synchronized void testReran(String testId, String testClass, String testName, int status, String trace,
			String expected, String actual) {

	}

	public synchronized void testTreeEntry(String description) {
	}

	public int getTotalNumberOfTests() {
		return totalNumberOfTests;
	}

	public int getTestsRunCount() {
		return testsRunCount;
	}

	public int getNumberOfTestsPassed() {
		if (numberOfTestsPassed == 0) {
			return count() - (numberOfTestsFailed + numberOfTestsWithError);
		}
		return numberOfTestsPassed;
	}

	public int getNumberOfTestsFailed() {
		return numberOfTestsFailed;
	}

	public int getNumberOfTestsWithError() {
		return numberOfTestsWithError;
	}

	public boolean isTestRunEnded() {
		return testRunEnded;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

}
