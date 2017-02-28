/*
 * www.fagnersouza.com.br
 */
package br.com.fagnersouza.util;

/**
 *
 * @author fagner.souza
 */
public class Progression {
    private final int steps;
    private double progress;
    private final double step;
    public Progression(int steps){
        this.steps = steps;
        this.step = (1.0/steps);
        this.progress = 0.0;
    }
    
    public double next(){
        
        double r = progress+=step;
        
        if(r > 1.0)
            r = 1.0;
        
        return r;
    }
    
    public void reset(){
        progress = 0.0;
    }
}
