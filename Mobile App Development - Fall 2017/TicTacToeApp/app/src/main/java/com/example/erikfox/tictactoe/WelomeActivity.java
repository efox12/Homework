/**
 * This class contains houses the activity for the startup screen of Tic Tac Toe.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #4
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 10/18/17
 */

package com.example.erikfox.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WelomeActivity extends AppCompatActivity implements View.OnClickListener{
    final int LOGIN_REQUEST_CODE = 1;
    private EditText playerOne;
    private EditText playerTwo;

    /**
     * Creates the Activity
     * @param savedInstanceState the last saved instance of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welome);
        Button play = (Button) findViewById(R.id.playButton);
        play.setOnClickListener(this);

        GameStats gameStats = new GameStats();
        setStats(gameStats);
    }

    /**
     * Switches to the next activity when the play button is clicked
     * @param view the current view
     */
    @Override
    public void onClick(View view){
        playerOne = (EditText) findViewById(R.id.playerOneName);
        playerTwo = (EditText) findViewById(R.id.playerTwoName);
        if(playerOne.getText().length() != 0 && playerTwo.getText().length() != 0 &&
                playerOne.getText().toString().compareTo(playerTwo.getText().toString()) != 0) {
            Intent intent = new Intent(WelomeActivity.this, GameActivity.class);

            intent.putExtra("player1", playerOne.getText().toString());
            intent.putExtra("player2", playerTwo.getText().toString());
            startActivityForResult(intent, LOGIN_REQUEST_CODE);
        }
        else {
            Toast.makeText(this, getString(R.string.welcomeToast), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE &&
                resultCode == Activity.RESULT_OK) {
            int scratchGames = data.getIntExtra("scratchGames", 0);
            int playerOneWinCount = data.getIntExtra("playerOneWinCount", 0);
            int playerTwoWinCount = data.getIntExtra("playerTwoWinCount", 0);
            int gamesPlayed = data.getIntExtra("gamesPlayed", 0);
            String playerOne = data.getStringExtra("player1");
            String playerTwo = data.getStringExtra("player2");
            GameStats gameStats = new GameStats(playerOneWinCount, playerTwoWinCount, gamesPlayed,
                        scratchGames, playerOne, playerTwo);
            setStats(gameStats);
        }
    }

    /**
     * Sets the stats in the TextView
     * @param gameStats the stats
     */
    public void setStats(GameStats gameStats){
        gameStats.calculateWinPercentage();
        TextView statText = (TextView) findViewById(R.id.Stats);
        statText.setText(gameStats.getPlayer1()+ getString(R.string.stat1) + gameStats.getXWins() +
                getString(R.string.stats2) + gameStats.getOWins() + getString(R.string.stats3) +
                String.format("%.02f",gameStats.getXWinPercentage()) + getString(R.string.stats4) +
                gameStats.getScratchGames() + getString(R.string.stats5) +
                gameStats.getPlayer2()+ getString(R.string.stat1) + gameStats.getOWins() +
                getString(R.string.stats2) + gameStats.getXWins() + getString(R.string.stats3) +
                String.format("%.02f",gameStats.getOWinPercentage()) + getString(R.string.stats4) +
                gameStats.getScratchGames());

    }
}
