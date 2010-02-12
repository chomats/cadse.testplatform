package fr.imag.adele.cadse.platform.tests_old

import fr.imag.adele.cadse.platform.*



/**
 * @author chomats
 *
 */
public class AllTestRun{

	public static void main(String[] args) {
		new CadseTestPlatform().runTests(
				//new CreateSimpleLinkRun(),
				//new DeleteCadseRun(),
				//new OrderPagesRun(),
				//new RunTestWLWC(),
				new SamCoreTestRun(),
				//new TestCadsegRun(),
				//new TutosRun()
				);
	}
}
