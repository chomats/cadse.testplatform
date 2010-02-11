package fr.imag.adele.cadse.testplatform.tests_jacky

import fr.imag.adele.cadse.platform.*


public class CreateSimpleLinkRun extends CadseTest {

	public void addBundleToCompile() {
		 run.buildManager.createBundle(run.wsTest, "fr.imag.adele.graphictests", "src")
		 run.buildManager.createBundle(run.wsTest, "TEST.CU.Workspace.Workspace", "src")
		 run.buildManager.createBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", , "src")
	}

	public boolean runTest() {
		return run.runJavaTest(null, 'CreateSimpleLinkRun',
				'TEST.CU.Workspace.Workspace','test.fede.workspace.domain.internal.CreateSimpleLinkSuite')
	}
	public static void main(String[] args) {
		main(new CreateSimpleLinkRun())
	}
}
