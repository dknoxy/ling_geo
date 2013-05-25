package org.knoxious.ling_geo.domain;



    /**
     * Describes a specific point
     * on the Field. Point differs
     * from a Location in that the Location has
     * additional properties and the Point is simply
     * the position in the field. A Location's 
     * position can change
     */
public abstract class Point 
    implements Comparable {

    /**
     * A point has at least
     * two dimensions
     */     
    protected int xposition = 0;
    protected int yposition = 0;
    protected int dimensions = 2;

    protected Point () {
    }

    protected Point( int x, int y) 
    {
        xposition = x;
        yposition = y;
    }
        
    /**
    * A point has at least
    * two dimensions
    */
    public void setXposition(int x) {

       xposition = x;

    }

    public int getXposition() {

        return xposition;

    }

        public void setYposition(int y) {

            yposition = y;

        }

        public int getYposition() {

            return yposition;

        }
        
        @Override
        public boolean equals(Object p) {
            if (p != null) {
                Point point;
                try {
                    point = Point.class.cast(p);
                    if (this == point) {
                        return true;
                    } 
                    return compareTo(point) == 0;
                } catch (ClassCastException ccx) {
                    return false;
                }
            }
            return false;
        }

        public String getName() {
            return "[Point2D(" + xposition + ", " + yposition +")]";
        }

        public String toString() {
            return getName();
        }

    public int hashCode()
    {
       return (37 * xposition) + yposition;
    }

    public int compareTo(Object p) 
        throws ClassCastException {

        if ( p == this ) {
            return 0;
        }
        Point other_p = Point.class.cast(p);
        return hashCode() - p.hashCode();
    }

    public void setDimensions(int dim) {

        this.dimensions = dim;

    }

    public int getDimensions() {

        return this.dimensions;

    }
}


