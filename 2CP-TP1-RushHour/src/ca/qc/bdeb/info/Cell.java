package ca.qc.bdeb.info;
 public class Cell {


    public Cell(char currentSymbol , int currentRow){


        CellPrinter(currentSymbol , currentRow);



    }
    public void CellPrinter(char printedSymbol , int currentRow) {

        if (!(printedSymbol == ' ' || printedSymbol == Settings.BORDER_SYMBOL)) {
            Colour symbolcolour;
            try {
                symbolcolour = Settings.get().getColour(printedSymbol);
            } catch (Exception e) {
                System.out.println('!');
                symbolcolour = null;
            }

            System.out.print(symbolcolour + " " + printedSymbol + Colour.IRON);

        } else {

            System.out.print(" " + printedSymbol);
            if (currentRow == 7){
                System.out.print("\n");
            }
        }
    }
 }