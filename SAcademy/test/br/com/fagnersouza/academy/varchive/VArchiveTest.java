/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fagnersouza.academy.varchive;

import java.io.File;
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
public class VArchiveTest {
    private static VArchive varchive;
    public VArchiveTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        varchive = VArchive.getInstance();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class VArchive.
     */
    public void testSave() {
        System.out.println("save");
        File[] files = null;
        VArchive instance = null;
        boolean expResult = false;
        boolean result = instance.save(files);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dir method, of class VArchive.
     */
    @Test
    public void testDir() {
        System.out.println("dir");

        for(File f:varchive.dir()){
            System.out.println(f.getName());
        }
    }    
}
