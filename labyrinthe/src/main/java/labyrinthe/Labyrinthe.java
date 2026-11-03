package labyrinthe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import personnages.IPersonnage;

/**
 *
 * @author professor team
 */
public class Labyrinthe extends ArrayList<ISalle> implements ILabyrinthe {
    
    private IEtage etageCourant = new Etage();
    
    /**
     * Constructeur de la classe Labyrinthe, charge les 2 premiers étages 
     */
    public Labyrinthe() {
        try {
            IEtage etage1 = new Etage(1);
            etage1.charger("etages/etage1N.txt");
            this.addAll(etage1);
 
            IEtage etage2 = new Etage(2);
            etage2.charger("etages/etage2N.txt");
            this.addAll(etage2);
 
            this.etageCourant = etage1;
        } catch (IOException exception) {
            System.out.println("Erreur trouvé lors du chargement des étages : " + exception.getMessage());
        }
    }

    
    /**
     * Regarde quelles salles sont accessibles de notre salle actuelle
     * @param heros le personnage qu'on controle
     * @return les salles accessibles de notre salle
     */
    @Override
    public Collection<ISalle> sallesAccessibles(IPersonnage heros) {
        Collection<ISalle> sallesAccessibles = new ArrayList<>();
        ISalle salleActuelle = heros.getPosition();
        for(ISalle salle : this){
            if (salleActuelle.estAdjacente(salle)) {
                sallesAccessibles.add(salle);
            }
        }
        return sallesAccessibles;
    }

    /**
     * Récupère l'entrée d'un etage
     * @return l'entrée de l'etage
     */
    @Override
    public ISalle getEntree() {
        ISalle entree = null;
        for(ISalle salle : this){
            if(salle.getType() == ESalle.ENTREE){
                entree = salle;
            }
        }
        return entree;
    }

    /**
     * Récupère la sortie d'un etage
     * @return la sortie de l'etage
     */
    @Override
    public ISalle getSortie() {
        ISalle sortie = null;
        for(ISalle salle : this){
            if(salle.getType() == ESalle.SORTIE){
                sortie = salle;
            }
        }
        return sortie;
    }

    /**
     * Récupère l'etage courant
     * @return l'etage courant
     */
    @Override
    public IEtage getEtageCourant() {
        return this.etageCourant;
    }

    /**
     * Modifie la valeur de l'etage courant
     * @param etage la valeur modifié de l'etage courant
     */
    @Override
    public void setEtageCourant(IEtage etage) {
        this.etageCourant = etage;
    }
    
    /**
     * le chemin d'une salle a l'autre (pas faites)
     * @param u salle u 
     * @param v salle v
     * @return null (car pas faites)
     */
    @Override
    public Collection<ISalle> chemin(ISalle u, ISalle v) {
        return null;
    }
    
}
