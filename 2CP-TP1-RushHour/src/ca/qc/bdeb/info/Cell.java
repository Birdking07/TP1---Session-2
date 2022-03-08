package ca.qc.bdeb.info;
 public class Cell {


     /*
     currentSymbol = symbole envoyé vers cell
     currentRow = Array courante de la table 2D (position verticale)
     currentColumn = position dans currentRow dans la table 2D (position Horizontale)
      */
    public Cell(char currentSymbol , int currentRow , int currentColumn){


        CellPrinter(currentSymbol , currentRow , currentColumn);



    }


    public void CellPrinter(char printedSymbol , int currentRow , int currentColumn) {

        //vérifie que la procédure suivante s'applique seulement aux véhicules
        if (!(printedSymbol == ' ' || printedSymbol == Settings.BORDER_SYMBOL)) {
            Colour symbolColour;
            try {
                symbolColour = Settings.get().getColour(printedSymbol);
            } catch (Exception e) {
                System.out.println('!');
                symbolColour = null;
            }
            //saute une ligne lors de la complétion (seulement visible à la fin du dernier défi
            if (currentRow == 3 && currentColumn == 7 && printedSymbol == 'R'){
                System.out.println(" " + symbolColour + printedSymbol + Colour.IRON);
                //procédure d'affichage pour le reste des véhicules
            }else {
                System.out.print( " " + symbolColour + printedSymbol + Colour.IRON);
            }

        } else {
            //lors de l'affichage du 8ᵉ élément de la colonne on saute une ligne
            System.out.print(" " + printedSymbol);
            if (currentColumn == 7){
                System.out.print("\n");
            }
        }
    }
 }