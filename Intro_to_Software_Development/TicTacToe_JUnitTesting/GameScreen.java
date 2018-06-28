/*
    Eric Paulz (epaulz)
    CPSC 2150-002
    HW2 (Extended Tic Tac Toe)
 */

package cpsc2150.hw2;

import java.util.*;

public class GameScreen {
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("|  888           888    TTTTTTT  I    CCC    TTTTTTT       A          CCC    TTTTTTT    OOO    EEEEEEE |");
        System.out.println("| 8   8         8   8      T     I   C          T         A A        C          T      O   O   E       |");
        System.out.println("| 8   8         8   8      T     I  C           T        A   A      C           T     O     O  E       |");
        System.out.println("|  888           888       T     I  C           T       AAAAAAA     C           T     O     O  EEEE    |");
        System.out.println("| 8   8   x x   8   8      T     I  C           T      A       A    C           T     O     O  E       |");
        System.out.println("| 8   8    x    8   8      T     I   C          T     A         A    C          T      O   O   E       |");
        System.out.println("|  888    x x    888       T     I    CCC       T    A           A    CCC       T       OOO    EEEEEEE |");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("***********************************************");
        System.out.println("** Connect 5 squares in any direction to win.**");
        System.out.println("** X plays first.                            **");
        System.out.println("***********************************************");

        //create and print the initial board
        GameBoard board = new GameBoard();
        System.out.println(board.toString());

        boolean playAgain = true;
        while(playAgain){
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

                        //check for a winner if at least 5 moves have been played
                        if (xMovesCount >= 5) {
                            if (board.checkForWinner(posX)) {
                                xMovesCount = 0;
                                System.out.println(board.toString());

                                System.out.println("\nPlayer X has won!");
                                System.out.println("Would you like to play again? (Y or N)");
                                Scanner yOrN = new Scanner(System.in);
                                char again = yOrN.next().charAt(0);

                                if (again == 'y' || again == 'Y') {
                                    board.clearBoard();
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
                        System.out.println("That position is already taken.  Choose a different one.");
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
                                    board.clearBoard();
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
                        System.out.println("That position is already taken.  Choose a different one.");
                        System.out.println(board.toString());
                    }
                }
            }
        }
    }
}