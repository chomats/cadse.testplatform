/**
 *
 */
package fr.imag.adele.cadse.platform



public abstract class CadseTest {
	public CadseTestPlatform	run;

	public void setCadseTestPlatform(CadseTestPlatform lRun) {
		run = lRun;
	}

	public abstract void addBundleToCompile();

	/** true if failed */
	public abstract boolean runTest();

	public static void main(CadseTest cadseTest) {
		new CadseTestPlatform().runTests(cadseTest);
	}
}
