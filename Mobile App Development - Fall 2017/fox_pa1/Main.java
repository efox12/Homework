/**
 * This program computes simulates a game of tic-tac-toe in the command line.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #1
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 9/14/17
 */

package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean playAgain = true;
        int gamesPlayed = 0;
        int xWins = 0;
        int oWins = 0;
        int scratchGames = 0;
        char currentPlayer;

        //randomly chooses a player to go first
        Random random = new Random();
        if(random.nextInt(2) == 0){
            currentPlayer = 'X';
        }
        else{
            currentPlayer = 'O';
        }

        //executes each time the player chooses to play again
        while(playAgain){
            System.out.print("Welcome to Tic Tac Toe! There are two players, player 'X' and player 'O'.\n" +
                    "Please enter the dimension, N, of the NxN Tic Tac Toe board (an integer in [3, 9]): ");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            TicTacToeBoard board = new TicTacToeBoard(i);


            System.out.println("Player " + currentPlayer + " is going first.");
            System.out.println(board.toString());

            boolean gameOver = false;
            //Executes until a game ends
            while(gameOver == false) {
                System.out.print("Player " + currentPlayer + " , please enter the coordinates of your placement: ");
                int y = sc.nextInt();
                int x = sc.nextInt();
                Coordinates coordinates = new Coordinates(x,y);

                //Checks to see if coordinates are valid and them places the current players symbol there
                //otherwise skips back to the beginning of the loop to maintain the current players turn
                if (board.isValidMove(coordinates)) {
                    board.makeMove(coordinates,currentPlayer);
                } else {
                    System.out.println(coordinates.toString() + " is not a valid placement.");
                    System.out.println();
                    continue;
                }
                //Prints out the board and checks for a winner
                System.out.println(board.toString());
                if (board.isWinner(currentPlayer)) {
                    System.out.println(currentPlayer + " Won!");
                    gameOver = true;

                    gamesPlayed++;
                    if(currentPlayer == 'X'){
                        xWins++;
                    }
                    else{
                        oWins++;
                    }
                }
                //See's if the board is full for a scratch game
                else if(board.isFull()){
                    System.out.println("Scratch game, too bad.");
                    gameOver = true;
                    gamesPlayed++;
                    scratchGames++;
                }
                //switches to the next players turn
                if (currentPlayer == 'X') {
                    currentPlayer = 'O';
                } else {
                    currentPlayer = 'X';
                }
            }
            //Asks the player if they would like to play again
            System.out.print("Would you like to play again? Enter 'y' to play or 'q' to quit: ");
            String answer = sc.next();
            System.out.println();
            if(answer.charAt(0) == 'y'){
                playAgain = true;
            }
            else{
                playAgain = false;
            }

            //Randomly chooses a new player to start
            if(random.nextInt(2) == 0){
                currentPlayer = 'X';
            }
            else{
                currentPlayer = 'O';
            }
        }
        //outputs leaderboards table
        double xWinPercentage = xWins/gamesPlayed*100;
        double oWinPercentage = oWins/gamesPlayed*100;
        System.out.println(gamesPlayed);
        System.out.println("Player X game Stats\n--------------------");
        System.out.println("Win to loss ratio: " + xWins + ":" + oWins);
        System.out.printf("Win percentage: %.2f %% %n", + xWinPercentage);
        System.out.println("Number of scratch games: " + scratchGames);

        System.out.println("Player O game Stats\n--------------------");
        System.out.println("Win to loss ratio: " + oWins + ":" + xWins);
        System.out.printf("Win percentage: %.2f %% %n", + oWinPercentage);
        System.out.println("Number of scratch games: " + scratchGames);
    }
}
