package fr.imag.adele.cadse.testplatform.tests_steph

import fr.imag.adele.cadse.platform.*

public class OrderPagesRun extends CadseTest {


	public void init() {
		run.addBundle(run.wsTest, "GraphicTests", "src")
		run.addBundle(run.wsTest, "TEST.CU.Workspace.Workspace", "src")
	
		// execute un premier fois
		addTestPart(null, 'OrderPagesRun_1',
				'TEST.CU.Workspace.Workspace','test.fede.workspace.domain.internal.OderPagesSuite');
		// check dix fois que le chargement est ok et l'ordre aussi
		for(int i = 0; i < 10; i++) {
	//		launch test
			addTestPart(null, 'OrderPagesRun_2',
					'TEST.CU.Workspace.Workspace','test.fede.workspace.domain.internal.OderPagesSuite_2') {
				delegate.deleteWsDir = false;
				}
		}
	}

	public static void main(String[] args) {
		main(new OrderPagesRun())
	}

}
