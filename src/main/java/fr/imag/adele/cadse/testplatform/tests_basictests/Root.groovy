package fr.imag.adele.cadse.testplatform.tests_basictests
import fr.imag.adele.cadse.platform.gr.CadsegTestCase 

import fr.imag.adele.cadse.platform.*


public class Root extends CadsegTestCase {
	
	public void init() {
		run.addBundelInM2("fr.imag.adele.cadse.test.basictests", "fr.imag.adele.cadse", "fr.imag.adele.cadse.test.basictests", "2.3.0-SNAPSHOT")
		
		/* Root - CADSEg */
		CadseTestPart tp;
		tp = addTestPart(null, 'Root_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.root.Root_ts_CADSEg');
		tp.deleteBundle("Model.Workspace.CADSE_Root")
		
		/* Root - Execution */
		tp = addTestPart(null, 'Root_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.root.Root_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle(null, "Model.Workspace.CADSE_Root", "src-gen")
		
	}
	
	public static void main(String[] args) {
		main(new Root())
	}
}
