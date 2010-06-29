package fr.imag.adele.cadse.platform;

public class MavenBundleDescription extends BundleDescription {

	private String vId;
	private String gId;
	private String aId;

	public MavenBundleDescription(String name, String gId, String aId, String vId) {
		super(name);
		this.gId = gId;
		this.aId = aId;
		this.vId = vId;
	}

	@Override
	public boolean build(IBuildManager bm) {
		return bm.deployMavenBundle(this);
	}

	public String getvId() {
		return vId;
	}

	public String getgId() {
		return gId;
	}

	public String getaId() {
		return aId;
	}
	
	
}
