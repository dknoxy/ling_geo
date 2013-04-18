
package org.knoxious.ling_geo.bootstrap;

import org.knoxious.ling_geo.agents.Agent;
import org.knoxious.ling_geo.domain.Field2D;
import org.knoxious.ling_geo.domain.Location2D;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

public class BootstrapParser {

	private final String configFileName;
	private final DocumentBuilder builder;
	private Document doc;
	private Field2D field;
	private String gameName;
	private Logger log;

	public BootstrapParser(final String configFileName) 
		throws SAXException 
	{
		log = Logger.getLogger(this.getClass().getName());
		log.entering(this.getClass().getName(), "CTOR", configFileName );
		try {
			this.configFileName = configFileName;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			dbf.setIgnoringElementContentWhitespace(true);
			builder = dbf.newDocumentBuilder();
			OutputStreamWriter osw = new OutputStreamWriter( System.err, "UTF-8");
			builder.setErrorHandler( new ParserErrorHandler( 
				new PrintWriter(osw, true)));
		} catch (ParserConfigurationException pcx) {
			throw new SAXException( "Error: " + pcx.getMessage(), pcx);
		} catch (UnsupportedEncodingException uex) {
			throw new SAXException( "Error: " + uex.getMessage(), uex);
		}
		log.exiting(this.getClass().getName(), "CTOR");
	}

	public Field2D parse() throws SAXException {
		log.entering(this.getClass().getName(), "parse");
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			InputStream is = cl.getResourceAsStream(configFileName);
			 if ( is == null ) {
				throw new SAXException("Input resource can't be found: " 
					+ configFileName);
			 }
			doc = builder.parse(is);
			Element el = doc.getDocumentElement();
			log.finer("DOC : "+  el.getLocalName());
			gameName = el.getAttribute("gameName");
			log.info("Initializing " + gameName);
			log.finer("=== SIBLING PARSE LOOP ===");
			Node sibling = el;
			while (sibling != null) {
				if (sibling.getNodeType() == Node.ELEMENT_NODE) {
				   sibling = visitNode(sibling);
				} else {
					sibling = sibling.getNextSibling();
				}
			}
			log.finer("=== END SIBLING PARSE LOOP ===");
		} catch (IOException iox) {
			SAXException saxx = new SAXException("IOException: " + iox.getMessage(), iox);
			log.throwing(BootstrapParser.class.getName(), "parse()",saxx);
			throw saxx;
		}
		log.exiting(this.getClass().getName(), "parse");
		return field;
	}

	private Node visitNode(Node node) {
		String nName = node.getNodeName();
		log.entering(
			this.getClass().getName(), "visitNode", node
			);
		if ("field2d".equals(nName)) {
			processField(node);
			processChildren(node);
      } else if ("location2d".equals(nName)) {
			processLocation(node);
			processChildren(node);
		} else if ("agent2d".equals(nName)) {
			try {
				processAgent(node);
				processChildren(node);
			} catch (IllegalArgumentException iax) {
				log.info("Problem interpreting agent metadata: " +
				iax.getMessage() + " on " + node.toString()
					);
			}
		} else {
			log.info("visitNode DEFAULT RULE applied" + node + 
			" Continuing to visit next node");
			processChildren(node);
		}
		log.exiting(this.getClass().getName(), "visitNode", node);
		return node.getNextSibling();
	}

	private void processChildren(Node node) 
	{
		try {
			log.entering(this.getClass().getName(), 
				"processChildren",
				node
				);
			log.finer("Child = " + node.getFirstChild());
			Node current = node.getFirstChild();
			while( current != null) {
				if ( current.getNodeType() == Node.ELEMENT_NODE) {
				  visitNode(current);
				}
				current = current.getNextSibling();
			}
		} catch  (Exception  ex) {
			log.severe(this.getClass().getName() + " processChildren: " + 
				ex.getMessage()
				);
		}
		log.exiting(this.getClass().getName(), "processChildren", node);
	}

	private void processLocation(Node node) 
		throws IllegalArgumentException 
	{
		log.entering(this.getClass().getName(), 
			"processLocation",
			node
			);
		NamedNodeMap nmap = node.getAttributes();
		Node attrx = nmap.getNamedItem("x");
		Node attry = nmap.getNamedItem("y");
		Node attrDark = nmap.getNamedItem("dark");
		Node attrWeight = nmap.getNamedItem("weight");
		try {
			int x = Integer.valueOf(attrx.getNodeValue()).intValue();
			int y = Integer.valueOf(attry.getNodeValue()).intValue();
			boolean dark = Boolean.getBoolean(attrDark.getNodeValue());
			double weight = 0.0;
			if (attrWeight != null ) {
				weight= Double.valueOf(attrWeight.getNodeValue());
			}
			Location2D location = new Location2D(x, y, dark, weight);
			field.addLocation(location);
		} catch (NumberFormatException nfx) {
			IllegalArgumentException iax = new IllegalArgumentException(
				"Can't understand coordinates: " + attrx + "/" + attry
				 );
			log.throwing(this.getClass().getName(), 
				"processLocation",
				iax
				);
			throw iax;
		}
		log.exiting(this.getClass().getName(), "processLocation", node);

	}

	private void processField(Node node)
	{
		// incoming field2d node
		// TBD: Making some assumptions at this early point in time
		// incoming location2d node
		// TBD: making some assumptions at this early point
		log.entering(this.getClass().getName(), "processField", node);
		NamedNodeMap nmap = node.getAttributes();
		try {
			Node attrx = nmap.getNamedItem("x");
			Node attry = nmap.getNamedItem("y");
			int x = Integer.valueOf(attrx.getNodeValue()).intValue();
			int y = Integer.valueOf(attry.getNodeValue()).intValue();
			field = new Field2D( x*y );
		} catch (NumberFormatException nfx) {
			IllegalArgumentException iax = new IllegalArgumentException(
				"Can't understand Field dimensions: " + nfx.getMessage()
				);
			log.throwing(this.getClass().getName(), "processField", iax);
			throw iax;
		}
		log.exiting(this.getClass().getName(), "processField", node);
	}

	private void processAgent(Node node)
	{
		log.entering(this.getClass().getName(), "processAgent", node);
		NamedNodeMap nmap = node.getAttributes();
		String attrName = nmap.getNamedItem("name").getNodeValue();
		String attrLocation2d = nmap.getNamedItem("location2d").getNodeValue();
		String attrType = nmap.getNamedItem("type").getNodeValue();
		Agent agent = Agent.newInstance(attrName, attrLocation2d, attrType);
		field.addAgent(agent);
		log.exiting(this.getClass().getName(), "processAgent", node);
	}
};
