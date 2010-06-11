package fr.imag.adele.cadse.testplatform.tests_steph


import fr.imag.adele.cadse.platform.*

public class DeleteCadseRun extends CadseTest {

	public void init() {
		run.addBundle(run.wsTest, "TEST.CU.Workspace.Workspace", "src/main/java")
	
		addTestPart(null, 'DeleteCadseRun',
				'TEST.CU.Workspace.Workspace','test.fede.workspace.domain.internal.DeleteCadseSuite')
	}
	
	public static void main(String[] args) {
		main(new DeleteCadseRun())
	}

}
