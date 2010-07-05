package fr.imag.adele.cadse.testplatform.tests_basictests
import fr.imag.adele.cadse.platform.gr.CadsegTestCase 

import fr.imag.adele.cadse.platform.*


public class DefaultInstanceName extends CadsegTestCase {
	
	public void init() {
		run.addBundelInM2("fr.imag.adele.cadse.test.basictests", "fr.imag.adele.cadse", "fr.imag.adele.cadse.test.basictests", "2.3.0-SNAPSHOT")
		
		/* DefaultInstanceName - CADSEg */
		CadseTestPart tp;
		tp = addTestPart(null, 'DefaultInstanceName_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.defaultinstancename.DefaultInstanceName_ts_CADSEg')
		tp.deleteBundle 'Model.Workspace.CADSE_DefaultInstanceName'
		
		/* DefaultInstanceName - Execution */
		tp = addTestPart(null, 'DefaultInstanceName_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.defaultinstancename.DefaultInstanceName_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle(null, "Model.Workspace.CADSE_DefaultInstanceName", "src-gen")
	}
	
	public static void main(String[] args) {
		main(new DefaultInstanceName())
	}
}
