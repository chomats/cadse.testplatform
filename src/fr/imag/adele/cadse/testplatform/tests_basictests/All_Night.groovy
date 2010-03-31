package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class All_Night extends CadseTest {
	
	public static void main(String[] args) {
		main(
		new BasicProperties_double(), 
		new BasicProperties_integer(),
		new BasicProperties_long(),
		new BasicProperties_string(),
		new CheckPages(),
		new DefaultInstanceName(),
		new HasContent(),
		new IsAbstract(),
		new Link(),
		new Root())
	}
}
