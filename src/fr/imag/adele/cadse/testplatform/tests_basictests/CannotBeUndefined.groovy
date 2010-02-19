package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class CannotBeUndefined extends CadseTest {

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

		/* CannotBeUndefined - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_CannotBeUndefined")
		run.runJavaTest(null, 'CannotBeUndefined_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.cannotbeundefined.CannotBeUndefined_ts_CADSEg') {
			wsDir = delegate.wsDir
		}
		
		/* CannotBeUndefined - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_CannotBeUndefined", "src-gen")
		run.runJavaTest(null, 'CannotBeUndefined_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.cannotbeundefined.CannotBeUndefined_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}

		return false;
	}

	public static void main(String[] args) {
		main(new CannotBeUndefined())
	}
}
