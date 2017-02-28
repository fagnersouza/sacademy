/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fagnersouza.academy;

import br.com.fagnersouza.log.Tracer;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;


/**
 *
 * @author fagner.souza
 */
class Contents {
    private File file;
    enum XML{Title,Tags,Description,Sequence,url,Training,Lesson,TrainingInformation};
        
    Contents(File xml){
        if(xml == null || !xml.exists())
            throw new IllegalArgumentException("XML File do not exist");

        this.file = xml;
    }

    public Map<String,Program> getLessons(){
        SAXBuilder builder = new SAXBuilder();

        try {
            Document document = (Document) builder.build(file);
            Element rootNode = document.getRootElement();
            Map<String,Program> programming = new HashMap<>();

            List list = rootNode.getChildren(XML.Lesson.toString());

           for (Object list1 : list) {
               Element node = (Element) list1;
               Program p = new Program();
               p.setTitle(node.getChildText(XML.Title.toString()));
               p.setTags(node.getChildText(XML.Tags.toString()));
               p.setDescription(node.getChildText(XML.Description.toString()));
               p.setSequence(Integer.parseInt(node.getChildText(XML.Sequence.toString())));
               programming.put(node.getAttribute(XML.url.toString()).getValue(), p);
           }

           return programming;
        } catch (IOException | JDOMException io) {
              Tracer.logger.error("", io);
        }        

        return null;            
    }

    public Program getTraining(){
       SAXBuilder builder = new SAXBuilder();

       try { 
            Document document = (Document) builder.build(file);
            Element rootNode = document.getRootElement();

            List list = rootNode.getChildren(XML.TrainingInformation.toString());

            if(!list.isEmpty()){
                Program tp = new Program();
                Element node = (Element) list.get(0);
                tp.setTitle(node.getChildText(XML.Title.toString()));
                tp.setTags(node.getChildText(XML.Tags.toString()));
                tp.setDescription(node.getChildText(XML.Description.toString()));
                tp.setSequence(Integer.parseInt(node.getChildText(XML.Sequence.toString())));

                return tp;
            }else{
                Tracer.logger.error("Program is not correct");
            }
        } catch (IOException | JDOMException io) {
            Tracer.logger.error("", io);
        }        

        return null;            
    }     
    
    public static String XMLFrom(Training training){        
        if(training == null || training.getProgram() == null)
            return "";
        
        Program p = training.getProgram();
        
        Element root = new Element(XML.Training.toString());
        Document doc = new Document(root);
        
        doc.getRootElement().addContent(mountElement(XML.TrainingInformation.toString(),p));

        for (Iterator it = training.iterator(); it.hasNext();) {
            Lesson lesson = (Lesson)it.next();
            Program lp = lesson.getProgram();
            
            Element le = mountElement(XML.Lesson.toString(), lp);
            
            le.setAttribute(new Attribute("url",lesson.getVideoURL()));
                        
            doc.getRootElement().addContent(le);
        }
        
        XMLOutputter xmlOutput = new XMLOutputter();
        
        return xmlOutput.outputString(doc);
    }  
    
    private static Element mountElement(String elementName, Program p){
        Element el = new Element(elementName);
        el.addContent(new Element(XML.Title.toString()).setText(p.getTitle()));
        el.addContent(new Element(XML.Tags.toString()).setText(p.getTags()));
        el.addContent(new Element(XML.Description.toString()).setText(p.getDescription()));
        el.addContent(new Element(XML.Sequence.toString()).setText(Integer.toString(p.getSequence())));   
        
        return el;
    }
     
}
