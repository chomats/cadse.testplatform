package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class BasicProperties extends CadseTest {

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

		/* BasicProperties - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_BasicProperties")
		if (run.runJavaTest(null, 'BasicProperties_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_ts_CADSEg') {
			wsDir = delegate.wsDir
		})
			return true;

		/* BasicProperties - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_BasicProperties", "src-gen")
		if (run.runJavaTest(null, 'BasicProperties_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		})
			return true;

		return false;
	}

	public static void main(String[] args) {
		main(new BasicProperties())
	}
}
