

public abstract class AbstractCadseTestPlatform implements ICadseTestPlatform {
	
	public String testPlatformPath;
	public String testEclipsePath;
	public AntBuilder ant;
	boolean debugFlag = false;
}