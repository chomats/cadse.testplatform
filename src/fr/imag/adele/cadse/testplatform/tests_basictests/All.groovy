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
		boolean testResult
		
		ArrayList<String> tests_names    = new ArrayList<String>()
		ArrayList<Boolean> tests_results = new ArrayList<String>()
		ArrayList<Long> tests_duration   = new ArrayList<Long>()
		
		
		/* ==================== */
		/* = BASIC PROPERTIES = */
		/* ==================== */
		
		/* BasicProperties - CADSEg */
		testName = 'BasicProperties_CADSEg'
		startTime = System.currentTimeMillis()
		run.buildManager.deleteBundle("Model.Workspace.CADSE_BasicProperties")
		testResult = run.runJavaTest(null, testName,'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_ts_CADSEg') {
			wsDir = delegate.wsDir
		}
		tests_results.add(testResult)
		tests_names.add(testName)
		tests_duration.add(System.currentTimeMillis() - startTime)

		/* BasicProperties - Execution */
		testName = 'BasicProperties_Execution'
		startTime = System.currentTimeMillis()
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_BasicProperties", "src-gen")
		testResult = run.runJavaTest(null, testName,'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tests_results.add(testResult)
		tests_names.add(testName)
		tests_duration.add(System.currentTimeMillis() - startTime)
		
		
		/* =============== */
		/* = CHECK PAGES = */
		/* =============== */
		
		/* CheckPages - CADSEg */
		testName = 'CheckPages_CADSEg'
		startTime = System.currentTimeMillis()
		run.buildManager.deleteBundle("Model.Workspace.CADSE_CheckPages")
		testResult = run.runJavaTest(null, testName,'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.checkpages.CheckPages_ts_CADSEg') {
			wsDir = delegate.wsDir
		}
		tests_results.add(testResult)
		tests_names.add(testName)
		tests_duration.add(System.currentTimeMillis() - startTime)

		
		/* ========================= */
		/* = DEFAULT INSTANCE NAME = */
		/* ========================= */
		
		/* DefaultInstanceName - CADSEg */
		testName = 'DefaultInstanceName_CADSEg'
		startTime = System.currentTimeMillis()
		run.buildManager.deleteBundle("Model.Workspace.CADSE_DefaultInstanceName")
		testResult = run.runJavaTest(null, testName,'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.defaultinstancename.DefaultInstanceName_ts_CADSEg') {
			wsDir = delegate.wsDir
		}
		tests_results.add(testResult)
		tests_names.add(testName)
		tests_duration.add(System.currentTimeMillis() - startTime)

		/* DefaultInstanceName - Execution */
		testName = 'DefaultInstanceName_Execution'
		startTime = System.currentTimeMillis()
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_DefaultInstanceName", "src-gen")
		testResult = run.runJavaTest(null, testName,'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.defaultinstancename.DefaultInstanceName_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tests_results.add(testResult)
		tests_names.add(testName)
		tests_duration.add(System.currentTimeMillis() - startTime)
	
		
		/* ========================== */
		/* = HAS CONTENT / MAPPINGS =
		/* ========================== */
		
		/* HasContent - CADSEg */
		testName = 'HasContent_CADSEg'
		startTime = System.currentTimeMillis()
		run.buildManager.deleteBundle("Model.Workspace.CADSE_HasContent")
		testResult = run.runJavaTest(null, testName,'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.hascontent.HasContent_ts_CADSEg') {
			wsDir = delegate.wsDir
		}
		tests_results.add(testResult)
		tests_names.add(testName)
		tests_duration.add(System.currentTimeMillis() - startTime)

		/* HasContent - Execution */
		testName = 'HasContent_Execution'
		startTime = System.currentTimeMillis()
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_HasContent", "src-gen")
		testResult = run.runJavaTest(null, testName,'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.hascontent.HasContent_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tests_results.add(testResult)
		tests_names.add(testName)
		tests_duration.add(System.currentTimeMillis() - startTime)
		
		
		/* =============== */
		/* = IS ABSTRACT = */
		/* =============== */
		
		/* IsAbstract - CADSEg */
		testName = 'IsAbstract_CADSEg'
		startTime = System.currentTimeMillis()
		run.buildManager.deleteBundle("Model.Workspace.CADSE_IsAbstract")
		testResult = run.runJavaTest(null, testName,'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.isabstract.IsAbstract_ts_CADSEg') {
			wsDir = delegate.wsDir
		}
		tests_results.add(testResult)
		tests_names.add(testName)
		tests_duration.add(System.currentTimeMillis() - startTime)

		/* IsAbstract - Execution */
		testName = 'IsAbstract_Execution'
		startTime = System.currentTimeMillis()
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_IsAbstract", "src-gen")
		testResult = run.runJavaTest(null, testName,'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.isabstract.IsAbstract_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tests_results.add(testResult)
		tests_names.add(testName)
		tests_duration.add(System.currentTimeMillis() - startTime)

		
		/* ======== */
		/* = ROOT = */
		/* ======== */
		
		/* Root - CADSEg */
		testName = 'Root_CADSEg'
		startTime = System.currentTimeMillis()
		run.buildManager.deleteBundle("Model.Workspace.CADSE_Root")
		testResult = run.runJavaTest(null, testName,'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.root.Root_ts_CADSEg') {
			wsDir = delegate.wsDir
		}
		tests_results.add(testResult)
		tests_names.add(testName)
		tests_duration.add(System.currentTimeMillis() - startTime)

		/* Root - Execution */
		testName = 'Root_Execution'
		startTime = System.currentTimeMillis()
		run.buildManager.createBundle(wsDir, "Model.Workspace.CADSE_Root", "src-gen")
		testResult = run.runJavaTest(null, testName,'fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.root.Root_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tests_results.add(testResult)
		tests_names.add(testName)
		tests_duration.add(System.currentTimeMillis() - startTime)

		
		/* =================== */
		/* = DISPLAY SUMMARY = */
		/* =================== */	
		
		System.out.println("\n=========\n SUMMARY\n=========");
		boolean error = false;
		for (int i=0; i< tests_names.size(); i++) {
			if (tests_results[i] == false) // yep, false = success
				System.out.println("[SUCCESS] " + tests_names[i] + " (" + tests_duration[i] + " ms)");
			else {
				System.out.println("[ ERROR ] " + tests_names[i] + " (" + tests_duration[i] + " ms)");
				error = true
			}
		}
		
		if (error == false) // success
			System.out.println("\n===> [SUCCESS] " + tests_names.size() + " tests in " + System.currentTimeMillis() - beginTime + " ms)");
		else
			System.out.println("\n===> [ ERROR ] " + tests_names.size() + " tests in " + System.currentTimeMillis() - beginTime + " ms)");
			
		return error;
	}

	public static void main(String[] args) {
		main(new All())
	}
}
