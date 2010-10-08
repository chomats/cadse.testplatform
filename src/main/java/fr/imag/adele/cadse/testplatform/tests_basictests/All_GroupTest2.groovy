package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*

public class All_GroupTest2 extends CadseTest {
	
	public static void main(String[] args) {
		main(
		
		// ================ //
		//    Group Test2   //
		// ================ //
		
		new GroupTest2_Boolean(),
		new GroupTest2_Double(),
		new GroupTest2_Enum(),
		new GroupTest2_Integer(),
		new GroupTest2_Long(),
		new GroupTest2_String()
		)
	}
}
