/**
 * This class contains information and methods for the coordinates of each cell.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #4
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 9/14/17
 */

package com.example.erikfox.tictactoe;

public class Coordinates {
    private int row;
    private int column;

    /**
     * default value constructor
     */
    public Coordinates(){
        this.row = 0;
        this.column = 0;
    }
    /**
     * a constructor that sets the row and column of this object
     * @param row the row
     * @param column the column
     */
    public Coordinates(int column, int row){
        this.row = row;
        this.column = column;
    }

    /**
     * Returns a string with the coordinates of a string
     * @return the string stating the coordinates
     */
    public String toString() {
        String coordinates = "";
        coordinates += "(" + this.row + ", " + this.column + ")";
        return coordinates;
    }

    /**
     * Gets the row
     * @return the row
     */
    public  int getRow(){
        return  row;
    }

    /**
     * Gets the column
     * @return the column
     */
    public int getColumn() {
        return column;
    }
}
