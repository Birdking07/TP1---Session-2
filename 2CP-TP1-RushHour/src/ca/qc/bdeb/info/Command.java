package ca.qc.bdeb.info;


import java.util.Scanner;

public class Command {


    private char[] choises;
    private String user;

    public Command(String UserCommand){

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
                      if (!(choises[1] == 'N' || choises[1] == 'S' || choises[1] == 'E' || choises[1] == 'W' )){
                          valid = false;
                       }
                      switch (choises[0]) {

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

    public boolean isQuit(boolean Quit) {

        if (user.equalsIgnoreCase("q")) {
            Quit = true;
        }
        return Quit;
    }
}
