/*
    Eric Paulz (epaulz)
    CPSC 2150-002
    Homework 4
 */

package cpsc2150.hw4;

import java.util.*;

/**
 * @invariant 0 < rows <= MAX_SIZE
 * @invariant 0 < cols <= MAX_SIZE
 * @invariant 0 < win <= rows && 0 < win <= cols
 * Correspondence: NUM_ROWS = rows
 * Correspondence: NUM_COLS = cols
 */
public class GameBoardMem implements IGameBoard{
    private int cols, rows, win;
    private List<BoardPosition> X, O;

    /**
     * @param x number of columns from user
     * @param y number of rows from user
     * @param w number of tokens in a row needed for a win
     * @requires [rows, cols, and win satisfy class invariants]
     * @ensures (X.isEmpty() && X instanceof ArrayList) && (O.isEmpty() && O instanceof ArrayList)
     */
    GameBoardMem(int x, int y, int w){
        cols = (x*3) + 3;
        rows = y + 1;
        win = w;
        X = new ArrayList<>();
        O = new ArrayList<>();
    }

    public boolean checkSpace(BoardPosition pos){
        boolean a = X.contains(pos);
        boolean b = O.contains(pos);
        return !(a || b);
    }

    public void placeMarker(BoardPosition lastPos){
        if(lastPos.getPlayer() == 'X')
            X.add(lastPos);
        else
            O.add(lastPos);
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
        int left = 1, right = 1, count = 1;
        List<BoardPosition> tempList;

        // assign temporary list appropriately
        if (lastPos.getPlayer() == 'X')
            tempList = X;
        else
            tempList = O;

        // start by checking positions to the west of lastPos
        for (int i = 0; i < win; i++) {
            BoardPosition tempPos = new BoardPosition(lastPos.getRow() - 1, ((lastPos.getColumn() - 3) / 3) - left, lastPos.getPlayer());
            if (tempList.contains(tempPos)) {
                count++;
                left++;
            }
            else
                break;
        }
        // if win not found yet, check positions east of lastPos
        if (count < win) {
            for (int i = 0; i < (win - count) + 1; i++) {
                BoardPosition tempPos = new BoardPosition(lastPos.getRow() - 1, ((lastPos.getColumn() - 3) / 3) + right, lastPos.getPlayer());
                if (tempList.contains(tempPos)) {
                    count++;
                    right++;
                }
                else
                    break;
            }
        }
        return (count == win);
    }

    /**
     * @param lastPos most recent position played
     * @requires [lastPos.getColumn() is on the board]
     * @ensures [lastPos.getColumn() will be checked for a win]
     * @return [true if a win is found, false if not]
     */
    private boolean checkVerticalWin(BoardPosition lastPos){
        int down = 1, up = 1, count = 1;
        List<BoardPosition> tempList;

        // assign temporary list appropriately
        if(lastPos.getPlayer() == 'X')
            tempList = X;
        else
            tempList = O;

        // start by checking positions south of lastPos
        for (int i = 0; i < win; i++) {
            BoardPosition tempPos = new BoardPosition((lastPos.getRow()-1)+down, (lastPos.getColumn()-3)/3, lastPos.getPlayer());
            if (tempList.contains(tempPos)){
                count++;
                down++;
            }
            else
                break;
        }
        // if win not found yet, check positions north of lastPos
        if(count < win){
            for(int i = 0; i < (win-count)+1; i++){
                BoardPosition tempPos = new BoardPosition((lastPos.getRow()-1)-up, (lastPos.getColumn()-3)/3, lastPos.getPlayer());
                if(tempList.contains(tempPos)){
                    count++;
                    up++;
                }
                else
                    break;
            }
        }
        return (count == win);
    }

    /**
     * @param lastPos most recent position played
     * @requires [lastPos.getRow() and lastPos.getColumn() on the board]
     * @ensures [lastPos.getRow() and lastPos.getColumn() will be checked in all directions for a win]
     * @return [true if a win is found, false if not]
     */
    private boolean checkDiagonalWin(BoardPosition lastPos){
        int up=1, down=1, left=1, right=1, count=1;
        List<BoardPosition> tempList;

        // assign temporary list appropriately
        if(lastPos.getPlayer() == 'X')
            tempList = X;
        else
            tempList = O;

        // start by checking positions southeast of lastPos
        for(int i=0; i < win; i++) {
            BoardPosition tempPos = new BoardPosition((lastPos.getRow()-1)+down, ((lastPos.getColumn()-3)/3)+right, lastPos.getPlayer());
            if (tempList.contains(tempPos)) {
                count++;
                down++;
                right++;
            }
            else
                break;
        }
        // if win not found yet, check positions northwest of lastPos
        if(count < win){
            for(int i=0; i < (win-count)+1; i++){
                BoardPosition tempPos = new BoardPosition((lastPos.getRow()-1)-up, ((lastPos.getColumn()-3)/3)-left, lastPos.getPlayer());
                if(tempList.contains(tempPos)){
                    count++;
                    up++;
                    left++;
                }
                else
                    break;
            }
        }

        // if win still not found, reset variables and check other direction
        // start by checking positions southwest of lastPos
        if(count < win){
            up=1; down=1; left=1; right=1; count=1;
            for(int i=0; i < win; i++) {
                BoardPosition tempPos = new BoardPosition((lastPos.getRow()-1)+down, ((lastPos.getColumn()-3)/3)-left, lastPos.getPlayer());
                if (tempList.contains(tempPos)) {
                    count++;
                    down++;
                    left++;
                }
                else
                    break;
            }
            // if win not found, check positions northeast of lastPos
            if(count != win){
                for(int i=0; i < (win-count)+1; i++){
                    BoardPosition tempPos = new BoardPosition((lastPos.getRow()-1)-up, ((lastPos.getColumn()-3)/3)+right, lastPos.getPlayer());
                    if(tempList.contains(tempPos)){
                        count++;
                        up++;
                        right++;
                    }
                    else
                        break;
                }
            }
        }
        return (count == win);
    }

    /**
     * @return string containing the game board
     */
    public String toString() {
        String printGrid = "";

        // top left corner on board is blank
        printGrid += "   ";

        char firstDigit = '0';
        char secondDigit = '0';
        int counter = 1;

        // label the columns
        for(int j = 3; j < cols; j+=3){
            printGrid += firstDigit;
            printGrid += secondDigit;
            printGrid += ' ';
            counter++;
            secondDigit++;
            if(counter > 10){
                counter = 1;
                firstDigit++;
                secondDigit = '0';
            }
        }

        printGrid += '\n';


        firstDigit = '0';
        secondDigit = '0';
        counter = 1;

        // label the rows
        for(int i = 1; i < rows; i++){
            printGrid += firstDigit;
            printGrid += secondDigit;
            printGrid += '|';
            counter++;
            secondDigit++;
            if(counter > 10){
                counter = 1;
                firstDigit++;
                secondDigit = '0';
            }

            // fill the board with either 'X', 'O', or '|' conditionally
            for(int j = 3; j < cols; j+=3){
                BoardPosition tempPosX = new BoardPosition(i-1, (j-3)/3, 'X');
                BoardPosition tempPosO = new BoardPosition(i-1, (j-3)/3, 'O');

                if(X.contains(tempPosX)) {
                    printGrid += "X |";
                }
                else if(O.contains(tempPosO)) {
                    printGrid += "O |";
                }
                else {
                    printGrid += "  |";
                }
            }
            printGrid += "\n";
        }
        return printGrid;
    }
}


