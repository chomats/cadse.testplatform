package fr.imag.adele.cadse.testplatform.tests_jacky


import fr.imag.adele.cadse.platform.*

public class DeleteCadseRun extends CadseTest {

	public void addBundleToCompile() {
	 	run.buildManager.createBundle(run.wsTest, "GraphicTests", "src")
		run.buildManager.createBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", , "src")
		run.buildManager.createBundle(run.wsTest, "TEST.CU.Workspace.Workspace", "src")
	}

	public boolean runTest() {
		return run.runJavaTest(null, 'DeleteCadseRun',
				'TEST.CU.Workspace.Workspace','test.fede.workspace.domain.internal.DeleteCadseSuite')
	}
	public static void main(String[] args) {
		main(new DeleteCadseRun())
	}

}
