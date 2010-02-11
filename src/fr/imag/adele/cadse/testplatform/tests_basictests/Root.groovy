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
		run.buildManager.deleteBundle("Model.Workspace.CADSE_root")
		if (run.runJavaTest(null, 'Root_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.root.root_ts_CADSEg') {
			wsDir = delegate.wsDir
		})
			return true;

		/* Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_root", "src-gen")
		if (run.runJavaTest(null, 'Root_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.root.root_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		})
			return true;

		return false;
	}

	public static void main(String[] args) {
		main(new Root())
	}
}
