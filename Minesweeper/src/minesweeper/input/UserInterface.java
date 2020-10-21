package minesweeper.input;

import java.util.Scanner;

/**
 * Responsible: Reading Input, printing Output and delegating validation
 * Collaborators: GameField, Validator
 */
public class UserInterface {
    //
    // COLOR CODES FOR CONSOLE
    //
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_WHITE = "\u001B[37m";
    public static final String TEXT_RED = "\u001B[31m";

    // Scanner for input
    private Scanner scanner;
    // Game state
    private boolean isCancelled = false;

    // Defines GameField and initializes scanner
    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads user input
     * Validates input collaborating with Validator-class
     * @return command to be executed or null if game has been cancelled
     */
    public Command readInput() {
        // Read Input loop
        do {
            // Gather input
            String input = scanner.nextLine();
            // Validate input
            Validator validator = new Validator(input);
            // If command was 'EXIT'
            if (validator.isCancelled()) {
                // Set game state to cancelled
                isCancelled = true;
                // Break
                return null;
            }
            // If Input was valid
            if (validator.istGueltig()) {
                // Return executed command
                return validator.erzeugeKommando();
            } else {
                // Invalid input
                fehlermeldung();
            }
            // Run while game is not cancelled
        } while (!isCancelled);
        return null;
    }

    /**
     * @return game state
     */
    public boolean isCancelled() {
        return isCancelled;
    }

    //
    // CONSOLE MESSAGES & INFO
    //
    public void welcome() {
        System.out.println(TEXT_WHITE + "███╗   ███╗██╗███╗   ██╗███████╗███████╗██╗    ██╗███████╗███████╗██████╗ ███████╗██████╗ \n" +
                "████╗ ████║██║████╗  ██║██╔════╝██╔════╝██║    ██║██╔════╝██╔════╝██╔══██╗██╔════╝██╔══██╗\n" +
                "██╔████╔██║██║██╔██╗ ██║█████╗  ███████╗██║ █╗ ██║█████╗  █████╗  ██████╔╝█████╗  ██████╔╝\n" +
                "██║╚██╔╝██║██║██║╚██╗██║██╔══╝  ╚════██║██║███╗██║██╔══╝  ██╔══╝  ██╔═══╝ ██╔══╝  ██╔══██╗\n" +
                "██║ ╚═╝ ██║██║██║ ╚████║███████╗███████║╚███╔███╔╝███████╗███████╗██║     ███████╗██║  ██║\n" +
                "╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚══════╝╚══════╝ ╚══╝╚══╝ ╚══════╝╚══════╝╚═╝     ╚══════╝╚═╝  ╚═╝\n" +
                "                                                                                          " + TEXT_RESET);

        System.out.println("\n");
        System.out.println(TEXT_YELLOW + "Sie können nun zwei Arten von Aktionen ausführen:");
        System.out.println("  - Ein Feld markieren:");
        System.out.println("  Nutzen Sie den Befehl 'M [X] [Y]' um ein Feld zu markieren");
        System.out.println("  - Ein Feld testen / aufdecken:");
        System.out.println("  Nutzen Sie den Befehl 'T [X] [Y]' um ein Feld aufzudecken \n" + TEXT_RESET);
    }

    public void fehlermeldung() {
        System.out.println("Invalid user input, try again!");
    }

    public void gameOver() {
        System.out.println("\n" + TEXT_RED + " ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗     \n" +
                "██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗    \n" +
                "██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝    \n" +
                "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗    \n" +
                "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║    \n" +
                " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝    \n" +
                "                                                                              " + TEXT_RESET);
        System.out.println(TEXT_YELLOW + "Revealed GameField: \n" + TEXT_RESET);
    }
}
