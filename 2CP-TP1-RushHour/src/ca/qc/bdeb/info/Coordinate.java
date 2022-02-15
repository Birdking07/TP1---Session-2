package ca.qc.bdeb.info;


import java.util.ArrayList;

public class Coordinate {

    static ArrayList<String> HorizontalCoordinates = new ArrayList<>();
    static ArrayList<String> VerticalCoordinates = new ArrayList<>();
    static ArrayList<String> MergedCoordinates = new ArrayList<>();
    static ArrayList<Orientation> CarOrientation = new ArrayList<>();

    public Coordinate(){
        ReadingCarCoordinates();
        ReadingCarOrientation();


    }

    private void ReadingCarCoordinates(){

        for(int i = 0 ; i < Challenge.FileSize ; i++){
            String[] CurrentCoordinates = Challenge.VehicleCoordinates.get(i).split(",");

            HorizontalCoordinates.add(CurrentCoordinates[0]);
            VerticalCoordinates.add(CurrentCoordinates[1]);

            MergedCoordinates.add(HorizontalCoordinates.get(i) + " "  + VerticalCoordinates.get(i));
        }



    }
    private static void ReadingCarOrientation(){
        for(int i = 0 ; i < Challenge.FileSize ; i++){
            if(Challenge.VehicleOrientation.get(i).equalsIgnoreCase("h")){
                CarOrientation.add(Orientation.Horizontal);
            } else {
                CarOrientation.add(Orientation.Vertical);
            }

        }


    }


}
