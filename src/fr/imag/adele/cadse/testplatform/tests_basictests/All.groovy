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
		long beginTime = System.currentTimeMillis()
		long startTime
				
		
		/* ==================== */
		/* = BASIC PROPERTIES = */
		/* ==================== */
		
		/* BasicProperties - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_BasicProperties")
		run.runJavaTest(null, 'BasicProperties_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_ts_CADSEg') {
			wsDir = delegate.wsDir
		}
		
		/* BasicProperties - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_BasicProperties", "src-gen")
		run.runJavaTest(null, 'BasicProperties_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		
		
		/* ======================= */
		/* = CANNOT BE UNDEFINED = */
		/* ======================= */
				
		/* CannotBeUndefined - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_CannotBeUndefined")
		run.runJavaTest(null, 'CannotBeUndefined_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.cannotbeundefined.CannotBeUndefined_ts_CADSEg') {
			wsDir = delegate.wsDir
		}
		
		/* CannotBeUndefined - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_CannotBeUndefined", "src-gen")
		run.runJavaTest(null, 'CannotBeUndefined_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.cannotbeundefined.CannotBeUndefined_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}

		
		/* =============== */
		/* = CHECK PAGES = */
		/* =============== */
		
		/* CheckPages - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_CheckPages")
		run.runJavaTest(null, 'CheckPages_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.checkpages.CheckPages_ts_CADSEg') {
			wsDir = delegate.wsDir
		}

		
		/* ========================= */
		/* = DEFAULT INSTANCE NAME = */
		/* ========================= */
		
		/* DefaultInstanceName - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_DefaultInstanceName")
		run.runJavaTest(null, 'DefaultInstanceName_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.defaultinstancename.DefaultInstanceName_ts_CADSEg') {
			wsDir = delegate.wsDir
		}

		/* DefaultInstanceName - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_DefaultInstanceName", "src-gen")
		run.runJavaTest(null, 'DefaultInstanceName_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.defaultinstancename.DefaultInstanceName_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		
		
		/* ========================== */
		/* = HAS CONTENT / MAPPINGS =
		/* ========================== */
		
		/* HasContent - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_HasContent")
		run.runJavaTest(null, 'HasContent_CADSEg', 'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.hascontent.HasContent_ts_CADSEg') {
			wsDir = delegate.wsDir
		}
		
		/* HasContent - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_HasContent", "src-gen")
		run.runJavaTest(null, 'HasContent_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.hascontent.HasContent_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		
		
		/* =============== */
		/* = IS ABSTRACT = */
		/* =============== */
		
		/* IsAbstract - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_IsAbstract")
		run.runJavaTest(null, 'IsAbstract_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.isabstract.IsAbstract_ts_CADSEg') {
			wsDir = delegate.wsDir
		}

		/* IsAbstract - Execution */
		startTime = System.currentTimeMillis()
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_IsAbstract", "src-gen")
		run.runJavaTest(null, 'IsAbstract_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.isabstract.IsAbstract_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}

		
		/* ======== */
		/* = ROOT = */
		/* ======== */
		
		/* Root - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_Root")
		run.runJavaTest(null, 'Root_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.root.Root_ts_CADSEg') {
			wsDir = delegate.wsDir
		}

		/* Root - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_Root", "src-gen")
		run.runJavaTest(null, 'Root_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.root.Root_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}

		return false;
	}

	public static void main(String[] args) {
		main(new All())
	}
}
