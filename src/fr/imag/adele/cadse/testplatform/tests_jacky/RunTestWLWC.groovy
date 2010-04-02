package fr.imag.adele.cadse.testplatform.tests_jacky


import fr.imag.adele.cadse.platform.*



public class RunTestWLWC extends CadseTest {

	public void init() {
		//compile and deploy bundles
		run.addBundle(run.wsTest, "GraphicTests", "src")
		run.addBundle(run.wsTest, "TEST.CU.Workspace.Workspace", "src")
		
		//launch test
		addTestPart(null, 'RunTestWLWC', 'TEST.CU.Workspace.Workspace', 'test.fede.workspace.domain.internal.WorkingLogiqueSuiteTest') {
			delegate.cadseToExecute = 'Model.Workspace.CadseRoot'
		}

	}

	public static void main(String[] args) {
		main(new RunTestWLWC())
	}

}
