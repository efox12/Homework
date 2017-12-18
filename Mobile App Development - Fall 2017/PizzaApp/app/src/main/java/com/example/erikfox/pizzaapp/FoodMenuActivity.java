/**
 * This class contains information and methods for the food menu activity.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #6
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 10/6/17
 */

package com.example.erikfox.pizzaapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class FoodMenuActivity extends AppCompatActivity implements View.OnClickListener {
    final int RETURN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        // set the images
        ImageView pizzaImage = (ImageView) findViewById(R.id.PizzaImage);
        pizzaImage.setOnClickListener(this);
        pizzaImage.setImageResource(R.drawable.pizza);
        ImageView burgerImage = (ImageView) findViewById(R.id.BurgerImage);
        burgerImage.setOnClickListener(this);
        burgerImage.setImageResource(R.drawable.burger);
        ImageView saladImage = (ImageView) findViewById(R.id.SaladImage);
        saladImage.setOnClickListener(this);
        saladImage.setImageResource(R.drawable.salad);
        ImageView coffeeImage = (ImageView) findViewById(R.id.CoffeeImage);
        coffeeImage.setOnClickListener(this);
        coffeeImage.setImageResource(R.drawable.coffee);
    }

    @Override
    public void onClick(View view){
        Intent intent;
        // switch to next intent if pizza image is selected
        if(view.getId() == R.id.PizzaImage){
            intent = new Intent(this, PizzaActivity.class);
            Pizza pizza = new Pizza();
            intent.putExtra("pizza", pizza);
            startActivityForResult(intent, RETURN);
        }
        else{
            Toast.makeText(this, getString(R.string.toast), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        // finish the activity and pass the pizza object to the main activity
        if (requestCode == RETURN && resultCode == Activity.RESULT_OK) {
            Intent intent = getIntent();
            getIntent().putExtra("pizza", data.getSerializableExtra("pizza"));
            setResult(RESULT_OK, intent);
            finish();
        }
    }


}
