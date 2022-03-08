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
    private final Cell[][] parking = new Cell[Settings.PARKING_SIZE][Settings.PARKING_SIZE]; // table 2D de cells à être construit
    private final int number;  // numéro du défi
    private int moveCount = 0; //va augmenter de 1 pour chaque mouvement fait.

    static ArrayList<String> VehicleColour = new ArrayList<>(); //enregistre le symbole de la véhicule
    static ArrayList<String> VehicleSize = new ArrayList<>(); //enregistre la taille de la véhicule
    static ArrayList<String> VehicleCoordinates = new ArrayList<>(); // enregistre la position initiale du véhicule
    static ArrayList<String> VehicleOrientation = new ArrayList<>(); // enregistre l'orientation du véhicule
    static int FileSize; // la taille du fichier souvent utilisé pour savoir le nombre de véhicules

    private final ArrayList<String> moveKeeper = new ArrayList<>(); //sauvegarde chaque mouvement effectué

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



        for (int i = 0 ; i < FileSize ; i++){ //construction de la grille preParking que l'on va envoyer un par un dans Cell


          char symbol =  vehicles.get(i).getSymbol(); //On identifie le véhicule



           if(Settings.get().isValidSymbol(symbol)){ //vérifie si le véhicule apparait dans la liste des couleurs


               //recherche des Coordonnées horizontale et vertical du véhicule

               int HorizontalCoords = Integer.parseInt(vehicles.get(i).getPosition().HorizontalCoordinates);
               int VerticalCoords = Integer.parseInt(vehicles.get(i).getPosition().VerticalCoordinates);


               /*
               Détermine l'orientation du véhicule et ensuite va imprimer le véhicule par rapport à sa taille.
               Ce qui va être imprimé sera alors un espace suivi par le symbole du véhicule.
                */

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

        int FinalCar = 0; //le véhicule qui va être envoyé lorsque le symbol correspondante est trouvé.
        int CurrentCar = 0; // va incrémenter avec chaque répétition du for each pour rechercher la couleur correspondante

        String carSymbol = symbol.toString(); //pour utiliser .equalsIgnoreCase()

        // Trouve la couleur du véhicule recherché

        for(String ignored : VehicleColour){
            if(carSymbol.equalsIgnoreCase(VehicleColour.get(CurrentCar))){

                FinalCar = CurrentCar;
            }
            CurrentCar++;
        }

            int VehicleSizeNumber = Integer.parseInt(VehicleSize.get(FinalCar)); //Taille du véhicule
            Coordinate LoadCoordinate = new Coordinate(FinalCar); //Recherche du position de véhicule
            Orientation CarOrientation = LoadCoordinate.CarOrientation; //Recherche de l'orientation du véhicule

            return new Vehicle(symbol , VehicleSizeNumber ,LoadCoordinate , CarOrientation );

        // INSÉREZ VOTRE CODE ICI
    }

    /**
     * Indique si le défi (challenge) est résolu.
     *
     * @return true si le défi est résolu, false sinon
     */
    public boolean isSolved(){

        //si la sortie du stationnement est représenté par un espace suivi du symbole R on montre les données et
        // on retourne true.
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

        // On supprime les données des défis précédentes pour éviter une répétition.
        VehicleSize.clear();
        VehicleCoordinates.clear();
        VehicleOrientation.clear();
        VehicleColour.clear();

        /*
        Lecture du fichier

        On convertit le numéro du défi en String et on utilise cela pour trouver le fichier dans laquelle chercher
        les données.
         */
        try {
            String CurrentFile = Integer.toString(number);
            CurrentFile += ".txt";
            File currentGame = new File(CurrentFile);
            Scanner ReadFile = new Scanner(currentGame);

            ArrayList<String> fileText = new ArrayList<>();
            // On ajoute chaque ligne du fichier dans un arraylist
            while (ReadFile.hasNextLine()) {
                fileText.add(ReadFile.nextLine());
            }
            FileSize = fileText.size();

            //dataTable est notre table 2D de données qui a la taille verticale du fichier et horizontale des données cherchés
            String[][] dataTable = new String[FileSize][4];

            /*
            On cherche les différentes valeurs de chaque ligne dans le fichier.
            Les données séparées dans chaque ligne de fileText sera ensuite copié automatiquement vers une rangée de
            dataTable (chaque rangé va alors représenté les valeurs d'un véhicule).
             */
            for (int i = 0 ; i < FileSize; i++){
                String[] SplitValues = fileText.get(i).split("\\|");

                System.arraycopy(SplitValues, 0, dataTable[i], 0, 4);

                VehicleColour.add(dataTable[i][0]);

                VehicleSize.add(dataTable[i][1]);

                VehicleCoordinates.add(dataTable[i][2]);

                VehicleOrientation.add(dataTable[i][3]);

            }

            //sauvegarde des positions des bordures de la première rangée.

            for(int i = 0 ; i < Settings.PARKING_SIZE ; i++){

                preParking[0][i] = " " + Settings.BORDER_SYMBOL;
            }

            //sauvegarde des positions des bordures dans la première et dernière colonne.
            for (int i = 1 ; i < Settings.PARKING_SIZE ; i++){
                preParking[i][0] = " " + Settings.BORDER_SYMBOL;
                preParking[i][7] = " " + Settings.BORDER_SYMBOL;
            }

            //remplissage du reste du cell avec un string de deux caractères.
            for(int i = 1 ; i < 7 ; i++){
                for(int a = 1 ; a < 7 ; a++){

                    preParking[i][a] = "  ";


                }
            }

            //sauvegarde des positions des bordures de la dernière rangée.
            for(int i = 0 ; i < Settings.PARKING_SIZE ; i++){

                preParking[7][i] = " " +  Settings.BORDER_SYMBOL;
            }

            //construction de la sortie
            preParking[3][7] = "  ";

            // On recherche toutes les véhicules dans le défi courant
            for(int i = 0 ; i < FileSize ; i++){
                char symbol = VehicleColour.get(i).charAt(0);
                Vehicle vehicle = getVehicle(symbol);
                vehicles.add(vehicle);
            }



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

        //choices = choix du véhicule ainsi que la direction désirée.
        char[] choices = command.getChoices();
        if (choices == null){
            return MoveResult.Invalid;
        }


        moveCount++;
        moveKeeper.add("" + choices[0] + choices[1]);

        //véhicule choisi transformé en Majuscule a fin d'éviter des erreurs dans la caisse des lettres.
        char symbol = String.valueOf(choices[0]).toUpperCase().charAt(0);

        //currentCar est la position dans vehicles dans laquelle on retrouve le véhicule cherché dans la commande.
        int currentCar = 0;
       for (int i = 0 ; i < FileSize ; i++){
           if (vehicles.get(i).getSymbol() == symbol){
               currentCar = i;
           }
       }

        //la direction choisie dans laquelle le véhicule va essayer de se déplacer
       char movement = String.valueOf(choices[1]).toUpperCase().charAt(0);

        int positionH = Integer.parseInt(vehicles.get(currentCar).getPosition().HorizontalCoordinates);
        int positionV = Integer.parseInt(vehicles.get(currentCar).getPosition().VerticalCoordinates);

        int vehicleLength = Integer.parseInt(VehicleSize.get(currentCar));


       if (vehicles.get(currentCar).getOrientation() == Orientation.Horizontal){
           switch (movement){

               case 'E' -> {

                   if (preParking[positionV][positionH + (vehicleLength)].charAt(1) == Settings.BORDER_SYMBOL){
                       return MoveResult.Border;
                   } else if (preParking[positionV][positionH + (vehicleLength)].charAt(1) != ' '){
                       return MoveResult.Vehicle;
                   } else {
                       preParking[positionV][positionH] = "  ";
                       vehicles.get(currentCar).move(1 , 0);
                   }

               }

               case 'W' -> {


                   if (preParking[positionV][positionH - 1].charAt(1) == Settings.BORDER_SYMBOL){
                       return MoveResult.Border;
                   } else if (preParking[positionV][positionH - 1].charAt(1) != ' ') {
                       return MoveResult.Vehicle;
                   } else {
                       preParking[positionV][positionH + vehicleLength - 1] = "  ";
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

                   if(preParking[positionV - 1][positionH].charAt(1) == Settings.BORDER_SYMBOL){
                       return MoveResult.Border;
                   } else if (preParking[positionV - 1][positionH].charAt(1) != ' '){
                       return MoveResult.Vehicle;
                   } else {
                       preParking[positionV + vehicleLength - 1][positionH] = "  ";
                       vehicles.get(currentCar).move(0 , -1);
                   }

               }

               case 'S' -> {

                   if(preParking[positionV + vehicleLength][positionH].charAt(1) == Settings.BORDER_SYMBOL){
                       return MoveResult.Border;
                   } else if (preParking[positionV + vehicleLength][positionH].charAt(1) != ' '){
                       return MoveResult.Vehicle;
                   } else {
                       preParking[positionV][positionH] = "  ";
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

                parking[i][a] = new Cell(preParking[i][a].charAt(1) ,i ,  a);


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