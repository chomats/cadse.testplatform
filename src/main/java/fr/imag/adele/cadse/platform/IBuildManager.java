package fr.imag.adele.cadse.platform;

import java.util.List;

public interface IBuildManager {

	boolean deleteBundle(String b);

	void setBundlestoCompile(List<BundleDescription> bundlestoCompile);

	List<BundleDescription> getBundlestoCompile();

	boolean createBundle(String path, String name, String defaultSource);
	
	boolean deployMavenBundle();
	
	public void addBundle(BundleDescription b);
}
