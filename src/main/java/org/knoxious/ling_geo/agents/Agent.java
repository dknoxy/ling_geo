
package org.knoxious.ling_geo.agents;

import org.knoxious.ling_geo.domain.Location2D;
import org.knoxious.ling_geo.domain.Point2D;

public abstract class Agent 
{

	String name;
	Point2D location;
	String type;

	protected Agent() {
	}

	public Agent( String name, String locationstr, String type) {
		this.name = name;
		this.location = new Point2D(locationstr);
		this.type = type;
	}

	public final String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public final String getLocationAsString() {
		return location.getName();
	}

	public void setLocationAsString(String locationstr)
	{
		System.out.println("setLocationAsString : " + locationstr);
		this.location = new Point2D(locationstr);
	}

	public void setType(String type) {
		this.type = type;
	}

	public final String getType() {
		return type;
	}

	public static Agent newInstance(String name, String locstr, String type) 
		throws IllegalArgumentException 
	{

		Agent agent = null;
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			Class agentClass = cl.loadClass(type);
			agent = (Agent)agentClass.newInstance();
			agent.setName(name);
			agent.setLocationAsString(locstr);
			agent.setType(type);
			return agent;
		} catch (InstantiationException inx) {
			throw new IllegalArgumentException(
				"Could not instantiate " + type
				);
		} catch (IllegalAccessException iax) {
			throw new IllegalArgumentException(
				"Could not access " + type  
				);
		} catch (ClassNotFoundException cnfx) {
			throw new IllegalArgumentException(
				"Could not find class " + type 
				);
		}

	}

	/**
	 * transition from one location to another
	 * @param toLoc the location to transition to
	 * @returns The previous location
	 */
	public abstract Location2D transitionTo(Location2D toLoc); 

}


