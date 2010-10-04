package fr.imag.adele.cadse.testplatform.tests_basictests
import fr.imag.adele.cadse.platform.gr.CadsegTestCase;

import fr.imag.adele.cadse.platform.*


public class GroupTest2_Long extends CadsegTestCase {
	
	public void init() {
		run.addBundelInM2("fr.imag.adele.cadse.test.basictests", "fr.imag.adele.cadse", "fr.imag.adele.cadse.test.basictests", "2.3.0-SNAPSHOT")
		
		/* TEST */
		CadseTestPart tp = addTestPart(null, 'Group_Test2_Long_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.group.test2.Test2_Long_ts_CADSEg')
		tp.deleteBundle("Model.Workspace.CADSEgroup_test2Long")
		
		
		tp = addTestPart(null, 'Group_Test2_Long_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.group.test2.Test2_Long_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle("${run.testPlatformPath}/test-ws", "Model.Workspace.CADSEgroup_test2Long", "src-gen")
		
	}
	
	public static void main(String[] args) {
		main(new GroupTest2_Long())
	}
}
