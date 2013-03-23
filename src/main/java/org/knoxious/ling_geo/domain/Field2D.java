
package org.knoxious.ling_geo.domain;

import java.util.HashMap;

// Don't make Field dependent on 3D or 2D Locations
//
// A Field is the complete landscape of Locations
// 

public class Field2D {

	private HashMap<Point2D, Location2D> field;

	public Field2D()
	{
		field = new HashMap<Point2D, Location2D>();
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
}
