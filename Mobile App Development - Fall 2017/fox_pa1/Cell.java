/**
 * This class contains information and methods for cells of the game board.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #1
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 9/14/17
 */
package TicTacToe;

public class Cell {
    protected char symbol;
    private boolean cellTaken;
    Coordinates coordinates;
    /**
     * Constructor that creates a cell object
     * @param row the row of the cell
     * @param column the column of the cell
     */
    Cell(int row, int column){
        symbol = '-';
        cellTaken = false;
        coordinates = new Coordinates(row, column);
    }

    /**
     * Changes the value of the cell to the current players symbol
     * @param value the current players symbol
     */
    public void ChangeValue(char value){
        symbol = value;
        cellTaken = true;
    }

    /**
     * Checks to see if the cell has already been assigned a value
     * @return true if the cell is taken, false if it isn't
     */
    public boolean CellIsTaken(){
        return cellTaken;
    }

    /**
     * Gets the current value of the cell
     * @return the current value of the cell
     */
    public String toString(){
        return String.valueOf(symbol);
    }
}
