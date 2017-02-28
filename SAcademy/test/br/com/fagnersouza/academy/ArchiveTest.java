/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fagnersouza.academy;

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
public class ArchiveTest {
    
    public ArchiveTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Archive.create();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPath method, of class Archive.
     */
   
    public void testGetPath() {
        System.out.println("getPath");
        String expResult = "";
        String result = Archive.getPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class Archive.
     */
    
    public void testCreate() {
        System.out.println("create");
        boolean expResult = false;
        boolean result = Archive.create();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRecord method, of class Archive.
     */
    @Test
    public void testCreateRecord() {
        System.out.println("createRecord");
        String dirName = "JAM";
        String fileName = "contents.xml";
        String contents;
        contents = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Training><TrainingInformation><Title>JAM</Title><Tags>JAM;Autoatendimento</Tags><Description>Java Development Kit for Advanced Management. Uma poderosa IDE para desenvolvimento Java orientado à ATM. Neste treinamento você será orientado sobre as capacidades dessa ferramenta, eum um curso iterativo desde a concepcão até a construcao completa de uma aplicacao de autoatendimento.</Description><Sequence>1</Sequence></TrainingInformation><Lesson url=\"C:\\Users\\fagner.souza\\Videos\\JAMSS_Introdução_original.mp4\"><Title>Introducao a JAM</Title><Tags>intro;JAM</Tags><Description>Voce aprendera sobre as principais funcionalidades da interface grafica do JAM, bem como criar um projeto do zero.</Description><Sequence>1</Sequence></Lesson></Training>";
        boolean expResult = false;
        boolean result = Archive.createRecord(dirName, fileName, contents);
        assertEquals(true, result);

    }
    
     @Test
    public void testMoveAsset() {
        String dirName = "JAM";
        String source = "C:\\Users\\fagner.souza\\Videos\\SAcademy_intro_original.mp4";
        String target = "1-SAcademy_intro_original.mp4";
        boolean result = Archive.copyAsset(source, target,dirName);
        assertEquals(true, result);
    }
    
}
