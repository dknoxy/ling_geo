
package org.knoxious.ling_geo.agents;

import org.knoxious.ling_geo.domain.Location;

public class DefaultAgent
	extends Agent
{
    public DefaultAgent() 
    {
	super();
    }

    /**
     * TBD: make meaningful
     * How does the default agent move?
     */
    public Location transitionTo(Location toLoc)
    {
	
	if (isReachable(toLoc) ) 
	{
	    setLocation(toLoc);
	}
	return toLoc;
    }

    public boolean isReachable(Location newLocation) 
    {
	return false;
    }

    protected void doInitialize() throws Exception
    {
    }

}
