package odomo;

import java.util.Scanner;

/**
 * Gestion de la partie Chauffage.
 */
class Chauffage {
    /** 
     * La température quand le chauffage est en mode économique.
     */
    static int temperEco;
    
    /** 
     * La température quand le chauffage est en mode normal.
     */
    static int temperNormal;

    /**
     * Premier créneau du jour en mode normal.
     */
    static int[][] creneau1;
    
    /**
     * Deuxième créneau du jour en mode normal.
     */
    static int[][] creneau2;   
    
    /**
     * L'heure maximale possible dans les tableaux créneaux.
     */
    final static int H_MAX = 23;

    /**
     * L'heure maximale possible dans les tableaux créneaux.
     */
    final static int H_MIN = 0;
    
    /**
     * Initialiser les données de chauffage.
     */
    static void initialiser() {
        initialiserValeurs();
        initialiserCreneaux();
    }
    
    
    static void initialiserCreneaux() {
        creneau1 = new int[7][2];
        creneau2 = new int[7][2];
        for (int i = 0; i < 7; i++) {
            creneau1[i][0] = 1;
            creneau1[i][1] = 0;
            creneau2[i][0] = 1;
            creneau2[i][1] = 0;
        }
    }
    /**
     * Initialiser les températures des différents modes du chauffage.
     */
    static void initialiserValeurs() {
        int temperEco = 18;
        int temperNormal = 24;
    }       

    static String saisirString() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir une chaine de caractères sous la forme : jour;Hdebut1;Hfin1;Hdebut2;Hfin2\n Hdebut2 et Hfin2 ne sont pas obligatoires");
        String saisie = sc.nextLine();
        return saisie;
    }

    /**
     * Matrice des créneaux en mode normal, pour l'histogramme.
     *
     * @return la matrice des créneaux
     */
    static boolean[][] matriceCreneaux() {
        boolean[][] matriceCreneaux = new boolean[8][24];
        for (int l = 0; l < matriceCreneaux.length-1; l++) {
            for (int c = 0; c < matriceCreneaux[l].length; c++) {
                if((c>= creneau1[l][0] && c < creneau1[l][1]) || (c>= creneau2[l][0] && c < creneau2[l][1])){
                     matriceCreneaux[l][c] = true;
                } 
            }
        }
        return matriceCreneaux;
    }
   
    /**
     * Procédure de saisie des créneaux de chauffage.
     */
    static void saisieCreneaux() {
        String saisie;
        boolean verif;
        do {
            saisie = saisirString();
            verif = traitementSaisieCreneaux(saisie);
        } while (!verif);

    }

    /**
     * Traite la saisie de créneaux par l'utilisateur.
     *
     * @param saisie la saisie de l'utilisateur
     * @return true ssi la saisie a été correcte
     */
    static boolean traitementSaisieCreneaux(String saisie) {
        boolean correct = saisie != null;
        String[] champs = null;
        if (correct) {
            champs = saisie.split(";");
            correct &= champs.length == 3 || champs.length == 5;
            if (!correct) {
                System.err.println("Format incorrect : 3 ou 5 champs separes "
                        + "par des points-virgules sont attendus, "
                        + champs.length + " ont été saisis.");
            }
        }
        if (correct) {
            correct &= Odomo.numeroJour(champs[0]) >= 0;
            if (!correct) {
                System.err.println("Nom de jour incorrect : " + champs[0] + ".");
            }
        }
        int creneau1debut = -1;
        if (correct) {
            creneau1debut = heureCreneau(champs[1]);
            correct = creneau1debut >= 0;
        }
        int creneau1fin = -1;
        if (correct) {
            creneau1fin = heureCreneau(champs[2]);
            correct = creneau1fin >= 0;
        }
        if (correct) {
            correct &= (creneau1debut <= creneau1fin) || (creneau1debut == 1 && creneau1fin == 0);
            if (!correct) {
                System.err.println("Créneau incorrect : l'heure de début doit "
                        + "précéder (ou égaler) l'heure de fin "
                        + "(ou choisir le créneau 1h-0h pour un créneau vide).");
            }
        }
        int creneau2debut = -1;
        int creneau2fin = -1;
        if (correct && champs.length == 5) {
            creneau2debut = heureCreneau(champs[3]);
            correct = creneau2debut >= 0;
            if (correct) {
                creneau2fin = heureCreneau(champs[4]);
                correct = creneau2fin >= 0;
            }
            if (correct) {
                correct &= (creneau2debut <= creneau2fin) || (creneau2debut == 1 && creneau2fin == 0);
                if (!correct) {
                    System.err.println("Créneau incorrect : l'heure de début doit "
                            + "précéder (ou égaler) l'heure de fin "
                            + "(ou choisir le créneau 1h-0h pour un créneau vide).");
                }
            }
        }
        if (correct) {
            int numJour = Odomo.numeroJour(champs[0]);
            Chauffage.creneau1[numJour][0] = creneau1debut;
            Chauffage.creneau1[numJour][1] = creneau1fin;
            if (champs.length == 5) {
                Chauffage.creneau2[numJour][0] = creneau2debut;
                Chauffage.creneau2[numJour][1] = creneau2fin;
            } else {
                Chauffage.creneau2[numJour][0] = 1;
                Chauffage.creneau2[numJour][1] = 0;
            }
        }
        return correct;
    }

    /**
     * Récupère l'heure d'un créneau donné sous forme de chaîne.
     *
     * @param chaineHeure l'heure sous forme de chaîne
     * @return l'heure sous forme d'entier (-1 si incorrecte)
     */
    static int heureCreneau(String chaineHeure) {
        int heure;
        try {
            heure = Integer.parseInt(chaineHeure);
        } catch (NumberFormatException e) {
            System.err.println("L'heure de créneau n'est pas un entier : " + chaineHeure);
            heure = -1;
        }
        if (heure > 23) {
            System.err.println("L'heure doit être comprise entre 0 et 23 "
                    + "(inclus), au lieu de : " + chaineHeure + ".");
            heure = -1;
        }
        return heure;
    }

}
