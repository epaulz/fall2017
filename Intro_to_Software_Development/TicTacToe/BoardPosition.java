/*
    Eric Paulz (epaulz)
    CPSC 2150-002
    Homework 4
 */

package cpsc2150.hw4;

public class BoardPosition {
    private int row, col;
    private char player;

    /**
     * @param usrRow row chosen by player
     * @param usrCol column chose by player
     * @param currentPlayer current player
     * @requires row >= 0 && row < MAX_SIZE
     * @requires col >= 0 && col < MAX_SIZE
     * @requires currentPlayer == 'X' || currentPlayer == 'O'
     * @ensures [will be a valid board position and player token]
     */
    BoardPosition(int usrRow, int usrCol, char currentPlayer){
        row = usrRow;
        col = usrCol;
        player = currentPlayer;
    }

    /**
     * @requires col >= 0 && col < MAX_SIZE
     * @ensures {}user col will be converted to actual grid position}
     * @return (col+1)*3
     */
    public int getColumn(){
        //conversion from user input to
        //actual grid position is necessary
        //due to the '|' chars separating each
        //square and the row/column numbers
        return (col + 1) * 3;
    }

    /**
     * @requires row >= 0 && row < MAX_SIZE
     * @ensures [user row will be converted to actual grid position]
     * @return row+1
     */
    public int getRow(){ return row + 1; }

    /**
     * @requires player == 'X' || player == 'O'
     * @ensures [player token is valid]
     * @return player
     */
    public char getPlayer(){ return player; }

    /**
     * @param obj other object that we are comparing to current one
     * @return [whether or not the two objects are equal]
     * @requires [the argument is an object]
     * @ensures [the function will compare equality correctly]
     */
    public boolean equals(Object obj){
        if(obj instanceof BoardPosition){
            BoardPosition pos = (BoardPosition) obj;
            return (pos.col == this.col && pos.row == this.row && pos.player == this.player);
        }
        else{
            return false;
        }
    }
}
