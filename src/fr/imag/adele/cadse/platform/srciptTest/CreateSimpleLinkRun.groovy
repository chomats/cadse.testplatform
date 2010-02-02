/**
 *
 */
 package fr.imag.adele.cadse.platform.srciptTest

 import fr.imag.adele.cadse.platform.*

/**
 * @author chomats
 *
 */
public class CreateSimpleLinkRun extends CadseTest {

	public void addBundleToCompile() {
		 run.buildManager.createBundle(run.wsTest, "GraphicTests", "src")
		 run.buildManager.createBundle(run.wsTest, "TEST.CU.Workspace.Workspace", "src")
		 run.buildManager.createBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", , "src")
	}

	public boolean runTest() {
		return run.runJavaTest(null, 'CreateSimpleLinkRun',
				'TEST.CU.Workspace.Workspace','test.fede.workspace.domain.internal.CreateSimpleLinkSuite')
	}
	public static void main(String[] args) {
		main(new CreateSimpleLinkRun())
	}
}
