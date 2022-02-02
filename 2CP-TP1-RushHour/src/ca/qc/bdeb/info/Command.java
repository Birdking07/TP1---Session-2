package ca.qc.bdeb.info;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Command {

    public static String[][] DataTable;
    private static int FileSize;
    public static char[] choises;

    public Command(){
        if (!(Prompt.user.equalsIgnoreCase("q"))){
           choises = Prompt.user.toCharArray();
           if (choises.length == 2){

                   FileData();

                   boolean valid = false;

                   for (int i = 0 ; i < FileSize ; i++){
                       char c = DataTable[i][0].charAt(0);
                       if(c == choises[0]){
                           valid = true;
                       }
                   }
                   for(int i = 0; i < 4 ;i++){
                      if (!(choises[1] == 'N' || choises[1] == 'S' || choises[1] == 'E' || choises[1] == 'W' )){
                          valid = false;
                       }
                   }

                   if (valid == false){
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
        try {
            File currentGame = new File(Settings.Current_Challenge +".txt");
            Scanner ReadFile = new Scanner(currentGame);

            ArrayList<String> Filetext = new ArrayList<String>();
            while (ReadFile.hasNextLine()){
                Filetext.add(ReadFile.nextLine());
            }
             FileSize = Filetext.size();
             DataTable = new String[Filetext.size()][9];

            for (int i = 0 ; i < Filetext.size() ; i++){
                for(int a = 0 ; a < 9 ; a++){
                     String[] SplitColums = Filetext.get(i).split("\n");
                     DataTable[i][a] += SplitColums[a].split("|");
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Erreur de lecture de fichier");
        }

    }

    public boolean isQuit(boolean Quit) {

        if (Prompt.user.equalsIgnoreCase("q")) {
            Quit = true;
        }
        return Quit;
    }
}
