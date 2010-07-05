package fr.imag.adele.cadse.testplatform.tests_basictests
import fr.imag.adele.cadse.platform.gr.CadsegTestCase;

import fr.imag.adele.cadse.platform.*


public class Bug1 extends CadsegTestCase {
	
	public void init() {
		run.addBundelInM2("fr.imag.adele.cadse.test.basictests", "fr.imag.adele.cadse", "fr.imag.adele.cadse.test.basictests", "2.3.0-SNAPSHOT")
		
		/* TEST */
		CadseTestPart tp = addTestPart(null, 'Bug1_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.bug1.Bug1_ts_CADSEg')
		tp.deleteBundle("Model.Workspace.CADSEBug1")
		
		
		tp = addTestPart(null, 'Bug1_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.bug1.Bug1_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle("${run.testPlatformPath}/test-ws", "Model.Workspace.CADSEBug1", "src-gen")
		
	}
	
	public static void main(String[] args) {
		main(new Bug1())
	}
}
