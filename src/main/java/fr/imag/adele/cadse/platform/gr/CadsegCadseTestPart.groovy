package fr.imag.adele.cadse.platform.gr;

import fr.imag.adele.cadse.platform.AbstractCadseTestPlatform;
import fr.imag.adele.cadse.platform.CadseTestPart;
import fr.imag.adele.cadse.platform.RunJavaTestClass;
import groovy.lang.Closure;

public class CadsegCadseTestPart extends CadseTestPart {

	public CadsegCadseTestPart(String testProperties, String testName,
			String testPluginName, String classname, Closure c) {
		super(testProperties, testName, testPluginName, classname, c);
	}
	
	
	@Override
	public void report(RunJavaTestClass runJavaTestClass,
			AbstractCadseTestPlatform run2, boolean failed) {
		boolean zipws = runJavaTestClass.toBoolean(runJavaTestClass.getTestValue('zipws', testName, 'true'));
		
		try {
			if (zipws) {
				Thread.sleep(20);
				String wsDir = runJavaTestClass.wsDir;
				String testReport = runJavaTestClass.testReport
				AntBuilder ant = run2.ant;
				ant.delete(file:"${testReport}/${testName}.zip")
				ant.zip(destfile:"${testReport}/${testName}.zip", basedir:wsDir, excludes:".metadata/**")
			}
		} catch(Throwable e) {
			System.out.println("Cannot create a zip file");
			e.printStackTrace();
		}
	}

}
