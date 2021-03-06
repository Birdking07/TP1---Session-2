package ca.qc.bdeb.info;


/**
 * Saisie des commandes utilisateurs.
 *
 * @author  Gilles-Philippe Grégoire et Eric Drouin
 * @version 0.1
 * @since   2022-01-05
 */
public class Prompt {

    /**
     * Affiche le prompt.
     */
    private void print() {

        System.out.print(" " + Colour.PROMPT + "Commande (#N, #S, #E, #W ou Q): ");
    }

    /**
     * Saisit une commande *obligatoirement valide* de l'utilisateur.
     * La méthode répète la saisie si la commande est invalide.
     *
     * @param challenge le défi présentement en cours (celui où récupérer les véhicules)
     * @return la commande saisie
     */

    //affiche le prompt pour la commande et retourne une instance de la classe Commande
    public Command readUserCommand(Challenge challenge) {

        print();

        return new Command();
    }
}