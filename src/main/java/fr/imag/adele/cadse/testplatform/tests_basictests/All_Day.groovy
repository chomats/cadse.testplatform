package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*


public class All_Day extends CadseTest {
	
	// IMPORTANT!!! READ ME FIRST!!!
	//
	// Some tests are commented in this file. They are only launched during night, and not during day... 
	
	public static void main(String[] args) {
		main(
		
		// ================ //
		//    First tests   //
		// ================ //
		
		new CheckPages(),
		new DefaultInstanceName(),
		new HasContent(),
		new IsAbstract(),
		new Link(),
		new Root(),
		
		// ================ //
		//       Misc       //
		// ================ //
		
		new Bug1(),
		new Bug2()
		
		// ================ //
		// Basic Properties //
		// ================ //
		
		//new BasicProperties_Boolean(),
		//new BasicProperties_Double(),
		//new BasicProperties_Enum(),
		//new BasicProperties_Integer(),
		//new BasicProperties_Long(),
		//new BasicProperties_String(),
		
		// ================ //
		//    Group Test1   //
		// ================ //
		
		//new GroupTest1_Boolean(),
		//new GroupTest1_Double(),
		//new GroupTest1_Enum(),
		//new GroupTest1_Integer(),
		//new GroupTest1_Long(),
		//new GroupTest1_String(),
		
		// ================ //
		//    Group Test2   //
		// ================ //
		
		//new GroupTest2_Boolean(),
		//new GroupTest2_Double(),
		//new GroupTest2_Enum(),
		//new GroupTest2_Integer(),
		//new GroupTest2_Long(),
		//new GroupTest2_String(),
		
		// ================ //
		//    Group Test3   //
		// ================ //
		
		//new GroupTest3_Boolean(),
		//new GroupTest3_Double(),
		//new GroupTest3_Enum(),
		//new GroupTest3_Integer(),
		//new GroupTest3_Long(),
		//new GroupTest3_String(),
		)
	}
}
