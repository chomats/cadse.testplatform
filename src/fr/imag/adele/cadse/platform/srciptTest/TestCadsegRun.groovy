/**
 *
 */
 package fr.imag.adele.cadse.platform.srciptTest

 import fr.imag.adele.cadse.platform.*



/**
 * @author chomats
 *
 */
public class TestCadsegRun extends CadseTest {

	public void addBundleToCompile() {
		//compile and deploy bundles
		run.addBundle(run.wsTest, "GraphicTests", "src")
		run.addBundle(run.wsTest, "TEST.CU.Workspace.Workspace", "src")
		run.addBundle(run.wsTest, "org.eclipse.swtbot.eclipse.junit4.headless", "src")
	}

	public boolean runTest() {
		//launch test
		return run.runJavaTest(null, 'TestCadsegRun', 'TEST.CU.Workspace.Workspace', 'test.fede.workspace.domain.internal.TestCadsegSuite') {
			delegate.cadseToExecute = 'Model.Workspace.Workspace'
		}

	}

	public static void main(String[] args) {
		main(new TestCadsegRun())
	}

}
