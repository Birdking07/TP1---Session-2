package ca.qc.bdeb.info;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
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

    private String[][] preParking = new String[Settings.PARKING_SIZE][Settings.PARKING_SIZE]; // garde les valeurs du grille
    private Cell[][] parking = new Cell[Settings.PARKING_SIZE][Settings.PARKING_SIZE];
    private int number;  // numéro du défi

    static ArrayList<String> VehicleColour = new ArrayList<>();
    static ArrayList<String> VehicleSize = new ArrayList<>();
    static ArrayList<String> VehicleCoordinates = new ArrayList<>();
    static ArrayList<String> VehicleOrientation = new ArrayList<>();
    static  String[][] DataTable;
    static int FileSize;
    private int PlayerPosition;
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


        for(int i = 0 ; i < Settings.PARKING_SIZE ; i++){

            preParking[0][i] = " " + Settings.BORDER_SYMBOL;
        }

        for (int i = 1 ; i < Settings.PARKING_SIZE ; i++){
            preParking[i][0] = " " + Settings.BORDER_SYMBOL;
            preParking[i][7] = " " + Settings.BORDER_SYMBOL;
        }


        preParking[3][7] = "  ";

        for(int i = 1 ; i < 7 ; i++){
            for(int a = 1 ; a < 7 ; a++){

                  preParking[i][a] = "  ";


            }
        }


        for (int i = 0 ; i < FileSize ; i++){

            char symbol = VehicleColour.get(i).charAt(0);
            Vehicle vehicle = getVehicle(symbol);
            ArrayList<Coordinate> coord = vehicle.getCoordinates();


            System.out.println(coord.get(i).HorizontalCoordinates.get(i));

           if(Settings.get().isValidSymbol(symbol)){

               int HorizontalCoords = Integer.parseInt(coord.get(i).HorizontalCoordinates.get(i));
               int VerticalCoords = Integer.parseInt(coord.get(i).VerticalCoordinates.get(i));




                switch (vehicle.getOrientation()) {

                    case Vertical -> {
                        for (int a = 0; a < Integer.parseInt(VehicleSize.get(i)); a++) {
                            preParking[HorizontalCoords][VerticalCoords + a] = " " +  vehicle.getSymbol();
                        }
                    }

                    case Horizontal -> {
                        for (int a = 0; a < Integer.parseInt(VehicleSize.get(i)); a++) {
                            preParking[HorizontalCoords + a][VerticalCoords] = " " +  vehicle.getSymbol();
                        }
                    }
                }
            }



        }


        for(int i = 0 ; i < Settings.PARKING_SIZE ; i++){

            preParking[7][i] = " " +  Settings.BORDER_SYMBOL;
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

        int FinalCar = 0;
        int CurrentCar = 0;
        String Carsymbol = symbol.toString();
        boolean isCar = false;

        for(String i : VehicleColour){
            if(Carsymbol.equals(VehicleColour.get(CurrentCar))){
                isCar = true;
                FinalCar = CurrentCar;
            }
            CurrentCar++;
        }

        if(isCar){
            int VehicleSizeNumber = Integer.parseInt(VehicleSize.get(FinalCar));
            Coordinate LoadCoordiate = new Coordinate(FinalCar);
            Orientation CarOrientation = Coordinate.CarOrientation.get(FinalCar);
            Vehicle CurrentVehicle = new Vehicle(symbol , VehicleSizeNumber ,LoadCoordiate , CarOrientation );

            return CurrentVehicle;
        } else {
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

        String player = VehicleColour.get(PlayerPosition);
        Character RedCar = player.charAt(0);
        Vehicle vehicle = getVehicle(RedCar);

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

        char[] choises = command.getChoises();
        Character symbol = choises[0];
       Vehicle vehicle = getVehicle(symbol);

       switch (choises[1]){

           case 'N' -> vehicle.move(0 , 1);

           case 'S' -> vehicle.move(0 , -1);

           case 'E' -> vehicle.move(1 , 0);

           case 'W' -> vehicle.move(-1 , 0);
       }

       ArrayList<Coordinate> coords = vehicle.getCoordinates();
       for(int c = 0 ; c < FileSize ; c++){
           for (int i = 0 ; i < 8 ; i++){
               for (int a = 0 ; a < 8 ; a++){
                   int HorizontalPos = Integer.parseInt(coords.get(c).HorizontalCoordinates.get(c));
                   int VerticalPos = Integer.parseInt(coords.get(c).VerticalCoordinates.get(c));
                   if(Objects.equals(preParking[i][a], Settings.BORDER_SYMBOL.toString()) && VerticalPos == i && HorizontalPos == a){
                       return MoveResult.Border;
                   } else if(!(Objects.equals(preParking[i][a], "")) && HorizontalPos == i && VerticalPos == a){
                       return MoveResult.Vehicle;
                   } else {
                       return MoveResult.Success;
                   }
               }
           }
       }




       return MoveResult.Success;
        // INSÉREZ VOTRE CODE ICI
    }

    /**
     * Affiche le défi (titre et stationnement).
     */
    public void print() {


        System.out.println(Colour.RED +" R U S H" + Colour.YELLOW + " H @ U R" + Colour.IRON);

        for (int i = 0 ; i < Settings.PARKING_SIZE ; i++){
            for (int a = 0 ; a < Settings.PARKING_SIZE ; a++){

                if (!(Objects.equals(preParking[i][a], "  ")) && Objects.equals(preParking[i][a], Settings.BORDER_SYMBOL.toString())){
                    char symbol = preParking[i][a].charAt(0);
                    parking[i][a] = new Cell(preParking[i][a].charAt(1) , a);
                } else {
                    parking[i][a] = new Cell(preParking[i][a].charAt(1) , a);
                }


            }
        }
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