package ca.qc.bdeb.info;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Command {


    private char[] choises;

    public Command(){
        if (!(Prompt.user.equalsIgnoreCase("q"))){
           choises = Prompt.user.toCharArray();
           if (choises.length == 2){

                   FileData();

                   boolean valid = false;

                   for (int i = 0 ; i < Challenge.FileSize ; i++){
                       char c = Challenge.DataTable[i][0].charAt(0);
                       if(c == choises[0]){
                           valid = true;
                       }
                   }
                   for(int i = 0; i < 4 ;i++){
                      if (!(choises[1] == 'N' || choises[1] == 'S' || choises[1] == 'E' || choises[1] == 'W' )){
                          valid = false;
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

    private static void FileData(){

      // if in doubt code goes here
    }

    public boolean isQuit(boolean Quit) {

        if (Prompt.user.equalsIgnoreCase("q")) {
            Quit = true;
        }
        return Quit;
    }
}
