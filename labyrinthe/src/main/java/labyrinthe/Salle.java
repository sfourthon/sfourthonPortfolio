/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labyrinthe;

/**
 *
 * @author sfourthon
 */
public class Salle implements ISalle {

    private int x;
    private int y;
    private ESalle type;
    private IEtage etage;
    
    /**
     * Constructeur de la classe Salle.
     * @param x Coordonnée x de la Salle
     * @param y Coordonnée y de la Salle
     * @param type Type de la Salle
     * @param etage Etage ou est situé la Salle
     */
    public Salle(int x, int y, ESalle type, IEtage etage) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.etage = etage;
    }
    
    
    /**
     * Récupère la coordonnée x de la Salle
     * @return la Coordonnée x de la Salle
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Récupère la coordonnée y de la Salle
     * @return la Coordonnée y de la Salle
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Récupère le type de la Salle
     * @return le type de la Salle
     */
    @Override
    public ESalle getType() {   
        return type;
    }
    
    
    /**
     * Récupère l'etage ou est la Salle
     * @return l'etage ou est la Salle
     */
    @Override
    public IEtage getEtage() {
        return etage;
    }
    
    /**
     * Vérifie si une Salle est adjacente a une autre horizontalement, verticalement, ou par rapport a l'etage d'au dessus ou d'en dessous si c'est un escalier
     * @param autre la salle avec qui on veut vérifier l'adjacence
     * @return Si c'est adjacent ou non
     */
    @Override
    public boolean estAdjacente(ISalle autre) {
        boolean estAdjacent = false;

        //Si autre est nulle ou si c'est la meme salle, alors c'est pas adjacent
        if (autre == null || this.equals(autre)) {
            estAdjacent = false;
        } 
        
        else if (this.etage.getNum() == autre.getEtage().getNum()) {
            //Vérifie les salles adjacentes horizontalement et verticalement
            boolean salleVoisineHorizontal = this.y == autre.getY()
                    && (this.x == autre.getX() + 1 || this.x == autre.getX() - 1);
            
            boolean salleVoisineVerticale = this.x == autre.getX()
                    && (this.y == autre.getY() + 1 || this.y == autre.getY() - 1);
               
            if(salleVoisineHorizontal == true || salleVoisineVerticale == true){
                estAdjacent = true;
            }
        } 
        
        else if (this.x == autre.getX() && this.y == autre.getY()) {
            //Vérifie que un escalier montant est adjacent a la salle de memes coordonnées dans l'étage au dessus
            boolean montantVersDescendant = this.type == ESalle.ESCALIER_MONTANT
                    && autre.getType() == ESalle.ESCALIER_DESCENDANT
                    && autre.getEtage().getNum() == this.etage.getNum() + 1;
            
            //Vérifie que un escalier descendant est adjacent a la salle de memes coordonnées dans l'étage en dessous
            boolean descendantVersMontant = this.type == ESalle.ESCALIER_DESCENDANT
                    && autre.getType() == ESalle.ESCALIER_MONTANT
                    && autre.getEtage().getNum() == this.etage.getNum() - 1;
 
            if(montantVersDescendant == true || descendantVersMontant == true){
                estAdjacent = true;
            }
        }
 
        return estAdjacent;
    }
    
    
    /**
     * Vérifie si 2 objets sont égaux
     * @param obj un objet
     * @return si les 2 objets sont égaux ou nonn
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Salle autre = (Salle) obj;
        return this.x == autre.getX()
                && this.y == autre.getY()
                && this.etage.getNum() == autre.getEtage().getNum();
    }
 
    
    /**
     * Créer un hashcode
     * @return le hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.x;
        hash = 31 * hash + this.y;
        hash = 31 * hash + this.etage.getNum();
        return hash;
    }



    
}
