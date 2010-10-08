package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*

public class All_GroupTest1 extends CadseTest {
	
	public static void main(String[] args) {
		main(
		
		// ================ //
		//    Group Test1   //
		// ================ //
		
		new GroupTest1_Boolean(),
		new GroupTest1_Double(),
		new GroupTest1_Enum(),
		new GroupTest1_Integer(),
		new GroupTest1_Long(),
		new GroupTest1_String()
		)
	}
}
