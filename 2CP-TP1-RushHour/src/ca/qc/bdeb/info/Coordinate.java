package ca.qc.bdeb.info;


import java.util.ArrayList;

public class Coordinate {

    static ArrayList<String> HorizontalCoordinates;
    static ArrayList<String> VerticalCoordinates;
    static ArrayList<String> MergedCoordinates;
    static ArrayList<String> CarCoodinates;

    private void ReadingCarCoordinates(){

        for(int i = 0 ; i < Challenge.FileSize ; i++){
            String[] CurrentCoordinates = Challenge.VehicleCoordinates.get(i).split(",");
            HorizontalCoordinates.add(CurrentCoordinates[0]);
            VerticalCoordinates.add(CurrentCoordinates[1]);

            MergedCoordinates.add(HorizontalCoordinates.get(i) + " "  + VerticalCoordinates.get(i));
        }



    }
    private void ReadingCarOrientation(){



    }


}
