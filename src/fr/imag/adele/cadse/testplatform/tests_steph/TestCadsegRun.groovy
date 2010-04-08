package fr.imag.adele.cadse.testplatform.tests_steph

import fr.imag.adele.cadse.platform.*



public class TestCadsegRun extends CadseTest {

	public void addBundleToCompile() {
		//compile and deploy bundles
		run.addBundle(run.wsTest, "TEST.CU.Workspace.Workspace", "src/main/java")
		
		//launch test
		addTestPart(null, 'TestCadsegRun', 'TEST.CU.Workspace.Workspace', 'test.fede.workspace.domain.internal.TestCadsegSuite') {
			delegate.cadseToExecute = 'Model.Workspace.CadseG'
		}
	}

	public static void main(String[] args) {
		main(new TestCadsegRun())
	}

}
