/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labyrinthe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sfourthon
 */
public class EtageTest {
    @BeforeClass
    public static void setUpClass() {
    }
 
    @AfterClass
    public static void tearDownClass() {
    }
 
    @Before
    public void setUp() {
    }
 
    @After
    public void tearDown() {
    }
 
    @Test
    public void testGetLargeur() {
        //La largeur de base
        Etage etage = new Etage();
        assertEquals(40, etage.getLargeur());
    }
 
    @Test
    public void testGetHauteur() {
        //La hauteur de base
        Etage etage = new Etage();
        assertEquals(40, etage.getHauteur());
    }
 
    @Test
    public void testGetNum() {
        //L'étage de base 
        Etage etage1 = new Etage();
        assertEquals(1, etage1.getNum());

    }
 
}
