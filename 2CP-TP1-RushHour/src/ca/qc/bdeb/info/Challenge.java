package ca.qc.bdeb.info;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Défi à résoudre (niveau de jeu)
 *
 * @author  Gilles-Philippe Grégoire et Eric Drouin
 * @version 0.1
 * @since   2022-01-05
 */

public class Challenge {

    private ArrayList<Vehicle> vehicles;  // liste des véhicules dans le stationnement

    private Cell[][] parking;  // grille de stationnement

    private int number;  // numéro du défi

    static ArrayList<String> VehicleColour = new ArrayList<>();
    static ArrayList<String> VehicleSize = new ArrayList<>();
    static ArrayList<String> VehicleCoordinates = new ArrayList<>();
    static ArrayList<String> VehicleOrientation = new ArrayList<>();
    static  String[][] DataTable;
    static int FileSize;
    static int PlayerPosition;
    private ArrayList<String> Filetext = new ArrayList<>();

    /**
     * Construit un objet représentant un défi.
     * Ce constructeur est privé. Il est appelé par la méthode statique publique loadChallenge().
     *
     * @param number numéro du défi à construire
     */
    private Challenge(int number) {

        this.number = number;
    }

    /**
     * Construit la grille de stationnement.
     */
    private void buildParking() {


        DataTable = new String[FileSize][4];

        for (int i = 0 ; i < FileSize; i++){
            String[] SplitValues = Filetext.get(i).split("\\|");
                for(int a = 0 ; a < 4 ; a++){
                    DataTable[i][a] = SplitValues[a];
                }

                    VehicleColour.add(DataTable[i][0]);

                    if(VehicleColour.get(i).equalsIgnoreCase("R"));
                    PlayerPosition = i;

                    VehicleSize.add(DataTable[i][1]);

                    VehicleCoordinates.add(DataTable[i][2]);

                    VehicleOrientation.add(DataTable[i][3]);


                }





        // INSÉREZ VOTRE CODE ICI
    }

    /**
     * Retourne le véhicule associé au symbole donné et qui est présent dans le stationnement.
     *
     * @param symbol le symbole associé au véhicule
     * @return le véhicule correspondant, null si le véhicule n'est pas trouvé
     */
    public Vehicle getVehicle(Character symbol) {

        //TODO add enhanced switch here along with a foreach elsewhere to determine which car to summon
       try{
               int VehicleSizeNumber = Integer.parseInt(VehicleSize.get(0));
               Orientation CarOrientation = Coordinate.CarOrientation.get(0);
               Coordinate LoadCoordiate = new Coordinate();
               Vehicle CurrentVehicle = new Vehicle(symbol , VehicleSizeNumber ,LoadCoordiate , CarOrientation );

           return CurrentVehicle;
       } catch (Exception e) {
           return null;
        }

                //length , Coordinate position , and Orientation orientation

        // INSÉREZ VOTRE CODE ICI
    }

    /**
     * Indique si le défi (challenge) est solutionné.
     *
     * @return true si le défi est résolu, false sinon
     */
    public boolean isSolved(){

            return false;
        // INSÉREZ VOTRE CODE ICI
    }

    /**
     * Charge un défi et construit la grille de stationnement.
     *
     * @return true si le chargement a fonctionné, false sinon
     */
    private boolean load() {

        try {
            String CurrentFile = Integer.toString(Settings.Current_Challenge);
            CurrentFile += ".txt";
            File currentGame = new File(CurrentFile);
            Scanner ReadFile = new Scanner(currentGame);

            Filetext = new ArrayList<String>();
            while (ReadFile.hasNextLine()) {
                Filetext.add(ReadFile.nextLine());
            }
            FileSize = Filetext.size();
            buildParking();
            ReadFile.close();
            return true;
        }  catch (FileNotFoundException e){
        System.out.println("Erreur de lecture de fichier");
        return false;
    }
        // INSÉREZ VOTRE CODE ICI
    }

    /**
     * Charge un défi et retourne l'objet Challenge correspondant.
     *
     * @param number numéro du défi à charger
     * @return une instance de la classe Challenge (objet défi), null si le chargement échoue
     */
    public static Challenge loadChallenge(int number) {

        Challenge challenge = new Challenge(number);
        if (challenge.load()) {
            return challenge;
        } else {
            return null;
        }

    }

    /**
     * Déplace (ou tente de déplacer) un véhicule dans le stationnement.
     *
     * @param command commande de déplacement (#N, #S, #E, #W)
     * @return le résultat de la tentative de déplacement (ce résultat peut être un déplacement)
     */
    public MoveResult moveVehicle(Command command) {

        return MoveResult.Success;
        // INSÉREZ VOTRE CODE ICI
    }

    /**
     * Affiche le défi (titre et stationnement).
     */
    public void print() {

        System.out.println(Colour.RED +" R U S H" + Colour.YELLOW + " H @ U R" + Colour.IRON);
        Cell Parking_lot = new Cell();



        // INSÉREZ VOTRE CODE ICI
    }

    /**
     * Affiche le message de félicitation lorsque le joueur a complété le dernier défi.
     */
    public void printLastOneCompleted() {

        System.out.print("\n" + Settings.INDENTATION);

        System.out.println(Colour.YELLOW + "Félicitations, vous venez de compléter le dernier défi. À la prochaine!");
    }
}