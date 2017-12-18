/**
 * This class contains information and methods for the coordinates of each cell.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #4
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.1 10/18/17
 */

package com.example.erikfox.tictactoe;

/**
 * Default value constructor
 */
public class GameStats {
    private int xWins;
    private int oWins;
    private int gamesPlayed;
    private int scratchGames;
    private String player1;
    private String player2;
    private double xWinPercentage;
    private double oWinPercentage;

    /**
     * The default value constructor
     */
    public GameStats(){
        this.scratchGames = 0;
        this.gamesPlayed = 0;
        this.xWins = 0;
        this.oWins = 0;
        this.player1 = "Player 1";
        this.player2 = "Player 2";
        this.oWinPercentage = 0;
        this.xWinPercentage = 0;
    }

    /**
     * The value constructor
     * @param xWins the number of x wins
     * @param oWins the number of o wins
     * @param gamesPlayed the number of games played
     * @param scratchGames the number of scratch games
     * @param player1 the name of player 1
     * @param player2 the name of player 2
     */
    public GameStats(int xWins, int oWins, int gamesPlayed, int scratchGames,
                     String player1, String player2){
        this.scratchGames = scratchGames;
        this.gamesPlayed = gamesPlayed;
        this.xWins = xWins;
        this.oWins = oWins;
        this.player1 = player1;
        this.player2 = player2;
        this.oWinPercentage = 0;
        this.xWinPercentage = 0;
    }

    /**
     * Sets player 1's name
     * @param player1 the name of player 1
     */
    public void setPlayer1(String player1) { this.player1 = player1; }

    /**
     * Gets player 1's name
     * @return the name of player 1
     */
    public String getPlayer1() { return player1; }

    /**
     * Sets player 2's name
     * @param player2 the name of player 2
     */
    public void setPlayer2(String player2) { this.player2 = player2; }

    /**
     * Gets player 2's name
     * @return the name of player 2
     */
    public String getPlayer2() { return player2; }

    /**
     * Sets the number of games played
     * @param gamesPlayed the number of games played
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * Gets the number of games played
     * @return the number of games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Sets the number of o wins
     * @param oWins the number of o wins
     */
    public void setOWins(int oWins) {
        this.oWins = oWins;
    }

    /**
     * Gets the number of o wins
     * @return the number of o wins
     */
    public int getOWins() {
        return oWins;
    }

    /**
     * Sets the number of x wins
     * @param xWins the number of x wins
     */
    public void setXWins(int xWins) {
        this.xWins = xWins;
    }

    /**
     * Gets the number of x wins
     * @return the number of x wins
     */
    public int getXWins() {
        return xWins;
    }

    /**
     * Sets the number of Scratch Games
     * @param scratchGames the number of scratch games
     */
    public void setScratchGames(int scratchGames) {
        this.scratchGames = scratchGames;
    }

    /**
     * Gets the number of scratch games
     * @return the number of scratch games
     */
    public int getScratchGames() {
        return scratchGames;
    }

    /**
     * Sets the o win percentage
     * @param oWinPercentage o's win percentage
     */
    public void setOWinPercentage(double oWinPercentage) {
            this.oWinPercentage = oWinPercentage;
    }

    /**
     * Gets the o win percentage
     * @return o's win percentage
     */
    public double getOWinPercentage() {
        return oWinPercentage;
    }

    /**
     * Sets the x win percentage
     * @param xWinPercentage x's win percentage
     */
    public void setXWinPercentage(double xWinPercentage) {
            this.xWinPercentage = xWinPercentage;
    }

    /**
     * Gets the win x win percentage
     * @return x's win percentage
     */
    public double getXWinPercentage() {
        return xWinPercentage;
    }

    /**
     * Resturns a string with all field values
     * @return the string
     */
    @Override
    public String toString() {
        return super.toString() + " " + xWins + " " + xWinPercentage + " " + oWins + " " +
               oWinPercentage + " " + gamesPlayed + " " + scratchGames;
    }

    /**
     * Calculates the win percentage
     */
    public void calculateWinPercentage(){
        if(gamesPlayed > 0) {
            xWinPercentage = Double.valueOf(xWins) / Double.valueOf(gamesPlayed) * 100;
            oWinPercentage = Double.valueOf(oWins) / Double.valueOf(gamesPlayed) * 100;
        }
        else{
            xWinPercentage = 0;
            oWinPercentage = 0;
        }
    }
}
