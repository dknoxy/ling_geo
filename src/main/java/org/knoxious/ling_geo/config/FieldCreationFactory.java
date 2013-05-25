
package org.knoxious.ling_geo.config;

import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.AbstractObjectCreationFactory;

import org.knoxious.ling_geo.domain.Field;

public class FieldCreationFactory
    extends AbstractObjectCreationFactory
{

    private Logger log = Logger.getLogger(getClass().getName());

   public FieldCreationFactory() {
       super();
       log.entering(getClass().getName(), "<ctor>");
       log.exiting(getClass().getName(), "<ctor>");
   }

    @Override
    public Object createObject(Attributes attrs) {

        log.entering(getClass().getName(), "createObject");

        Field fldobj = null;
	String classname = "org.knoxious.ling_geo.domain.Field2D";

	log.fine("Num Attrs: " + attrs.getLength());
	int xpos = Integer.parseInt(attrs.getValue("xdim"));
	int ypos = Integer.parseInt(attrs.getValue("ydim"));
	// Must be a subclass of Point2D
	String defaultPointClass =
	        String.valueOf(attrs.getValue("defaultPointClass"));
	// Must be a subclass of Location2D
	String defaultLocationClass =
	        String.valueOf(attrs.getValue("defaultLocationClass"));
	classname = (
            attrs.getValue("class") == null ? 
	    classname : String.valueOf(attrs.getValue("class"))
            );
        try {
	    log.fine("Instancing new Field: " + classname);
            fldobj = (Field)Class.forName(classname).newInstance();
	    fldobj.setXdim(xpos);
	    fldobj.setYdim(ypos);
	    if (defaultPointClass != null) {
		log.fine("Setting Default Point Class " + defaultPointClass);
		fldobj.setDefaultPointClass(defaultPointClass);
	    } 
	    if (defaultLocationClass != null) {
		log.fine("Setting Default Loc Class " + defaultLocationClass);
		fldobj.setDefaultLocationClass(defaultLocationClass);
	    }
	    fldobj.initialize();
	} catch (LinkageError lerr) {
	    log.severe(lerr.toString());
	} catch (ClassNotFoundException cnfx) {
	    log.severe(cnfx.toString());
	} catch (IllegalAccessException iax) {
	    log.severe(iax.toString());
	} catch (InstantiationException inx) {
	    log.severe(inx.toString());
	} catch (Exception ex) {
	    log.severe(ex.toString());
	}
        log.exiting(getClass().getName(), "createObject");
        return fldobj;
    }
}

