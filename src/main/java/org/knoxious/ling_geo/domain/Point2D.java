package org.knoxious.ling_geo.domain;



    /**
     * Describes a specific point
     * on the Field. Point2D differs
     * from a Location2D in that the Location2D has
     * additional properties and the Point2D is simply
     * the position in the field.
     */
public class Point2D 
    extends Point
    implements Comparable {


        private int xposition;

        private int yposition;

        public Point2D () {
        }

        public Point2D(int xpos, int ypos) {

            xposition = xpos;
            yposition = ypos;
        }

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
                Point2D p2d;
                try {
                    p2d = (Point2D)p;
                    if (this == p) {
                        return true;
                    } 
                    return compareTo(p2d) == 0;
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
            Point2D other_p = (Point2D)p;
            return hashCode() - p.hashCode();
        }
    }

