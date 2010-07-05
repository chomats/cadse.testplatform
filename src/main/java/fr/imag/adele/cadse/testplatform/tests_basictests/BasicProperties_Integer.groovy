package fr.imag.adele.cadse.testplatform.tests_basictests
import fr.imag.adele.cadse.platform.gr.CadsegTestCase 

import fr.imag.adele.cadse.platform.*


public class BasicProperties_Integer extends CadsegTestCase {
	
	public void init() {
		run.addBundelInM2("fr.imag.adele.cadse.test.basictests", "fr.imag.adele.cadse", "fr.imag.adele.cadse.test.basictests", "2.3.0-SNAPSHOT")
		
		/* TEST */
		CadseTestPart tp = addTestPart(null, 'BasicProperties_Integer_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Integer_ts_CADSEg')
		tp.deleteBundle("Model.Workspace.CADSEBasicPropertiesInteger")
		
		
		tp = addTestPart(null, 'BasicProperties_Integer_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_Integer_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle("${run.testPlatformPath}/test-ws", "Model.Workspace.CADSEBasicPropertiesInteger", "src-gen")
		
	}
	
	public static void main(String[] args) {
		main(new BasicProperties_Integer())
	}
}
