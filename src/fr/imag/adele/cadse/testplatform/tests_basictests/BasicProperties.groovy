package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class BasicProperties extends CadseTest {

	public void init() {
		/* Libraries */
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", "src")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Bundle to be created */
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.basictests", "src")
		
		/* TEST */
		CadseTestPart tp = addTestPart(null, 'BasicProperties_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_ts_CADSEg')
		tp.deleteBundle("Model.Workspace.CADSE_BasicProperties")
		
		
		tp = addTestPart(null, 'BasicProperties_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle("${run.testPlatformPath}/test-ws", "Model.Workspace.CADSE_BasicProperties", "src-gen")
		
	}
	
//	public boolean runTest() {
//		String wsDir
//
//		
//		
//		/* BasicProperties - CADSEg */
//		run.buildManager.deleteBundle("Model.Workspace.CADSE_BasicProperties")
//		run.runJavaTest(null, 'BasicProperties_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_ts_CADSEg') {
//			wsDir = delegate.wsDir
//		}
//		
//		/* BasicProperties - Execution */
//		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_BasicProperties", "src-gen")
//		run.runJavaTest(null, 'BasicProperties_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_ts_execution') {
//			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
//			run.ant.mkdir(dir: delegate.wsDir)
//		}
//
//		return false;
//	}

	public static void main(String[] args) {
		main(new BasicProperties())
	}
}
