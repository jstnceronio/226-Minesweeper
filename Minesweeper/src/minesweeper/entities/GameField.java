package minesweeper.entities;

import java.util.ArrayList;
import java.util.Random;

/**
 * Responsibility:
 * Collaborators: Cell
 */
public class GameField {

    // 2D-Array of Cells on the GameField
    private ArrayList<ArrayList<Cell>> cells = new ArrayList<>(new ArrayList<>());

    // Attributes of GameField
    private int size = 9;
    private int bombs = 10;

    // Generate random
    private Random rdm = new Random();

    /**
     * Creates a new GameField with empty cells then fills them with bombs
     */
    public GameField() {
        for (int i = 0; i < size; i++) {
            // Row of cells
            ArrayList<Cell> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                // Add cell to Row-Array
                row.add(new Cell(this, i, j));
            }
            // Add row to Cell-Array
            cells.add(row);
        }

        // Fill empty cells with bombs
        for (int i = 0; i < bombs; i++) {
            // pick random x / y - coordinates
            int x = rdm.nextInt(size);
            int y = rdm.nextInt(size);
            // get cell with according position
            Cell cell = getCell(x, y);

            // do not refill same cell again
            if (cell.isBomb()) {
                i--;
                continue;
            }
            // fill cell
            cell.setBomb(true);
        }
    }

    /**
     * @param x
     * @param y
     * @return cell with according position
     * @throws IndexOutOfBoundsException
     */
    private Cell getCell(int x, int y) throws IndexOutOfBoundsException {
        return cells.get(x).get(y);
    }

    /**
     * @return true if all bombs have either been revealed or marked
     */
    private boolean isWon() {
        return cells.stream().anyMatch(zeile -> zeile.stream().noneMatch(cell -> (cell.isBomb() && cell.isMarked()) || (!cell.isBomb() && cell.isRevealed())));
    }

    /**
     * Returns cell relative to the other
     * @param start Cell of origin
     * @param xModifier x Modification (positive or negative)
     * @param yModifier y Modification (positive or negative)
     * @return cell on the new position
     * @throws IndexOutOfBoundsException when the new target is out of bounds
     */
    private Cell getRelative(Cell start, int xModifier, int yModifier) throws IndexOutOfBoundsException {
        int newX = start.getX() + xModifier;
        int newY = start.getY() + yModifier;

        if (newX < 0 || newX >= size || newY < 0 || newY >= size) {
            return null;
        }
        return getCell(newX, newY);
    }

    /**
     * Returns an array of all surrounding cells
     * @param field Cell of origin
     * @return All (up to 8) surrounding cells
     */
    ArrayList<Cell> getSurroundingCells(Cell field) {
        ArrayList<Cell> fields = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                // Get relative
                Cell relative = getRelative(field, i, j);
                if (relative != null) {
                    // Add relative to list
                    fields.add(relative);
                }
            }
        }
        return fields;
    }

    /**
     * Tests if given cell is a bomb
     * @param x pos
     * @param y pos
     * @return true if the game should end, false oterwhise
     * @throws IndexOutOfBoundsException if cell is out of bounds
     */
    public boolean test(int x, int y) throws IndexOutOfBoundsException {
        // Get Cell
        Cell selected = getCell(x, y);


        if (selected.isRevealed()) {
            System.out.println("This field has already been revealed!");
            return false;
        }

        if (selected.isBomb()) {
            System.out.println("You stepped on a bomb! Game Over!");
            return true;
        }

        selected.reveal();

        if (!isWon()) {
            System.out.println("You cleared the field! Congratulations you have won!");
            return true;
        }
        return false;
    }

    public void mark(int x, int y) throws IndexOutOfBoundsException {
        Cell selected = getCell(x, y);

        if (selected.isRevealed()) {
            System.out.println("Dieses Feld wurde bereits aufgedeckt und kann nicht mehr markiert werden.");
            return;
        }

        selected.toggleMark();
    }

    /**
     * Zeigt das Spielfeld formatiert in der Konsole an.
     */
    public void display() {
        for(int i = -1; i < size; i++) {
            if (i == -1) {
                System.out.print("   ");
            } else {
                System.out.print(" " + i + " ");
            }
            for (int j = 0; j < size; j++) {
                if (i == -1) {
                    System.out.print(" " + j + " ");
                } else {
                    System.out.print(" " + getCell(j, i).display() + " ");
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * Reveals all cells
     */
    public void revealAll() {
        cells.forEach(zeile -> zeile.forEach(Cell::reveal));
    }
}
