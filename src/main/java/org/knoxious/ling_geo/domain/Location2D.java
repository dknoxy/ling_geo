
package org.knoxious.ling_geo.domain;


public class Location2D 
	implements Comparable {

	private boolean dark = false;
	private final Point2D point;
	private double value = 0.0;
	private Relations relations = null;

	public Location2D(int x, int y, boolean dark, double value) {

		// FIX ME: this limits us to a 2D Field.
		point = new Point2D(x,y);
		this.dark = dark;
		this.value = value;
	}

	public Location2D(int x, int y, boolean dark)
	{
		this(x, y, dark, -1);
	}

	public Location2D(int x, int y) {
		this(x,y,false);
	}

	public boolean isDark() {
		return dark;
	}

	public void setIsDark(boolean dark) {
		this.dark = dark;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	/** For now a Location's name is it's position
	 * This will need to be expanded to include
	 * the latin alphabet. e.g. A1 or B5 like a chess board
	 * The advantage of using an integer tuple is the Field can
	 * be infinitely large over both axis. 
	 */
	public String getName() {
		return point.getName();
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
	private class Point2D 
		implements Comparable {

		private final int x;
		private final int y;

		public Point2D (int x, int y) {
			this.x = x;
			this.y = y;
		 }
		
		@Override
		public boolean equals(Object p) {
			if (p != null) {
				if (this == p) {
					return true;
				}
			}
			return false;
		}

		public int getX()
		{
			return x;
		}

		public int getY()
		{
			return y;
		}

		public String getName() {
			return "(" + x +", "+ y + ")";
		}

		/**
		 * Find the delta between this point and @param p
		 * We use a double because small differences might 
		 * be significant at some point.
		 */
		public double delta(Point2D p)
		{
			return ((p.getX() - getX())/(p.getY() - getY()));
		}

		public int compareTo(Object p) throws ClassCastException {

			Point2D other_p = (Point2D)p;
			if ( other_p == this) {
				return 0;
			} else if ( other_p.getX() > this.getX()) {
				return 1;
			} else if ( other_p.getX() < this.getX()) {
				return -1;
			} else {
				// the points are in the same row test for y
				// Like a number line, the values increase from left to right
				if ( other_p.getY() > this.getY()) {
					return 1;
				} else if ( other_p.getY() < this.getY()) {
					return -1;
				} else {
					// y == y return 0
					return 0;
				}
			}
		}
	}
}


