/**
 * This class houses the activity for the game section of Tic Tac Toe.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #4
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 10/18/17
 */

package com.example.erikfox.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private int playerOneWinCount;
    private int playerTwoWinCount;
    private int gamesPlayed;
    private int scratchGames;
    private char currentPlayer;

    private TicTacToeBoard board;
    private ImageView currentPlayerIcon;
    private TextView playerPrompt;

    /**
     *  Creates the activity from the last saved instance
     * @param savedInstanceState that saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerOneWinCount = 0;
        playerTwoWinCount = 0;
        gamesPlayed = 0;
        scratchGames = 0;

        ImageView one = (ImageView) findViewById(R.id.button1);
        one.setOnClickListener(this);

        ImageView two = (ImageView) findViewById(R.id.button2);
        two.setOnClickListener(this);

        ImageView three = (ImageView) findViewById(R.id.button3);
        three.setOnClickListener(this);

        ImageView four = (ImageView) findViewById(R.id.button4);
        four.setOnClickListener(this);

        ImageView five = (ImageView) findViewById(R.id.button5);
        five.setOnClickListener(this);

        ImageView six = (ImageView) findViewById(R.id.button6);
        six.setOnClickListener(this);

        ImageView seven = (ImageView) findViewById(R.id.button7);
        seven.setOnClickListener(this);

        ImageView eight = (ImageView) findViewById(R.id.button8);
        eight.setOnClickListener(this);

        ImageView nine = (ImageView) findViewById(R.id.button9);
        nine.setOnClickListener(this);

        Button ten = (Button) findViewById(R.id.playAgainButton);
        ten.setOnClickListener(this);

        Button eleven = (Button) findViewById(R.id.quitButton);
        eleven.setOnClickListener(this);

        setupGame();

    }

    /**
     * Triggers each time a button or ImageView is clicked
     * @param view the view that was clicked
     */
    @Override
    public void onClick(View view){
        if(view.getId() == R.id.playAgainButton){
            clearBoard();
            setupGame();
        }
        else if(view.getId() == R.id.quitButton){
            setResult(RESULT_OK, getIntentWithExtras());
            GameActivity.this.finish();
        }
        else if(!board.isWinner(currentPlayer) && !board.isFull()) {
            if (board.isValidMove(getCoordinates(view))) {

                // Make a move on the board and display the image
                board.makeMove(getCoordinates(view), currentPlayer);
                ImageView image = (ImageView) view;
                setImageView(image);
                playerPrompt = (TextView) findViewById(R.id.playerPrompt);

                // Check to see if the game is complete
                if (board.isWinner(currentPlayer)) {
                    playerPrompt.setText(getCurrentPlayerName() + getString(R.string.winner));
                    if (currentPlayer == 'X') {
                        playerOneWinCount++;
                    }
                    else {
                        playerTwoWinCount++;
                    }
                }
                else if (board.isFull()) {
                    playerPrompt.setText(getString(R.string.sctatchGame));
                    scratchGames++;
                }
                else {
                    switchPlayer();
                    playerPrompt = (TextView) findViewById(R.id.playerPrompt);
                    playerPrompt.setText(getCurrentPlayerName() + getString(R.string.turn));
                }

                // Display the play again button if the game is complete
                if(board.isWinner(currentPlayer) || board.isFull()){
                    Button button = (Button) findViewById(R.id.playAgainButton);
                    button.setVisibility(View.VISIBLE);
                    button.setActivated(true);
                    gamesPlayed++;
                }
            }
            else {
                Toast.makeText(this, getString(R.string.invalidMove), Toast.LENGTH_SHORT).show();
            }
        }


    }

    /**
     * Loads all necessary the into an intent and returns it
     * @return the intent
     */
    public Intent getIntentWithExtras(){
        Intent intent = getIntent();
        intent.putExtra("gamesPlayed", gamesPlayed);
        intent.putExtra("playerOneWinCount", playerOneWinCount);
        intent.putExtra("playerTwoWinCount", playerTwoWinCount);
        intent.putExtra("scratchGames", scratchGames);
        intent.putExtra("player1", getIntent().getStringExtra("player1"));
        intent.putExtra("player2", getIntent().getStringExtra("player2"));
        return intent;
    }

    /**
     * Switches to the next players turn
     */
    public void switchPlayer(){
        // Switches to the next players turn
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
        currentPlayerIcon = (ImageView) findViewById(R.id.currentPlayer);
        setImageView(currentPlayerIcon);
    }

    /**
     * Returns the coordinates of the clicked button
     * @param view the current view
     * @return The Coordinate
     */
    public Coordinates getCoordinates(View view){
        Coordinates coordinates;
        if (view.getId() == R.id.button1){
            coordinates = new Coordinates(0, 0);
        }
        else if (view.getId() == R.id.button2){
            coordinates = new Coordinates(1, 0);
        }
        else if (view.getId() == R.id.button3){
            coordinates = new Coordinates(2, 0);
        }
        else if (view.getId() == R.id.button4){
            coordinates = new Coordinates(0, 1);
        }
        else if (view.getId() == R.id.button5){
            coordinates = new Coordinates(1, 1);
        }
        else if (view.getId() == R.id.button6){
            coordinates = new Coordinates(2, 1);
        }
        else if (view.getId() == R.id.button7){
            coordinates = new Coordinates(0, 2);
        }
        else if(view.getId() == R.id.button8){
            coordinates = new Coordinates(1, 2);
        }
        else{
            coordinates = new Coordinates(2, 2);
        }
        return coordinates;
    }

    /**
     * Returns the name of the current player
     * @return the name
     */
    public String getCurrentPlayerName(){
        if(currentPlayer == 'X'){
            return getIntent().getStringExtra("player1");
        }
            return getIntent().getStringExtra("player2");
    }

    /**
     * Sets up the board for a new game;
     */
    public void setupGame(){
        board = new TicTacToeBoard(3);
        // Hides play again button
        Button button = (Button) findViewById(R.id.playAgainButton);
        button.setVisibility(View.INVISIBLE);

        // Randomly chooses a player to go first
        Random random = new Random();
        if(random.nextInt(2) == 0){
            currentPlayer = 'X';
        }
        else{
            currentPlayer = 'O';
        }

        // Sets the prompts
        playerPrompt = (TextView) findViewById(R.id.playerPrompt);
        playerPrompt.setText(getCurrentPlayerName() + getString(R.string.firstPrompt));
        currentPlayerIcon = (ImageView) findViewById(R.id.currentPlayer);
        setImageView(currentPlayerIcon);
    }

    /**
     * Sets the current players image in the current view
     * @param imageView the current view
     */
    public void setImageView(ImageView imageView){
        if (currentPlayer == 'X') {
            imageView.setImageResource(R.drawable.fox);
        } else {
            imageView.setImageResource(R.drawable.hedgehog);
        }
    }

    /**
     * Clears the ImageViews on the game board
     */
    public void clearBoard(){
        ImageView imageView;
        imageView = (ImageView) findViewById(R.id.button1);
        imageView.setImageResource(0);
        imageView = (ImageView) findViewById(R.id.button2);
        imageView.setImageResource(0);
        imageView = (ImageView) findViewById(R.id.button3);
        imageView.setImageResource(0);
        imageView = (ImageView) findViewById(R.id.button4);
        imageView.setImageResource(0);
        imageView = (ImageView) findViewById(R.id.button5);
        imageView.setImageResource(0);
        imageView = (ImageView) findViewById(R.id.button6);
        imageView.setImageResource(0);
        imageView = (ImageView) findViewById(R.id.button7);
        imageView.setImageResource(0);
        imageView = (ImageView) findViewById(R.id.button8);
        imageView.setImageResource(0);
        imageView = (ImageView) findViewById(R.id.button9);
        imageView.setImageResource(0);
    }
}
