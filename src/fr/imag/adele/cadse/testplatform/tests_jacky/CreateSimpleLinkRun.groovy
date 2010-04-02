package fr.imag.adele.cadse.testplatform.tests_jacky

import fr.imag.adele.cadse.platform.*


public class CreateSimpleLinkRun extends CadseTest {

	public void init() {
		 run.addBundle(run.wsTest, "fr.imag.adele.graphictests", "src")
		 run.addBundle(run.wsTest, "TEST.CU.Workspace.Workspace", "src")
		 
		 addTestPart(null, 'CreateSimpleLinkRun',
				'TEST.CU.Workspace.Workspace','test.fede.workspace.domain.internal.CreateSimpleLinkSuite')
	}
	public static void main(String[] args) {
		main(new CreateSimpleLinkRun())
	}
}
