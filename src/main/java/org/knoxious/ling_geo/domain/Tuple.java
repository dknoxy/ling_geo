

package org.knoxious.ling_geo.domain;


public class Tuple
	implements Comparable
{
	private final int x;
	private final int y;

	public Tuple(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * The difference between two Tuples
	 * @param t The tuple to compare
	 */
	public Tuple getDelta(Tuple t) {
		int deltaX = t.getX()- getX();
		int deltaY = t.getY()- getY();
		return new Tuple(deltaX, deltaY);
	}


	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public String toString() {
		
		String encoded = "" + x +"/" + y;
		return encoded.intern();
	}

	public String getName() {
		return toString();
	}


	@Override
	public boolean equals(Object t) {

		return compareTo(t) == 0 ? true: false;
	}


	// TBD: try this for now. What makes one tuple
	// greater than another? Distance? Ratio value?
	// If Ratio, then what about y == 0?
	// How is distance positive or negative?
	// Perhaps absolute value?
	
	@Override
	public int compareTo(Object o)
		throws ClassCastException
	{
		if (this == o) {
			return 0;
		} else {
			try {
				Tuple t = (Tuple)o;
				Tuple delta = getDelta(t);
				if ( delta.getX() ==  0 && delta.getY() == 0) {
					// it's the same location
					return 0;
				} else if ( delta.getY() != 0 ) { // avoid division by 0
					double ratio = delta.getX()/delta.getY();
					if (ratio > 0 ) {
						return 1;
					} 
					return -1;
				} else {
					// deltaY == 0
					if ( delta.getX() > 0 ) {
						return 1;
					}
					return -1;
				}
			} catch (ClassCastException ccx) {
				return -1;
			}
		}
	}
}

