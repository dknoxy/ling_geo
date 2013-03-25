
package org.knoxious.ling_geo.bootstrap;

import java.io.PrintWriter;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class ParserErrorHandler
	implements ErrorHandler
{

	private final PrintWriter pwriter;

	public ParserErrorHandler(final PrintWriter pwriter) {
		this.pwriter = pwriter;
	}

	public void warning(final SAXParseException spx) throws SAXException { 
		pwriter.println("Warning: " + parseException(spx));
	}

	public void error(final SAXParseException spx) throws SAXException {
		throw new SAXException( "ERROR: " + parseException(spx));
	}

	public void fatalError(final SAXParseException spx) throws SAXException {
		throw new SAXException( "FATAL ERROR: " + parseException(spx));
	}

	private String parseException(final SAXParseException spx) {

		String sysid = spx.getSystemId();
		String excInfo = "URI = " + ( sysid == null ? "null": sysid ) + 
			" Line = " + spx.getLineNumber() + " : " + spx.getMessage();
		return excInfo;
	}

};


