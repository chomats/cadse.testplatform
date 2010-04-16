package fr.imag.adele.cadse.testplatform.tests_tutos

import fr.imag.adele.cadse.platform.*


public class TutosRun extends CadseTest {
	
	public void init() {
		/* Libraries */
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Test to be executed */
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.tutos", "src")
		
		CadseTestPart tp;
		
		/* ============ */
		/*    TUTO 1    */
		/* ============ */
		
		/* CADSEg : from the beginning up to part 5.5 */
		tp = addTestPart(null, 'Tuto1_Part1_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part1_ts_CADSEg')
		tp.deleteBundle 'Model.Workspace.WebAppModel';
		
		/* Execution : part 5.6 */
		tp = addTestPart(null, 'Tuto1_Part1_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part1_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle (null, "Model.Workspace.WebAppModel", "src-gen")
		
		/* CADSEg : from part 6 to part 6.3 */
		tp = addTestPart(null, 'Tuto1_Part2_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part2_ts_CADSEg') { delegate.deleteWsDir = false; }
		tp.deleteBundle 'Model.Workspace.WebAppModel';
		
		/* Execution : end of part 6.3 */
		tp = addTestPart(null, 'Tuto1_Part2_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part2_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = false;
		}
		tp.addBundle (null, "Model.Workspace.WebAppModel", "src-gen")
		
		/* CADSEg : part 6.4 */
		tp = addTestPart(null, 'Tuto1_Part3_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part3_ts_CADSEg') { delegate.deleteWsDir = false; }
		tp.deleteBundle 'Model.Workspace.WebAppModel';
		
		/* Execution : end of part 6.4 */ 
		tp = addTestPart(null, 'Tuto1_Part3_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part3_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = false;
		}
		tp.addBundle (null, "Model.Workspace.WebAppModel", "src-gen")
		
		/* CADSEg : part 6.5 */
		tp = addTestPart(null, 'Tuto1_Part4_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part4_ts_CADSEg') { delegate.deleteWsDir = false; }
		tp.deleteBundle 'Model.Workspace.WebAppModel';
		
		
		/* Execution : end of part 6.5 */ 
		tp = addTestPart(null, 'Tuto1_Part4_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto1.Tuto1Part4_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = false;
		}
		tp.addBundle (null, "Model.Workspace.WebAppModel", "src-gen")
		
		
		
		/* ============ */
		/*    TUTO 2    */
		/* ============ */
		
		/* CADSEg : part 3.1 */
		tp = addTestPart(null, 'Tuto2_Part1_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part1_ts_CADSEg') { delegate.deleteWsDir = false; }
		tp.deleteBundle 'Model.Workspace.WebAppModel';
		
		/* Execution : part 3.1 */
		tp = addTestPart(null, 'Tuto2_Part1_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part1_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = true;
		}
		tp.addBundle (null, "Model.Workspace.WebAppModel", "src-gen")
		
		/* CADSEg : part 3.2 */
		tp = addTestPart(null, 'Tuto2_Part2_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part2_ts_CADSEg') { delegate.deleteWsDir = false; }
		tp.deleteBundle 'Model.Workspace.WebAppModel';
		
		/* Execution : end of part 3.2 */ 
		tp = addTestPart(null, 'Tuto2_Part2_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part2_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = false;
		}
		tp.addBundle (null, "Model.Workspace.WebAppModel", "src-gen")
		
		/* CADSEg : part 3.3 */ 
		tp = addTestPart(null, 'Tuto2_Part3_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part3_ts_CADSEg') { delegate.deleteWsDir = false; }
		tp.deleteBundle 'Model.Workspace.WebAppModel';
		
		/* Execution : end of part 3.3  
		 tp = addTestPart(null, 'Tuto2_Part3_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part3_ts_execution'){
		 delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
		 delegate.deleteWsDir = false;
		 }
		 tp.addBundle (null, "Model.Workspace.WebAppModel", "src-gen")*/
		
		/* CADSEg : part 3.4*/ 
		tp = addTestPart(null, 'Tuto2_Part4_CADSEg','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part4_ts_CADSEg') { delegate.deleteWsDir = false; }
		tp.deleteBundle 'Model.Workspace.WebAppModel';
		
		
		/* Execution : end of part 3.4 */ 
		tp = addTestPart(null, 'Tuto2_Part4_Execution','fr.imag.adele.cadse.test.tutos','fr.imag.adele.cadse.test.tutos.tuto2.Tuto2Part4_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			delegate.deleteWsDir = false;
		}
		tp.addBundle (null, "Model.Workspace.WebAppModel", "src-gen")
	}
	
	public static void main(String[] args) {
		main(new TutosRun())
	}
}
