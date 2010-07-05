package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.CadseTest;

import fr.imag.adele.cadse.platform.*


public class Metrics extends CadseTest {
	
	public void init() {
		run.addBundelInM2("fr.imag.adele.cadse.test.basictests", "fr.imag.adele.cadse", "fr.imag.adele.cadse.test.basictests", "2.3.0-SNAPSHOT")
		
		CadseTestPart tp;
		tp = addTestPart(null, 'Metrics_Integer','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.metrics.Metrics_Integer');
		tp.deleteBundle 'Model.Workspace.CADSEMetricsInteger'
	}
	
	public static void main(String[] args) {
		main(new Metrics())
	}
}
