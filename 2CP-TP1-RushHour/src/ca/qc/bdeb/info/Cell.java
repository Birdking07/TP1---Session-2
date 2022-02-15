package ca.qc.bdeb.info;

import java.util.Locale;

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
        char symbol = Challenge.VehicleColour.get(CarCount).toUpperCase(Locale.ROOT).charAt(0); //Symbol

        Vehicle vehicle = new Vehicle(symbol ,VehicleSizeNumber, CurrentCarCoord , CurrentCarOrientation);

        Colour symbolcolour = Colour.VIOLET;

        if(Settings.get().isValidSymbol(symbol)) {
             symbolcolour = Settings.get().getColour(symbol);
        } else {
            symbolcolour = null;
        }
        int HorizontalCoords = Integer.parseInt(Coordinate.HorizontalCoordinates.get(CarCount));
        int VerticalCoords = Integer.parseInt(Coordinate.VerticalCoordinates.get(CarCount));
        Parking[HorizontalCoords][VerticalCoords] = (" " + symbolcolour + symbol + Colour.IRON);
    }


    private void printTable(){
        for (int i = 0 ; i < 8 ; i++){
            System.out.print(Parking[0][i]);
        }

        for (int i = 1 ; i < 7 ; i++){
            for(int a = 0 ; a < 8 ; a++){
               if(a == 0){
                   System.out.println("");
                   System.out.print(Parking[0][i]);
               } else {
                   System.out.print(Parking[a][i]);
               }

            }
        }
        for (int i = 0 ; i < 8 ; i++){ //bottom piece
            if(i == 0){
                System.out.println("");
            } else {
                System.out.print(Parking[7][i]);
            }

        }
    }


    private Colour getVehicleColour(char symbol) {

        switch(symbol){
            case 'A' -> {return Colour.ARMY;}
            case 'B' -> {return Colour.BABY;}
            case 'C' -> {return Colour.CREAM;}
            case 'E' -> {return Colour.ELECTRIC;}
            case 'G' -> {return Colour.GREEN;}
            case 'I' -> {return Colour.IRON;}
            case 'L' -> {return Colour.LILAC;}
            case 'M' -> {return Colour.MINT;}
            case 'O' -> {return Colour.ORANGE;}
            case 'P' -> {return Colour.PINK;}
            case 'R' -> {return Colour.RED;}
            case 'S' -> {return Colour.SADDLE;}
            case 'T' -> {return Colour.TURQUOISE;}
            case 'V' -> {return Colour.VIOLET;}
            case 'W' -> {return Colour.WHEAT;}
            case 'Y' -> {return Colour.YELLOW;}
            default -> {return null;}
        }
    }

}
