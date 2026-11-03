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
public abstract class APersonnage implements IPersonnage{
    private ISalle position;
    
    public APersonnage(ISalle positionInitiale){
        this.position = positionInitiale;
    }

    /**
     * Récupère la position du personnage
     * @return la position du personnage
     */
    @Override
    public ISalle getPosition() {
        return this.position;
    }

    /**
     * Modifie la position du personnage
     * @return la position du personnage
     */
    @Override
    public void setPosition(ISalle s) {
        this.position = s;
    }
    
    /**
     * 
     * @param sallesAccessibles
     * @return 
     */
    @Override
    public abstract ISalle faitSonChoix(Collection<ISalle> sallesAccessibles);
    
}
