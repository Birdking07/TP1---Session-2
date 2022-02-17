package ca.qc.bdeb.info;

import java.util.ArrayList;

public class Cell {

    static String[][] Parking;

    public Cell(){
        Parking = new String[Settings.PARKING_SIZE][Settings.PARKING_SIZE];


        for (int i = 0 ; i < 8 ; i++){ // Top piece
            Parking[0][i] = " ";
            Parking[0][i] += Settings.BORDER_SYMBOL;
        }

        for (int i = 1 ; i < 7 ; i++){ // Body Cell
            for(int a = 0 ; a < 8 ; a++){

                 if(a == 0 || a == 7){
                    Parking[i][0] = " " + Settings.BORDER_SYMBOL;
                    Parking[i][7] = " " + Settings.BORDER_SYMBOL;
                } else {
                    Parking[i][a] = "  ";
                }



            }
        }
        for (int i = 0 ; i < 8 ; i++){ //bottom piece

            Parking[7][i] = " ";
            Parking[7][i] += Settings.BORDER_SYMBOL;
        }

        Parking[3][7] = " ";

        for (int a = 0 ; a < Challenge.FileSize ; a++ ){ //FIXME Calls all cars into the cell
            CallCars(a);
        }

        printTable();

    }


    private void CallCars(int CarCount){

        Coordinate CurrentCarCoord = new Coordinate();                          // Coordinate
        int VehicleSizeNumber = Integer.parseInt(Challenge.VehicleSize.get(CarCount)); //int
        Orientation CurrentCarOrientation = Coordinate.CarOrientation.get(CarCount); // Orientation
        char symbol = Challenge.VehicleColour.get(CarCount).toUpperCase().charAt(0); //Symbol

        Vehicle vehicle = new Vehicle(symbol ,VehicleSizeNumber, CurrentCarCoord , CurrentCarOrientation);
        ArrayList<Coordinate> CoordInstance = vehicle.getCoordinates();
        Colour symbolcolour;
        try{
            symbolcolour = Settings.get().getColour(symbol);
        } catch (Exception e){
            System.out.println('!');
            symbolcolour = null;
        }
        if(Settings.get().isValidSymbol(symbol)){

            int HorizontalCoords = Integer.parseInt(Coordinate.HorizontalCoordinates.get(CarCount));
            int VerticalCoords = Integer.parseInt(Coordinate.VerticalCoordinates.get(CarCount));

            Parking[HorizontalCoords][VerticalCoords] = (" " + symbolcolour + symbol + Colour.IRON);
        }

    }


    private void printTable(){
        for (int i = 0 ; i < 8 ; i++){
            System.out.print(Parking[0][i]);
        }

        for (int i = 1 ; i < 7 ; i++){
            for(int a = 0 ; a < 8 ; a++){
               if(a == 0){
                   System.out.print("\n");
                   System.out.print(Parking[0][i]);
               } else {
                   System.out.print(Parking[a][i]);
               }

            }
        }
        for (int i = 0 ; i < 8 ; i++){ //bottom piece
            if(i == 0){
                System.out.print("\n");
            } else {
                System.out.print(Parking[7][i]);
            }

        }
    }

}
