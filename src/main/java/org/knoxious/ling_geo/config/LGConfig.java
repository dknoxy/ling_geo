
package org.knoxious.ling_geo.config;


import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.xml.sax.SAXException;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.ExtendedBaseRules;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.BeanPropertySetterRule;
import org.apache.commons.digester3.CallParamRule;
import org.apache.commons.digester3.ObjectCreateRule;
import org.apache.commons.digester3.SetNextRule;
import org.apache.commons.digester3.SetPropertyRule;

import org.knoxious.ling_geo.bootstrap.LG;
import org.knoxious.ling_geo.domain.Field2D;

public class LGConfig 
{
    private final String filename;
    private LG game;
    private static LGConfig lgconfig;
    private Logger log = Logger.getLogger(getClass().getName());

    public LGConfig(String filename)
    {

        super();
        this.filename = filename;
        lgconfig = this;

    }

public void configureGame() 
    throws IllegalStateException {

    try {
        //DigesterLoader digesterloader = DigesterLoader.newLoader(this);
            Digester digester = new Digester(); //digesterloader.newDigester();
	    digester.setRules(new ExtendedBaseRules());
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream is = cl.getResourceAsStream(filename);
            if ( is == null ) {
                throw new IllegalStateException("Input resource can't be found: " 
                    + filename );
            }

            loadLGRules(digester);
            loadFieldRules(digester);
            loadLocationRules(digester);
            
            game = digester.parse(is);

        } catch (java.io.IOException iox) {
            throw new IllegalStateException("IOException caught..", iox);
        } catch (SAXException saxex) {
            log.severe(saxex.toString());
            System.out.println(saxex.toString());
            saxex.printStackTrace();
            Throwable ex = saxex.getCause();
            while (ex != null) {
                System.out.println("=======");
                ex.printStackTrace();
                ex  = ex.getCause();
                System.out.println("=======");
            }
            throw new IllegalStateException("SAXException caught..", saxex);
        } catch (NullPointerException ex) {

        ex.printStackTrace();
        log.severe(ex.toString());
    }
    }

    public static LG getGame() {

        return getInstance().getGameInstance();

    }


    private static LGConfig getInstance()
    {

        return lgconfig;
    
    }

    private LG getGameInstance() {

        return game;

    }

    private void loadLGRules(Digester digester) {

        log.entering(getClass().getName(), "loadLGRules");
        digester.addObjectCreate("lg", LG.class.getName());
        digester.addBeanPropertySetter("lg/gamename");
        log.exiting(getClass().getName(), "loadLGRules");
    
    }

    private void loadFieldRules(Digester digester) {
       
       try {    
           log.entering(getClass().getName(), "loadFieldRules");

	   FieldCreationFactory fcf = new FieldCreationFactory();
	   digester.addFactoryCreate("lg/field", fcf);
           SetNextRule snr = new SetNextRule("setField",
                "org.knoxious.ling_geo.domain.Field"
                );
            snr.setFireOnBegin(false);
            digester.addRule("lg/field", snr);

           // Create location rules
           LocationCreationFactory lcf = new LocationCreationFactory();
           digester.addFactoryCreate("lg/field/location", lcf);
	   digester.addBeanPropertySetter("lg/field/location/name");
	   digester.addBeanPropertySetter("lg/field/location/dark");
	   digester.addBeanPropertySetter("lg/field/location/weight");
	   digester.addCallMethod(
		   "lg/field/location/property",
                   "addProperty", 2, new Class[] {String.class, Object.class}
		   );
	   digester.addCallParam(
		   "lg/field/location/property", 0, "name"
		   );
	   digester.addCallParam(
		   "lg/field/location/property", 1, "value"
		   );
	   snr = new SetNextRule("addLocation",
		   "org.knoxious.ling_geo.domain.Location2D"
		   );
	   snr.setFireOnBegin(true);
	   digester.addRule("lg/field/location", snr);
	   // Agent rules
           AgentCreationFactory acf = new AgentCreationFactory();
           digester.addFactoryCreate("lg/agent", acf);

        } catch (Exception ex) {
            log.severe(ex.toString());
        }

        log.exiting(getClass().getName(), "loadFieldRules");
    }

    private void loadLocationRules(Digester digester) {

        log.entering(getClass().getName(), "loadLocationRules");

        log.exiting(getClass().getName(), "loadLocationRules");
    }

};
