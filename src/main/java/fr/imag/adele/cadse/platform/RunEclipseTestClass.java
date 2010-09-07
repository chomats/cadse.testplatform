/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
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
