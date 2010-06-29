package fr.imag.adele.cadse.platform.gr

import java.io.File;

import fr.imag.adele.cadse.platform.CadseTestPart;
import fr.imag.adele.cadse.platform.*;
import groovy.util.AntBuilder;
import org.apache.tools.ant.types.FileSet;

public class FelixTestPart extends CadseTestPart {
	
	public FelixTestPart(String testProperties, String testName,
			String testPluginName, String classname, Closure c) {
		super(testProperties, testName, testPluginName, classname, c);
	}
	
	public RunJavaTestClass createRunner() {
		String felixHome = ant.project.properties.get('felix.home');
		if (felixHome == null)
			throw new RuntimeException("Cannot found felix home");
		
		String maxMem = ant.project.properties.get('felix.mx')
		if (maxMem == null)
			maxMem = "1024m"
		String felixClassName = ant.project.properties.get('felix.mainClass')
		if (felixClassName == null)
			throw new RuntimeException("Cannot found felix home");
		
		RunJavaFelixTestClass rjftc = new RunJavaFelixTestClass(run, this);
		rjftc.setClassName(felixClassName);
		rjftc.setBasedir(felixHome);
		FileSet fs = ant.fileset(dir:"${mavenHome}/lib") { include( name:'*.jar') }
		rjftc.setClassPath(fs)
		ant.delete(dir:"$felixHome/$testName");
		
		rjftc.addParam("--bundles-dir", "$felixHome/dropins");
		rjftc.addParam("--bundles-dir", "$felixHome/bundles");
		rjftc.addParam("--cache-dir" , "$felixHome/$testName");
		return rjftc;
	}
}
