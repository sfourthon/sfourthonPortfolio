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
import vue2D.javafx.Dessin;

/**
 *
 * @author sfourthon
 */
public class DessinTest {
    private IEtage etage1;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        etage1 = new Etage(1);
    }

    @After
    public void tearDown() {
    }
    
    
    @Test
    public void testDistanceManhattan() {
        Salle s1 = new Salle(5, 5, ESalle.NORMALE, etage1);
 
        //2 salles identiques 
        Salle s2 = new Salle(5, 5, ESalle.NORMALE, etage1);
        assertEquals(0, Dessin.distanceManhattan(s1, s2));
 
        //Ecart de 3 en x
        Salle s3 = new Salle(8, 5, ESalle.NORMALE, etage1);
        assertEquals(3, Dessin.distanceManhattan(s1, s3));
 
        //Ecart de 4 en y
        Salle s4 = new Salle(5, 9, ESalle.NORMALE, etage1);
        assertEquals(4, Dessin.distanceManhattan(s1, s4));
 
        //Ecart de 3 en x et de 4 en y 
        Salle s5 = new Salle(8, 9, ESalle.NORMALE, etage1);
        assertEquals(7, Dessin.distanceManhattan(s1, s5));

 
        //Ecart negatif de -3 en x et -2 en y
        Salle s6 = new Salle(2, 3, ESalle.NORMALE, etage1);
        assertEquals(5, Dessin.distanceManhattan(s1, s6));
    }
 
    @Test
    public void testCalculOpaciteAssombrissement() {
        //Sur la salle du heros
        assertEquals(0.0, Dessin.calculOpaciteAssombrissement(0), 0.0001);
 
        //Si l'opacité est plus grande que l'opacité max alor elle est égale a l'opacité max (0.85)
        assertEquals(0.85, Dessin.calculOpaciteAssombrissement(8), 0.0001);
        assertEquals(0.85, Dessin.calculOpaciteAssombrissement(100), 0.0001);
    }

    
}
