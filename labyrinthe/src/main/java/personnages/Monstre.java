/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.Collection;
import java.util.Random;
import labyrinthe.ISalle;

/**
 *
 * @author sfourthon
 */
public class Monstre extends APersonnage {

    /**
     * Constructeur de la classe Monstre
     * @param positionInitiale la position initiale du monstre au lancement du jeu
     */
    public Monstre(ISalle positionInitiale) {
        super(positionInitiale);
    }

    /**
     * Choisit aléatoirement une salle parmi les salles accessibles
     * @param sallesAccessibles les salles accessibles depuis la salle actuelle
     * @return la salle choisie
     */
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        ISalle salleChoisie = this.getPosition();
        Random random = new Random();
        if (sallesAccessibles != null && !sallesAccessibles.isEmpty()) {
            int salleAleatoire = random.nextInt(sallesAccessibles.size());
            int i = 0;
            for (ISalle salle : sallesAccessibles) {
                if (i == salleAleatoire) {
                    salleChoisie = salle;
                }
                i++;
            }
        }
        return salleChoisie;
    }
}