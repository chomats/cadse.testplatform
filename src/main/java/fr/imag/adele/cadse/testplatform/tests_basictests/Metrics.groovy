import fr.imag.adele.cadse.platform.CadseTest;

import fr.imag.adele.cadse.platform.*


public class Metrics extends CadseTest {
	
	public void init() {
		/* Libraries */
		run.addBundle(run.wsTest, 'fr.imag.adele.graphictests', 'src/main/java')
		run.addBundle(run.wsTest, 'fr.imag.adele.graphictests.cadse', 'src/main/java')
		run.addBundle(run.wsTest, 'org.eclipse.swtbot.swt.finder.keyboard.fr', 'src')
		
		/* Bundle to be created */
		run.addBundle(run.wsTest, 'fr.imag.adele.cadse.test.basictests', 'src')
		
		CadseTestPart tp;
		tp = addTestPart(null, 'Metrics_Integer','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.metrics.Metrics_Integer');
		tp.deleteBundle 'Model.Workspace.CADSEMetricsInteger'
	}
	
	public static void main(String[] args) {
		main(new Metrics())
	}
}
