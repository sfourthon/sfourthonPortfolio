/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import javafx.scene.image.Image;
import personnages.IPersonnage;

/**
 *
 * @author sfourthon
 */
public class MonstreSprite extends ASprite {

    /**
     * Constructeur de la classe MonstreSprite
     * @param personnage le monstre a afficher
     */
    public MonstreSprite(IPersonnage personnage) {
        super(personnage);
        Image image = new Image("file:icons/monstre0.gif");
        this.setImage(image);
    }
}