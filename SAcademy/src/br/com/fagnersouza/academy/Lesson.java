/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fagnersouza.academy;

/**
 *
 * @author fagner.souza
 */
public class Lesson {
    private String videoURL;
    private Program program;

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
    
    public Program getProgram(){
        return this.program;
    }
}
