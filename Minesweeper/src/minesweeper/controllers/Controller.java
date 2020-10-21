package minesweeper.controllers;

import minesweeper.input.Command;
import minesweeper.input.UserInterface;
import minesweeper.entities.GameField;

/**
 * Responsible: Handling game loop and interaction between them
 * Collaborators: GameField, UserInterface and Command
 */
public class Controller {

    // GameField to apply commands on
    private final GameField gamefield = new GameField();
    // UserInterface to handle input/output
    private final UserInterface userInterface
            = new UserInterface();

    // Starts the game
    public void play() {
        // Welcome message
        userInterface.welcome();
        // Game Loop
        do {
            // Display GameField
            gamefield.display();
            // Create command with validated input
            Command command = userInterface.readInput();
            // If game has not been cancelled during the loop
            if (!userInterface.isCancelled())
                // Execute command and see if mine has been hit or field cleared
                if (command.execute(gamefield)) {
                    break;
                }
            // While game has not been cancelled
        } while(!userInterface.isCancelled());

        // Reveal entire field
        gamefield.revealAll();
        // GameOver Message
        userInterface.gameOver();
        // Display entire field
        gamefield.display();
    }
}
