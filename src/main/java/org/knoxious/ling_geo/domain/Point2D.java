package org.knoxious.ling_geo.domain;

	/**
	 * Describes a specific point
	 * on the Field. Point2D differs
	 * from a Location2D in that the Location2D has
	 * additional properties and the Point2D is simply
	 * the position in the field.
	 */
	public class Point2D 
		implements Comparable {

		private final Tuple xy;

		public Point2D (int x, int y) {
			xy = new Tuple(x,y);
		}
		
		@Override
		public boolean equals(Object p) {
			if (p != null) {
				Point2D p2d;
				try {
					p2d = (Point2D)p;
					if (this == p) {
						return true;
					} 
					return getTuple().equals(p2d.getTuple());
				} catch (ClassCastException ccx) {
					return false;
				}
			}
			return false;
		}

		public Tuple getTuple()
		{
			return xy;
		}

		public String getName() {
			return xy.toString();
		}

		public int hashCode()
		{
			int retval = xy.hashCode();
			return retval;
		}

		/**
		 * Find the delta between this point and @param p
		 * We use a double because small differences might 
		 * be significant at some point.
		 * TBD: Should this be a string in the form of 
		 * "x/y"? Or should it be a ratio (double)?
		 */
		public Tuple getDelta(Point2D p)
		{
			Tuple t = xy.getDelta(p.getTuple());
			return t;
		}

		public int compareTo(Object p) 
			throws ClassCastException {


			if ( p == this ) {
				return 0;
			}

			Point2D other_p = (Point2D)p;
			return getTuple().compareTo(other_p.getTuple());
		}
	}

