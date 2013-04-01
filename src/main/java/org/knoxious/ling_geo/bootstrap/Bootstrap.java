
package org.knoxious.ling_geo.bootstrap;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogManager;


import org.knoxious.ling_geo.domain.Field2D;

public class Bootstrap {

	private String configFileName = "lg.xml";
	private boolean verbose = false;
	private Field2D field;
	private Logger log;

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
		try {
			LogManager mngr = LogManager.getLogManager();
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			InputStream is = cl.getResourceAsStream("lg-logging.properties");
			mngr.readConfiguration(is);
			log = Logger.getLogger(this.getClass().getName());
			log.setUseParentHandlers(false);
			log.info("Logging initialized");
			log.info("Debug level? " + log.getLevel().getName());
		} catch (IOException iox) {
			// TBD: place holder
			throw iox;
		}
			readCL(args);
	}

	public void start() throws Exception {
			BootstrapParser bp = new BootstrapParser(configFileName);
			field = bp.parse();
	}

	private void readCL(String[] args)
	{
		log.entering(this.getClass().getName(), "readCL");
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-f") ) {
				configFileName = args[++i];
			} else if (args[i].equals("-v")) {
				verbose = true;
			}
		}
		log.info("Bootstrap starting with: Config file = " + configFileName +
			" Verbose = "+ verbose
			);
		log.exiting(this.getClass().getName(), "readCL");
	}	
};
