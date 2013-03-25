
package org.knoxious.ling_geo.bootstrap;

import org.knoxious.ling_geo.domain.Field2D;

public class Bootstrap {

	private String configFileName = "lg.xml";
	private boolean verbose = false;
	private Field2D field;

	public static void main(String[] args)
	{
		try {

			// wake up and read the config file from the classpath
			Bootstrap bstrap = new Bootstrap(args);
			//construct the game field
		} catch (Exception ex) {
			ex.toString();
		}
	}

	public Bootstrap(String[] args) throws Exception {
		readCL(args);
	}

	public void start() throws Exception {
		BootstrapParser bp = new BootstrapParser(configFileName);
		field = bp.parse();
	}

	private void readCL(String[] args)
	{
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-f") ) {
				configFileName = args[++i];
			} else if (args[i].equals("-v")) {
				verbose = true;
			}
		}
		System.out.println("Bootstrap starting with:");
		System.out.println("Config file = " + configFileName);
		System.out.println("Verbose = "+ verbose);
	}
};
