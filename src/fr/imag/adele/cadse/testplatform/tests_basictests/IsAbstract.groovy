package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class IsAbstract extends CadseTest {

	public void init() {
		/* Libraries */
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", "src")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Bundle to be created */
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.basictests", "src")
	
		/* IsAbstract - CADSEg */
		CadseTestPart tp;
		tp = addTestPart(null, 'IsAbstract_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.isabstract.IsAbstract_ts_CADSEg')
		tp.deleteBundle 'Model.Workspace.CADSE_IsAbstract'
		
		/* IsAbstract - Execution */
		tp = addTestPart(null, 'IsAbstract_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.isabstract.IsAbstract_ts_execution'){
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle(null, "Model.Workspace.CADSE_IsAbstract", "src-gen")
	}

	public static void main(String[] args) {
		main(new IsAbstract())
	}
}
