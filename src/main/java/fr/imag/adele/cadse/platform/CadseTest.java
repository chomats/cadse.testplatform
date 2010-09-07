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

import java.util.ArrayList;
import java.util.List;

import groovy.lang.Closure;


public abstract class CadseTest {
	public AbstractCadseTestPlatform	run;
	public List<CadseTestPart> 		parts = new ArrayList<CadseTestPart>();
	public int status = -1 ; // 0 SUCESS, 1: FAILED; -1 NOT EXECUTED, -2 RUNNING
	public long timestamp;
	
	public void setCadseTestPlatform(AbstractCadseTestPlatform lRun) {
		run = lRun;
	}
	
	/** true if failed */
	public CadseTestPart addTestPart(String testProperties, String testName, String testPluginName, String classname) {
		return addTestPart(testProperties, testName, testPluginName, classname, null);
	}
	
	/** true if failed */
	public CadseTestPart addTestPart(String testProperties, String testName, String testPluginName, String classname, Closure c) {
		CadseTestPart tp = new CadseTestPart(testProperties, testName, testPluginName, classname, c);
		parts.add(tp);
		tp.setCadseTestPlatform(run);
		return tp;
	}
	
	public CadseTestPart addFelixTestPart(String testProperties, String testName, String testPluginName, String classname) {
		return addFelixTestPart(testProperties, testName, testPluginName, classname, null);
	}
	
	public CadseTestPart addFelixTestPart(String testProperties, String testName, String testPluginName, String classname, Closure c) {
		return null;
	}
	
	public void init() {
	}
	
	/** true if failed */
	public boolean runTest() {
		long startTime = System.currentTimeMillis();
		status = -2;
		boolean failed = false;
		for (CadseTestPart tp : parts) {
			if (run.run(this, tp)) {
				failed = true;
				break;
			}
		}
		timestamp = System.currentTimeMillis()-startTime;
		status = failed ? 1 : 0;
		return failed;
	}
	
	public static void main(CadseTest ...cadseTest) {
		int errorCode = 0;
		try {
			final AbstractCadseTestPlatform platform = (AbstractCadseTestPlatform) Class.forName("fr.imag.adele.cadse.platform.gr.CadseTestPlatform").newInstance();
			boolean failed = platform.runTests(cadseTest);
			if (failed) {
				errorCode = 1;
			}				
		} catch (Throwable e) {
			e.printStackTrace();
			errorCode = 2;
		} 
		if (errorCode != 0)
			System.exit(errorCode);
	}
	
	public String getName() {
		return getClass().getSimpleName();
	}
}
