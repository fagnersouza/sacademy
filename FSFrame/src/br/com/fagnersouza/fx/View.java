/*
 * www.fagnersouza.com.br
 */
package br.com.fagnersouza.fx;

import br.com.fagnersouza.log.Tracer;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author fagner.souza
 */
public class View {
    private final String fxmlFile;
    private FXMLLoader descriptor;
    private Properties properties;
    
    /**
     * Use this when you need create a instance for a View+Controller
     * @param presenter - This is the "this" of the Presenter that contains the instance
     * @param fxmlFile  - This is the name of the descriptor of the view
     */
    public View(Object presenter, String fxmlFile){
       this.fxmlFile = fxmlFile;
       
       
        try {
            descriptor = new FXMLLoader(presenter.getClass().getResource(fxmlFile));
            descriptor.load();
            
            properties = new Properties();
            
            //System.out.println("FXML: " + presenter.getClass().getResource(fxmlFile));
            //System.out.println("PROPERTIES: " + presenter.getClass().getResource("view.properties"));
            
            InputStream in = presenter.getClass().getResourceAsStream("view.properties");
                    
            properties.load(in);
            
        } catch (IOException ex) {
            Tracer.logger.error("", ex);
            System.out.println(ex);
        }
        
    }  
    
    public FXMLLoader getDescriptor(){
        return this.descriptor;
    }
    
    /**
     * Use to get a title for the view
     * @return 
     */
    public String getTitle(){
        //TODO: obter isso aqui de um arquivo de propriedade 
        //com suporte a multi-idioma
        
        return properties.getProperty("Title");
    }
}
