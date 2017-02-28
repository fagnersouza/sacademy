/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fagnersouza.academy;

import br.com.fagnersouza.log.Tracer;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author fagner.souza
 */
public class Library {
    private static Library instance;
    private static final String LIBRARY_PATH = Archive.getPath();
    private static final String PROGRAM = "contents.xml";
    
    private Library(){
        Tracer.logger.debug("Create Archive: " + Archive.create());       
    }
    
    public static Library getInstance(){
        if(instance == null)
            instance  = new Library();
        
        return instance;
    }
    
    public List<Training>getLibrary(){
        File file = new File(LIBRARY_PATH);
        
        if(!file.exists())
            return null;
        
        List<Training> library = new ArrayList<>();
        
        
        for(File node:file.listFiles()){
            Tracer.logger.debug(node.getAbsoluteFile());
            
            if(node.isDirectory())
                library.add(loadTraining(node));
        }
        
        return library;
    }
    
    public boolean save(Training t){
        if(t == null)
            return false;
                
        if(t.size() <= 0)
            return false;
        
        Lesson lesson = (Lesson) t.get(0);
        File url = new File(lesson.getVideoURL());
        String target = lesson.getProgram().getSequence() + "-" + url.getName();
        
        lesson.setVideoURL(target);
        
        String xml = Contents.XMLFrom(t);
        
        if(xml == null || xml.isEmpty())
            return false;
       
        if(!Archive.createRecord(t.getProgram().getTitle(), 
                                                      PROGRAM, 
                                                         xml))
            return false;        
        
        return Archive.copyAsset(url.getAbsolutePath(),target, t.getProgram().getTitle());            
    }
    
    private Training loadTraining(File file){
        Training training = new Training();
        File pf = new File(file.getAbsolutePath() + "\\" + PROGRAM);
        
        if(!pf.exists())
            return null;
        
        Contents contents = new Contents(pf);
        
        Program tp = contents.getTraining();
        
        if(tp != null)
            training.setProgram(tp);
        
        Map<String,Program> programming = contents.getLessons();
        
        if(programming == null || programming.isEmpty())
            return null;        
        
        final Set<String> filesNme = programming.keySet();
        
        FilenameFilter fnf = (File file1, String name) -> {
            for(String it:filesNme)
                if(name.startsWith(it))
                    return true;
            
            return false;
        };       
        
        for(File it:file.listFiles(fnf)){
            Program p = programming.get(it.getName());
            Lesson l = new Lesson();
            l.setProgram(p);
            l.setVideoURL(it.toURI().toString());
            training.add(l);
        }
        
        return training;
    }  
}
