/**
 *
 */
package fr.imag.adele.cadse.platform;

import java.io.File;
import java.util.List;

import org.apache.tools.ant.util.JavaEnvUtils;

public class RunJavaFelixTestClass extends RunJavaTestClass {

	protected String basedir;
	protected String className;
	private List<File> cp;
	
	public RunJavaFelixTestClass(AbstractCadseTestPlatform run, CadseTestPart tp,
			String className, String basedir, List<File> cp) {
		super(run, tp);
		this.className = className;
		this.basedir = basedir;
		this.cp =cp;
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
		processBuilder.command().add(getClassPath());
		processBuilder.command().add(className);
		for (String p : params) {
			processBuilder.command().add(p);
		}
	}

	private String getClassPath() {
		StringBuilder sb = new StringBuilder();
		for (File f : cp) {
			sb.append(f.getAbsolutePath());
			sb.append(":");
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}

	protected String getJvmClass() {
		return className;
	}
	
}
