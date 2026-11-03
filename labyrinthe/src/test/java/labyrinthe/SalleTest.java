/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
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
 * @author gothmog
 */
public class SalleTest {

    private IEtage etage1;
    private IEtage etage2;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        etage1 = new Etage(1);
        etage2 = new Etage(2);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetX() {
        //La coordonnee x qu'on récupère est bien celle de la salle
        Salle s1 = new Salle(5, 8, ESalle.NORMALE, etage1);
        assertEquals(5, s1.getX());
    }
 
    @Test
    public void testGetY() {
        //La coordonnee y qu'on récupère est bien celle de la salle
        Salle s1 = new Salle(5, 8, ESalle.NORMALE, etage1);
        assertEquals(8, s1.getY());
    }
 
    @Test
    public void testGetType() {
        //Le type qu'on récupère est bien le type de la salle
        Salle s1 = new Salle(5, 8, ESalle.ENTREE, etage1);
        assertEquals(ESalle.ENTREE, s1.getType());
    }
 
    @Test
    public void testGetEtage() {
        
        //L'étage qu'on récupère est bien l'etage de la salle
        Salle s1 = new Salle(5, 8, ESalle.NORMALE, etage1);
        assertEquals(etage1, s1.getEtage());
    }

    

    @Test
    public void testEstAdjacente() {
        Salle s1 = new Salle(5, 5, ESalle.NORMALE, etage1);
        Salle s2 = new Salle(6, 5, ESalle.NORMALE, etage1);
        
        //Notre salle est adjacente a une salle qui a un x de 1 de plus que nous
        assertTrue(s1.estAdjacente(s2));
        
        //Notre salle est adjacente a une salle qui a un x de 1 de moins que nous
        Salle s3 = new Salle(4, 5, ESalle.NORMALE, etage1);
        assertTrue(s1.estAdjacente(s3));
        
        //Notre salle est adjacente a une salle qui a un y de 1 de plus que nous
        Salle s4 = new Salle(5, 6, ESalle.NORMALE, etage1);
        assertTrue(s1.estAdjacente(s4));
        
        //Notre salle n'est pas adjacente a une salle qui est en diagonale de nous
        Salle s5 = new Salle(6, 6, ESalle.NORMALE, etage1);
        assertFalse(s1.estAdjacente(s5));
        
        //Notre salle n'est pas adjacente a une salle qui a un x de 2 de plus que nous
        Salle s6 = new Salle(7, 5, ESalle.NORMALE, etage1);
        assertFalse(s1.estAdjacente(s6));
        
        //Une salle n'est pas adjacente a elle meme
        assertFalse(s1.estAdjacente(s1));
        assertFalse(s1.estAdjacente(null));  
        
        
        //Un escalier montant est adjacent a l'escalier descendant qui est aux memes coordonnées a l'étage du dessus
        Salle montant = new Salle(3, 3, ESalle.ESCALIER_MONTANT, etage1);
        Salle descendant = new Salle(3, 3, ESalle.ESCALIER_DESCENDANT, etage2);
        assertTrue(montant.estAdjacente(descendant));
        
        //Un escalier descendant est adjacent a l'escalier montant qui est aux memes coordonnées a l'étage du dessous
        assertTrue(descendant.estAdjacente(montant));
        
        //Pas d'adjacence si les coordonnees des escaliers sont differentes
        Salle descendantAilleurs = new Salle(4, 3, ESalle.ESCALIER_DESCENDANT, etage2);
        assertFalse(montant.estAdjacente(descendantAilleurs));
              
        //Si les étages ne sont pas consécutifs alors les escaliers sont pas adjacents
        IEtage etage3 = new Etage(3);
        Salle descendantEtage3 = new Salle(3, 3, ESalle.ESCALIER_DESCENDANT, etage3);
        assertFalse(montant.estAdjacente(descendantEtage3));
    }

    
    @Test
    public void testEquals() {
        //2 salles identiques
        Salle s1 = new Salle(5, 5, ESalle.NORMALE, etage1);
        Salle s2 = new Salle(5, 5, ESalle.NORMALE, etage1);
        assertEquals(s1, s2);
    
        //Coordonnées différentes
        Salle s3 = new Salle(6, 5, ESalle.NORMALE, etage1);
        assertNotEquals(s1, s3);
 
        //Etages différents
        Salle s4 = new Salle(5, 5, ESalle.NORMALE, etage2);
        assertNotEquals(s1, s4);
    
        //Une salle nulle
        assertNotEquals(s1, null);
    }
    
    @Test
    public void testHashCode() {
        //2 salles identiques doivent avoir le meme hashCode
        Salle s1 = new Salle(5, 5, ESalle.NORMALE, etage1);
        Salle s2 = new Salle(5, 5, ESalle.NORMALE, etage1);
        assertEquals(s1.hashCode(), s2.hashCode());
    }



}