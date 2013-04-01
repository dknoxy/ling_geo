
package org.knoxious.ling_geo.agents;

import org.knoxious.ling_geo.domain.Location2D;

public class DefaultAgent
	extends Agent
{
	public DefaultAgent() {
	}

	/**
	 * TBD: make meaningful
	 * How does the default agent move?
	 */
	public Location2D transitionTo(Location2D toLoc) {
		return toLoc;
	}
}
