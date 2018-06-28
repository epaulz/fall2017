/*
    Eric Paulz (epaulz)
    CPSC 2150-002
    HW3 (Tic Tac Toe JUnit Testing)
 */

package cpsc2150.hw2;

public class GameBoard {
    private int xMax = 18;  //board has 18 columns (including column numbers and '|' dividers
    private int yMax = 9;   //board has 9 rows (including row for row numbers)
    private int win = 5;
    private char [][] grid;

    //creates the initial board to be played on
    GameBoard(){
        int i, j;
        char first = '0';
        grid = new char[yMax][xMax];

        grid[0][0] = ' ';

        //labels rows
        for(i = 1; i < yMax; i++){
            j = 0;
            grid[i][j] = first;
            first++;
        }

        //labels columns
        first = '0';
        for(j = 1; j < xMax; j++){
            i = 0;
            if(j % 2 == 0) {
                grid[i][j] = first;
                first++;
            }
            else
                grid[i][j] = ' ';
        }

        //separates squares with '|'
        for(i = 1; i < yMax; i++){
            for(j = 1; j < xMax; j++){
                if(j % 2 != 0)
                    grid[i][j] = '|';
                else
                    grid[i][j] = ' ';
            }
        }
    }

    // clears the board to play again
    public void clearBoard() {
        int i, j;
        for (i = 1; i < yMax; i++) {
            for (j = 2; j < xMax; j += 2){
                grid[i][j] = ' ';
            }
        }
    }

    /**
     * @param pos current position being played
     * @return true if pos is empty, otherwise false
     * @requires
     * pos instanceof BoardPosition
     * @ensures
     * [function will accurately check that a position if empty]
     */
    public boolean checkSpace(BoardPosition pos) {
        return (grid[pos.getRow()][pos.getColumn()] == ' ');
    }

    /**
     * @param marker current player's symbol
     * @requires
     * marker instanceof BoardPosition
     * @ensures
     * [position on board will be marked with the current player's symbol]
     */
    public void placeMarker(BoardPosition marker){
        grid[marker.getRow()][marker.getColumn()] = marker.getPlayer();
    }

    /**
     * @param lastPos most recent position played
     * @return true if a win is found, otherwise false
     * @requires
     * lastPos instanceof BoardPosition
     * @ensures
     * [wins will be checked accurately]
     */
    public boolean checkForWinner(BoardPosition lastPos){
        return (checkHorizontalWin(lastPos) || checkVerticalWin(lastPos) || checkDiagonalWin(lastPos));
    }

    /* check for 5 in a row in the row where the last
       position was played

        requires and ensures for checkForWinner make sure that
        this function will be getting correct input
    */
    private boolean checkHorizontalWin(BoardPosition lastPos) {
        int i, col, count;
        int check = 9;
        int row = lastPos.getRow();

        for(i = (xMax-xMax)+2; i < (xMax / 2); i += 2){
            count = 0;
            for(col = i; col < (i + check); col++){
                if(grid[row][col] == lastPos.getPlayer())
                    count++;
            }
            if (count == win)
                return true;
        }
        return false;
    }

    /* check for 5 in a row in the column where the last
       position was played

        requires and ensures for checkForWinner make sure that
        this function will be getting correct input
    */
    private boolean checkVerticalWin(BoardPosition lastPos){
        int i, row, count;
        int check = 5;
        int col = lastPos.getColumn();

        for(i = (yMax-yMax)+1; i < (yMax-yMax+5); i++){
            count = 0;
            for(row = i; row < (i + check); row++){
                if(grid[row][col] == lastPos.getPlayer())
                    count++;
            }
            if (count == win)
                return true;
        }
        return false;
    }

    /* check entire board for 5 in a row diagonally

         requires and ensures for checkForWinner make sure that
        this function will be getting correct input
    */
    private boolean checkDiagonalWin(BoardPosition lastPos){
        int i, j, k, countTop, countBottom;
        int check = 5;

        //checking for diagonals from top to bottom
        for(i = 0; i < (yMax-yMax)+5; i++){
            for(j = 0; j < (xMax / 2); j++){
                countTop = 0;
                for(k = 0; k < check; k++){
                    if(grid[i+k][j+(2*k)] == lastPos.getPlayer()) {
                        countTop++;
                    }
                }
                if(countTop == win) {
                    return true;
                }
            }
        }

        //checking for diagonals from bottom to top
        for(i = yMax-1; i > (yMax-yMax)+3; i--){
            for(j = 0; j < (xMax / 2); j++){
                countBottom = 0;
                for(k = 0; k < check; k++){
                    if(grid[i-k][j+(2*k)] == lastPos.getPlayer()) {
                        countBottom++;
                    }
                }
                if(countBottom == win) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @return string containing the game board
     */
    public String toString() {

        String printGrid = "";
        for(int i = 0; i < yMax; i++){
            for(int j = 0; j < xMax; j++){
                printGrid += grid[i][j];
            }
            printGrid += "\n";
        }
        return printGrid;
    }
}

