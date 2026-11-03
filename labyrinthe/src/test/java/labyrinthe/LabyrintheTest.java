/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package labyrinthe;

import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import personnages.Heros;

/**
 *
 * @author gothmog
 */
public class LabyrintheTest {

    private ILabyrinthe labyrinthe;


    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        labyrinthe = new Labyrinthe();
    }

    @After
    public void tearDown() {
    }
    
    
    @Test
    public void testSallesAccessibles() {
        ISalle position = null;
        boolean trouve = false;
        for (ISalle salle : labyrinthe) {
            if (!trouve) {
                position = salle;
                trouve = true;
            }
        }
        Heros heros = new Heros(position);
        Collection<ISalle> sallesAccessibles = labyrinthe.sallesAccessibles(heros);
 
        //Toutes les salles qui sont adjacentes a la salle du heros sont accessibles
        for (ISalle salle : labyrinthe) {
            if (position.estAdjacente(salle)) {
                assertTrue(sallesAccessibles.contains(salle));
            }
        }
 
        //Aucune salles non adjacentes est salles accessibles 
        for (ISalle salle : labyrinthe) {
            if (!position.estAdjacente(salle)) {
                assertFalse(sallesAccessibles.contains(salle));
            }
        }
    }


    
    
    @Test
    public void testGetEntree() {
        //L'entree qu'on recupere est pas nulle
        ISalle entree = labyrinthe.getEntree();
        assertNotNull(entree);
 
        //L'entree qu'on recupere est bien une salle de type ENTREE
        assertEquals(ESalle.ENTREE, entree.getType());
    }
 
    @Test
    public void testGetSortie() {
        //La sortie qu'on recupere est pas nulle
        ISalle sortie = labyrinthe.getSortie();
        assertNotNull(sortie);
 
        //La sortie qu'on recupere est bien une salle de type SORTIE
        assertEquals(ESalle.SORTIE, sortie.getType());
    }
 
    @Test
    public void testGetEtageCourant() {
        //Au début l'etage courant est 1
        assertEquals(1, labyrinthe.getEtageCourant().getNum());
    }
 
    @Test
    public void testSetEtageCourant() {
        //L'etage courant change si on en set un autre
        IEtage etage2 = new Etage(2);
        labyrinthe.setEtageCourant(etage2);
        assertEquals(etage2, labyrinthe.getEtageCourant());
    }


}