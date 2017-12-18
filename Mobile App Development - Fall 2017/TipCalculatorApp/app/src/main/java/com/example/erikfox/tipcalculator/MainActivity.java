/**
 * This program calculates the tip and total bill when the calculate
 * button is clicked.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #3
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 10/4/17
 */
package com.example.erikfox.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    /**
     * when the app gets opened
     * @param savedInstanceState the last saved instance of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * calculates the tip and bill when the button is clicked
     * @param view the current view
     */
    public void calculateClicked(View  view){
        // Create the calculator object
        TipCalculator calculator = new TipCalculator();

        // Create variables for accessing the final TextView fields
        TextView totalBillText = (TextView)
                findViewById(R.id.totalBillAmount);
        TextView tipText = (TextView)
                findViewById(R.id.tipAmount);

        // Create variables for accessing the EditText fields
        EditText billTextEdit = (EditText)
                findViewById(R.id.billEditText);
        EditText tipTextEdit = (EditText)
                findViewById(R.id.tipEditText);

        // This catches errors with calculating empty EditText Fields
        try {
            // Set the fields in calculator
            calculator.setTip(Float.parseFloat(tipTextEdit.getText().toString()));
            calculator.setBill(Float.parseFloat(billTextEdit.getText().toString()));

            // Calculate the totals and set the strings that display them
            tipText.setText(getString(R.string.tip_amount) +
                    getString(R.string.dollar_sign)+
                    String.format("%.02f", calculator.calculateTip()));
            totalBillText.setText(getString(R.string.bill_amount) +
                    getString(R.string.dollar_sign)+
                    String.format("%.02f", calculator.calculateBill()));

        } catch (NumberFormatException e){
            // Clears the editText and Gives the user a hint if nothing or a non-integer is entered
            if(!isNumber(tipTextEdit.getText().toString())){
                tipTextEdit.getText().clear();
                tipTextEdit.setHint(getString(R.string.hint));
            }
            if (!isNumber(billTextEdit.getText().toString())){
                billTextEdit.getText().clear();
                billTextEdit.setHint(getString(R.string.hint));
            }
        }

    }

    /**
     * returns true if a string represents a number greater than 0
     * @param string the string
     * @return weather it is a number grater than 0
     */
    public boolean isNumber(String string){
        try{
            return Float.parseFloat(string) > 0;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
