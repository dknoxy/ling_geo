package org.knoxious.ling_geo.domain;


import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

import org.knoxious.ling_geo.agents.Agent;

public abstract class Location
    implements Comparable {

    // A dark Location is unreachable
    protected boolean dark = false;
    
    protected String name = "";

    protected double weight = 0.0;

    protected Point point;

    private HashMap<String, Object> properties = new HashMap<String, Object>();
    private HashMap<String,Agent> occupants = new HashMap<String,Agent>();

    protected Logger log = Logger.getLogger(getClass().getName());

    protected Location() 
    {

    }

    protected Location(Point point) 
    {
	this.point = point;
    }

    /**
      * We force the override and still allow
      * this base class to initialize itself
      */
    public void initialize() throws Exception
    {
	doInitialize();
    }
    // This is delegated to the subclass because
    // the subclass knows about storage.
    protected abstract void doInitialize() throws Exception; 
    
    public void addOccupant(Agent agent) 
    {
	occupants.put(agent.getName(), agent);
    }

    public Agent getOccupant(String agentName)
    {
	return occupants.get(agentName);
    }

    public Agent removeOccupant(String agentName) 
    {
	return occupants.remove(agentName);
    }

    public Collection<Agent> getOccupants()
    {
	return occupants.values();
    }

    public boolean isDark() {
        return dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }

    public void setPoint( Point point ) {

        this.point = point;
    
    }

    public Point getPoint() {

        return this.point;
    
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;

    }

    public Object getProperty(String name)
    {
        return properties.get(name);
    }

    public void addProperty(
            String name, 
            Object val
            )
        {

        log.entering(getClass().getName(), 
            "addProperty", new Object[] {name, val}
        );
            properties.put(name, val);
        log.exiting(getClass().getName(), "addProperty");
        }

    public void setWeight(double value) {
        this.weight = value;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
       // point must be created
       // first
       int retval = point.hashCode();
       return retval;
    }

    @Override
    public boolean equals(Object t) {
        if (t == null) {
            return false;
        }
        if (t == this) {
            return true;
        }
        try {
            Location loc = (Location)t;
            return point.equals(loc.getPoint());
        } catch (ClassCastException ccx) {
            return false;
        }
    }


    /**
     * Defers to Point 
     */
    @Override
    public int compareTo(Object l) 
        throws ClassCastException 
    {
        if ( l instanceof Location ) {
            Location loc = Location.class.cast(l);
            return getPoint().compareTo(loc.getPoint());
        } else if ( l instanceof Point ) {
            return getPoint().compareTo(l);
        } else {
            throw new ClassCastException("Object is not a valid type.");
        }
    } 
    

}
