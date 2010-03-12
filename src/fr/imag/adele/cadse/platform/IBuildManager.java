package fr.imag.adele.cadse.platform;

import java.util.List;

public interface IBuildManager {

	boolean deleteBundle(String b);

	void setBundlestoCompile(List<BundleBuildDescription> bundlestoCompile);

	List<BundleBuildDescription> getBundlestoCompile();

	boolean createBundle(String path, String name, String defaultSource);
	public void addBundle(BundleBuildDescription b);
}
