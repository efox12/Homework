/**
 * This is an abstact class for foodItems.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #6
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 10/6/17
 */

package com.example.erikfox.pizzaapp;

/**
 * Created by erikfox on 11/6/17.
 */

public abstract class FoodItem {
    private double price;
    private String type;

    /**
     * the default constructor
     */
    public FoodItem(){
        this.price = 0.00;
        this.type = null;
    }

    /**
     * the value constructor
     * @param price the price
     * @param type the type
     */
    public  FoodItem(int price, String type){
        this.price = price;
        this.type = type;
    }

    /**
     * returns the toString
     * @return the toString
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * sets the price
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * gets the price
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     *  sets the type
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * returns the type
     * @return the type
     */
    public String getType() {
        return type;
    }
}
