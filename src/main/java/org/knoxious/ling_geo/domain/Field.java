
package org.knoxious.ling_geo.domain;


import java.util.logging.Logger;

import org.knoxious.ling_geo.agents.Agent;

public abstract class Field
{

    // a Field has at least two dimensions
    protected int xdim;
    protected int ydim;
    protected String defaultLocationClass = 
	"org.knoxious.ling_geo.domain.Location2D";
    protected String defaultPointClass =
	"org.knoxious.ling_geo.domain.Point2D";

    protected Logger log = Logger.getLogger(getClass().getName());

    protected Field() {}

    public final void initialize() throws Exception
    {
	//initializeSelf();
	log.entering(getClass().getName(), "initialize with x = " +
		getXdim() + " y = " + getYdim()
		);
	doInitialize();
	if (getXdim() <= 0 || getYdim() <= 0 ) {
	    throw new IllegalArgumentException(
		    "x and y dimensions must be greater than 0"
		    );
	}
	log.exiting(getClass().getName(), "Field initialized with x = " +
		getXdim() + " y = " + getYdim()
		);
		
    }
    
    // force a subclass to call doInitialize
    protected abstract void doInitialize() throws Exception;
    
    // 
    public abstract Location getLocation(String locationname);

    public abstract void addOccupant(String locationname, Agent agent);
    public abstract void addOccupant(Location location, Agent agent);
    
    /**
    * This needs to be reworked so a
    * three-dimensional field can 
    * also get a point. We are assuming
    * a two dimensional field, which is
    * not a good thing
    */
    public abstract Point getPoint(int x, int y);
    
    public String getDefaultLocationClass()
    {
	return defaultLocationClass;
    }

    public void setDefaultLocationClass(String defaultLocationClass)
    {
	this.defaultLocationClass = defaultLocationClass;
    }
      

    public void setXdim(int xdim) 
    {
	this.xdim = xdim;
    }

    public void setYdim(int ydim) 
    {
	this.ydim = ydim;
    }

    public int getXdim()
    {
	return xdim;
    }

    public int getYdim()
    {
	return ydim;
    }

    public int getDomainSize()
    {
	return xdim * ydim;
    }

    public void setDefaultPointClass(String defaultPointClass) 
    {
	this.defaultPointClass = defaultPointClass;
    }

    public String getDefaultPointClass()
    {
	return defaultPointClass;
    }
}
