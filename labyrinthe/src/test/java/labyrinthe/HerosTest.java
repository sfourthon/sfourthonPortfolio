/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labyrinthe;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import personnages.Heros;
import java.util.Collection;

/**
 *
 * @author sfourthon
 */
public class HerosTest {
    
    private IEtage etage1;
    private ISalle salleDepart;
 
    
    @BeforeClass
    public static void setUpClass() {
    }
 
    @AfterClass
    public static void tearDownClass() {
    }
 
    @Before
    public void setUp() {
        etage1 = new Etage(1);
        salleDepart = new Salle(5, 5, ESalle.NORMALE, etage1);
    }
 
    @After
    public void tearDown() {
    }
}
