package ca.qc.bdeb.info;


import java.util.ArrayList;

/**
 * Un véhicule.
 *
 * @author  Gilles-Philippe Grégoire et Eric Drouin
 * @version 0.1
 * @since   2022-01-05
 */
public class Vehicle {

    private Character symbol;  // symbole représentant le véhicule de façon unique
    private int length;  // longueur du véhicule (2 ou 3)

    private Coordinate position;  // emplacement du véhicule dans le stationnement
    private Orientation orientation;  // disposition du véhicule (horizontale ou verticale)

    /**
     * Constructeur.
     *
     * @param symbol symbole (caractère associé, ex : 'A')
     * @param length longueur du véhicule
     * @param position emplacement du véhicule dans le stationnement (ex : 1,2)
     * @param orientation orientation du véhicule (ex : horizontale)
     */
    public Vehicle(Character symbol, int length, Coordinate position, Orientation orientation) {

        this.symbol = symbol;
        this.length = length;
        this.position = position;
        this.orientation = orientation;
    }

    /**
     * Retourne toutes les coordonnées occupées par le véhicule en fonction de sa position, de sa longueur et
     * de son orientation.
     *
     * @return une liste de coordonnées
     */
    public ArrayList<Coordinate> getCoordinates() {

        //inutile? on peut juste utiliser vehicle.getPosition ?

        ArrayList<Coordinate> CurrentCarCoordinates = new ArrayList<>();
       for (int i = 0 ; i < Challenge.FileSize ; i++){
            position = new Coordinate(i);
           CurrentCarCoordinates.add(position);
       }

       return CurrentCarCoordinates;
        // INSÉREZ VOTRE CODE ICI
    }

    /**
     * Déplace le véhicule (change sa position).
     *
     * @param deltaX déplacement sur l'axe horizontal
     * @param deltaY déplacement sur l'axe vertical
     */
    public void move(int deltaX, int deltaY) {

        // les deux coordonnées actuelles du véhicule
        int newHorizontal = Integer.parseInt(position.HorizontalCoordinates);
        int newVertical = Integer.parseInt(position.VerticalCoordinates);


       if (deltaX == 1){ //déplacement vers l'est
           newHorizontal += 1;
           position.HorizontalCoordinates = String.valueOf(newHorizontal);
       } else if (deltaX == -1){ //déplacement vers l'ouest
           newHorizontal -= 1;
           position.HorizontalCoordinates = String.valueOf(newHorizontal);
       }

        if (deltaY == 1){ //déplacement vers le sud
            newVertical += 1;
            position.VerticalCoordinates = String.valueOf(newVertical);
        } else if (deltaY == -1){ //déplacement vers le nord
            newVertical -= 1;
            position.VerticalCoordinates = String.valueOf(newVertical);
        }

        // INSÉREZ VOTRE CODE ICI
    }

    // setters et getters

    public Orientation getOrientation() {
        return orientation;
    }
    public Coordinate getPosition() {
        return position;
    }
    public char getSymbol() {
        return symbol;
    }
}