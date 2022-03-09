package ca.qc.bdeb.info;


import java.util.Scanner;

public class Command {


    private char[] choices; //toutes les charactères dans la commande de l'utilisateur
    private final String userCmd; //la commande de l'utilisateur au complet

    public Command(){

        Scanner scanner = new Scanner(System.in);
        userCmd = scanner.nextLine();
        choices = userCmd.toCharArray();


               if (choices.length == 2){ //avant de vérifier le contenu de la commande on vérifie sa taille

                   boolean validCommand = false; //va déterminer si on renvoie choices comme étant null

                   //vérifie si le choix correspond à une des véhicules dans le défi courante
                   for (int i = 0 ; i < Challenge.FileSize ; i++){
                       char c = Challenge.VehicleColour.get(i).charAt(0);
                       if (c == choices[0]) {
                           validCommand = true;
                           break;
                       }
                   }

                   // Va vérifier que l'orientation choisie est valide
                   String[] directions = { "N" , "S" , "E" , "W" };
                   int attemptCounter = 0;
                   for (int i = 0 ; i < 4 ; i++){
                       if(directions[i].equalsIgnoreCase(String.valueOf(choices[1]))){
                           validCommand = true;
                       } else if(attemptCounter == 3){
                           validCommand = false;
                       } else {
                           attemptCounter++;
                       }

                   }

                   if (!validCommand){ //si l'un des deux charactères ne sont pas valides
                       choices = null;
                   }

               } else { //si la taille de la commande est invalide
                   choices = null;
               }





        }

    //retourne choices (utilisé seulement dans MoveVehicle)
    public char[] getChoices(){
        return choices;
    }

    public boolean isQuit(boolean Quit) { // si l'utilisateur entre q OU Q on met fin au jeu

        if (userCmd.equalsIgnoreCase("q")) {
            Quit = true;
        }
        return Quit;
    }
}
