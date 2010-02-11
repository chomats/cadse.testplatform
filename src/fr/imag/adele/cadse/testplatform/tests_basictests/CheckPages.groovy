package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class CheckPages extends CadseTest {

	public void addBundleToCompile() {
		/* Libraries */
		run.buildManager.createBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.buildManager.createBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.buildManager.createBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", "src")
		run.buildManager.createBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Bundle to be created */
		run.buildManager.createBundle(run.wsTest, "fr.imag.adele.cadse.test.basictests", "src")
	}
	
	public boolean runTest() {
		String wsDir

		/* CheckPages - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_CheckPages")
		if (run.runJavaTest(null, 'CheckPages_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.checkpages.CheckPages_ts_CADSEg') {
			wsDir = delegate.wsDir
		})
			return true;

		return false;
	}

	public static void main(String[] args) {
		main(new CheckPages())
	}
}
