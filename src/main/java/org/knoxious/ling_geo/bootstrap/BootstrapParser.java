
package org.knoxious.ling_geo.bootstrap;

import org.knoxious.ling_geo.domain.Field2D;
import org.knoxious.ling_geo.domain.Location2D;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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

	public BootstrapParser(final String configFileName) 
		throws SAXException 
	{
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
	}

	public Field2D parse() throws SAXException {

		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			 InputStream is = cl.getResourceAsStream(configFileName);
			 if ( is == null ) {
				throw new SAXException("Input resource can't be found: " 
					+ configFileName);
			 }
			doc = builder.parse(is);
			doc.normalize();
			Element el = doc.getDocumentElement();
			el.normalize();
			System.out.println("DOC : "+  el.getLocalName());
			gameName = el.getAttribute("gameName");
			System.out.println("Game Name : " + gameName);
			Node sibling = el;
			while (sibling != null) {
				System.out.println("=== PARSE LOOP ===");
				if (sibling.getNodeType() == Node.ELEMENT_NODE) {
				   sibling = visitNode(sibling);
				} else {
					sibling = sibling.getNextSibling();
				}
			}
			System.out.println("=== END PARSE LOOP ===");
		} catch (IOException iox) {
			throw new SAXException("IOException: " + iox.getMessage(), iox);
		}
		return field;
	}

	private Node visitNode(Node node) {
		String nName = node.getNodeName();
		System.out.println(" ==== VISIT NODE ===");
		System.out.println("Node name : "+ nName);
		System.out.println("Node type :" + node.getNodeType());
		switch( nName ) {
			case "field2d" : 
					processField(node);
					processChildren(node);
					break;
			case "location2d" :
					processLocation(node);
					processChildren(node);
					break;
			default:
				System.out.println("DEFAULT RULE " + node);
				processChildren(node);
				break;
		}
		System.out.println(" ==== END VISIT NODE ===");
		return node.getNextSibling();
	}

	private void processChildren(Node node) {
		try {
		System.out.println("=== processChildren ==== " + node);
		System.out.println("Child = " + node.getFirstChild());
		Node current = node.getFirstChild();
		while( current != null) {
			if ( current.getNodeType() == Node.ELEMENT_NODE) {
			  visitNode(current);
			}
			current = current.getNextSibling();
			//System.out.println(current);
		}
		System.out.println("=== End processChildren ====");
		} catch  (Exception  ex) {
			System.out.println(ex);
			throw ex;
		}
	}

	private void processLocation(Node node) {
		System.out.println("ProcessLocation");
		NamedNodeMap nmap = node.getAttributes();
		Node elx = nmap.getNamedItem("x");
		Node ely = nmap.getNamedItem("y");
		Node elDark = nmap.getNamedItem("dark");
		int x = Integer.valueOf(elx.getNodeValue()).intValue();
		int y = Integer.valueOf(ely.getNodeValue()).intValue();
		boolean dark = Boolean.getBoolean(elDark.getNodeValue());
		Location2D location = new Location2D(x,y,dark);
		field.addLocation(location);
	}

	private void processField(Node node)
	{
		// incoming field2d node
		// TBD: Making some assumptions at this early point in time
		// incoming location2d node
		// TBD: making some assumptions at this early point
		System.out.println("ProcessField");
		NamedNodeMap nmap = node.getAttributes();
		Node attrx = nmap.getNamedItem("x");
		Node attry = nmap.getNamedItem("y");
		int x = Integer.valueOf(attrx.getNodeValue()).intValue();
		int y = Integer.valueOf(attry.getNodeValue()).intValue();
		field = new Field2D( x*y );
	}



};
