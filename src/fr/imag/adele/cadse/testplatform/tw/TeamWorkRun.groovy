package fr.imag.adele.cadse.testplatform.tw

import fr.imag.adele.cadse.platform.*


public class TeamWorkRun extends CadseTest {

	public void init() {
		/* Libraries */
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Test to be executed */
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.tutos", "src")
	}
	
	public boolean runTest() {
		String wsDir

		/* ============ */
		/*    TUTO 1    */
		/* ============ */
		
		/* CADSEg : from the beginning up to part 5.5 */
		run.buildManager.deleteBundle("Model.Workspace.WebAppModel")
		if (run.runJavaTest(null, 'Tuto1_Part1_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part1_ts_CADSEg') {
			wsDir = delegate.wsDir
		})
			return true;

		/* Execution : part 5.6 */
		run.buildManager.createBundle(wsDir, "Model.Workspace.WebAppModel", "src-gen")
		if (run.runJavaTest(null, 'Tuto1_Part1_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part1_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		})
			return true;

		/* CADSEg : from part 6 to part 6.3 */
		run.buildManager.deleteBundle("Model.Workspace.WebAppModel")
		if (run.runJavaTest(null, 'Tuto1_Part2_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part2_ts_CADSEg') {
			delegate.deleteWsDir = false;
		})
			return true;
		
		/* Execution : end of part 6.3 */
		run.buildManager.createBundle(wsDir, "Model.Workspace.WebAppModel", "src-gen")
		if (run.runJavaTest(null, 'Tuto1_Part2_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part2_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = false;
		})
			return true;
	
		/* CADSEg : part 6.4 */
		run.buildManager.deleteBundle("Model.Workspace.WebAppModel")
		if (run.runJavaTest(null, 'Tuto1_Part3_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part3_ts_CADSEg') {
			delegate.deleteWsDir = false;
		})
			return true;
		
		/* Execution : end of part 6.4 */ 
		run.buildManager.createBundle(wsDir, "Model.Workspace.WebAppModel", "src-gen")
		if (run.runJavaTest(null, 'Tuto1_Part3_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part3_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = false;
		})
			return true;
	
		/* CADSEg : part 6.5 */
		run.buildManager.deleteBundle("Model.Workspace.WebAppModel")
		if (run.runJavaTest(null, 'Tuto1_Part4_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part4_ts_CADSEg') {
			delegate.deleteWsDir = false;
		})
			return true;
	
		/* Execution : end of part 6.5 */ 
		run.buildManager.createBundle(wsDir, "Model.Workspace.WebAppModel", "src-gen")
		if (run.runJavaTest(null, 'Tuto1_Part4_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part4_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = false;
		})
			return true;
	
		/* CADSEg : part 6.6 */
		run.buildManager.deleteBundle("Model.Workspace.WebAppModel")
		if (run.runJavaTest(null, 'Tuto1_Part5_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part5_ts_CADSEg') {
			delegate.deleteWsDir = false;
		})
			return true;
	
		/* Execution : end of part 6.6 */ 
		run.buildManager.createBundle(wsDir, "Model.Workspace.WebAppModel", "src-gen")
		if (run.runJavaTest(null, 'Tuto1_Part5_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part5_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = false;
		})
			return true;
	
	
		/* ============ */
		/*    TUTO 2    */
		/* ============ */
		
		/* CADSEg : part 3.1 */
		run.buildManager.deleteBundle("Model.Workspace.WebAppModel")
		if (run.runJavaTest(null, 'Tuto2_Part1_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part1_ts_CADSEg') {
			delegate.deleteWsDir = false;
		})
			return true;
			
		/* Execution : part 3.1 */
		run.buildManager.createBundle(wsDir, "Model.Workspace.WebAppModel", "src-gen")
		if (run.runJavaTest(null, 'Tuto2_Part1_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part1_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = true;
		})
			return true;
			
		/* CADSEg : part 3.2 */
		run.buildManager.deleteBundle("Model.Workspace.WebAppModel")
		if (run.runJavaTest(null, 'Tuto2_Part2_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part2_ts_CADSEg') {
			delegate.deleteWsDir = false;
		})
			return true;
	
		/* Execution : end of part 3.2 */ 
		run.buildManager.createBundle(wsDir, "Model.Workspace.WebAppModel", "src-gen")
		if (run.runJavaTest(null, 'Tuto2_Part2_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part2_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = false;
		})
			return true;
		
		/* CADSEg : part 3.3 */ 
		run.buildManager.deleteBundle("Model.Workspace.WebAppModel")
		if (run.runJavaTest(null, 'Tuto2_Part3_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part3_ts_CADSEg') {
			delegate.deleteWsDir = false;
		})
			return true; 		
		
		/* Execution : end of part 3.3 */ 
		run.buildManager.createBundle(wsDir, "Model.Workspace.WebAppModel", "src-gen")
		if (run.runJavaTest(null, 'Tuto2_Part3_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part3_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = false;
		})
			return true;
			
		/* CADSEg : part 3.4*/ 
		run.buildManager.deleteBundle("Model.Workspace.WebAppModel")
		if (run.runJavaTest(null, 'Tuto2_Part4_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part4_ts_CADSEg') {
			delegate.deleteWsDir = false;
		})
			return true;
		
		/* Execution : end of part 3.4 */ 
		run.buildManager.createBundle(wsDir, "Model.Workspace.WebAppModel", "src-gen")
		if (run.runJavaTest(null, 'Tuto2_Part4_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part4_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = false;
		})
			return true;			

		return false;
	}

	public static void main(String[] args) {
		main(new TeamWorkRun())
	}
}
