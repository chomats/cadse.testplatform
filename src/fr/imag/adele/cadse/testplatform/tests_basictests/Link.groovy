package fr.imag.adele.cadse.testplatform.tests_basictests
import fr.imag.adele.cadse.platform.gr.CadsegTestCase 

import fr.imag.adele.cadse.platform.*


public class Link extends CadsegTestCase {
	
	public void init() {
		/* Libraries */
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", "src")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Bundle to be created */
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.basictests", "src")
		
		/* Link - CADSEg */
		CadseTestPart tp;
		tp = addTestPart(null, 'Link_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.link.Link_ts_CADSEg') 
		tp.deleteBundle("Model.Workspace.CADSE_Link")
		
		
		/* Link - Execution */
		tp = addTestPart(null, 'Link_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.link.Link_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle(null, "Model.Workspace.CADSE_Link", "src-gen")
	}
	
	public static void main(String[] args) {
		main(new Link())
	}
}
