package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class IsAbstract extends CadseTest {

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

		/* IsAbstract - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_IsAbstract")
		run.runJavaTest(null, 'IsAbstract_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.isabstract.IsAbstract_ts_CADSEg') {
			wsDir = delegate.wsDir
		}

		/* IsAbstract - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_IsAbstract", "src-gen")
		run.runJavaTest(null, 'IsAbstract_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.isabstract.IsAbstract_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}

		return false;
	}

	public static void main(String[] args) {
		main(new IsAbstract())
	}
}
