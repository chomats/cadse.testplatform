package fr.imag.adele.cadse.testplatform.tests_basictests
import fr.imag.adele.cadse.platform.gr.CadsegTestCase 

import fr.imag.adele.cadse.platform.*


public class BasicProperties_integer extends CadsegTestCase {
	
	public void init() {
		/* Libraries */
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", "src")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Bundle to be created */
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.basictests", "src")
		
		/* TEST */
		CadseTestPart tp = addTestPart(null, 'BasicProperties_integer_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_integer_ts_CADSEg')
		tp.deleteBundle("Model.Workspace.CADSE_BasicProperties_integer")
		
		
		tp = addTestPart(null, 'BasicProperties_integer_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_integer_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle("${run.testPlatformPath}/test-ws", "Model.Workspace.CADSE_BasicProperties_integer", "src-gen")
		
	}
	
	public static void main(String[] args) {
		main(new BasicProperties_integer())
	}
}
