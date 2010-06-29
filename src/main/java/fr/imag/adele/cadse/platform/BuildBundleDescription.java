package fr.imag.adele.cadse.platform;

import org.eclipse.core.internal.events.BuildManager;

public class BuildBundleDescription extends BundleDescription {

	String	_name;
	String	_path;
	String	_defaultSource;

	public BuildBundleDescription(String path, String name, String defaultSource) {
		super(name);
		_path = path;
		_defaultSource = defaultSource;
	}

	public String getPath() {
		return _path;
	}

	public void setPath(String path) {
		_path = path;
	}

	public String getDefaultSource() {
		return _defaultSource;
	}

	public void setDefaultSource(String source) {
		_defaultSource = source;
	}
	
	@Override
	public boolean build(IBuildManager buildManager) {
		return buildManager.createBundle(getPath(), getName(), getDefaultSource());
	}

}
