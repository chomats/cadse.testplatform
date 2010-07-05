package fr.imag.adele.cadse.testplatform.tests_basictests
import fr.imag.adele.cadse.platform.gr.CadsegTestCase 

import fr.imag.adele.cadse.platform.*


public class IsAbstract extends CadsegTestCase {
	
	public void init() {
		run.addBundelInM2("fr.imag.adele.cadse.test.basictests", "fr.imag.adele.cadse", "fr.imag.adele.cadse.test.basictests", "2.3.0-SNAPSHOT")
		
		/* IsAbstract - CADSEg */
		CadseTestPart tp;
		tp = addTestPart(null, 'IsAbstract_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.isabstract.IsAbstract_ts_CADSEg')
		tp.deleteBundle 'Model.Workspace.CADSE_IsAbstract'
		
		/* IsAbstract - Execution */
		tp = addTestPart(null, 'IsAbstract_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.isabstract.IsAbstract_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle(null, "Model.Workspace.CADSE_IsAbstract", "src-gen")
	}
	
	public static void main(String[] args) {
		main(new IsAbstract())
	}
}
