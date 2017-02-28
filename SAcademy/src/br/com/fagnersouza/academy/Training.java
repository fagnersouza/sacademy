/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fagnersouza.academy;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author fagner.souza
 * @param <T>
 */
public class Training <T extends Lesson> extends AbstractSequentialList<T> {
    private Program program;
    private int depth; //Quantidade de aulas
    private final List<T>lessons;
    

    public Training(){
        lessons = new ArrayList<>();
    }
    
    @Override
    public boolean add(T e) {
        if(e == null || lessons.contains(e))
            return false;
        
        return lessons.add(e); 
    }

    public int getDepth() {
        return size();
    }
    
    @Override
    public ListIterator<T> listIterator(int i) {
        return lessons.listIterator(i);
    }

    @Override
    public ListIterator<T> listIterator() {
        return lessons.listIterator();
    }
    
    

    @Override
    public int size() {
        return lessons.size();
    }      

    public void setProgram(Program program) {
       this.program = program;
    }
    
    public Program getProgram(){
        return this.program;
    }
}
