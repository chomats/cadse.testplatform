/**
 *
 */
 package fr.imag.adele.cadse.platform.srciptTest

 import fr.imag.adele.cadse.platform.*

/**
 * @author chomats
 *
 */
public class OrderPagesRun extends CadseTest {


	public void addBundleToCompile() {
		run.addBundle(run.wsTest, "GraphicTests", "src")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", "src")
		run.addBundle(run.wsTest, "TEST.CU.Workspace.Workspace", "src")
	}

	public boolean runTest() {
		// execute un premier fois
		if (run.runJavaTest(null, 'OrderPagesRun_1',
				'TEST.CU.Workspace.Workspace','test.fede.workspace.domain.internal.OderPagesSuite'))
			return true;

		// check dix fois que le chargement est ok et l'ordre aussi
		for(int i = 0; i < 10; i++) {
	//		launch test
			if (run.runJavaTest(null, 'OrderPagesRun_2',
					'TEST.CU.Workspace.Workspace','test.fede.workspace.domain.internal.OderPagesSuite_2') {
				delegate.deleteWsDir = false;
				})

				return true;
		}
		return false;

	}

	public static void main(String[] args) {
		main(new OrderPagesRun())
	}

}
