package minesweeper.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Responsibility: Manage Cells properties and relative functions
 * Collaborators: GameField
 */
public class Cell {

    // According GameField
    private GameField assignedMatchfield;

    // Positions
    private int x;
    private int y;

    // States
    private boolean isBomb = false;
    private boolean isRevealed = false;
    private boolean isMarked = false;

    /**
     * Cell constructor
     * @param assignedMatchfield
     * @param x
     * @param y
     */
    Cell(GameField assignedMatchfield, int x, int y) {
        this.assignedMatchfield = assignedMatchfield;
        this.x = x;
        this.y = y;
    }

    /**
     * Switch marked state
     */
    void toggleMark() {
        isMarked = !isMarked;
    }

    /**
     * @return amount of neighbour bombs
     */
    private int getNumberOfSurroundingBombs() {
        // List surrounding cells in GameField
        List<Cell> cells = assignedMatchfield.getSurroundingCells(this);
        // Filter the ones who are bombs
        cells = cells.stream().filter(Cell::isBomb).collect(Collectors.toList());
        // Return amount
        return cells.size();
    }

    /**
     * Evaluate which symbol gets chosen for cell
     * @return Number of surrounding bombs
     */
    public String display() {
        // Is Cell Marked
        if (isMarked) {
            return "M";
        }
        // If cell was not revealed
        if (!isRevealed) {
            return "-";
        }
        // If cell is bomb
        if (isBomb) {
            return "*";
        }
        // Get surrounding bombs
        int numberOfBombs = getNumberOfSurroundingBombs();
        // Clear field when there are no bombs surrounding it
        if (numberOfBombs == 0) {
            return " ";
        }
        // Return number that gets displayed on cell
        return String.valueOf(numberOfBombs);
    }

    /**
     * Set bomb
     * @param isBomb
     */
    void setBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    /**
     * Is bomb?
     * @return result
     */
    boolean isBomb() {
        return isBomb;
    }

    /**
     * Reveal cell
     */
    void reveal() {
        // Break if cell has already been revealed
        if (isRevealed) {
            return;
        }

        // Set attributes
        this.isRevealed = true;
        this.isMarked = false;

        // List surrounding Cells
        ArrayList<Cell> surroundingCells = assignedMatchfield.getSurroundingCells(this);
        // Reveal each Cell that has no surroundingBombs
        if (surroundingCells.stream().noneMatch(Cell::isBomb)) {
            for (Cell surroundingCell : surroundingCells) {
                if (!surroundingCell.isBomb()) {
                    surroundingCell.reveal();
                }
            }
        }
    }

    /**
     * @return result
     */
    boolean isRevealed() {
        return isRevealed;
    }

    /**
     * @return result
     */
    boolean isMarked() {
        return isMarked;
    }

    /**
     * @return x pos
     */
    int getX() {
        return x;
    }

    /**
     * @return y pos
     */
    int getY() {
        return y;
    }
}
