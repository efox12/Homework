/**
 * This class contains information and methods for a pizza object.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #6
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 10/6/17
 */

package com.example.erikfox.pizzaapp;

import java.io.Serializable;

/**
 * Created by erikfox on 11/6/17.
 */

public class Pizza extends FoodItem implements Serializable {
    final double glutenFreeCost = 2.00;
    final double whiteSauceCost = 2.00;
    final double pepperoniCost = 1.00;
    final double mushroomCost = 1.00;
    final double olivesCost = 1.00;
    final double pinappleCost = 1.00;
    final double zeroCost = 0.00;
    private boolean glutenFree;
    private int sauce;
    private int cheese;
    private boolean pepperoni;
    private boolean mushrooms;
    private boolean olives;
    private boolean pinapple;
    private double price;
    private String type;

    /**
     * The default constructor
     */
    public Pizza(){
        type = "Pizza";
        price = 9.99;
        glutenFree = false;
        sauce = 0;
        cheese = 0;
        pepperoni = false;
        mushrooms = false;
        olives = false;
        pinapple = false;
    }

    /**
     * gets the type
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * gets the amount of cheese
     * @return the amount of cheese
     */
    public int getCheese() {
        return cheese;
    }

    /**
     * gets the price
     * @return the price
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * gets the type of sauce
     * @return the type of sauce
     */
    public int getSauce() {
        return sauce;
    }

    /**
     * sets the amount of cheese
     * @param cheese the amount of cheese
     */
    public void setCheese(int cheese) {
        this.cheese = cheese;
    }

    /**
     * sets the type
     * @param type the type
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * sets whether it's gluten free
     * @param glutenFree whether it's gluten free
     */
    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    /**
     * sets if there are mushrooms
     * @param mushrooms
     */
    public void setMushrooms(boolean mushrooms) {
        this.mushrooms = mushrooms;
    }

    /**
     * sets if there are olives
     * @param olives
     */
    public void setOlives(boolean olives) {
        this.olives = olives;
    }

    /**
     * sets if there is pepperoni
     * @param pepperoni
     */
    public void setPepperoni(boolean pepperoni) {
        this.pepperoni = pepperoni;
    }

    /**
     * sets if there is pineapple
     * @param pinapple if there is pinapple
     */
    public void setPinapple(boolean pinapple) {
        this.pinapple = pinapple;
    }

    /**
     * sets the price
     * @param price the price
     */
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * sets the type of sauce
     * @param sauce the type of sauce
     */
    public void setSauce(int sauce) {
        this.sauce = sauce;
    }

    /**
     * returns true if gluten free
     * @return true if gluten free
     */
    public boolean isGlutenFree() {
        return glutenFree;
    }

    /**
     * returns true if mushrooms
     * @return true if mushrooms
     */
    public boolean isMushrooms() {
        return mushrooms;
    }

    /**
     * returns true if olives
     * @return true if olives
     */
    public boolean isOlives() {
        return olives;
    }

    /**
     * returns true if pepperoni
     * @return true if pepperoni
     */
    public boolean isPepperoni() {
        return pepperoni;
    }

    /**
     * returns true if pineapple
     * @return true if pineapple
     */
    public boolean isPinapple() {
        return pinapple;
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
     * returns the calculated price
     * @return the calculated price
     */
    public double calculatePrice(){
        price = 9.99;
        if(glutenFree)
            price += 2;
        if(sauce == 1)
            price +=2;
        price += cheese*0.5;
        if(pepperoni)
            price += 1;
        if(mushrooms)
            price += 1;
        if(olives)
            price += 1;
        if(pinapple)
            price += 1;
        return price;
    }
}
