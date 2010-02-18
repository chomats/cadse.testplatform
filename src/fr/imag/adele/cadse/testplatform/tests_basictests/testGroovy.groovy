package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*
import java.util.ArrayList


public class All extends CadseTest {

	public void addBundleToCompile() {
		/* Libraries */
		run.buildManager.createBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.buildManager.createBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.buildManager.createBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", "src")
		run.buildManager.createBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Bundle to be created */
		run.buildManager.createBundle(run.wsTest, "fr.imag.adele.cadse.test.basictests", "src")
	}
	
	public boolean runTest() {
		String wsDir
		String testName

		
		/* ==================== */
		/* = BASIC PROPERTIES = */
		/* ==================== */
		
		/* BasicProperties - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_BasicProperties")
		run.runJavaTest(null, 'BasicProperties_CADSEg', 'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_ts_CADSEg') {
			wsDir = delegate.wsDir
		}
		
		/* BasicProperties - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_BasicProperties", "src-gen")
		run.runJavaTest(null, 'BasicProperties_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		
		
		/* ========================= */
		/* = DEFAULT INSTANCE NAME = */
		/* ========================= */
		
		/* DefaultInstanceName - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_DefaultInstanceName")
		run.runJavaTest(null, 'DefaultInstanceName_CADSEg', 'fr.imag.adele.cadse.test.basictests', 'fr.imag.adele.cadse.test.basictests.defaultinstancename.DefaultInstanceName_ts_CADSEg') {
			wsDir = delegate.wsDir
		}

		/* DefaultInstanceName - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_DefaultInstanceName", "src-gen")
		run.runJavaTest(null, 'DefaultInstanceName_Execution', 'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.defaultinstancename.DefaultInstanceName_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}

		
		return false;
	}

	public static void main(String[] args) {
		main(new All())
	}
}