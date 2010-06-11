package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class All_Day extends CadseTest {
	
	public static void main(String[] args) {
		main(
		//new BasicProperties_Boolean(), // Night test only
		//new BasicProperties_Double(),  // Night test only
		//new BasicProperties_Enum(),    // Night test only
		//new BasicProperties_Integer(), // Night test only
		//new BasicProperties_Long(),    // Night test only
		//new BasicProperties_String(),  // Night test only
		//new GroupTest1_Boolean(),      // Night test only
		//new GroupTest1_Double(),       // Night test only
		//new GroupTest1_Enum(),         // Night test only
		//new GroupTest1_Integer(),      // Night test only
		//new GroupTest1_Long(),         // Night test only
		//new GroupTest1_String(),       // Night test only
		new CheckPages(),
		new DefaultInstanceName(),
		new HasContent(),
		new IsAbstract(),
		new Link(),
		new Root())
	}
}
