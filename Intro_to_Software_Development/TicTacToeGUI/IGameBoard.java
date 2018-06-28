/*
    Eric Paulz (epaulz)
    CPSC 2150-002
    Homework 5
 */

package cpsc2150.hw5;

/**
 * IGameBoard represents a 2-dimensional gameboard that has characters
 * on it as markers (X, O).  No space on the board can have multiple
 * players, and there can be a clear winner.  Board is NUM_ROWS x
 * NUM_COLS in size
 *
 * Initialization ensures: the board does not have any markers on it
 * Defines: NUM_ROWS: Z
 * Defines: NUM_COLS: Z
 * Constraints: 0 < NUM_ROWS <= MAX_SIZE
 *              0 < NUM_COLS <= MAX_SIZE
 */

public interface IGameBoard{
    int MAX_SIZE = 100;

    /**
     * @param pos current position being played
     * @return true if pos is empty, false otherwise
     * @requires pos instanceof BoardPosition
     * @ensures [function will accurately check that a position is empty]
     */
    boolean checkSpace(BoardPosition pos);

    /**
     * @param lastPos current player's position and symbol
     * @requires lastPos instanceof BoardPosition
     * @ensures [position on board will be marked with the current player's symbol]
     */
    void placeMarker(BoardPosition lastPos);

    /**
     * @param lastPos most recent position played
     * @return true if a win is found, false otherwise
     * @requires lastPos instanceof BoardPosition
     * @ensures [wins will be checked accurately]
     */
    boolean checkForWinner(BoardPosition lastPos);
}
