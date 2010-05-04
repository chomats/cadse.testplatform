package fr.imag.adele.cadse.testplatform.tests_basictests
import fr.imag.adele.cadse.platform.gr.CadsegTestCase;

import fr.imag.adele.cadse.platform.*


public class Group_basic extends CadsegTestCase {
	
	public void init() {
		/* Libraries */
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Bundle to be created */
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.basictests", "src")
		
		/* TEST */
		CadseTestPart tp = addTestPart(null, 'Group_basic_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.group.Group_basic_ts_CADSEg')
		tp.deleteBundle("Model.Workspace.CADSE_group_basic")
		
		
		tp = addTestPart(null, 'Group_basic_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.group.Group_basic_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle("${run.testPlatformPath}/test-ws", "Model.Workspace.CADSE_group_basic", "src-gen")
		
	}
	
	public static void main(String[] args) {
		main(new Group_basic())
	}
}
