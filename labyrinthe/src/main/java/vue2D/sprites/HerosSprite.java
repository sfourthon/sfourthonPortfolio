/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import java.util.Collection;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import personnages.Heros;
import personnages.IPersonnage;

/**
 *
 * @author sfourthon
 */
public class HerosSprite extends ASprite implements EventHandler<KeyEvent> {

    private ILabyrinthe labyrinthe;

    public HerosSprite(IPersonnage personnage, ILabyrinthe labyrinthe) {
        super(personnage);
        this.labyrinthe = labyrinthe;
        Image image = new Image("file:icons/link/LinkRunShieldD8.gif");
        this.setImage(image);
        this.personnage.setPosition(labyrinthe.getEntree());
    }
    
    /**
     * Permet au personnage de bouger selon la touche appuyée
     * @param t un evenement de touche
     */
    @Override
    public void handle(KeyEvent t) {
        Heros heros = (Heros) getPersonnage();
        ISalle salleActuelle = heros.getPosition();
        ISalle prochaineSalle = null;

        switch (t.getCode()) {
            case LEFT:
                prochaineSalle = calculCoordonneesProchaineSalle(-1, 0, salleActuelle);
                break;
            case RIGHT:
                prochaineSalle = calculCoordonneesProchaineSalle(1, 0, salleActuelle);
                break;
            case UP:
                prochaineSalle = calculCoordonneesProchaineSalle(0, -1, salleActuelle);
                break;
            case DOWN:
                prochaineSalle = calculCoordonneesProchaineSalle(0, 1, salleActuelle);
                break;
            case M:
                // Un escalier montant a les memes coordonnées entre 2 étages
                prochaineSalle = calculCoordonneesProchaineSalle(0, 0, salleActuelle);
                break;
            case D:
                // Un escalier descendant a les memes coordonnées entre 2 étages
                prochaineSalle = calculCoordonneesProchaineSalle(0, 0, salleActuelle);
                break;
                
            default:
                break;
        }

        if (prochaineSalle != null) {
            heros.salleChoisie = prochaineSalle;
        }
    }

    /**
     * Cherche parmi les salles accessibles celles correspondant aux coordonnées avec les décalages
     * @param x décalage en x 
     * @param y décalage en y 
     * @param salleActuelle la salle ou l'on est
     * @return la prochaine salle si elle est accessible
     */
    private ISalle calculCoordonneesProchaineSalle(int x, int y, ISalle salleActuelle) {
        ISalle prochaineSalle = salleActuelle;
        int xCible = salleActuelle.getX() + x;
        int yCible = salleActuelle.getY() + y;

        Collection<ISalle> sallesAccessibles = labyrinthe.sallesAccessibles(getPersonnage());
        for (ISalle salle : sallesAccessibles) {
            if (salle.getX() == xCible && salle.getY() == yCible) {
                prochaineSalle = salle;
            }
        }
        return prochaineSalle;
    }
}
