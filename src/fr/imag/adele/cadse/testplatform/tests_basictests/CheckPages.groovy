package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class CheckPages extends CadseTest {

	public void init() {
		/* Libraries */
		run.addBundle(run.wsTest, 'fr.imag.adele.graphictests', 'src/main/java')
		run.addBundle(run.wsTest, 'fr.imag.adele.graphictests.cadse', 'src/main/java')
		run.addBundle(run.wsTest, 'org.eclipse.swtbot.eclipse.junit4.headless', 'src')
		run.addBundle(run.wsTest, 'org.eclipse.swtbot.swt.finder.keyboard.fr', 'src')
		
		/* Bundle to be created */
		run.addBundle(run.wsTest, 'fr.imag.adele.cadse.test.basictests', 'src')
		
		CadseTestPart tp;
		tp = addTestPart(null, 'CheckPages_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.checkpages.CheckPages_ts_CADSEg');
		tp.deleteBundle 'Model.Workspace.CADSE_CheckPages'
	}

	public static void main(String[] args) {
		main(new CheckPages())
	}
}
