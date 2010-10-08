package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*

public class All_GroupTest3 extends CadseTest {
	
	public static void main(String[] args) {
		main(
		
		// ================ //
		//    Group Test3   //
		// ================ //
		
		new GroupTest3_Boolean(),
		new GroupTest3_Double(),
		new GroupTest3_Enum(),
		new GroupTest3_Integer(),
		new GroupTest3_Long(),
		new GroupTest3_String()
		)
	}
}
