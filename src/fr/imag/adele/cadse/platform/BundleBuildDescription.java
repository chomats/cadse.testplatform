package fr.imag.adele.cadse.platform;

public class BundleBuildDescription {

	String	_name;
	String	_path;
	String	_defaultSource;

	public BundleBuildDescription(String path, String name, String defaultSource) {
		super();
		_path = path;
		_name = name;
		_defaultSource = defaultSource;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
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
	public boolean equals(Object obj) {
		if (obj instanceof BundleBuildDescription) {
			return _name.equals(((BundleBuildDescription) obj)._name);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}

}
