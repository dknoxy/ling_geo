
package org.knoxious.ling_geo.config;

import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.AbstractObjectCreationFactory;


import org.knoxious.ling_geo.domain.Field;
import org.knoxious.ling_geo.domain.Location;
import org.knoxious.ling_geo.domain.Point;

public class LocationCreationFactory
    extends AbstractObjectCreationFactory
{

    protected Logger log = Logger.getLogger(getClass().getName());

    public LocationCreationFactory() {

        super();
        log.entering(getClass().getName(), "<ctor>");
        log.exiting(getClass().getName(), "<ctor>");
    
    }
    
    @Override
    public Object createObject(Attributes attrs) {

        log.entering(getClass().getName(), "createObject");

        Location locobj = null;
        Field field = Field.class.cast(getDigester().peek());
	log.fine( "Field : " + field);

        log.fine("Num Attrs: " + attrs.getLength());
	for (int i=0; i < attrs.getLength(); i++ ) {
	    log.fine("Attr " + i + " = " +
		    attrs.getValue(i)
		    );
	}

        int xpos =     Integer.parseInt(attrs.getValue("xpos"));
        int ypos =     Integer.parseInt(attrs.getValue("ypos"));
        String classname = String.valueOf(attrs.getValue("class"));
        String name =  String.valueOf(attrs.getValue("name"));
        if ( "null".equals(classname)) {
            log.fine("Using Default Location Class");
            classname = field.getDefaultLocationClass();
        } 

        try {
	    log.fine("Instancing new Location: " + classname);
            locobj = Location.class.cast(
		        Class.forName(classname).newInstance()
                        );
	    // Assumes two dimensions
	    // Hate doing it but need to
	    // move on
	    Point p = field.getPoint(xpos, ypos);
	    log.fine("Found point " + p);
	    locobj.setPoint(p);
	    locobj.setName(name);
	    locobj.initialize();
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
        return locobj;
    }

}
