package org.knoxious.ling_geo.test;

import org.knoxious.ling_geo.domain.Tuple;
import org.knoxious.ling_geo.domain.Field2D;
import org.knoxious.ling_geo.domain.Point2D;
import org.knoxious.ling_geo.domain.Location2D;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Assert;

public class Field2DTest 
	extends TestCase     {

	public Field2DTest(String name) {
		super(name);
	}

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( Field2DTest.class );
    }

	 public void testLocation() {
		 Location2D loc1 = new Location2D(1,1);
		 Location2D loc2 = new Location2D(1,1);
		 Assert.assertEquals(loc1.hashCode(), loc2.hashCode());
		 loc1.setDark(true);
		 Assert.assertTrue(loc1.isDark());
		 loc1.setValue(5.0);
		 Assert.assertEquals(loc1.getValue(),5.0);
	 }


	 public void testField() {
		 Field2D field = new Field2D();
		 Location2D loc1 = new Location2D(1,1);
		 Location2D loc2 = new Location2D(1,1);
		 Location2D loc3 = new Location2D(2,1);
		 field.addLocation(loc1);
		 field.addLocation(loc2);
		 field.addLocation(loc3);
		 Point2D p2d = new Point2D(1,1);
		 Location2D loc4 = field.getLocation(p2d);
		 Assert.assertNotNull("getLocation returned null", loc4);
		 Assert.assertTrue(loc4.equals(loc1));
		 Assert.assertFalse(loc2.equals(loc3));
		 p2d = new Point2D(5,3);
		 Assert.assertTrue(loc1.equals(loc2));
		 loc2.setPoint(p2d);
		 Assert.assertFalse(loc1.equals(loc2));
	 }
}
