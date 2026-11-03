package vue2D.javafx;

import javafx.scene.Group;
import javafx.scene.Scene;
import labyrinthe.ILabyrinthe;
import vue2D.IVue;
import vue2D.AVue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import vue2D.sprites.ISprite;

/**
 *
 * @author INFO Professors team
 */
public class Vue extends AVue implements IVue {

    Dessin dessin;
    ILabyrinthe labyrinthe;
    public Scene scene;

    /**
     * Constructeur de la classe Vue
     * @param labyrinthe le labyrinthe ou l'on joue
     */
    public Vue(ILabyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
        dessin = new Dessin(labyrinthe, this);
        Group root = new Group();
        this.scene = new Scene(root);
        root.getChildren().add(dessin);
    }

    /**
     * Dessine le fond et l'étage du labyrinthe
     */
    @Override
    public void dessiner() {
        // recopie du fond (image); murs + salles
        dessin.dessinFond();
        // dessin des salles
        dessin.dessinSalles(labyrinthe.getEtageCourant());
        //dessin des sprites des personnages
        dessin.dessinSprites(labyrinthe.getEtageCourant());
    }
    
    /**
     * Ajoute un sprite et le lie au mouvement du personnage
     * @param sprite le sprite qu'on veut ajouter
     * @return true
     */
    @Override
    public boolean add(ISprite sprite) {
        super.add(sprite);
        // si le sprite est controlé par le clavier
        if (sprite instanceof EventHandler) {
            System.out.println("registering keylistener");
            // association de l'ecouteur sur le clavier avec le composant graphique principal
            this.scene.setOnKeyPressed((EventHandler) sprite);
        }
        return true;
    }

}
