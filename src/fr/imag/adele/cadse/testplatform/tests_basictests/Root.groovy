package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class Root extends CadseTest {

	public void addBundleToCompile() {
		/* Libraries */
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", "src")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Bundle to be created */
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.basictests", "src")
	}
	
	public boolean runTest() {
		String wsDir

		/* Root - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_Root")
		run.runJavaTest(null, 'Root_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.root.Root_ts_CADSEg') {
			wsDir = delegate.wsDir
		}

		/* Root - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_Root", "src-gen")
		run.runJavaTest(null, 'Root_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.root.Root_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}

		return false;
	}

	public static void main(String[] args) {
		main(new Root())
	}
}
