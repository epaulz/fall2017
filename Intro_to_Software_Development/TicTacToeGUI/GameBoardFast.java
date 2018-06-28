/*
    Eric Paulz (epaulz)
    CPSC 2150-002
    Homework 5
 */

package cpsc2150.hw5;

/**
 * @invariant 0 < rows <= MAX_SIZE
 * @invariant 0 < cols <= MAX_SIZE
 * @invariant 0 < win <= rows && 0 < win <= cols
 * Correspondence: NUM_ROWS = rows
 * Correspondence: NUM_COLS = cols
 * Correspondence: this = grid[0...rows-1][0...cols-1]
 */
public class GameBoardFast implements IGameBoard {
    private int rows, cols, win;
    private char [][] grid;

    /**
     * @param x number of columns from user
     * @param y number of rows from user
     * @param w number of tokens in a row needed for a win
     * @requires [rows, cols, and win satisfy class invariants]
     * @ensures grid[rows][cols] is empty
     */
    GameBoardFast(int x, int y, int w) {
        // set private variables and initialize grid
        rows = y + 1;
        cols = x * 3 + 3;
        win = w;
        grid = new char[MAX_SIZE][MAX_SIZE];

        int i, j;
        char firstDigit = '0';
        char secondDigit = '0';

        // top left corner of grid is blank
        grid[0][0] = ' ';
        grid[0][1] = ' ';
        grid[0][2] = ' ';

        //label the rows
        for (i = 1; i < rows; i++) {
            grid[i][0] = firstDigit;
            grid[i][1] = secondDigit;
            secondDigit++;
            if (i % 10 == 0) {
                firstDigit++;
                secondDigit = '0';
            }
        }

        //labels the columns
        firstDigit = '0';
        secondDigit = '0';
        i = 1;
        for (j = 3; j+2 < cols; j += 3) {
            grid[0][j] = firstDigit;
            grid[0][j + 1] = secondDigit;
            grid[0][j + 2] = ' ';
            i++;
            secondDigit++;
            if(i > 10){
                i = 1;
                firstDigit++;
                secondDigit = '0';
            }
        }

        //separate each space with '|'
        for (i = 1; i < rows; i++) {
            for (j = 2; j < cols; j += 3) {
                grid[i][j] = '|';
                if(j+2 < cols) {
                    grid[i][j + 1] = ' ';
                    grid[i][j + 2] = ' ';
                }
            }
        }
    }

    public boolean checkSpace(BoardPosition pos) {
        return (grid[pos.getRow()][pos.getColumn()] == ' ');
    }

    public void placeMarker(BoardPosition lastPos){
        grid[lastPos.getRow()][lastPos.getColumn()] = lastPos.getPlayer();
    }

    public boolean checkForWinner(BoardPosition lastPos){
        return (checkHorizontalWin(lastPos) || checkVerticalWin(lastPos) || checkDiagonalWin(lastPos));
    }

    /**
     * @param lastPos most recent position played
     * @requires [lastPos.getRow() is on the board]
     * @ensures [lastPos.getRow() will be checked for a win]
     * @return [true if a win is found, false if not]
     */
    private boolean checkHorizontalWin(BoardPosition lastPos) {
        int i, col, count;
        int check = (win * 3) - 2;
        int row = lastPos.getRow();

        for (i = 3; i <= cols-(win*3); i += 3) {
            count = 0;
            for (col = i; col < (i+check); col++) {
                if (grid[row][col] == lastPos.getPlayer()) {
                    count++;
                }
            }
            if (count == win)
                return true;
        }
        return false;
    }

    /**
     * @param lastPos most recent position played
     * @requires [lastPos.getColumn() is on the board]
     * @ensures [lastPos.getColumn() will be checked for a win]
     * @return [true if a win is found, false if not]
     */
    private boolean checkVerticalWin(BoardPosition lastPos){
        int i, row, count;
        int check = win;
        int col = lastPos.getColumn();

        for(i = 1; i <= rows-win; i++){
            count = 0;
            for(row = i; row < (i+check); row++){
                if(grid[row][col] == lastPos.getPlayer())
                    count++;
            }
            if (count == win)
                return true;
        }
        return false;
    }

    /**
     * @param lastPos most recent position played
     * @requires [lastPos.getRow() and lastPos.getColumn() on the board]
     * @ensures [lastPos.getRow() and lastPos.getColumn() will be checked in all directions for a win]
     * @return [true if a win is found, false if not]
     */
    private boolean checkDiagonalWin(BoardPosition lastPos){
        int i, j, k, countTop, countBottom;
        int check = win;

        //checking for diagonals from top to bottom
        for(i = 1; i <= rows-win; i++){
            for(j = 3; j <= cols-(win*3); j+=3){
                countTop = 0;
                for(k = 0; k < check; k++){
                    if(grid[i+k][j+(3*k)] == lastPos.getPlayer()) {
                        countTop++;
                    }
                }
                if(countTop == win) {
                    return true;
                }
            }
        }

        //checking for diagonals from bottom to top
        for(i = rows-1; i >= win; i--){
            for(j = 3; j <= cols-(win*3); j+=3){
                countBottom = 0;
                for(k = 0; k < check; k++){
                    if(grid[i-k][j+(3*k)] == lastPos.getPlayer()) {
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
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                printGrid += grid[i][j];
            }
            printGrid += "\n";
        }
        return printGrid;
    }
}
