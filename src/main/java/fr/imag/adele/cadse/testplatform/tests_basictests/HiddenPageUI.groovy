package fr.imag.adele.cadse.testplatform.tests_basictests
import fr.imag.adele.cadse.platform.gr.CadsegTestCase;

import fr.imag.adele.cadse.platform.*


public class HiddenPageUI extends CadsegTestCase {
	
	public void init() {
		/* Libraries */
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Bundle to be created */
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.basictests", "src")
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.generatorManager", "src")
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.ui", "src")
		
		/* TEST */
		CadseTestPart tp = addTestPart(null, 'HiddenPageUI_CADSEg','fr.imag.adele.cadse.test.ui','fr.imag.adele.cadse.test.ui.HiddenPageUI_ts_CADSEg')
		
		
		tp = addTestPart(null, 'HiddenPageUI_Execution','fr.imag.adele.cadse.test.ui','fr.imag.adele.cadse.test.ui.HiddenPageUI_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.setExtraBundleClosure { addBundle( "Model.Workspace.CADSE_UI_", "src-gen") }
		
	}
	
	public static void main(String[] args) {
		main(new HiddenPageUI())
	}
}
