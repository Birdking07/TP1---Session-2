package ca.qc.bdeb.info;

import java.util.Scanner;


/**
 * Saisie des commandes utilisateurs.
 *
 * @author  Gilles-Philippe Grégoire et Eric Drouin
 * @version 0.1
 * @since   2022-01-05
 */
public class Prompt {

    final private Scanner scanner = new Scanner(System.in);

    static String UserCommand;

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
    public Command readUserCommand(Challenge challenge) {
        System.out.println("");
        print();
       Command PromptCommand = new Command(UserCommand);

        return PromptCommand;
    }
}