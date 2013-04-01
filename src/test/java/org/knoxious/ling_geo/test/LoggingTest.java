package org.knoxious.ling_geo.test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Assert;

public class LoggingTest 
	extends TestCase 
{

	public LoggingTest(String name) {
		super(name);
	}

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( LoggingTest.class );
    }

	 public void testLogging()
	 {
		 System.out.println("Test Logging");
		 Logger log = Logger.getLogger(this.getClass().getName());
		 log.info("Log1 test");
		 log.debug("Log2 test");
		 System.out.println("Level: " + log.getLevel());
	 }



}
