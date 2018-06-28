/*
    Eric Paulz (epaulz)
    CPSC 2150-002
    HW3 (Tic Tac Toe JUnit Testing)
 */

package cpsc2150.hw2;

public class BoardPosition {
    private int row, col;
    private char player;

    /**
     * @param usrRow row chosen by player
     * @param usrCol column chose by player
     * @param currentPlayer current player
     * @requires
     * row >= 0 and row < 8
     * col >= 0 and row < 8
     * currentPlayer == 'X' or currentPlayer == 'O'
     * @ensures
     * [position will be valid on the board] and
     * [player is valid]
     */
    BoardPosition(int usrRow, int usrCol, char currentPlayer){
        row = usrRow;
        col = usrCol;
        player = currentPlayer;
    }

    /**
     * @requires [col has a value]
     * @ensures getColumn = convertedCol
     * @return actual position of the column where the marker will be placed
     */
    public int getColumn(){
        int add = 2;
        int convertedCol = 0;

        for(int i = 0; i < 8; i++){             //conversion from user input to
            if(col == i){                       //actual grid position is necessary
                convertedCol = i + (i + add);   //due to the '|' chars separating each
            }                                   //square and the row/column numbers
        }
        return convertedCol;
    }

    /**
     * @requires [row has a value]
     * @ensures getRow = row+1
     * @return actual position of the row where the marker will be placed
     */
    public int getRow(){ return row + 1; }       //same as ^^

    /**
     * @requires [player has a value]
     * @ensures getPlayer = player
     * @return current player's symbol
     */
    public char getPlayer(){ return player; }
}

