package ca.qc.bdeb.info;

import java.util.ArrayList;

public class Coordinate {

     static ArrayList<String> HorizontalCoordinates = new ArrayList<>();
     static ArrayList<String> VerticalCoordinates = new ArrayList<>();
    static ArrayList<String> MergedCoordinates = new ArrayList<>();
    static ArrayList<Orientation> CarOrientation = new ArrayList<>();

    public Coordinate(int CarCounter){
        ReadingCarCoordinates(CarCounter);
        ReadingCarOrientation(CarCounter);


    }

    private void ReadingCarCoordinates(int CarCounter){



            String[] CurrentCoordinates = Challenge.VehicleCoordinates.get(CarCounter).split(",");

            HorizontalCoordinates.add(CurrentCoordinates[0]);
            VerticalCoordinates.add(CurrentCoordinates[1]);

            MergedCoordinates.add(HorizontalCoordinates.get(CarCounter) + " " + VerticalCoordinates.get(CarCounter));
    }


    private void ReadingCarOrientation(int CarCounter){

            if(Challenge.VehicleOrientation.get(CarCounter).equalsIgnoreCase("h")){
                CarOrientation.add(Orientation.Horizontal);
            } else {
                CarOrientation.add(Orientation.Vertical);
            }

    }


}
