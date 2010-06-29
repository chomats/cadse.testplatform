package fr.imag.adele.cadse.platform;


public class BundleDescription {

	String	_name;

	public BundleDescription(String name) {
		super();
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BundleDescription) {
			return _name.equals(((BundleDescription) obj)._name);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}

	public boolean build(IBuildManager bm) {
		return false;
	}
}
