/**
 * This class contains information and methods for the game board.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #1
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 9/14/17
 */


package TicTacToe;
public class TicTacToeBoard {
    private int N;
    private Cell board[][];

    /**
     * Contructor that creates the game board object
     * @param n the desired size of the board
     */
    public TicTacToeBoard(int n){
        N = n;
        board = new Cell[n][n];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                board[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Checks to see if the coordinates the user picked are already taken
     * @param coordinates the coordinates of the cell
     * @return true if the move is valid, false otherwise
     */
    public boolean isValidMove(Coordinates coordinates){
        if(board[coordinates.getRow()][coordinates.getColumn()].CellIsTaken() == false) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Puts the players symbol in the spot that they choose
     * @param coordinates the coordinates the player chooses
     * @param playerSymbol the player symbol "X' or 'O'
     */
    public void makeMove(Coordinates coordinates, char playerSymbol){
            board[coordinates.getRow()][coordinates.getColumn()].ChangeValue(playerSymbol);
    }

    /**
     * Creates the game board by building a string
     * @return a string output of the game board
     */
    public String toString(){
        String currentBoard = " ";
        for(int i = 0; i<N; i++) {
            currentBoard += " " + i;
        }
        currentBoard += "\n";
        for(int i = 0; i<N; i++) {
            currentBoard += String.valueOf(i);
            for (int j = 0; j < N; j++) {
                currentBoard += " " + board[i][j].toString();
            }
            currentBoard += "\n";
        }
        return currentBoard;
    }

    /**
     * Check to see if a player won the game
     * @param playerSymbol the current player 'X' or 'O'
     * @return true if the current player won, false if they didn't
     */
    public boolean isWinner(char playerSymbol){
        int rowCount = 0;
        int colCount = 0;
        int diagCountOne = 0;
        int diagCountTwo = 0;
        for(int i = 0; i < N; i++){
            rowCount = 0;
            colCount = 0;
            for(int j = 0; j<N; j++){
                if(board[i][j].symbol == playerSymbol){
                    rowCount++;
                }
                if(board[j][i].symbol == playerSymbol){
                    colCount++;
                }
                if(rowCount == N || colCount == N ){
                    return true;
                }
            }
            if(board[i][i].symbol == playerSymbol){
                diagCountOne++;
            }
            if(board[i][N-1-i].symbol == playerSymbol){
                diagCountTwo++;
            }
        }
        if(diagCountOne == N || diagCountTwo == N){
            return true;
        }
        return false;
    }

    /**
     * Checks to see if all the spaces on the board are full
     * @return true if they are all taken, false if they aren't
     */
    public boolean isFull(){
        int count = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                Coordinates coordinates = new Coordinates(i,j);
                if(!(isValidMove(coordinates))){
                    count++;
                }
            }
        }
        if (count == N*N){
            return true;
        }
        else{
            return false;
        }
    }
}
