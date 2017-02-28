/*
 * www.fagnersouza.com.br
 */
package br.com.fagnersouza.fx;

import javafx.fxml.FXMLLoader;

/**
 *
 * @author fagner.souza
 */
public abstract class AViewer {
    protected View view;
    
    public FXMLLoader getDescriptor() throws Exception {
        return this.view.getDescriptor();
    }
    
    public View getView(){
        return view;
    }
}
