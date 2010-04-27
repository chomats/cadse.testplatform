package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class All_Day extends CadseTest {
	
	public static void main(String[] args) {
		main(
		//new BasicProperties_double(), // Night test only
		//new BasicProperties_integer(),// Night test only
		//new BasicProperties_long(), // Night test only
		//new BasicProperties_string(), // Night test only
		new CheckPages(),
		new DefaultInstanceName(),
		new HasContent(),
		new IsAbstract(),
		new Link(),
		new Root())
	}
}
