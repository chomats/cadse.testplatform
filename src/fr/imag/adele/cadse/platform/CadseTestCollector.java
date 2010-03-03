package fr.imag.adele.cadse.platform;

import org.eclipse.jdt.internal.junit.model.ITestRunListener2;
import org.eclipse.jdt.internal.junit.model.RemoteTestRunnerClient;

public class CadseTestCollector {
	AllPDETestListener		alltest	= new AllPDETestListener();
	public PDETestListener	pdeTestListener;

	public void runTestRunnerClient(CadseTestPart p, String classname, int portNumber, String testOutput) {
		try {
			pdeTestListener = new PDETestListener(classname, p);
			pdeTestListener.setOutputFile(testOutput);
			ITestRunListener2[] listeners = new ITestRunListener2[2];
			listeners[0] = pdeTestListener;
			listeners[1] = alltest;
			new RemoteTestRunnerClient().startListening(listeners, portNumber);
			System.out.println("Listening on port " + portNumber + " for test suite " + p.testName + " results ...");
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}

	public boolean failed() {
		return pdeTestListener.failed();
	}
}
