package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class Root extends CadseTest {

	public void addBundleToCompile() {
		/* Libraries */
		run.buildManager.createBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.buildManager.createBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.buildManager.createBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", "src")
		run.buildManager.createBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Test to be executed */
		run.buildManager.createBundle(run.wsTest, "fr.imag.adele.cadse.test.basictests", "src")
	}
	
	public boolean runTest() {
		String wsDir

		/* CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_interfaces")
		if (run.runJavaTest(null, 'Interfaces_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.interfaces.CheckCadsegPages_ts') {
			wsDir = delegate.wsDir
		})
			return true;

		return false;
	}

	public static void main(String[] args) {
		main(new Root())
	}
}
