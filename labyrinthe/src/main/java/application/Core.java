package application;

import java.util.Collection;
import java.util.Random;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import personnages.Heros;
import personnages.Monstre;
import vue2D.IVue;
import vue2D.sprites.HerosSprite;
import vue2D.sprites.ISprite;
import vue2D.sprites.MonstreSprite;

/**
 *
 * @author arpecher
 */
public class Core {
    ISprite heros;
    ILabyrinthe labyrinthe;

    /**
     * initialise le labyrinthe ou l'on va jouer
     */
    protected void initLabyrinthe() {
        // creation du labyrinthe
        labyrinthe = new labyrinthe.Labyrinthe();
    }   

    /**
     * Initialise tous les sprites qui vont etre utilisés dans le jeu
     * @param vue la vue ou l'on doit mettre les sprites
     */
    protected void initSprites(IVue vue) {
        // creation du heros 
        Heros h = new personnages.Heros(labyrinthe.getEntree());
        this.heros = new HerosSprite(h, labyrinthe);
        vue.add(this.heros);
        
        // creation des monstres et placement de ceux-ci aléatoirement
        Random random = new Random();
        int nombreDeMonstres = 10;
        int nombreDeSalles = labyrinthe.size();

        for (int i = 0; i < nombreDeMonstres; i++) {
            int index = random.nextInt(nombreDeSalles);
            int j = 0;
            ISalle positionInitiale = null;
            for (ISalle salle : labyrinthe) {
                if (j == index) {
                    positionInitiale = salle;
                }
                j++;
            }
            Monstre m = new personnages.Monstre(positionInitiale);
            vue.add(new MonstreSprite(m));
        }
    }

    /**
     * La boucle qui fait fonctionner le jeu
     * @param vue notre vue
     */
    protected void jeu(IVue vue) {
        // boucle principale
        ISalle destination = null;
        while (!labyrinthe.getSortie().equals(heros.getPosition())) {
            // ajustement etage courant: celui du héros
            labyrinthe.setEtageCourant(heros.getPosition().getEtage());
            // choix et deplacements de chaque sprite
            for (ISprite s : vue) {
                Collection<ISalle> sallesAccessibles = labyrinthe.sallesAccessibles(s);
                destination = s.faitSonChoix(sallesAccessibles); // on demande au personnage de faire son choix de salle
                s.setPosition(destination); // deplacement
                
            }
            // detection des collisions
            boolean collision = false;
            ISprite monstre = null;
            for (ISprite s : vue) {
                if (s != heros) {
                    if (s.getPosition() == heros.getPosition()) {
                        System.out.println("Collision !!");
                        collision = true;
                        monstre = s;
                    }
                }
            }
            if (collision) {
                vue.remove(monstre);
                vue.remove(heros);
                System.out.println("Perdu !");
                System.out.println("Plus que " + vue.size() + " personnages ...");
            }

            temporisation(10);
        }
        System.out.println("Gagné!");
    }
    
    protected void temporisation(int nb) {
        try {
            Thread.sleep(nb); // pause de nb millisecondes
        } catch (InterruptedException ie) {
        }
    }
}
