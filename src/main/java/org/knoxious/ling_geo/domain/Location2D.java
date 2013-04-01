
package org.knoxious.ling_geo.domain;

import org.knoxious.ling_geo.agents.Agent;

import java.util.Collection;
import java.util.HashMap;

public class Location2D 
	implements Comparable {

	// A dark Location is unreachable
	private boolean dark = false;
	private Point2D point;
	private double value = 0.0;
	private Relations relations = null;
	private HashMap<String, Object> properties = new HashMap<String, Object>();
	private HashMap<String, Agent> occupantMap = new HashMap<String, Agent>();

	public Location2D(int x, int y, boolean dark, double value) {
		// TBD: this limits us to a 2D Field.
		point = new Point2D(x,y);
		System.out.println("<ctor> Location2D : "+  x + "/" + y + " Point= " + point);
		this.dark = dark;
		this.value = value;
	}

	public Location2D(int x, int y, boolean dark)
	{
		this(x, y, dark, 0);
	}

	public Location2D(int x, int y) {
		this(x,y,false);
	}

	public boolean isDark() {
		return dark;
	}

	public void setDark(boolean dark) {
		this.dark = dark;
	}

	// Breaks a normal 'mutator' contract but
	// it seems useful
	public Point2D setPoint(Point2D point) {
	   Point2D prev_point = this.point;
		this.point = point;
		return prev_point;
	}

	public Point2D getPoint() {
		return this.point;
	}

	public String getName() {
		return point.getName();
	}

	public Object getProperty(String name)
	{
		return properties.get(name);
	}

	public void setProperty(String name, Object prop)
	{
		properties.put(name, prop);
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		int retval = point.hashCode();
		return retval;
	}

	@Override
	public boolean equals(Object t) {
		if (t == null) {
			return false;
		}
		try {
			Location2D loc = (Location2D)t;
			return point.equals(loc.getPoint());
		} catch (ClassCastException ccx) {
			return false;
		}
	}

	public void addOccupant(Agent agent) {
		System.out.println("Assigning agent to " + point.toString());
		agent.setLocationAsString(point.toString());
		occupantMap.put(agent.getName(), agent);
	}

	public Agent getOccupant(String agentName) {
		return occupantMap.get(agentName);
	}

	public Agent removeOccupant(String agentName) {
		return occupantMap.remove(agentName);
	}

	public Collection<Agent> getOccupants() {
		return occupantMap.values();
	}

	/**
	 * returns a shallow copy of the occupant map
	 */
	public HashMap<String, Agent> getOccupantMap()
	{
		return (HashMap<String, Agent>)occupantMap.clone();
	}

	/**
	 * Defers to Point2D 
	 */
	public int compareTo(Object l) 
		throws ClassCastException 
	{
		if ( l instanceof Location2D ) {
			Location2D l2d = (Location2D)l;
			return point.compareTo(l2d.getPoint());
		} else if ( l instanceof Point2D ) {
			return point.compareTo(l);
		} else {
			throw new ClassCastException("Object is not a valid type.");
		}
	}	

}


