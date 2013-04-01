package org.knoxious.ling_geo.test;

import org.knoxious.ling_geo.bootstrap.Bootstrap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Assert;

public class BootstrapTest
	extends TestCase     {

	public BootstrapTest(String name) {
		super(name);
	}

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( BootstrapTest.class );
    }

	 public void testBootstrap()
	 {
		 try {
			 // first form the cl arguments in an array
			 String[] args = new String[3];
			 // mimic the cl args passed to a java program
			 args[0] = "-f";
			 args[1] = "lg.xml";
			 args[2] = "-v";
			 Bootstrap bstrap = new Bootstrap(args);
			 // Start up
			 bstrap.start();
		 } catch (Exception ex) {
			 ex.printStackTrace();
			 Assert.fail(ex.getMessage());
		 }
	 }

};
