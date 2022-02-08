package ca.qc.bdeb.info;

public class Cell {

    static String[][] Parking;

    public Cell(){
        Parking = new String[Settings.PARKING_SIZE][Settings.PARKING_SIZE];


        for (int i = 0 ; i < 8 ; i++){
            Parking[0][i] = " ";
            Parking[0][i] += Settings.BORDER_SYMBOL;
            System.out.print(Parking[0][i]);
        }

        System.out.println("");
        for (int i = 1 ; i < 7 ; i++){
            Parking[i][0] = " ";
            Parking[i][0] += Settings.BORDER_SYMBOL;
            Parking[i][7] = " ";
            Parking[i][7] += Settings.BORDER_SYMBOL;
            if(i == 3){
                Parking[3][7] = "";
                System.out.print(Parking[3][7]);
                System.out.print(Parking[3][0]);
            } else{
                System.out.print(Parking[i][7]);
            }

            for(int a = 0 ; a < 6 ; a++){
                System.out.print("  ");
            }
            System.out.println(Parking[i][7]);
        }
        for (int i = 0 ; i < 8 ; i++){
            Parking[7][i] = " ";
            Parking[7][i] += Settings.BORDER_SYMBOL;
            System.out.print(Parking[7][i]);
        }
    }

}
