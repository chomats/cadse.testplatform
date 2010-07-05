package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class CheckPages extends CadseTest {

	public void init() {
		run.addBundelInM2("fr.imag.adele.cadse.test.basictests", "fr.imag.adele.cadse", "fr.imag.adele.cadse.test.basictests", "2.3.0-SNAPSHOT")
		
		CadseTestPart tp;
		tp = addTestPart(null, 'CheckPages_CADSEg','fr.imag.adele.cadse.test.basictests','fr.imag.adele.cadse.test.basictests.checkpages.CheckPages_ts_CADSEg');
		tp.deleteBundle 'Model.Workspace.CADSE_CheckPages'
	}

	public static void main(String[] args) {
		main(new CheckPages())
	}
}
