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

import org.eclipse.jdt.internal.junit.model.ITestRunListener2;
import org.eclipse.jdt.internal.junit.model.RemoteTestRunnerClient;

public class CadseTestCollector {
	AllPDETestListener		alltest	= new AllPDETestListener();
	public PDETestListener	pdeTestListener;

	public void runTestRunnerClient(CadseTestPart p, String classname, int portNumber, String testOutput) {
		try {
			pdeTestListener = new PDETestListener(classname, p);
			pdeTestListener.setOutputFile(testOutput);
			ITestRunListener2[] listeners = new ITestRunListener2[2];
			listeners[0] = pdeTestListener;
			listeners[1] = alltest;
			new RemoteTestRunnerClient().startListening(listeners, portNumber);
			System.out.println("Listening on port " + portNumber + " for test suite " + p.testName + " results ...");
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}

	public boolean failed() {
		return pdeTestListener.failed();
	}
	
	public boolean isTerminated() {
		return pdeTestListener.isTestRunEnded();
	}
}
