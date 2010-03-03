package fr.imag.adele.cadse.testplatform.tests_basictests

import fr.imag.adele.cadse.platform.*
import java.util.ArrayList


public class All extends CadseTest {

	public void addBundleToCompile() {
	}
	
	public boolean runTest() {
		return false;
	}

	public static void main(String[] args) {
		main(	new BasicProperties(), 
				new CannotBeUndefined(),
				new CheckPages(),
				new DefaultInstanceName(),
				new HasContent(),
				new IsAbstract(),
				new Link(),
				new Root())
	}
}
