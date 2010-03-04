package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class CannotBeUndefined extends CadseTest {

	public void init() {
		/* Libraries */
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", "src")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Bundle to be created */
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.basictests", "src")
	
		CadseTestPart tp;
		
		/* CannotBeUndefined - CADSEg */
		tp = addTestPart(null, 'CannotBeUndefined_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.cannotbeundefined.CannotBeUndefined_ts_CADSEg') {
			wsDir = delegate.wsDir
		}
		tp.deleteBundle 'Model.Workspace.CADSE_CannotBeUndefined'
		
		/* CannotBeUndefined - Execution */
		tp = addTestPart(null, 'CannotBeUndefined_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.cannotbeundefined.CannotBeUndefined_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle(null, "Model.Workspace.CADSE_CannotBeUndefined", "src-gen")
		
	}

	public static void main(String[] args) {
		main(new CannotBeUndefined())
	}
}