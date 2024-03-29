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
import org.eclipse.jdt.internal.junit.runner.MessageIds;

public class AllPDETestListener implements ITestRunListener2 {

	/**
	 * Default constructor
	 */
	public AllPDETestListener() {
	}

	/**
	 * A test run has started.
	 * 
	 * @param testCount
	 *            the number of individual tests that will be run
	 */
	public void testRunStarted(int testCount) {
	}

	/**
	 * A test run has ended.
	 * 
	 * @param elapsedTime
	 *            the total elapsed time of the test run
	 */
	public void testRunEnded(long elapsedTime) {
	}

	/**
	 * A test run has been stopped prematurely.
	 * 
	 * @param elapsedTime
	 *            the time elapsed before the test run was stopped
	 */
	public void testRunStopped(long elapsedTime) {
	}

	/**
	 * The VM instance performing the tests has terminated.
	 */
	public void testRunTerminated() {
	}

	/**
	 * An individual test has started.
	 * 
	 * @param testId
	 *            a unique Id identifying the test
	 * @param testName
	 *            the name of the test that started
	 */
	public void testStarted(String testId, String testName) {
	}

	/**
	 * An individual test has ended.
	 * 
	 * @param testId
	 *            a unique Id identifying the test
	 * @param testName
	 *            the name of the test that ended
	 */
	public void testEnded(String testId, String testName) {
	}

	/**
	 * Information about a member of the test suite that is about to be run. The format of the string is:
	 * 
	 * <pre>
	 *  testId,testName,isSuite,testcount
	 * 
	 *  testId: a unique id for the test
	 *  testName: the name of the test
	 *  isSuite: true or false depending on whether the test is a suite
	 *  testCount: an integer indicating the number of tests
	 * 
	 *  Example: &quot;324968,testPass(junit.tests.MyTest),false,1&quot;
	 * </pre>
	 * 
	 * @param description
	 *            a string describing a tree entry
	 * @see MessageIds#TEST_TREE
	 */
	public void testTreeEntry(String description) {
	}

	/**
	 * An individual test has failed with a stack trace.
	 * 
	 * @param status
	 *            the outcome of the test; one of {@link #STATUS_ERROR STATUS_ERROR} or {@link #STATUS_FAILURE
	 *            STATUS_FAILURE}
	 * @param testId
	 *            a unique Id identifying the test
	 * @param testName
	 *            the name of the test that failed
	 * @param trace
	 *            the stack trace
	 * @param expected
	 *            the expected value
	 * @param actual
	 *            the actual value
	 */
	public void testFailed(int status, String testId, String testName, String trace, String expected, String actual) {
	}

	/**
	 * An individual test has been rerun.
	 * 
	 * @param testId
	 *            a unique Id identifying the test
	 * @param testClass
	 *            the name of the test class that was rerun
	 * @param testName
	 *            the name of the test that was rerun
	 * @param status
	 *            the outcome of the test that was rerun; one of {@link #STATUS_OK}, {@link #STATUS_ERROR}, or
	 *            {@link #STATUS_FAILURE}
	 * @param trace
	 *            the stack trace in the case of abnormal termination, or the empty string if none
	 * @param expected
	 *            the expected value in case of abnormal termination, or the empty string if none
	 * @param actual
	 *            the actual value in case of abnormal termination, or the empty string if none
	 */
	public void testReran(String testId, String testClass, String testName, int status, String trace, String expected,
			String actual) {
	}

}
