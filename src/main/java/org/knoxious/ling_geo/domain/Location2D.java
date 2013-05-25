
package org.knoxious.ling_geo.domain;

import org.apache.commons.digester3.annotations.rules.CallMethod;
import org.apache.commons.digester3.annotations.rules.CallParam;
import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;


import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

import org.knoxious.ling_geo.agents.Agent;


public class Location2D 
    extends Location
{
    
    public Location2D() {}


    @Override
    public void doInitialize() throws Exception
    {
	// everything should be done 
	// in the CreationFactory

    }

    public void addOccupant(Agent agent) {;} 
    public Agent getOccupant(String agentName) {return null;} 
    public Agent removeOccupant(String agentName){return null;} 
    public Collection<Agent> getOccupants() {return null;}
}

