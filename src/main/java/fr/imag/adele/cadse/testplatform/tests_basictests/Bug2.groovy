package fr.imag.adele.cadse.testplatform.tests_basictests
import fr.imag.adele.cadse.platform.gr.CadsegTestCase;

import fr.imag.adele.cadse.platform.*


public class Bug2 extends CadsegTestCase {
	
	public void init() {
		run.addBundelInM2("fr.imag.adele.cadse.test.basictests", "fr.imag.adele.cadse", "fr.imag.adele.cadse.test.basictests", "2.3.0-SNAPSHOT")
		
		/* TEST */
		CadseTestPart tp = addTestPart(null, 'Bug2_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.bug2.Bug2_ts_CADSEg')
		tp.deleteBundle("Model.Workspace.CADSEBug2")
		
		
		tp = addTestPart(null, 'Bug2_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.bug2.Bug2_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle("${run.testPlatformPath}/test-ws", "Model.Workspace.CADSEBug2", "src-gen")
		
	}
	
	public static void main(String[] args) {
		main(new Bug2())
	}
}
