
package org.knoxious.ling_geo;

import org.knoxious.ling_geo.domain.Tuple;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Assert;

public class TupleTest 
	extends TestCase     {

	public TupleTest(String name) {
		super(name);
	}

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TupleTest.class );
    }

	public void testTuple() {
		Tuple t1 = new Tuple(1,1);
		Tuple t2 = new Tuple(1,1);
		Assert.assertEquals("Failed " + t1.getName() + "-" + t2.getName() , 
			t1.compareTo(t2), 0);
		Tuple t3 = new Tuple(0,5);
		Assert.assertEquals("Failed " + t1.getName() + "-" + t3.getName(), 
			t1.compareTo(t3), -1);
		Tuple t4 = new Tuple(5,4);
		Assert.assertEquals("Failed " + t1.getName() + "-" + t4.getName(),
			t1.compareTo(t4), 1);
		Assert.assertEquals("Failed hashCode: "+ t1.hashCode() + "-" + 
			t2.hashCode(), t1.hashCode(), t2.hashCode()
			);
		Assert.assertEquals(t1.getX(), t2.getX());
	}
}
