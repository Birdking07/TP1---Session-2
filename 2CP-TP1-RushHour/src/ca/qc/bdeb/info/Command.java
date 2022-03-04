package ca.qc.bdeb.info;


import java.util.Scanner;

public class Command {


    private char[] choices;
    private final String user;

    public Command(){

        Scanner scanner = new Scanner(System.in);
        user = scanner.nextLine();

        if (!(user.equalsIgnoreCase("q"))){
           choices = user.toCharArray();
           if (choices.length == 2){

                   boolean valid = false;

                   for (int i = 0 ; i < Challenge.FileSize ; i++){
                       char c = Challenge.VehicleColour.get(i).charAt(0);
                       if (c == choices[0]) {
                           valid = true;
                           break;
                       }
                   }


                   String[] directions = { "N" , "S" , "E" , "W" };
                   int attemptCounter = 0;

                   for (int i = 0 ; i < 4 ; i++){
                       if(directions[i].equalsIgnoreCase(String.valueOf(choices[1]))){
                           valid = true;
                       } else if(attemptCounter == 3){
                           valid = false;
                       } else {
                           attemptCounter++;
                       }

                   }

                   if (!valid){
                       choices = null;
                   }

           } else {
               System.out.println("Taille de commande invalide");
           }




        } else {
            System.out.println("Fermeture du jeu");
        }



    }

    public char[] getChoices(){
        return choices;
    }

    public boolean isQuit(boolean Quit) {

        if (user.equalsIgnoreCase("q")) {
            Quit = true;
        }
        return Quit;
    }
}
