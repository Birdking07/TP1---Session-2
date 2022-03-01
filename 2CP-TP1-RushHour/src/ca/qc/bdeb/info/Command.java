package ca.qc.bdeb.info;


import java.util.Scanner;

public class Command {


    private char[] choises;
    private String user;

    public Command(){

        Scanner scanner = new Scanner(System.in);
        user = scanner.nextLine();

        if (!(user.equalsIgnoreCase("q"))){
           choises = user.toCharArray();
           if (choises.length == 2){

                   boolean valid = false;

                   for (int i = 0 ; i < Challenge.FileSize ; i++){
                       char c = Challenge.VehicleColour.get(i).charAt(0);
                       if(c == choises[0]){
                           valid = true;
                       }
                   }


                   String[] directions = { "N" , "S" , "E" , "W" };
                   int attemptcounter = 0;

                   for (int i = 0 ; i < 4 ; i++){
                       if(directions[i].equalsIgnoreCase(String.valueOf(choises[1]))){
                           valid = true;
                       } else if(attemptcounter == 3){
                           valid = false;
                       } else {
                           attemptcounter++;
                       }

                   }

                   if (!valid){
                       choises = null;
                   }

           } else {
               System.out.println("Taille de commande invalide");
           }




        } else {
            System.out.println("Fermetture du jeu");
        }



    }

    public char[] getChoises(){
        return choises;
    }

    public boolean isQuit(boolean Quit) {

        if (user.equalsIgnoreCase("q")) {
            Quit = true;
        }
        return Quit;
    }
}
