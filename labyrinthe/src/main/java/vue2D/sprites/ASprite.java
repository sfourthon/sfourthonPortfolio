/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import java.util.Collection;
import javafx.scene.canvas.GraphicsContext;
import labyrinthe.ISalle;
import javafx.scene.image.Image;
import personnages.IPersonnage;

/**
 *
 * @author sfourthon
 */
public abstract class ASprite implements ISprite{
    
    IPersonnage personnage;
    private Image image;
    private int x;
    private int y;
    private int XCible;
    private int YCible;
    private int vitesse = 2; 

    /**
     * Constructeu de la classe abstraite ASprite
     * @param personnage le personnage a afficher
     */
    public ASprite(IPersonnage personnage) {
        this.personnage = personnage;
        ISalle positionInitiale = personnage.getPosition();
        if (positionInitiale != null) {
            this.x = positionInitiale.getX() * vue2D.AVue.UNITE;
            this.y = positionInitiale.getY() * vue2D.AVue.UNITE;
            this.XCible = this.x;
            this.YCible = this.y;
        }
    }
     
    /**
     * Avance le sprite vers la position ciblé
     */
    private void avancer() {
        
        //Quand x est inférieur on ajoute la vitesse
        if (this.x < this.XCible) {
            this.x = this.x + this.vitesse;
            if (this.x > this.XCible) {
                this.x = this.XCible;
            }
        } 
        
        //Quand x est supérieur on ajoute la vitesse
        else if (this.x > this.XCible) {
            this.x = this.x - this.vitesse;
            if (this.x < this.XCible) {
                this.x = this.XCible;
            }
        }
        
        //Quand y est inférieur on ajoute la vitesse  
        if (this.y < this.YCible) {
            this.y = this.y + this.vitesse;
            if (this.y > this.YCible) {
                this.y = this.YCible;
            }
            
        //Quand y est supérieur on enlève la vitesse    
        } else if (this.y > this.YCible) {
            this.y = this.y - this.vitesse;
            if (this.y < this.YCible) {
                this.y = this.YCible;
            }
        }
    }

    /**
     * Indique si le personnage est en mouvement ou non
     * @return true si le personnage est en mouvement
     */
    public boolean estEnMouvement() {
        boolean mouvement = false;
        if(this.x != this.XCible || this.y != this.YCible){
            mouvement = true;
        }
        return mouvement;
    }

    /**
     * dessine l'image du personnage 
     * @param g graphique pour dessiner l'image
     */
    @Override
    public void dessiner(GraphicsContext g) {
        avancer();
        g.drawImage(this.image, this.x, this.y);
    }
    
    /**
     * Récupère le personnage 
     * @return le personnage
     */
    public IPersonnage getPersonnage(){
        return this.personnage;
    }
    
    /**
     * Change le personnage par un nouveau personnage 
     * @param nouveauPersonnage le nouveau personnage 
     */
    public void getPersonnage(IPersonnage nouveauPersonnage){
        this.personnage = nouveauPersonnage;
    }
    
    
    
    /**
     * récupère la coordonnée x
     * @return la coordonnée x
     */
    public int getX(){
        return this.x;
    }
    
    /**
     * récupère la coordonnée y
     * @return la coordonnée y
     */
    public int getY(){
        return this.y;
    }
    
    /**
     * Change les valeurs des coordonnées x et y
     * @param x2 Nouvelle valeur de la coordonnée x
     * @param y2 Nouvelle valeur de la coordonnée y
     */
    @Override
    public void setCoordonnees(int x2, int y2) {
        this.x = x2;
        this.y = y2;
    }

    /**
     * Le choix de la prochaine salle du personnage qui s'effectue que apres le deplacement
     * @param sallesAccessibles les salles accessibles de la salle ou le personnage a afficher est situé
     * @return la salle choisie par le personnage a afficher
     */
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles){
        ISalle choix;
        if (this.estEnMouvement()) {
            choix = this.getPosition();
        } else {
            choix = this.personnage.faitSonChoix(sallesAccessibles);
        }
        return choix;
    }
    
    /**
     * Récupère l'image du personnage
     * @return l'image du personnage
     */
    public Image getImage(){
        return this.image;
    }
    
    /**
     * Change l'image du personnage
     * @param nouvelleImage la nouvelle image du personnage
     */
    public void setImage(Image nouvelleImage){
        this.image = nouvelleImage;
    }
    
    /**
     * Récupère la position du personnage
     * @return la position du personnage
     */
    @Override
    public ISalle getPosition() {
        return this.personnage.getPosition();
    }

    /**
     * Change la position d'un personnage et modifie ses coordonnées cibles par celles de la salle ou il souhaite aller
     * @param s la nouvelle salle ou sera le personnage
     */
    @Override
    public void setPosition(ISalle s) {
        this.personnage.setPosition(s);
        this.XCible = s.getX() * vue2D.AVue.UNITE;
        this.YCible = s.getY() * vue2D.AVue.UNITE;
    }
}