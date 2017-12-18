/**
 * This class contains information and methods for the pizza activity.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #6
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 10/6/17
 */

package com.example.erikfox.pizzaapp;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class PizzaActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private Switch switchBar;
    private RadioGroup radioGroup;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private TextView price;
    private Pizza pizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        // initialize all the variables
        pizza =(Pizza)getIntent().getSerializableExtra("pizza");
        switchBar = (Switch) findViewById(R.id.switch1);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);

        // run this if viewing a placed order
        if(getIntent().getStringExtra("title") != null){
            // set the view to match the pizza object
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(getIntent().getStringExtra("title"));
            switchBar.setChecked(pizza.isGlutenFree());
            seekBar.setProgress(pizza.getCheese()*25-1);
            if(pizza.getSauce() == 0) {
                radioGroup.check(findViewById(R.id.radioButton).getId());
            }
            else if(pizza.getSauce() == 1) {
                radioGroup.check(findViewById(R.id.radioButton2).getId());
            }
            checkBox1.setChecked(pizza.isPepperoni());
            checkBox2.setChecked(pizza.isMushrooms());
            checkBox3.setChecked(pizza.isOlives());
            checkBox4.setChecked(pizza.isPinapple());

            // disable actions
            seekBar.setEnabled(false);
            switchBar.setEnabled(false);
            radioGroup.setEnabled(false);
            findViewById(R.id.radioButton).setEnabled(false);
            findViewById(R.id.radioButton2).setEnabled(false);
            checkBox1.setEnabled(false);
            checkBox2.setEnabled(false);
            checkBox3.setEnabled(false);
            checkBox4.setEnabled(false);
        }

        // set the pizza costs
        setUpActivity(pizza);

        // switch listener
        switchBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pizza.setGlutenFree(b);
                setUpActivity(pizza);
            }
        });

        // SeekBar listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i == 0)
                    pizza.setCheese(0);
                else if(i <25)
                    pizza.setCheese(1);
                else if(i < 50)
                    pizza.setCheese(2);
                else if(i < 75)
                    pizza.setCheese(3);
                else if(i <= 100)
                    pizza.setCheese(4);
                setUpActivity(pizza);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // RadioGroup listener
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton radioButton = (RadioButton) findViewById(R.id.radioButton);
                if(radioGroup.getCheckedRadioButtonId() == radioButton.getId())
                    pizza.setSauce(0);
                else
                    pizza.setSauce(1);
                setUpActivity(pizza);
            }
        });

        // CheckBox listeners
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pizza.setPepperoni(b);
                setUpActivity(pizza);
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pizza.setMushrooms(b);
                setUpActivity(pizza);
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pizza.setOlives(b);
                setUpActivity(pizza);
            }
        });


        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                pizza.setPinapple(b);
                setUpActivity(pizza);
            }
        });

    }

    /**
     * Sets up the pizza order based on the current pizza object
     * @param pizza the current pizza order
     */
    public void setUpActivity(Pizza pizza){
        TextView glutenFreeText =(TextView) findViewById(R.id.textView);
        TextView cheeseText =(TextView) findViewById(R.id.textView10);
        TextView sauceText =(TextView) findViewById(R.id.textView2);
        TextView pepperoniText =(TextView) findViewById(R.id.textView4);
        TextView mushroomText =(TextView) findViewById(R.id.textView5);
        TextView oliveText =(TextView) findViewById(R.id.textView6);
        TextView pinappleText =(TextView) findViewById(R.id.textView7);
        if(pizza.isGlutenFree()) {
            glutenFreeText.setText(getString(R.string.dollar)
                    + String.format("%.02f",pizza.glutenFreeCost));
        }
        else {
            glutenFreeText.setText(getString(R.string.dollar)
                    + String.format("%.02f",pizza.zeroCost));
        }

        if(pizza.getSauce() == 0) {
            sauceText.setText(getString(R.string.dollar)
                    + String.format("%.02f", pizza.zeroCost));
        }
        else {
            sauceText.setText(getString(R.string.dollar)
                    + String.format("%.02f", pizza.whiteSauceCost));
        }
        cheeseText.setText(getString(R.string.dollar)
                + String.format("%.02f", pizza.getCheese()*.5));

        if(pizza.isPepperoni())
            pepperoniText.setText(getString(R.string.dollar)
                    + String.format("%.02f", pizza.pepperoniCost));
        else
            pepperoniText.setText(getString(R.string.dollar)
                    + String.format("%.02f", pizza.zeroCost));
        if(pizza.isMushrooms())
            mushroomText.setText(getString(R.string.dollar)
                    + String.format("%.02f", pizza.mushroomCost));
        else
            mushroomText.setText(getString(R.string.dollar)
                    + String.format("%.02f", pizza.zeroCost));
        if(pizza.isOlives())
            oliveText.setText(getString(R.string.dollar)
                    + String.format("%.02f", pizza.olivesCost));
        else
            oliveText.setText(getString(R.string.dollar)
                    + String.format("%.02f", pizza.zeroCost));
        if(pizza.isPinapple())
            pinappleText.setText(getString(R.string.dollar)
                    + String.format("%.02f", pizza.pinappleCost));
        else
            pinappleText.setText(getString(R.string.dollar)
                    + String.format("%.02f", pizza.zeroCost));
        price = (TextView) findViewById(R.id.textView3);
        price.setText(getString(R.string.dollar)
                + String.format("%.02f", pizza.calculatePrice()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // inflate the options menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.select_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int menuId = item.getItemId();
        switch (menuId){
            case R.id.doneMenuItem:
                // finish the activity if done is clicked
                if(getIntent().getStringExtra("title") == null) {
                    Intent intent = getIntent();
                    intent.putExtra("pizza", pizza);
                    setResult(RESULT_OK, intent);
                    PizzaActivity.this.finish();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
