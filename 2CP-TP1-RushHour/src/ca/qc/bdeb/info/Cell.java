package ca.qc.bdeb.info;
 public class Cell {


    public Cell(char currentSymbol , char symbol){


        CellPrinter(currentSymbol , symbol);



    }
    public void CellPrinter(char printedSymbol , char symbol) {

        if (symbol != ' ') {
            Colour symbolcolour;
            try {
                symbolcolour = Settings.get().getColour(symbol);
            } catch (Exception e) {
                System.out.println('!');
                symbolcolour = null;
            }
            System.out.print(symbolcolour + " " + printedSymbol + Colour.IRON);

        } else {
            System.out.print(" " + printedSymbol);
        }
    }
 }