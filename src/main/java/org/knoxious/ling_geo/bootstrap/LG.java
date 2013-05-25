
package org.knoxious.ling_geo.bootstrap;

import java.util.logging.Logger;


import org.knoxious.ling_geo.domain.Field;

public class LG
{
    private String gamename;

    private Field field;

    private Logger log = Logger.getLogger(this.getClass().getName());

    public LG() {
        log.entering(this.getClass().getName(), "<ctor>");
        log.exiting(this.getClass().getName(), "<ctor>");
    
    }

    public void setField( Field field) {
    
        log.entering(this.getClass().getName(), "setField", 
            field.getClass().getName()
            );
        this.field = field;
        log.exiting(this.getClass().getName(), "setField");
    
    }

    public Field getField()
    {
	return this.field;
    }

    public void setGamename(String gamename) {
        
        log.entering(this.getClass().getName(), "setGamename", gamename);
        this.gamename = gamename;
        log.exiting(this.getClass().getName(), "setGamename");
    
    }

    public String getGamename() {
        
        return this.gamename;

    }

    public int getGameDomainSize() {

        return field.getDomainSize();

    }

    public int getGameYDimension() {

        return field.getYdim();

    }

    public int getGameXDimension() {

        return field.getXdim();

    }

}

