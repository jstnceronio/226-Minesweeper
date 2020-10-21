package minesweeper.input;


import minesweeper.entities.GameField;

/**
 * Responsible: Create command according to given letter and execute it
 */
public class Command {

    // Input letter
    private String commandoLetter;
    // Positions
    private int x;
    private int y;


    /**
     * @param commandoLetter
     * @param zeile
     * @param spalte
     */
    public Command(String commandoLetter, int zeile, int spalte) {
        this.commandoLetter = commandoLetter;
        this.x = zeile;
        this.y = spalte;
    }

    /**
     * Either tests or marks cells
     * @param gamefield
     */
    public boolean execute(GameField gamefield) {
        switch (commandoLetter) {
            case "T":
                if (gamefield.test(x, y)) {
                    return true;
                } else {
                    return false;
                }
            case "M":
                gamefield.mark(x, y);
                return false;
        }
        return false;
    }
}
