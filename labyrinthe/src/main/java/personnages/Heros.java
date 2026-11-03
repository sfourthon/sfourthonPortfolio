/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.Collection;
import labyrinthe.ISalle;

/**
 *
 * @author sfourthon
 */
public class Heros extends APersonnage{
    
    public ISalle salleChoisie;

    /**
     * Constructeur de la classe heros
     * @param positionInitiale la position initiale du heros 
     */
    public Heros(ISalle positionInitiale) {
        super(positionInitiale);
        this.salleChoisie = positionInitiale;
    }
    
    /**
     * Donne la salle finale choisie par le personnage parmi les salles accessibles
     * @param sallesAccessibles les salles accessibles de la salle ou est le personnage
     * @return la salle finale choisi
     */
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        ISalle salleFinale;
        if (sallesAccessibles.contains(this.salleChoisie)){
            salleFinale = this.salleChoisie;
        } else salleFinale = this.getPosition();
        return salleFinale;
    }
    
}
