package ca.qc.bdeb.info;

import java.awt.*;

import java.util.ArrayList; //ajout

import java.util.HashMap;
import java.util.Map;


/**
 * Les paramètres de l'application. Singleton.
 *
 * @author  Gilles-Philippe Grégoire et Eric Drouin
 * @version 0.1
 * @since   2022-01-05
 */
class Settings {

    public static final String INDENTATION = "      ";
    public static final Character PROMPT_SYMBOL = '?';
    public static final Character BORDER_SYMBOL = '▓';
    public static final Character EMPTY_SYMBOL = ' ';
    public static final Character RED_SYMBOL = 'R';

    public static final int PARKING_SIZE = 8;  // bordure + 6 espaces + bordure
    public static final int NB_CHALLENGES = 3;

    public static int Current_Challenge = 1;

    private static Settings instance = null;

    // "dictionnaire" associatif des symboles et couleurs (codes d'échappement)
    private final Map<Character, Colour> colourMap = new HashMap<>();

    private int FileSize;
    private ArrayList<String> VehicleColour;


    /**
     * Constructeur privé du singleton.
     */
    private Settings() {

        for (Colour colour : Colour.values()) {

            Character symbol = switch(colour) {
                case PROMPT -> PROMPT_SYMBOL;
                case BORDER -> BORDER_SYMBOL;
                case EMPTY -> EMPTY_SYMBOL;
                default -> colour.name().charAt(0);  // le symbole est le premier caractère du nom de la couleur
            };

            colourMap.put(symbol, colour);
        }
    }

    /**
     * Retourne l'instance unique des paramètres de jeu.
     *
     * @return instance des paramètres de jeu
     */
    public static Settings get() {

        if (null == instance)
            instance = new Settings();

        return instance;
    }

    /**
     * Retourne la couleur associée à un symbole.
     *
     * @param symbol le symbole (un caractère, ex.: 'A')
     * @return la couleur associée au symbole
     */
    public Colour getColour(Character symbol) {


        /*

        String CurrentColour;


            switch()
            {
                case "A" -> {return Colour.ARMY;}
                case "B" -> {return Colour.BABY;}
              case "C" -> {return Colour.CREAM;}
              case "E" -> {return Colour.ELECTRIC;}
              case "G" -> {return Colour.GREEN;}
              case "I" -> {return Colour.IRON;}
              case "L" -> {return Colour.LILAC;}
              case "M" -> {return Colour.MINT;}
              case "O" -> {return Colour.ORANGE;}
              case "P" -> {return Colour.PINK;}
              case "R" -> {return Colour.RED;}
              case "S" -> {return Colour.SADDLE;}
              case "T" -> {return Colour.TURQUOISE;}
              case "V" -> {return Colour.VIOLET;}
              case "W" -> {return Colour.WHEAT;}
              case "Y" -> {return Colour.YELLOW;}

            }

         */

        for (int i = 0 ; i < Colour.values().length ; i++){
            if (symbol == Colour.values()[i].name().charAt(3));
            return Colour.values()[i];
        }

        // INSÉREZ VOTRE CODE ICI

    }

    /**
     * Vérifie si un symbole est valide (se retrouve dans le "dictionnaire" de couleurs).
     * @param symbol le symbole (un caractère, ex.: 'A')
     * @return true si le symbole est valide, false sinon
     */
    public boolean isValidSymbol(Character symbol) {

        // INSÉREZ VOTRE CODE ICI
    }
}