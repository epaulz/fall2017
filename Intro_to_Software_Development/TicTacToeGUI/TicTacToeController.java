/*
    Eric Paulz (epaulz)
    CPSC 2150-002
    Homework 5
 */

package cpsc2150.hw5;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 *
 * This is where you will write code
 *
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and one of the IGameBoard implementations from Homework 4
 * You can choose which IGameBoard implementation to use
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class besides the package name
 */
public class TicTacToeController {
    //our current game that is being played
    private IGameBoard curGame;
    //to track who's turn it is
    private char curPlayer;
    //The screen that provides our view
    private TicTacToeView screen;
    // no other class variables are needed or should be declared


    // add the code and contracts for the constructor of our tic tac toe game
    /**
     * @param model reusable model which contains entity objects
     * @param view view which allows the user to interact with the code
     * @requires model and view have been initialized and observers have been set up
     * @ensures model, view, controller design will be complete and the user(s) can
     *          play the game
     */
    TicTacToeController(IGameBoard model, TicTacToeView view)
    {
        this.curGame = model;
        this.screen = view;
        this.curPlayer = 'X';
    }

    //Add the code to respond to a user clicking on a button to try to claim a space
    //User will click on the button at row, col
    //User may click on a space that is not available, they do not get that space
    //We no longer have a main function that controls the flow of the game. Users will click buttons, anf
    //Will handle the events here
    // Make sure to make any changes to the screen needed through publicly available functions
    // When a player wins, display a message. You do not need to reset the game or close the window
    // The players can close the window and restart the game themselves
    // remember your javadoc comments and contracts

    /**
     * @param row row position chosen by curPlayer
     * @param col column position chosen by curPlayer
     * @requires 0 <= row < 10 && 0 <= col < 10
     * @ensures if the position is available, curPlayer's symbol will be placed on
     *          the board and a win will be checked for.  else, an error message will
     *          appear.
     */
    public void processButtonClick(int row, int col)
    {
        BoardPosition tempPos = new BoardPosition(col, row, curPlayer);

        // check if the position if available
        if(!curGame.checkSpace(tempPos))
            screen.setMessage("That position is unavailable.  Please pick again.");
        else {
            // place the marker on the game board as well as the screen
            curGame.placeMarker(tempPos);
            screen.setMarker(row, col, curPlayer);

            // check for a win
            if(curGame.checkForWinner(tempPos))
                screen.setMessage("Player " + curPlayer + " wins!");
            else {
                curPlayer = (curPlayer == 'X') ? 'O' : 'X';
                screen.setMessage("It's " + curPlayer + "\'s turn. ");
            }
        }
    }
}
