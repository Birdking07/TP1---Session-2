package ca.qc.bdeb.info;

import java.util.ArrayList;

public class Coordinate {

     public String HorizontalCoordinates;
     public String VerticalCoordinates;
     public ArrayList<String> MergedCoordinates = new ArrayList<>();
     public Orientation CarOrientation;

    public Coordinate(int CarCounter){
        ReadingCarCoordinates(CarCounter);
        ReadingCarOrientation(CarCounter);


    }

    private void ReadingCarCoordinates(int CarCounter){



            String[] CurrentCoordinates = Challenge.VehicleCoordinates.get(CarCounter).split(",");

            HorizontalCoordinates = CurrentCoordinates[0];
            VerticalCoordinates = CurrentCoordinates[1];

            MergedCoordinates.add(HorizontalCoordinates + " " + VerticalCoordinates );
    }


    private void ReadingCarOrientation(int CarCounter){

            if(Challenge.VehicleOrientation.get(CarCounter).equalsIgnoreCase("H")){
                CarOrientation = Orientation.Horizontal;
            } else if (Challenge.VehicleOrientation.get(CarCounter).equalsIgnoreCase("V")){
                CarOrientation = Orientation.Vertical;
            }

    }


}
