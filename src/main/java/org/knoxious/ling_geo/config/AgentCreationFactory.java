
package org.knoxious.ling_geo.config;

import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.AbstractObjectCreationFactory;


import org.knoxious.ling_geo.domain.Field;
import org.knoxious.ling_geo.domain.Location;
import org.knoxious.ling_geo.domain.Point;
import org.knoxious.ling_geo.agents.Agent;

public class AgentCreationFactory
    extends AbstractObjectCreationFactory
{
    Logger log = Logger.getLogger(getClass().getName());

    public AgentCreationFactory()
    {
	super();
    }

    public Object createObject(Attributes attrs) 
    {

	org.knoxious.ling_geo.bootstrap.LG lg =
	    org.knoxious.ling_geo.bootstrap.LG.class.cast(
		    getDigester().peek()
		    );
        Field field = lg.getField();
	log.fine( "Field : " + field);
        
	log.fine("Attributes: " + attrs.getLength());
	for ( int i = 0; i< attrs.getLength(); i++)
	{
	    log.fine(attrs.getLocalName(i) + " = " +
		    attrs.getValue(i)
		    );
	}
	Agent agent = null;
	String classname = String.valueOf(attrs.getValue("class"));
	String locname = String.valueOf(attrs.getValue("location"));
	String name = String.valueOf(attrs.getValue("name"));

	classname = ("null".equals(classname)  ? 
		   "org.knoxious.ling_geo.agents.DefaultAgent" : classname
		);
	// we need to hurl somehow if location and name are null
	// for the time being just expect them. Maybe let the 
	// agent.initialize throw it

        try {
	    log.fine("Instancing new Agent: " + classname);
            agent = Agent.class.cast(
		        Class.forName(classname).newInstance()
                        );
	    // Assumes two dimensions
	    // Hate doing it but need to
	    // move on
	    Location loc = field.getLocation(locname);
	    if (loc == null) 
	    {
		throw new IllegalArgumentException(
			"Location " + loc + " does not exist"
			);
	    }
	    agent.setName(name);
	    agent.setLocation(loc);
	    loc.addOccupant(agent);
	    field.addOccupant(loc, agent);
	    agent.initialize();
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
	return agent;
    }

}
    
