package ca.qc.bdeb.info;

import java.util.Locale;

public class Cell {

    static String[][] Parking;

    public Cell(){
        Parking = new String[Settings.PARKING_SIZE][Settings.PARKING_SIZE];


        for (int i = 0 ; i < 8 ; i++){ // Top piece
            Parking[0][i] = " ";
            Parking[0][i] += Settings.BORDER_SYMBOL;
            System.out.print(Parking[0][i]);
        }

        System.out.println("");
        for (int i = 1 ; i < 7 ; i++){ // Rest of the sides
            Parking[i][0] = " ";
            Parking[i][0] += Settings.BORDER_SYMBOL; //used to initialise wall on 4th line
            Parking[i][7] = " ";
            Parking[i][7] += Settings.BORDER_SYMBOL; //used in loops everywhere else
            if(i == 3){ //Hardcoded Door
                Parking[3][7] = "";
                System.out.print(Parking[3][7]);
                System.out.print(Parking[3][0]);
            } else{ //if not the 4th line continue as usual
                System.out.print(Parking[i][7]);
            }



            for(int a = 0 ; a < 6 ; a++){ //Cell Body
                int delta_X = a + 1;
                int delta_Y = i + 1;
                Parking[delta_X][delta_Y] = "  ";
            }
            for (int a = 0 ; i < Challenge.FileSize ; a++ ){ //FIXME Calls all cars into the cell

                 char symbol = Challenge.VehicleColour.get(i).toUpperCase(Locale.ROOT).charAt(0);
                 Colour symbolcolour = Colour.PROMPT;
                if(Settings.get().isValidSymbol(symbol)) {
                     symbolcolour = getVehicleColour(symbol);
                }
                int HorizontalCoords = Integer.parseInt(Coordinate.HorizontalCoordinates.get(i));
                int VerticalCoords = Integer.parseInt(Coordinate.VerticalCoordinates.get(i));
                Parking[HorizontalCoords][VerticalCoords] = (symbolcolour + symbol + " ");
            }
            if(i == 6){
                Parking[6][7] = " ";
                Parking[6][7] += Settings.BORDER_SYMBOL;
                System.out.print(Parking[6][7]);
                break;
            }
            System.out.println(Parking[i][7]); // Right border

        }
        System.out.println("");
        for (int i = 0 ; i < 8 ; i++){ //bottom piece

            Parking[7][i] = " ";
            Parking[7][i] += Settings.BORDER_SYMBOL;
            System.out.print(Parking[7][i]);
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
