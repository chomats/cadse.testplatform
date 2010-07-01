package fr.imag.adele.cadse.platform;

import java.util.Map;

public class RunEclipseTestClass extends RunJavaTestClass {

	public RunEclipseTestClass(AbstractCadseTestPlatform run, CadseTestPart tp) {
		super(run, tp);
	}

	protected void initEnv(Map<String, String> env) {
		env.put("PLUGIN_PATH", testEclipsePath + "/plugins");
		if (display != null) {
			env.put(DISPLAY_CST, display);
		}
	}
	
	
	@Override
	protected void addExtraParam() {
		addParam("-application", application, "-data", wsDir);
		addParam("-consoleLog", "-console", "-clean");
		
		addJvmParam(
				"-Dorg.eclipse.swtbot.screenshots.dir="
						+ screenshots, 
				"-Dorg.eclipse.swtbot.keyboard.strategy=org.eclipse.swtbot.swt.finder.keyboard.SWTKeyboardStrategy",
				"-Dorg.eclipse.swtbot.search.timeout='30000'");
	}
	
	protected void constructCmdLine() {
		if (((String) System.getProperties().get("os.name")).startsWith("Windows")) {
			exec = converToPath(getTestValue("execEclipse", testName, testEclipsePath + "/eclipse.exe"));
		}
		else {
			exec = converToPath(getTestValue("execEclipse", testName, testEclipsePath + "/eclipse"));
		}
		processBuilder.command().add(exec);
		for (String p : params) {
			processBuilder.command().add(p);
		}
		processBuilder.command().add("-vmargs");
		for (String p : paramsJVM) {
			processBuilder.command().add(p);
		}
		
		
	}
}
