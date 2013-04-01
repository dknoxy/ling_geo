
package org.knoxious.ling_geo.domain;

import org.knoxious.ling_geo.agents.Agent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

// Don't make Field dependent on 3D or 2D Locations
//
// A Field is the complete landscape of Locations
// 

public class Field2D {

	private HashMap<Point2D, Location2D> field;
	private HashMap<Point2D, Agent> agents;

	public Field2D(int x, int y)
	{
		this( x*y );
	}

	// A default constructor for convenience
	public Field2D()
	{
		this( 24 * 24 );
	}

	public Field2D(int capacity)
	{
		field = new HashMap<Point2D, Location2D>(capacity);
	}

	public void addLocation(Location2D location)
	{
		field.put(location.getPoint(), location);
	}

	public Location2D getLocation(Point2D p) {
		Location2D retval = field.get(p);
		return retval;
	}

	public void addAgent(Agent agent) {

		Point2D point = new Point2D(agent.getLocationAsString());
		Location2D loc = field.get(point);
		// Debug
		// Set<Point2D> keyset = field.keySet();
		// Iterator<Point2D>  iterator = keyset.iterator();
		// while (iterator.hasNext()) {
		//	System.out.println(iterator.next().getName());
		// }
		if (loc != null) {
			System.out.println("Adding Agent " + agent.getName());
			loc.addOccupant(agent);
		} else {
			System.out.println("Warning - Location " + 
			agent.getLocationAsString() +
			"  does not exist. Agent " + agent.getName() +" was not added");
		}
		
	}
}
