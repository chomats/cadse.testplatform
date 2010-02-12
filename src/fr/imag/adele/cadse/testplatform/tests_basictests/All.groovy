package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


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

		/* =============== */
		/* = CHECK PAGES =
		/* =============== */
		
		/* CheckPages - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_CheckPages")
		if (run.runJavaTest(null, 'CheckPages_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.checkpages.CheckPages_ts_CADSEg') {
			wsDir = delegate.wsDir
		})
			return true;

		
		/* =============== */
		/* = IS ABSTRACT =
		/* =============== */
		
		/* IsAbstract - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_IsAbstract")
		if (run.runJavaTest(null, 'IsAbstract_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.isabstract.IsAbstract_ts_CADSEg') {
			wsDir = delegate.wsDir
		})
			return true;

		/* IsAbstract - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_IsAbstract", "src-gen")
		if (run.runJavaTest(null, 'IsAbstract_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.isabstract.IsAbstract_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		})
			return true;

		
		/* ======== */
		/* = ROOT =
		/* ======== */
		
		/* Root - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_Root")
		if (run.runJavaTest(null, 'Root_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.root.Root_ts_CADSEg') {
			wsDir = delegate.wsDir
		})
			return true;

		/* Root - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_Root", "src-gen")
		if (run.runJavaTest(null, 'Root_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.root.Root_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		})
			return true;

		
		/* ========================== */
		/* = HAS CONTENT / MAPPINGS =
		/* ========================== */
		
		/* HasContent - CADSEg */
		run.buildManager.deleteBundle("Model.Workspace.CADSE_HasContent")
		if (run.runJavaTest(null, 'HasContent_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.hascontent.HasContent_ts_CADSEg') {
			wsDir = delegate.wsDir
		})
			return true;

		/* HasContent - Execution */
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_HasContent", "src-gen")
		if (run.runJavaTest(null, 'HasContent_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.hascontent.HasContent_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		})
			return true;
		
		
		return false;
	}

	public static void main(String[] args) {
		main(new All())
	}
}
