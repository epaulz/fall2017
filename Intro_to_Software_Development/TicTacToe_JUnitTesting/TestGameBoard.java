/*
    Eric Paulz (epaulz)
    CPSC 2150-002
    HW3 (Tic Tac Toe JUnit Testing)
 */

package cpsc2150.hw2;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGameBoard extends TestCase {
    // private fields
    private GameBoard testBoard;
    private int rows, cols;
    private BoardPosition checkTest;
    private BoardPosition place;
    private BoardPosition checkWin;

    @Before
    public void setUp(){
        // create a new blank board for each test case
        testBoard = new GameBoard();
		  rows = 9;
		  cols = 18;
    }

    @After
    public void tearDown(){
        // clear board and nullify fields after each test case
        testBoard.clearBoard();
        testBoard = null;
        checkTest = null;
        place = null;
        checkWin = null;
    }


    @Test
    public void test_checkSpace_1(){
        // put 0,0,X on the board
        BoardPosition check1 = new BoardPosition(0,0, 'X');
        testBoard.placeMarker(check1);

        // test it with the same point and expect false to make sure if will not allow a player
        // to overwrite a token that is already on the board
        checkTest = new BoardPosition(0, 0, 'X');
        assertEquals("testing checkSpace(testCheck)", false, testBoard.checkSpace(checkTest));
    }
    @Test
    public void test_checkSpace_2(){
        // test and open space and expect true
        checkTest = new BoardPosition(7, 7, 'O');
        assertEquals("testing checkSpace(testCheck)", true, testBoard.checkSpace(checkTest));
    }
    @Test
    public void test_checkSpace_3(){
        // test an invalid position and expect false
        checkTest = new BoardPosition(-1, 0, 'O');
        assertEquals("testing checkSpace(testCheck)", false, testBoard.checkSpace(checkTest));
    }
    @Test
    public void test_checkSpace_4(){
        // test another invalid position and expect false
        checkTest = new BoardPosition(0, 8, 'X');
        assertEquals("testing checkSpace(testCheck)", false, testBoard.checkSpace(checkTest));
    }
    @Test
    public void test_checkSpace_5(){
        // test an open space and expect true
        checkTest = new BoardPosition(2, 0, 'O');
        assertEquals("testing checkSpace(testCheck)", true, testBoard.checkSpace(checkTest));
    }

    //check placeMarker here
    @Test
    public void test_placeMarker_1(){
        // create a grid just like in GameBoard
        char [][] tempGrid = new char[rows][cols];
        int i, j;
        char first = '0';
        tempGrid[0][0] = ' ';

        //labels rows
        for(i = 1; i < rows; i++){
            j = 0;
            tempGrid[i][j] = first;
            first++;
        }
        //labels columns
        first = '0';
        for(j = 1; j < cols; j++){
            i = 0;
            if(j % 2 == 0) {
                tempGrid[i][j] = first;
                first++;
            }
            else
                tempGrid[i][j] = ' ';
        }
        //separates squares with '|'
        for(i = 1; i < rows; i++){
            for(j = 1; j < cols; j++){
                if(j % 2 != 0)
                    tempGrid[i][j] = '|';
                else
                    tempGrid[i][j] = ' ';
            }
        }

        // place arbitrary tokens on the grid (all 4 corners of the grid to test boundary cases)
        tempGrid[1][2] = 'X'; tempGrid[1][16] = 'O';
        tempGrid[8][2] = 'O'; tempGrid[8][16] = 'X';

        // convert the test grid into a string
        String tempGridString = "";
        for(i = 0; i < rows; i++){
            for(j = 0; j < cols; j++){
                tempGridString += tempGrid[i][j];
            }
            tempGridString += "\n";
        }

        // place identical tokens on the testBoard (all 4 corners of the grid to test boundary cases)
        BoardPosition a = new BoardPosition(0, 0, 'X');
        BoardPosition b = new BoardPosition(0, 7, 'O');
        BoardPosition c = new BoardPosition(7, 0, 'O');
        BoardPosition d = new BoardPosition(7, 7, 'X');
        testBoard.placeMarker(a); testBoard.placeMarker(b);
        testBoard.placeMarker(c); testBoard.placeMarker(d);

        // convert the board into a string
        String testBoardString = testBoard.toString();

        // compare the two strings for equality
        assertEquals(tempGridString, testBoardString);
    }

    @Test
    public void test_checkForWinner_horizontal_1(){
        // put 4 tokens on the grid horizontally
        BoardPosition horiz2 = new BoardPosition(7, 1, 'O');
        BoardPosition horiz3 = new BoardPosition(7, 2, 'O');
        BoardPosition horiz4 = new BoardPosition(7, 3, 'O');
        BoardPosition horiz5 = new BoardPosition(7, 4, 'O');

        testBoard.placeMarker(horiz2); testBoard.placeMarker(horiz3);
        testBoard.placeMarker(horiz4); testBoard.placeMarker(horiz5);

        // place a token that should create a win
        checkWin = new BoardPosition(7, 0, 'O');
        testBoard.placeMarker(checkWin);

        // test and expect true
        assertEquals("testing checkForWinner(checkWin)", true, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_horizontal_2(){
        // put 4 tokens on the grid horizontally
        BoardPosition horiz1 = new BoardPosition(7, 0, 'O');
        BoardPosition horiz2 = new BoardPosition(7, 1, 'O');
        BoardPosition horiz4 = new BoardPosition(7, 3, 'O');
        BoardPosition horiz5 = new BoardPosition(7, 4, 'O');

        testBoard.placeMarker(horiz1); testBoard.placeMarker(horiz2);
        testBoard.placeMarker(horiz4); testBoard.placeMarker(horiz5);

        // place a token that should create a win
        checkWin = new BoardPosition(7, 2, 'O');
        testBoard.placeMarker(checkWin);

        // test and expect true
        assertEquals("testing checkForWinner(checkWin)", true, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_horizontal_3(){
        // put 4 tokens on the grid horizontally
        BoardPosition horiz1 = new BoardPosition(7, 0, 'O');
        BoardPosition horiz2 = new BoardPosition(7, 1, 'O');
        BoardPosition horiz3 = new BoardPosition(7, 2, 'O');
        BoardPosition horiz4 = new BoardPosition(7, 3, 'O');

        testBoard.placeMarker(horiz1); testBoard.placeMarker(horiz2);
        testBoard.placeMarker(horiz3); testBoard.placeMarker(horiz4);

        // place a token that should create a win
        checkWin = new BoardPosition(7, 4, 'O');
        testBoard.placeMarker(checkWin);

        // test and expect true
        assertEquals("testing checkForWinner(checkWin)", true, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_horizontal_4(){
        // put 4 tokens on the grid horizontally
        BoardPosition horiz1 = new BoardPosition(7, 0, 'O');
        BoardPosition horiz3 = new BoardPosition(7, 2, 'O');
        BoardPosition horiz4 = new BoardPosition(7, 3, 'O');
        BoardPosition horiz5 = new BoardPosition(7, 4, 'O');

        testBoard.placeMarker(horiz1); testBoard.placeMarker(horiz3);
        testBoard.placeMarker(horiz4); testBoard.placeMarker(horiz5);

        // place a token that would create a win except it's the wrong player
        checkWin = new BoardPosition(7, 1, 'X');
        testBoard.placeMarker(checkWin);

        // test and expect false
        assertEquals("testing checkForWinner(checkWin)", false, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_horizontal_5(){
        // put 4 tokens on the grid horizontally
        BoardPosition horiz2 = new BoardPosition(7, 1, 'O');
        BoardPosition horiz3 = new BoardPosition(7, 2, 'O');
        BoardPosition horiz4 = new BoardPosition(7, 3, 'O');
        BoardPosition horiz5 = new BoardPosition(7, 4, 'O');

        testBoard.placeMarker(horiz2); testBoard.placeMarker(horiz3);
        testBoard.placeMarker(horiz4); testBoard.placeMarker(horiz5);

        // place a token that ALMOST creates a win
        checkWin = new BoardPosition(7, 6, 'O');
        testBoard.placeMarker(checkWin);

        // test and expect false
        assertEquals("testing checkForWinner(checkWin)", false, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_vertical_1(){
        // put 4 tokens on the grid vertically
        BoardPosition vert2 = new BoardPosition(1, 7, 'O');
        BoardPosition vert3 = new BoardPosition(2, 7, 'O');
        BoardPosition vert4 = new BoardPosition(3, 7, 'O');
        BoardPosition vert5 = new BoardPosition(4, 7, 'O');

        testBoard.placeMarker(vert2); testBoard.placeMarker(vert3);
        testBoard.placeMarker(vert4); testBoard.placeMarker(vert5);

        // place a token that should create a win
        checkWin = new BoardPosition(0, 7, 'O');
        testBoard.placeMarker(checkWin);

        // test and expect true
        assertEquals("testing checkForWinner(checkWin)", true, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_vertical_2(){
        // put 4 tokens on the grid vertically
        BoardPosition vert1 = new BoardPosition(0, 7, 'O');
        BoardPosition vert2 = new BoardPosition(1, 7, 'O');
        BoardPosition vert4 = new BoardPosition(3, 7, 'O');
        BoardPosition vert5 = new BoardPosition(4, 7, 'O');

        testBoard.placeMarker(vert1); testBoard.placeMarker(vert2);
        testBoard.placeMarker(vert4); testBoard.placeMarker(vert5);

        // place a token that should create a win
        checkWin = new BoardPosition(2, 7, 'O');
        testBoard.placeMarker(checkWin);

        // test and expect true
        assertEquals("testing checkForWinner(checkWin)", true, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_vertical_3(){
        // put 4 tokens on the grid vertically
        BoardPosition vert1 = new BoardPosition(0, 7, 'O');
        BoardPosition vert2 = new BoardPosition(1, 7, 'O');
        BoardPosition vert3 = new BoardPosition(2, 7, 'O');
        BoardPosition vert4 = new BoardPosition(3, 7, 'O');

        testBoard.placeMarker(vert1); testBoard.placeMarker(vert2);
        testBoard.placeMarker(vert3); testBoard.placeMarker(vert4);

        // place a token that should create a win
        checkWin = new BoardPosition(4, 7, 'O');
        testBoard.placeMarker(checkWin);

        // test and expect true
        assertEquals("testing checkForWinner(checkWin)", true, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_vertical_4(){
        // put 4 tokens on the grid vertically
        BoardPosition vert1 = new BoardPosition(0, 7, 'O');
        BoardPosition vert3 = new BoardPosition(2, 7, 'O');
        BoardPosition vert4 = new BoardPosition(3, 7, 'O');
        BoardPosition vert5 = new BoardPosition(4, 7, 'O');

        testBoard.placeMarker(vert1); testBoard.placeMarker(vert3);
        testBoard.placeMarker(vert4); testBoard.placeMarker(vert5);

        // place a token that would create a win except it's the wrong player
        checkWin = new BoardPosition(1, 7, 'X');
        testBoard.placeMarker(checkWin);

        // test and expect false
        assertEquals("testing checkForWinner(checkWin)", false, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_vertical_5(){
        // put 4 tokens on the grid vertically
        BoardPosition vert1 = new BoardPosition(0, 7, 'O');
        BoardPosition vert2 = new BoardPosition(1, 7, 'O');
        BoardPosition vert4 = new BoardPosition(2, 7, 'O');
        BoardPosition vert5 = new BoardPosition(3, 7, 'O');

        testBoard.placeMarker(vert1); testBoard.placeMarker(vert2);
        testBoard.placeMarker(vert4); testBoard.placeMarker(vert5);

        // place a token that ALMOST creates a win
        checkWin = new BoardPosition(5, 7, 'O');
        testBoard.placeMarker(checkWin);

        // test and expect false
        assertEquals("testing checkForWinner(checkWin)", false, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_diagonal_1(){
        // put 4 tokens on the grid diagonally
        BoardPosition diag1 = new BoardPosition(1, 2, 'X');
        BoardPosition diag2 = new BoardPosition(2, 3, 'X');
        BoardPosition diag3 = new BoardPosition(3, 4, 'X');
        BoardPosition diag4 = new BoardPosition(4, 5, 'X');

        testBoard.placeMarker(diag1); testBoard.placeMarker(diag2);
        testBoard.placeMarker(diag3); testBoard.placeMarker(diag4);

        // place a token that should create a win
        checkWin = new BoardPosition(0, 1, 'X');
        testBoard.placeMarker(checkWin);

        // test and expect true
        assertEquals("testing checkForWinner(checkWin)", true, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_diagonal_2(){
        // put 4 tokens on the grid diagonally
        BoardPosition diag1 = new BoardPosition(0, 1, 'X');
        BoardPosition diag2 = new BoardPosition(1, 2, 'X');
        BoardPosition diag3 = new BoardPosition(2, 3, 'X');
        BoardPosition diag4 = new BoardPosition(3, 4, 'X');

        testBoard.placeMarker(diag1); testBoard.placeMarker(diag2);
        testBoard.placeMarker(diag3); testBoard.placeMarker(diag4);

        // place a token that should create a win
        checkWin = new BoardPosition(4, 5, 'X');
        testBoard.placeMarker(checkWin);

        // test and expect true
        assertEquals("testing checkForWinner(checkWin)", true, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_diagonal_3(){
        // put 4 tokens on the grid diagonally
        BoardPosition diag1 = new BoardPosition(6, 0, 'X');
        BoardPosition diag2 = new BoardPosition(4, 2, 'X');
        BoardPosition diag3 = new BoardPosition(3, 3, 'X');
        BoardPosition diag4 = new BoardPosition(2, 4, 'X');

        testBoard.placeMarker(diag1); testBoard.placeMarker(diag2);
        testBoard.placeMarker(diag3); testBoard.placeMarker(diag4);

        // place a token that should create a win
        checkWin = new BoardPosition(5, 1, 'X');
        testBoard.placeMarker(checkWin);

        // test and expect true
        assertEquals("testing checkForWinner(checkWin)", true, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_diagonal_4(){
        // put 4 tokens on the grid diagonally
        BoardPosition diag1 = new BoardPosition(6, 0, 'X');
        BoardPosition diag2 = new BoardPosition(5, 1, 'X');
        BoardPosition diag3 = new BoardPosition(4, 2, 'X');
        BoardPosition diag4 = new BoardPosition(2, 4, 'X');

        testBoard.placeMarker(diag1); testBoard.placeMarker(diag2);
        testBoard.placeMarker(diag3); testBoard.placeMarker(diag4);

        // place a token that should create a win
        checkWin = new BoardPosition(3, 3, 'X');
        testBoard.placeMarker(checkWin);

        // test and expect true
        assertEquals("testing checkForWinner(checkWin)", true, testBoard.checkForWinner(checkWin));
    }

    @Test
    public void test_checkForWinner_diagonal_5(){
        // put 4 tokens on the grid diagonally
        BoardPosition diag1 = new BoardPosition(6, 0, 'X');
        BoardPosition diag2 = new BoardPosition(5, 1, 'X');
        BoardPosition diag3 = new BoardPosition(4, 2, 'X');
        BoardPosition diag4 = new BoardPosition(2, 4, 'X');

        testBoard.placeMarker(diag1); testBoard.placeMarker(diag2);
        testBoard.placeMarker(diag3); testBoard.placeMarker(diag4);

        // place a token that would create a win except it's the wrong player
        checkWin = new BoardPosition(3, 3, 'O');
        testBoard.placeMarker(checkWin);

        // test and expect false
        assertEquals("testing checkForWinner(checkWin)", false, testBoard.checkForWinner(checkWin));
    }
    @Test
    public void test_checkForWinner_diagonal_6(){
        // put 4 tokens on the grid diagonally
        BoardPosition diag1 = new BoardPosition(0, 1, 'X');
        BoardPosition diag2 = new BoardPosition(1, 2, 'X');
        BoardPosition diag3 = new BoardPosition(2, 3, 'X');
        BoardPosition diag4 = new BoardPosition(3, 4, 'X');

        testBoard.placeMarker(diag1); testBoard.placeMarker(diag2);
        testBoard.placeMarker(diag3); testBoard.placeMarker(diag4);

        // place a token that ALMOST creates a win
        checkWin = new BoardPosition(5, 6, 'X');
        testBoard.placeMarker(checkWin);

        // test and expect false
        assertEquals("testing checkForWinner(checkWin)", false, testBoard.checkForWinner(checkWin));
    }
}
