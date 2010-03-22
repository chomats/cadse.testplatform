package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class BasicProperties extends CadseTest {
	
	public void addBundleToCompile() {
	}
	
	public boolean runTest() {
		return false;
	}
	
	public static void main(String[] args) {
		main(
		new BasicProperties_double(), 
		new BasicProperties_integer(),
		new BasicProperties_long())
	}
}