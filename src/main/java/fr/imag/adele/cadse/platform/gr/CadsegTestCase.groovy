package fr.imag.adele.cadse.platform.gr

import fr.imag.adele.cadse.platform.CadseTest;
import fr.imag.adele.cadse.platform.CadseTestPart;
import groovy.lang.Closure;

public class CadsegTestCase extends CadseTest {

	/** true if failed */
	public CadseTestPart addTestPart(String testProperties, String testName, String testPluginName, String classname, Closure c) {
		CadsegCadseTestPart tp = new CadsegCadseTestPart(testProperties, testName, testPluginName, classname, c);
		parts.add(tp);
		tp.setCadseTestPlatform(run);
		return tp;
	}
}
