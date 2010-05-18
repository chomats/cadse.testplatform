package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class All_Night extends CadseTest {
	
	public static void main(String[] args) {
		main(
		new BasicProperties_Boolean(),
		new BasicProperties_Double(),
		new BasicProperties_Enum(),
		new BasicProperties_Integer(),
		new BasicProperties_Long(),
		new BasicProperties_String(),
		new CheckPages(),
		new DefaultInstanceName(),
		new HasContent(),
		new IsAbstract(),
		new Link(),
		new Root())
	}
}
