package minesweeper.controllers;

/**
 * MINESWEEPER PROJECT
 * LECTURE 10: DELEGATION
 *
 * Responsible: Launching game
 * Collaborators: Controller
 */
public class Minesweeper {

    // Entry-point
    public static void main(String[] args) {
        // Instantiate controller
        Controller controller = new Controller();
        // Start game
        controller.play();
    }
}
