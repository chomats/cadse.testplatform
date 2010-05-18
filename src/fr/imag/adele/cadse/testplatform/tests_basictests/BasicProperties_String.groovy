package fr.imag.adele.cadse.testplatform.tests_basictests
import fr.imag.adele.cadse.platform.gr.CadsegTestCase 

import fr.imag.adele.cadse.platform.*


public class BasicProperties_String extends CadsegTestCase {
	
	public void init() {
		/* Libraries */
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests", "src/main/java")
		run.addBundle(run.wsTest, "fr.imag.adele.graphictests.cadse", "src/main/java")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.swt.finder.keyboard.fr", "src")
		
		/* Bundle to be created */
		run.addBundle(run.wsTest, "fr.imag.adele.cadse.test.basictests", "src")
		
		/* TEST */
		CadseTestPart tp = addTestPart(null, 'BasicProperties_String_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_String_ts_CADSEg')
		tp.deleteBundle("Model.Workspace.CADSE_BasicProperties_String")
		
		
		tp = addTestPart(null, 'BasicProperties_String_Execution','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.basicproperties.BasicProperties_String_ts_execution') {
			delegate.wsDir = "${run.testPlatformPath}/test-ws-2"
			run.ant.mkdir(dir: delegate.wsDir)
		}
		tp.addBundle("${run.testPlatformPath}/test-ws", "Model.Workspace.CADSE_BasicProperties_String", "src-gen")
		
	}
	
	public static void main(String[] args) {
		main(new BasicProperties_String())
	}
}
