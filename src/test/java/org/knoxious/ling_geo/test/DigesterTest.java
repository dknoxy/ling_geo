package org.knoxious.ling_geo.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.xml.sax.SAXException;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.annotations.FromAnnotationsRuleModule;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.CallMethod;
import org.apache.commons.digester3.annotations.rules.CallParam;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;
import org.apache.commons.digester3.annotations.rules.SetProperty;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Assert;

public class DigesterTest
	extends TestCase     {

    public DigesterTest(String name)
    {
	super(name);
    }

    public static Test suite()
    {
        return new TestSuite( DigesterTest.class );
    }

    public void testConfigFile()
    {
	/**
	try {
	    DigesterLoader digesterloader = DigesterLoader.newLoader(this);
	    Digester digester = digesterloader.newDigester();
	    ClassLoader cl = Thread.currentThread().getContextClassLoader();
	    InputStream is = cl.getResourceAsStream("lg.xml");
	    if ( is == null ) {
		throw new IllegalStateException("Input resource can't be found: "
		   + filename );
	    }

	    game = digester.parse(is);
	} catch (java.io.IOException iox) {
	    throw new IllegalStateException("IOException caught..", iox);
	} catch (SAXException saxex) {
	    log.severe(saxex.toString());
	    System.out.println(saxex.toString());
	    saxex.printStackTrace();
	   Throwable ex = saxex.getCause();
	    while (ex != null) {
		System.out.println("=======");
	       ex.printStackTrace();
		ex  = ex.getCause();
	       System.out.println("=======");
	    }
	    throw new IllegalStateException("SAXException caught..", saxex);
	}
	**/
    }
}
