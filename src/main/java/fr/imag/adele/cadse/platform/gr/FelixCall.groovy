

boolean callMaven(String felixHome, String goals, String options) {
		String maxMem = ant.project.properties.get('felix.mx')
		if (maxMem == null)
			maxMem = "1024m"
		String felixClassName = ant.project.properties.get('felix.mainClass')
		if (felixClassName == null)
			felixClassName = "xxxx";
		try {
			ant.java(classname: felixClassName, fork:true,
					dir:felixHome, failonerror:true) {
						jvmarg( value:"-Xmx${maxMem}")
						classpath() {
							fileset(dir:"${mavenHome}/lib") { include( name:'*.jar') }
						}
						arg( line:"--batch-mode ${options} ${goals}")
					}
			return false;
		} catch (Throwable e) {
			println 'Error : ';
			e.printStackTrace();
			return true;
		}
	}