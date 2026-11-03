package odomo;

import org.junit.Assert;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests des méthodes de la classe Chauffage.
 */
public class ChauffageTest {
    
    @Test
    public void testInitialiser() {
    int[][] creneauxTest = {{1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}};
    Assert.assertArrayEquals(creneauxTest, creneauxTest);
    assertNotNull(Chauffage.temperEco);
    assertNotNull(Chauffage.temperNormal);   
    }
    
    @Test
    public void testMatriceCreneaux() {
    Odomo.mode = Odomo.MODE_CHAUFFAGE;
    Ecran.typeHisto = Ecran.TYPE_HISTO_CHAUFFAGE;  
    //données testées :
    Chauffage.creneau1 = new int[][]{{6,9},{6,9},{6,9},{6,9},{6,9},{1, 0}, {1, 0}};
    Chauffage.creneau2 = new int[][]{{1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}, {1, 0}};
    boolean[][] matriceCreneaux = new boolean[8][24];
    assertEquals("Nombre de lignes incorrect", 8, matriceCreneaux.length);
    assertNotNull(matriceCreneaux[0]);
    assertEquals("Nombre de colonnes incorrect", 24, matriceCreneaux[0].length);
    assertNotNull(matriceCreneaux[0][0]);
    // valeurs correctes ?
    for (int l = 0; l < matriceCreneaux.length - 1; l++) {
            for (int c = 0; c < matriceCreneaux[l].length - 1; c++) {
                matriceCreneaux[l][c] = l < 5 && (c >= 6 && c < 9);
            }
        }
        assertArrayEquals(matriceCreneaux, Chauffage.matriceCreneaux());
    }
}
