
package org.knoxious.ling_geo.domain;

import java.util.HashMap;

public class Location2D 
	implements Comparable {

	// A dark Location is unreachable
	private boolean dark = false;
	private Point2D point;
	private double value = 0.0;
	private Relations relations = null;
	private HashMap<String, Object> properties = new HashMap<String, Object>();

	public Location2D(int x, int y, boolean dark, double value) {

		// TBD: this limits us to a 2D Field.
		point = new Point2D(x,y);
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

	/**
	 * Return true if @param location is reachable from this location
	 * within @param degree depth.
	 */
	public boolean isReachable(Location2D location ) //, trajectories)
	{
		// FIX ME: isReachable needs definition
		return false;
	}


	/**
	 * Defers to Point2D 
	 */
	public int compareTo(Object l) {
		return point.compareTo(l);
	}	

	/**
	 * Identifies a single location in the Field
	 */
}


