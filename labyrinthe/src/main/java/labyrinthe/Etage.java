package labyrinthe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author INFO Professors team
 */
public class Etage extends ArrayList<ISalle> implements IEtage {

    private int largeur;
    private int hauteur;
    private int num;
    
    /**
     * Constructeur par défaut de la classe Etage
     */
    public Etage(){
        largeur = 40;
        hauteur = 40;
        num = 1;
    }
    
    /**
     * Constructeur de la classe Etage
     * @param id numéro que va prendre l'étage
     */
    public Etage(int id){
        this.num = id;
    }
    
    /**
     * Charge un étage selon le fichier
     * @param file le fichier d'un étage
     * @throws IOException 
     */
    @Override
    public void charger(String file) throws IOException {
        this.clear();
        List<String> lignes = Files.readAllLines(Paths.get(file));
        // dimensions
        String entete = lignes.get(0);
        String[] mots = entete.split(" ");
        largeur = Integer.parseInt(mots[0]);
        hauteur = Integer.parseInt(mots[1]);
        lignes.remove(0);
        // salles
        for (String ligne : lignes){
            mots = ligne.split(" ");
            ESalle type = StringEnESalle(mots[2]);
            int x = Integer.parseInt(mots[0]);
            int y = Integer.parseInt(mots[1]);
            ISalle salle = new Salle(x, y, type, this);
            this.add(salle);
        }
    }
    
    /**
     * Convertit les strings des fichiers en ESalle pour avoir le type
     * @param mot
     * @return 
     */
    private ESalle StringEnESalle(String mot){
        switch (mot) {
            case "N": return ESalle.NORMALE;
            case "E": return ESalle.ENTREE;
            case "S": return ESalle.SORTIE;
            case "D": return ESalle.ESCALIER_DESCENDANT;
            case "M": return ESalle.ESCALIER_MONTANT;
            default:
                throw new AssertionError();
        }
    }
    
    /**
     * Recupère la largeur de l'etage
     * @return la largeur de l'étage
     */
    @Override
    public int getLargeur() {
        return largeur;
    }

    /**
     * Recupère la hauteur de l'etage
     * @return la hauteur de l'étage
     */
    @Override
    public int getHauteur() {
        return hauteur;
    }  

    /**
     * Récupère le numéro de l'etage
     * @return le numéro de l'etage
     */
    @Override
    public int getNum() {
        return num;
    }
    

}
