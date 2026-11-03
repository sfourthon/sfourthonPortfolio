package vue2D.javafx;

import java.util.Collection;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import vue2D.AVue;
import vue2D.sprites.ISprite;
import labyrinthe.IEtage;
import vue2D.sprites.HerosSprite;

/**
 *
 * @author INFO Professors team
 */
public class Dessin extends Canvas {

    private Collection<ISprite> sprites;
    private int unite;

    // donnees labyrinthe labyrinthe
    private ILabyrinthe labyrinthe;
    private ISalle entree;
    private ISalle sortie;
    private int largeur;
    private int hauteur;

    private GraphicsContext tampon;
    private Image murImage;
    private Image solImage;
    private Image escalierM;
    private Image escalierD;
    private Image sortieImage;
    private int tailleLinkH = 6;
    private int tailleLinkL = 2;
    
    private static final int PORTEE_ECLAIRAGE = 8;
    private static final double OPACITE_MAX = 0.85;

    /**
     * Constructeur de la classe Dessin
     * @param labyrinthe le labyrinthe ou l'on joue
     * @param sprites tous les sprites qu'on possèdeé
     */
    public Dessin(ILabyrinthe labyrinthe, Collection<ISprite> sprites) {
        this.labyrinthe = labyrinthe;
        this.sprites = sprites;
        this.unite = AVue.UNITE;
        entree = labyrinthe.getEntree();
        sortie = labyrinthe.getSortie();
        largeur = labyrinthe.getEtageCourant().getLargeur();
        hauteur = labyrinthe.getEtageCourant().getHauteur();
        setWidth(largeur * unite);
        setHeight(hauteur * unite);
        tampon = this.getGraphicsContext2D();
        chargementImages();
        dessinFond();
    }

    /**
     * Charge les images qui vont etre utilise
     */
    public void chargementImages() {
        murImage = new Image("file:icons/mur0.gif");
        solImage = new Image("file:icons/pyramide.png");
        escalierM = new Image("file:icons/up.gif");
        escalierD = new Image("file:icons/down.gif");
        sortieImage = new Image("file:icons/sortie.gif");
    }

    /**
     * Dessine le fond du labyrinthe
     */
    public void dessinFond() {
        tampon.drawImage(solImage, 0, 0, unite * largeur,
                unite * hauteur);
    }

    /**
     * Boucle qui dessine toutes les salles de l'etage
     * @param etage l'etage qu'on veut dessiner
     */
    public void dessinSalles(IEtage etage) {
        for (ISalle s : etage) {
            Color c = Color.rgb(200, 200, 200);
            dessinSalle(s, c);
        }
    }

    /**
     * Dessine les salles selon la case
     * @param s La salle qu'on dessine
     * @param c la couleur
     */
    public void dessinSalle(ISalle s, Color c) {
        int positionX = s.getX()*unite;
        int positionY = s.getY()*unite;
        switch (s.getType()) {
            case NORMALE: tampon.drawImage(murImage, positionX, positionY, unite, unite);
            tampon.setFill(c);
            break;
            
            case ENTREE: tampon.setFill(Color.GREEN);
            tampon.fillRect(positionX, positionY, unite, unite);
            break;
            
            case SORTIE: tampon.drawImage(sortieImage, positionX, positionY, unite, unite);
            break;
            
            case ESCALIER_MONTANT : tampon.drawImage(escalierM, positionX, positionY, unite, unite);
            break;
            
            case ESCALIER_DESCENDANT : tampon.drawImage(escalierD, positionX, positionY, unite, unite);
            break;
        }
        assombrirSelonDistance(s, positionX, positionY);
    }
    
    /**
     * Calcule la position du Heros
     */
    private ISalle positionHeros() {
        ISalle position = null;
        for (ISprite sprite : sprites) {
            if (sprite instanceof HerosSprite) {
                position = sprite.getPosition();
            }
        }
        return position;
    }
    
    /**
     * Modifie la couleur des cases pour faire un effet sombre
     * @param s la salle ou l'on doit modifier la couleur
     * @param positionX la position x de la salle
     * @param positionY la position y de la salle
     */
    private void assombrirSelonDistance(ISalle s, int positionX, int positionY){
        ISalle positionHeros = positionHeros();
        if(positionHeros != null){
            int distance = distanceManhattan(s, positionHeros);
            double opacite = calculOpaciteAssombrissement(distance);
            tampon.setFill(Color.rgb(0, 0, 0, opacite));
            tampon.fillRect(positionX, positionY, unite, unite);
        }   
    }
    
    /**
     * A FINIR
     * @param s1
     * @param s2
     * @return 
     */
    public static int distanceManhattan(ISalle s1, ISalle s2){
        int distanceX = s1.getX() - s2.getX();
        if (distanceX < 0) {
            distanceX = -distanceX;
        }
        int distanceY = s1.getY() - s2.getY();
        if (distanceY < 0) {
            distanceY = -distanceY;
        }
        return distanceX + distanceY;

    }
    
    /**
     * Calcule l'opacité de l'assombrissement de la case selon la distance 
     * @param distance la distance entre la salle et la position du personnage
     * @return l'opacité
     */
    public static double calculOpaciteAssombrissement(int distance) {
        double opacite = (double) distance / PORTEE_ECLAIRAGE;
        if (opacite > OPACITE_MAX) {
            opacite = OPACITE_MAX;
        }
        return opacite;
    }
    
    /**
     * Dessine les sprites des personnages
     * @param etageCourant l'etage actuelle de notre personnage
     */
    public void dessinSprites(IEtage etageCourant) {
    for (ISprite sprite : sprites) {
        if (sprite.getPosition().getEtage().equals(etageCourant)) {
            sprite.dessiner(tampon);
        }
    }
}

}
