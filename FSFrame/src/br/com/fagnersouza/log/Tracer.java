/*
 * www.fagnersouza.com.br
 */
package br.com.fagnersouza.log;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author fagner.souza
 */
public class Tracer {
    public static Logger logger = Logger.getLogger(Tracer.class);
    
    static {
        try{
            Properties properties = new Properties();
            properties.load(Tracer.class.getResourceAsStream("tracer.properties"));
            PropertyConfigurator.configure(properties);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
