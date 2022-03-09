package ca.qc.bdeb.info;

public class Coordinate {

     public String HorizontalCoordinates; // la position sur l'axe horizontale sous forme string du fichier
     public String VerticalCoordinates; //la position sur l'axe verticale sous forme string du fichier
     public Orientation CarOrientation; //l'orientation du véhicule

    //CarCounter représente la position de VehicleCoordinates out VehicleOrientation utilisé
    public Coordinate(int CarCounter){
        ReadingCarCoordinates(CarCounter);
        ReadingCarOrientation(CarCounter);
    }

    private void ReadingCarCoordinates(int CarCounter){

        //on sépare les valeurs horizontales et verticales
            String[] CurrentCoordinates = Challenge.VehicleCoordinates.get(CarCounter).split(",");
            HorizontalCoordinates = CurrentCoordinates[0];
            VerticalCoordinates = CurrentCoordinates[1];

    }


    private void ReadingCarOrientation(int CarCounter){

        //transforme VehicleOrientation sous forme String à soit Horizontal ou Vertical dépendant du contenu.
            if(Challenge.VehicleOrientation.get(CarCounter).equalsIgnoreCase("H")){
                CarOrientation = Orientation.Horizontal;
            } else if (Challenge.VehicleOrientation.get(CarCounter).equalsIgnoreCase("V")){
                CarOrientation = Orientation.Vertical;
            }

    }


}
