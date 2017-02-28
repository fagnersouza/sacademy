/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fagnersouza.academy;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fagner.souza
 */
public class ContentsTest {
    Training training;
    
    public ContentsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Program tp = new Program();
        tp.setTitle("Treinamento de JAM");
        tp.setDescription("A plataforma JAM é uma IDE de desenvolvimento Java");
        tp.setSequence(1);
        tp.setTags("JAM, JSI");
        
        Training t = new Training();
        t.setProgram(tp);
        
        Program lp = new Program();
        lp.setTitle("Introdução ao ambiente JAM");
        lp.setDescription("Esta aula consiste na apresentação da IDE");
        lp.setTags("Intro,JAM");
        lp.setSequence(1);

        Lesson l = new Lesson();
        l.setProgram(lp);
        l.setVideoURL("1-SAcademy-jam-into.mp4");

        t.add(l);
        
        training = t;
    
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLessons method, of class Contents.
     */
    @Test
    public void testGetLessons() {
        System.out.println("getLessons");
        Contents instance = null;
        Map<String, Program> expResult = null;
        Map<String, Program> result = instance.getLessons();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTraining method, of class Contents.
     */
    @Test
    public void testGetTraining() {
        System.out.println("getTraining");
        Contents instance = null;
        Program expResult = null;
        Program result = instance.getTraining();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of XMLFrom method, of class Contents.
     */
    @Test
    public void testXMLFrom_Training() {
        System.out.println("XMLFrom");
        
        String expResult = "";
        String result = Contents.XMLFrom(training);
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
