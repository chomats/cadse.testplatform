package fr.imag.adele.cadse.platform.gr

import java.io.File;

import fr.imag.adele.cadse.platform.AbstractCadseTestPlatform;
import fr.imag.adele.cadse.platform.RunJavaTestClass;
import groovy.util.AntBuilder;

public class CadseTestPlatform extends AbstractCadseTestPlatform {
	
	public CadseTestPlatform() {
		ant =  new AntBuilder();
		// load properties ...
		File f = null;
		f = new File(ant.project.properties.basedir, "cadse_test.properties");
		if (f.exists()) {
			ant.property(file:f);
		}
		f = new File(ant.project.properties.get("user.home"), ".cadse_test.properties")
		if (f.exists()) {
			ant.property(file:f);
		}
		f = new File(ant.project.properties.basedir, "test.properties");
		if (f.exists()) {
			ant.property(file:f);
		}
		
		testPlatformPath = ant.project.properties.testPlatformPath;
		testReport = ant.project.properties.'testPlatformPath.report';
		testEclipsePath  = ant.project.properties.'testPlatformPath.eclipse';
		
		collapseSuccess = ant.project.properties.collapseSuccess == null ? true : Boolean.parseBoolean( ant.project.properties.collapseSuccess);
		wsTest = ant.project.properties.wsTest
		
		System.out.println("********************************************************");
		System.out.println("* base dir : ${ant.project.properties.basedir}");
		System.out.println("* test Platform Path : ${testPlatformPath}");
		System.out.println("* test report Path   : ${testReport}");
		System.out.println("* test eclipse Path  : ${testEclipsePath}");
		System.out.println("* wsTest             : ${wsTest}");
		System.out.println("********************************************************");
		System.out.println("");
		
		ant.delete(dir : testReport);
		buildManager = new BuildManager(ant, testPlatformPath, testEclipsePath)
		
	}
	
	public void loadPropertyFile(String testProperties) {
		ant.property(file:testProperties);
	}
	
	public void initRunJavaTest(RunJavaTestClass runJavaTestClass) {
		if (runJavaTestClass.deleteWsDir)
			ant.delete(dir : runJavaTestClass.wsDir)
		ant.mkdir(dir: runJavaTestClass.testReport)
		ant.mkdir(dir: runJavaTestClass.screenshots)

		ant.delete(file: runJavaTestClass.testOutput)
		if (runJavaTestClass.outputFile == "")
			runJavaTestClass.outputFile = null;
		if (runJavaTestClass.outputFile != null)
			ant.delete(file: runJavaTestClass.outputFile)

		ant.echo(message:"Running ${runJavaTestClass.testName}. Result file: ${runJavaTestClass.testOutput}");
	}
	
	protected void finishTest() {
		ant.'cobertura-merge'(datafile:"${testReport}/cobertura.ser") {
	    	fileset( dir:"${testReport}") {
	    		include(name:'*.cobertura.ser')
	    		exclude(name:'cobertura.ser')
	    	}
		}
		ant.delete() {
			fileset( dir:"${testReport}") {
	    		include(name:'*.cobertura.ser')
	    		exclude(name:'cobertura.ser')
	    	}
		}
	}
}
