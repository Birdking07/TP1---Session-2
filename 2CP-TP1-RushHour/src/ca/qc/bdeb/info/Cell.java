package ca.qc.bdeb.info;

public class Cell {

    public static void parking_lot(){

        String[][] Parking = new String[Settings.PARKING_SIZE][Settings.PARKING_SIZE];

        for (int i = 0 ; i < 8 ; i++){
            Parking[0][i] += Settings.BORDER_SYMBOL;
            Parking[7][i] += Settings.BORDER_SYMBOL;
        }
        for (int i = 1 ; i < 7 ; i++){
            Parking[i][0] += Settings.BORDER_SYMBOL;
            Parking[i][7] += Settings.BORDER_SYMBOL;
        }
        Parking[3][7] = "";



    }
}
