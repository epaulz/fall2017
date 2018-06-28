/*
    Eric Paulz (epaulz)
    CPSC 2150-002
    Homework 4
 */

package cpsc2150.hw4;

import java.util.*;
import static cpsc2150.hw4.IGameBoard.MAX_SIZE;

public class GameScreen {
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("| TTTTTTT  I    CCC    TTTTTTT       A          CCC    TTTTTTT    OOO    EEEEEEE |");
        System.out.println("|    T     I   C          T         A A        C          T      O   O   E       |");
        System.out.println("|    T     I  C           T        A   A      C           T     O     O  E       |");
        System.out.println("|    T     I  C           T       AAAAAAA     C           T     O     O  EEEE    |");
        System.out.println("|    T     I  C           T      A       A    C           T     O     O  E       |");
        System.out.println("|    T     I   C          T     A         A    C          T      O   O   E       |");
        System.out.println("|    T     I    CCC       T    A           A    CCC       T       OOO    EEEEEEE |");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("***********************************************");
        System.out.println("** X plays first.                            **");
        System.out.println("***********************************************");

        boolean playAgain = true;
        while(playAgain){
            int rows=0, cols=0, win=0;
            String imp="a";
            IGameBoard board;

            // set up the game by asking the user for specifications
            Scanner setup = new Scanner(System.in);
            while(rows < 1 || rows > MAX_SIZE) {
                System.out.println("\nHow many rows should be on the board? (0 < rows <= " + MAX_SIZE + ")");
                rows = setup.nextInt();
            }
            while(cols < 1 || cols > MAX_SIZE) {
                System.out.println("How many columns should be on the board? (0 < columns <= " + MAX_SIZE + ")");
                cols = setup.nextInt();
            }
            while(win < 1 || (win > rows && win > cols)) {
                System.out.println("How many in a row to win? (0 < win <= rows) OR (0 < win <= columns)");
                win = setup.nextInt();
            }

            setup.nextLine();
            while(!imp.equals("F") && !imp.equals("f") && !imp.equals("M") && !imp.equals("m")) {
                System.out.println("Enter F for a (F)ast implementation or M for a (M)emory efficient implementation");
                imp = setup.nextLine();
            }

            // create the board based on the implementation selected by the user
            if(imp.equals("F") || imp.equals("f"))
                board = new GameBoardFast(cols, rows, win);
            else
                board = new GameBoardMem(cols, rows, win);

            System.out.println(board.toString());

            boolean winner = false;
            int xMovesCount = 0;
            int yMovesCount = 0;
            while(!winner) {

                //Player X
                boolean spaceOpen = false;
                while (!spaceOpen) {
                    int playerX_ROW, playerX_COL;

                    System.out.println("Player X, pick your ROW coordinate (0-7): ");
                    Scanner scanX = new Scanner(System.in);
                    playerX_ROW = scanX.nextInt();


                    System.out.println("Now pick your COLUMN coordinate (0-7): ");
                    Scanner scanY = new Scanner(System.in);
                    playerX_COL = scanY.nextInt();

                    //create BoardPosition object to hold current position
                    BoardPosition posX = new BoardPosition(playerX_ROW, playerX_COL, 'X');

                    //check if position is open on the board
                    if (board.checkSpace(posX)) {
                        board.placeMarker(posX);
                        spaceOpen = true;
                        xMovesCount++;

                        //check for a winner if the number of moves players is >= win
                        if (xMovesCount >= win) {
                            if (board.checkForWinner(posX)) {
                                xMovesCount = 0;
                                System.out.println(board.toString());

                                System.out.println("\nPlayer X has won!");
                                System.out.println("Would you like to play again? (Y or N)");
                                Scanner yOrN = new Scanner(System.in);
                                char again = yOrN.next().charAt(0);

                                if (again == 'y' || again == 'Y') {
                                    System.out.println();
                                    winner = true;
                                    break;
                                }
                                else if (again == 'n' || again == 'N')
                                    playAgain = false;

                                return;
                            }
                        }
                        System.out.println(board.toString());
                    }
                    else {
                        System.out.println("That space is unavailable.  Please pick again.");
                        System.out.println(board.toString());
                    }
                }

                //Player Y
                spaceOpen = false;
                while (!spaceOpen) {
                    if(winner)
                        break;

                    int playerY_ROW, playerY_COL;

                    System.out.println("Player Y, pick your ROW coordinate (0-7): ");
                    Scanner scanX = new Scanner(System.in);
                    playerY_ROW = scanX.nextInt();


                    System.out.println("Now pick your COLUMN coordinate (0-7): ");
                    Scanner scanY = new Scanner(System.in);
                    playerY_COL = scanY.nextInt();

                    BoardPosition posY = new BoardPosition(playerY_ROW, playerY_COL, 'O');

                    if (board.checkSpace(posY)) {
                        board.placeMarker(posY);
                        spaceOpen = true;
                        yMovesCount++;

                        if (yMovesCount >= 5) {
                            if (board.checkForWinner(posY)) {
                                yMovesCount = 0;
                                System.out.println(board.toString());

                                System.out.println("\nPlayer Y has won!");
                                System.out.println("Would you like to play again? (y or n)");
                                Scanner yOrN = new Scanner(System.in);
                                char again = yOrN.next().charAt(0);

                                if (again == 'y' || again == 'Y') {
                                    System.out.println();
                                    winner = true;
                                    break;
                                } else if (again == 'n' || again == 'N')
                                    playAgain = false;

                                return;
                            }
                        }
                        System.out.println(board.toString());
                    }
                    else {
                        System.out.println("That space is unavailable.  Please pick again.");
                        System.out.println(board.toString());
                    }
                }
            }
        }
    }
}
