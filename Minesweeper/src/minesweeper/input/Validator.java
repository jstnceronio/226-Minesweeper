package minesweeper.input;

/**
 * Responsible: Validating input and producing corresponding command
 * Collaborators: Command
 */
public class Validator {
    // Fractions of input
    private String[] splitInput;
    // Game state
    boolean isCancelled = false;
    // Validation
    private boolean isValid = true;

    /**
     * Splits Input and Validates it
     * Break when input is invalid
     * @param input
     */
    public Validator(String input) {

        // Return if input is EXIT
        if (input.equals("EXIT")) {
            isValid = true;
            isCancelled = true;
            return;
        }

        // Split input otherwise
        splitInput = input.split(" ");

        // length must be longer than 3
        if (splitInput.length != 3) {
            isValid = false;
            return;
        }

        // check fractions
        if (!splitInput[0].equals("T") && !splitInput[0].equals("M")) {
            isValid = false;
            return;
        }

        try {
            // check value of numeric parameters
            Integer.valueOf(splitInput[1]);
            Integer.valueOf(splitInput[2]);
        } catch (NumberFormatException e) {
            isValid = false;
        }
    }

    /**
     * @return validation result
     */
    public boolean istGueltig() {
        return isValid;
    }

    /**
     * Produce new command with validated input
     * @return produced command
     */
    public Command erzeugeKommando() {
        String kommandoZeichen = splitInput[0];
        int zeile = Integer.valueOf(splitInput[1]);
        int spalte = Integer.valueOf(splitInput[2]);
        return new Command(kommandoZeichen, zeile, spalte);
    }

    boolean isCancelled() {
        return isCancelled;
    }

}
