/**
 *
 */
package fr.imag.adele.cadse.platform;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.util.JavaEnvUtils;

public class RunJavaFelixTestClass extends RunJavaTestClass {

	protected String basedir;
	protected String className;
	private String cp;
	
	public RunJavaFelixTestClass(AbstractCadseTestPlatform run, CadseTestPart tp) {
		super(run, tp);
	}
	
	@Override
	protected void addExtraParam() {
		
	}
	
	protected void constructCmdLine() {
		exec = JavaEnvUtils.getJreExecutable("java");
		
		processBuilder.command().add(exec);
		for (String p : paramsJVM) {
			processBuilder.command().add(p);
		}
		processBuilder.command().add("-classpath");
		processBuilder.command().add(cp);
		processBuilder.command().add(className);
		for (String p : params) {
			processBuilder.command().add(p);
		}
	}

	

	protected String getJvmClass() {
		return className;
	}
	
	public void setBasedir(String basedir) {
		this.basedir = basedir;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public void setClassPath(FileSet fs) {
		StringBuilder sb = new StringBuilder();
		for (Iterator iterator = fs.iterator(); iterator.hasNext();) {
			String f = (String) iterator.next();
			sb.append(f);
			sb.append(":");
		}
		sb.setLength(sb.length()-1);
		this.cp = sb.toString();
	}
}
