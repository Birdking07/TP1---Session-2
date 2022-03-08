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

    private final ArrayList<Vehicle> vehicles = new ArrayList<>();  // liste des véhicules dans le stationnement

    private final String[][] preParking = new String[Settings.PARKING_SIZE][Settings.PARKING_SIZE]; // garde les valeurs de la grille
    private final Cell[][] parking = new Cell[Settings.PARKING_SIZE][Settings.PARKING_SIZE];
    private final int number;  // numéro du défi
    private int moveCount = 0;

    static ArrayList<String> VehicleColour = new ArrayList<>();
    static ArrayList<String> VehicleSize = new ArrayList<>();
    static ArrayList<String> VehicleCoordinates = new ArrayList<>();
    static ArrayList<String> VehicleOrientation = new ArrayList<>();
    static int FileSize;

    private ArrayList<String> moveKeeper = new ArrayList<>();

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


        for(int i = 0 ; i < FileSize ; i++){
            char symbol = VehicleColour.get(i).charAt(0);
            Vehicle vehicle = getVehicle(symbol);
            vehicles.add(vehicle);
        }



        for (int i = 0 ; i < FileSize ; i++){


          char symbol =  vehicles.get(i).getSymbol();



           if(Settings.get().isValidSymbol(symbol)){

               int HorizontalCoords = Integer.parseInt(vehicles.get(i).getPosition().HorizontalCoordinates);
               int VerticalCoords = Integer.parseInt(vehicles.get(i).getPosition().VerticalCoordinates);




                switch (vehicles.get(i).getOrientation()) {

                    case Vertical -> {
                        for (int a = 0; a < Integer.parseInt(VehicleSize.get(i)); a++) {
                            preParking[VerticalCoords + a][HorizontalCoords] = " " + vehicles.get(i).getSymbol();
                        }
                    }

                    case Horizontal -> {
                        for (int a = 0; a < Integer.parseInt(VehicleSize.get(i)); a++) {
                            preParking[VerticalCoords][HorizontalCoords + a] = " " + vehicles.get(i).getSymbol();
                        }
                    }
                }
            }



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

        int FinalCar = 0;
        int CurrentCar = 0;
        String carSymbol = symbol.toString();

        for(String ignored : VehicleColour){
            if(carSymbol.equalsIgnoreCase(VehicleColour.get(CurrentCar))){

                FinalCar = CurrentCar;
            }
            CurrentCar++;
        }

            int VehicleSizeNumber = Integer.parseInt(VehicleSize.get(FinalCar));
            Coordinate LoadCoordinate = new Coordinate(FinalCar);
            Orientation CarOrientation = LoadCoordinate.CarOrientation;

            return new Vehicle(symbol , VehicleSizeNumber ,LoadCoordinate , CarOrientation );

        // INSÉREZ VOTRE CODE ICI
    }

    /**
     * Indique si le défi (challenge) est résolu.
     *
     * @return true si le défi est résolu, false sinon
     */
    public boolean isSolved(){

        if (preParking[3][7].equals(" R")){
            System.out.println("Nombre de déplacements: " + moveCount);
            System.out.print("Vos déplacements : ");
            for (String s : moveKeeper) {
                System.out.print(" " + s);
            }
            System.out.print("\n");


            return true;
        }

     return false;
        // INSÉREZ VOTRE CODE ICI
    }

    /**
     * Charge un défi et construit la grille de stationnement.
     *
     * @return true si le chargement a fonctionné, false sinon
     */
    private boolean load() {

        VehicleSize.clear();
        VehicleCoordinates.clear();
        VehicleOrientation.clear();
        VehicleColour.clear();

        try {
            String CurrentFile = Integer.toString(number);
            CurrentFile += ".txt";
            File currentGame = new File(CurrentFile);
            Scanner ReadFile = new Scanner(currentGame);

            ArrayList<String> fileText = new ArrayList<>();
            while (ReadFile.hasNextLine()) {
                fileText.add(ReadFile.nextLine());
            }
            FileSize = fileText.size();

            String[][] dataTable = new String[FileSize][4];

            for (int i = 0 ; i < FileSize; i++){
                String[] SplitValues = fileText.get(i).split("\\|");

                System.arraycopy(SplitValues, 0, dataTable[i], 0, 4);

                VehicleColour.add(dataTable[i][0]);

                VehicleSize.add(dataTable[i][1]);

                VehicleCoordinates.add(dataTable[i][2]);

                VehicleOrientation.add(dataTable[i][3]);

            }


            for(int i = 0 ; i < Settings.PARKING_SIZE ; i++){

                preParking[0][i] = " " + Settings.BORDER_SYMBOL;
            }

            for (int i = 1 ; i < Settings.PARKING_SIZE ; i++){
                preParking[i][0] = " " + Settings.BORDER_SYMBOL;
                preParking[i][7] = " " + Settings.BORDER_SYMBOL;
            }


            for(int i = 1 ; i < 7 ; i++){
                for(int a = 1 ; a < 7 ; a++){

                    preParking[i][a] = "  ";


                }
            }

            for(int i = 0 ; i < Settings.PARKING_SIZE ; i++){

                preParking[7][i] = " " +  Settings.BORDER_SYMBOL;
            }

            preParking[3][7] = "  ";


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

        moveCount++;

        char[] choices = command.getChoices();
        moveKeeper.add("" + choices[0] + choices[1]);
        char symbol = String.valueOf(choices[0]).toUpperCase().charAt(0);
      
        int currentCar = 0;
        
       for (int i = 0 ; i < FileSize ; i++){
           if (vehicles.get(i).getSymbol() == symbol){
               currentCar = i;
           }
       }


       char movement = String.valueOf(choices[1]).toUpperCase().charAt(0);

        int preMoveH = Integer.parseInt(vehicles.get(currentCar).getPosition().HorizontalCoordinates);
        int preMoveV = Integer.parseInt(vehicles.get(currentCar).getPosition().VerticalCoordinates);

        int vehicleLength = Integer.parseInt(VehicleSize.get(currentCar));


       if (vehicles.get(currentCar).getOrientation() == Orientation.Horizontal){
           switch (movement){

               case 'E' -> {

                   if (preParking[preMoveV][preMoveH + (vehicleLength)].charAt(1) == Settings.BORDER_SYMBOL){
                       return MoveResult.Border;
                   } else if (preParking[preMoveV][preMoveH + (vehicleLength)].charAt(1) != ' '){
                       return MoveResult.Vehicle;
                   } else {
                       preParking[preMoveV][preMoveH] = "  ";
                       vehicles.get(currentCar).move(1 , 0);
                   }

               }

               case 'W' -> {


                   if (preParking[preMoveV][preMoveH - 1].charAt(1) == Settings.BORDER_SYMBOL){
                       return MoveResult.Border;
                   } else if (preParking[preMoveV][preMoveH - 1].charAt(1) != ' ') {
                       return MoveResult.Vehicle;
                   } else {
                       preParking[preMoveV][preMoveH + vehicleLength - 1] = "  ";
                       vehicles.get(currentCar).move(-1 , 0);
                   }

               }
               default -> {
                   return MoveResult.Invalid;
               }
           }

       } else if(vehicles.get(currentCar).getOrientation() == Orientation.Vertical){

           switch (movement){

               case 'N' -> {

                   if(preParking[preMoveV - 1][preMoveH].charAt(1) == Settings.BORDER_SYMBOL){
                       return MoveResult.Border;
                   } else if (preParking[preMoveV - 1][preMoveH].charAt(1) != ' '){
                       return MoveResult.Vehicle;
                   } else {
                       preParking[preMoveV + vehicleLength - 1][preMoveH] = "  ";
                       vehicles.get(currentCar).move(0 , -1);
                   }

               }

               case 'S' -> {

                   if(preParking[preMoveV + vehicleLength][preMoveH].charAt(1) == Settings.BORDER_SYMBOL){
                       return MoveResult.Border;
                   } else if (preParking[preMoveV + vehicleLength][preMoveH].charAt(1) != ' '){
                       return MoveResult.Vehicle;
                   } else {
                       preParking[preMoveV][preMoveH] = "  ";
                       vehicles.get(currentCar).move(0 , 1);
                   }

               }
               default -> {
                   return MoveResult.Invalid;
               }

           }

       }

       if (preParking[3][6].equals(" R")){
           buildParking();
           return MoveResult.Solved;
       }





       buildParking();
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

                parking[i][a] = new Cell(preParking[i][a].charAt(1) , a);


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