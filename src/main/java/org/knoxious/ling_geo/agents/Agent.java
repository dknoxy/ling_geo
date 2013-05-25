
package org.knoxious.ling_geo.agents;

import org.knoxious.ling_geo.domain.Location;

public abstract class Agent 
{

	String name;
	Location loc;

	protected Agent() {
	}

	public abstract void transitionTo(Location loc);

        public Location getLocation()	
	{
	    return loc;
	}

	public void setLocation(Location newLocation)
	{
	    loc = newLocation;
	}

	public abstract boolean isReachable(Location newLocation);

	public final String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void initialize() throws Exception
	{
	    doInitialize();

	}

	protected abstract void doInitialize() throws Exception;


}


