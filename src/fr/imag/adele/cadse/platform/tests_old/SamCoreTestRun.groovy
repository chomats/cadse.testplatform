package fr.imag.adele.cadse.platform.tests_old

import fr.imag.adele.cadse.platform.CadseTest
import fr.imag.adele.cadse.platform.*


public class SamCoreTestRun extends CadseTest {


	public void init() {
		//compile and deploy bundles
		run.addBundle(run.wsTest, "GraphicTests", "src")
		run.addBundle(run.wsTest, "Model.Workspace.sam.core", "sources")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", , "src")
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.samcore","src")
			
		addTestPart(null, 'SamCoreTestRun', 'fr.imag.adele.cadse.test.samcore','fr.imag.adele.cadse.test.samcore.SamCoreTestSuite')
	}

	public static void main(String[] args) {
		main(new SamCoreTestRun())
	}

}
