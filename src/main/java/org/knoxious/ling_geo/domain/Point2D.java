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
		
		// expects a string in the form of "x/y"
		public Point2D ( String locstr ) 
		{
			// dig out x
			//String foobar = locstr;
			//System.out.println("FOOBAR = " + foobar);
			String[] s = locstr.split("/");
			//System.out.println("<ctor> Location: " + locstr );
			// for ( String axis : s ) {
			//	System.out.println( axis );
			//}
			//System.out.println( "Counting: " + s[0] );
			//System.out.println( "Counting: " + s[1] );
			int x;
			int y;
			try {
				if (s[0] != null && s[1] != null) {
					x = Integer.valueOf(s[0]).intValue();
					y = Integer.valueOf(s[1]).intValue();
					xy = new Tuple(x,y);
				} else {
					throw new IllegalArgumentException(
					locstr + "can't be formatted to an integer"
					);
				}
			} catch (NumberFormatException nfx) {
				throw new IllegalArgumentException( 
					"Can't understand locstr: " + locstr
					);
			}
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

		public String toString() {
			return getName();
		}

		public int hashCode()
		{
		   return xy.hashCode();
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

		// Expects a string in the form of "x/y"
		public static Point2D newInstance(String locStr)
			throws IllegalArgumentException
	   {
			return new Point2D(locStr);
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

