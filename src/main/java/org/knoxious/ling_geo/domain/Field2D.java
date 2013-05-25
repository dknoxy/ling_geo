
package org.knoxious.ling_geo.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.knoxious.ling_geo.agents.Agent;

// Don't make Field dependent on 3D or 2D Locations
//
// A Field is the complete landscape of Locations
// 
public class Field2D
    extends Field
{

    /**
     * The basic array of points
     */
    private ArrayList<Point2D> points = null;

    /**
     * A map of locations and occupying agents
     */
    private HashMap<Location2D, Vector<Agent> > location_agent_map =
        new HashMap<Location2D, Vector<Agent> >();
    
    /**
     * A map of agents and their respective locations
     */
    private HashMap<Agent, Vector<Location2D> > agent_location_map =
        new HashMap<Agent, Vector<Location2D> >();
    
    /**
     * A map of locations keyed by name versus point
     */
    private HashMap<String, Location2D> name_location_map =
        new HashMap<String, Location2D>();

    /**
      * A map of locations keyed by point
      */
    private HashMap<Point2D, Location2D> point_location_map =
	new HashMap<Point2D, Location2D>();
    
    /**
     * A map of agents keyed by name versus point
     */
    private HashMap<String, Agent> agent_map =
        new HashMap<String, Agent>();
    
    public Field2D() 
    {
        super();
    }
    
    /**
      * See the comment in Field
      */
    public Point getPoint( int x, int y)
    {
	int index = getIndex( x, y);
	return points.get(index);
    }
    
    // required by super
    @Override
    protected void doInitialize( ) throws Exception
    {
        log.entering(getClass().getName(), "doInitialize");
        if ( getXdim() <= 0 || getYdim() <= 0) {
             throw new IllegalArgumentException(
            "Xdim and Ydim must both be greater than 0"
            );
        }

       // Field2D presumes Points are derivatives of Point2D
        points = new ArrayList<Point2D>( getXdim() * getYdim());
	// Loop through and add a point to each position
	for ( int x = 0; x < getXdim(); x++)  {
	    for ( int y = 0; y < getYdim(); y++) 
	    {
               Point2D p = Point2D.class.cast(
		      Class.forName(getDefaultPointClass()).newInstance()
		      ); 
	        p.setXposition(x);
		p.setYposition(y);
		points.add(getIndex(x,y), p);
		point_location_map.put(p, null);
	    }
	}

        log.exiting(getClass().getName(), "doInitialize");
    }

    public void addLocation(Location2D location)
    {
        log.entering(getClass().getName(), 
            "addLocation(1)", location.toString()
            ); 
	Point2D point = Point2D.class.cast(location.getPoint());
        log.entering(getClass().getName(), "Using Point: " + point);
	Location existing_loc = point_location_map.get(point);
	if ( existing_loc != null ) {
           log.info(
		"Overwritting existing location: " + existing_loc.getName()
                );
	}
	point_location_map.put(point, location);
	log.fine("Adding location to name map " + location.getName());
	name_location_map.put(location.getName(), location);
        log.exiting(getClass().getName(), "addLocation(1)");
    }


    public Location2D getLocation(Point2D p) {

        return point_location_map.get( p );
    }

    public Location2D getLocation(String locname)
    {
        Location2D location = null;

        log.entering(getClass().getName(),"getLocation", locname);

        // find a location by name
        if ( locname.indexOf('/') > 0 ) 
        {
	    // in the form of 'x/y'
	    log.fine("Split name form " + locname);
            String[] xy = locname.split("/");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            Point2D point = points.get(getIndex(x,y));
            location = point_location_map.get(point);
        } else {
            // assume that it's the location name
            location = name_location_map.get(locname);
        }
        log.exiting(getClass().getName(),"getLocation");
        return location;
    }


    public Agent getAgent(String agent_name)
    {
        return null;
    }

    public void addOccupant(Location location, Agent agent)
    {
	Location2D l2d = Location2D.class.cast(location);
        // protect against overriding an agent
        if (! agent_map.containsValue(agent.getName())) {
            agent_map.put(agent.getName(), agent);
            Vector<Location2D> vl = new Vector<Location2D>();
            vl.add(l2d);
            agent_location_map.put(agent, vl);
	    Vector<Agent> va = location_agent_map.get(l2d);
	    if ( va == null )
	    {
		va = new Vector<Agent>();
	    }
            va.add(agent);
            location_agent_map.put(l2d, va);
	}
    }

    public void addOccupant(
            String locationname, 
            Agent agent
            )
    {
        Location location = name_location_map.get(locationname);
	addOccupant(location, agent);
    }

    public int getDomainSize() {

        return super.getDomainSize();

    }

    @Override
    // So it's explicit that we depend
    // on Field's assumption of two
    // dimensions
    public String getDefaultLocationClass()
    {
	return super.getDefaultLocationClass();
    }

    @Override
    public String toString() {

        return "Field2D[" + xdim + ", " + ydim + "]";

    }


    // xdim and ydim are assumed to have been set
    // there is danger of returning a negative
    // number
    private int getIndex(int x, int y) {

        Object[] obj = new Object[] {x,y};
            log.entering(getClass().getName(), "getIndex", obj);
        log.exiting(getClass().getName(), 
            "getIndex returning " +
            (xdim * x) + " + " + y +" = " +
            ((xdim * x) + y)
	    );;

	return ((xdim * x) + y);

    }

    private boolean isLocated(Agent agent) {

        if (agent_location_map.containsValue(agent) &&
            agent_location_map.get(agent).size() > 0 ) {
            return true;
        } else {
            return false;
        }
    }
}
