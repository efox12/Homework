/**
 * This class contains information and methods for the main activity.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #6
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 10/6/17
 */

package com.example.erikfox.pizzaapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int RETURN = 1;
    private ArrayList<FoodItem> orderList;
    private ListView listView;
    private ArrayList<String> strings;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create the lists for the listview
        strings = new ArrayList<>();
        orderList = new ArrayList<>();

        // set the lists if the orientation is changed
        if(savedInstanceState != null){
            orderList = (ArrayList<FoodItem>)savedInstanceState.getSerializable("orderList");
            strings = (ArrayList<String>)savedInstanceState.getSerializable("stringList");
        }

        // set up and display the ListView
        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(arrayAdapter);

        // view clicked orders
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewOrderActivity(orderList.get(i));
            }
        });

        // offer to delete long clicked order
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick (final AdapterView<?> adapterView, final View view, final int i, long l){
                // set up alert dialog
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle(getString(R.string.alertTitle))
                        .setMessage(getString(R.string.alertDiscription1)
                                + adapterView.getItemAtPosition(i).toString()
                                + getString(R.string.questionMark))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener(){
                            // delete the note if clicked
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                strings.remove(i);
                                orderList.remove(i);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {

                            }
                        });
                alertDialog.show();
                return true;
            }
        });
    }

    /**
     * goes to selected order to view it
     * @param foodItem the selected order
     */
    private void viewOrderActivity(FoodItem foodItem) {
        if(foodItem.getType().toString().compareTo("Pizza") == 0){
            Intent intent = new Intent(this, PizzaActivity.class);
            intent.putExtra("title", foodItem.getType()
                    + getString(R.string.stringone)
                    + (orderList.indexOf(foodItem) + 1));
            intent.putExtra("pizza", (Pizza)foodItem);
            startActivityForResult(intent, RETURN);
        }
    }

    /**
     * creates an activity for a food order
     */
    private void startOrderActivity() {
        Intent intent = new Intent(this,
                FoodMenuActivity.class);
        startActivityForResult(intent, RETURN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // inflate the options menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int menuId = item.getItemId();
        switch (menuId){
            case R.id.addMenuItem:
                // add item to the menu
                startOrderActivity();
                return true;
            case R.id.deleteMenueItem:
                // delete all items in the list
                // set up alert dialog
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle(getString(R.string.alertTitle2))
                        .setMessage(getString(R.string.alertDiscription2))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener(){
                            // delete the note if clicked
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                orderList.clear();
                                strings.clear();
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(getString(R.string.no),
                                new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {

                            }
                        });

                alertDialog.show();
                return true;
            case R.id.helpMenuItem:
                // set up alert dialog for help
                AlertDialog.Builder alertDialogHelp = new AlertDialog.Builder(MainActivity.this);
                alertDialogHelp.setTitle(getString(R.string.alertTitle3))
                        .setMessage(getString(R.string.alertDiscription3))
                        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener(){
                            // delete the note if clicked
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {

                            }
                        });
                alertDialogHelp.show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        // add the result to the list
        if (requestCode == RETURN && resultCode == Activity.RESULT_OK) {
            FoodItem foodItem = (FoodItem) data.getSerializableExtra("pizza");
            orderList.add(foodItem);
            strings.add(foodItem.getType()
                    + getString(R.string.stringone)
                    + (orderList.indexOf(foodItem)+1)
                    + getString(R.string.stringtwo)
                    + getString(R.string.dollar)
                    + String.format("%.02f", foodItem.getPrice())
                    + getString(R.string.stringthree));
            arrayAdapter.notifyDataSetChanged();
        }
    }

    // save the instance of the activity
    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putSerializable("orderList", orderList);
        outState.putSerializable("stringList", strings);
        super.onSaveInstanceState(outState);
    }
}
