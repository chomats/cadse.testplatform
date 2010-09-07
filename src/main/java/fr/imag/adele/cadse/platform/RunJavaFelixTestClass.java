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

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.resources.FileResource;
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
		for (String c : processBuilder.command()) {
			System.out.print(c);
			System.out.print(" ");
		}
		System.out.println();
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
			FileResource f = (FileResource) iterator.next();
			sb.append(f.getFile().getPath());
			sb.append(":");
		}
		sb.setLength(sb.length()-1);
		this.cp = sb.toString();
	}
}
