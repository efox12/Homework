/**
 * This program calculates the tip and total bill.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #3
 * No sources to cite.
 *
 * @author Erik Fox
 * @version v1.0 10/4/17
 */

package com.example.erikfox.tipcalculator;

public class TipCalculator {
    float bill;
    float tip;

    /**
     * Default Constructor
     */
    public TipCalculator(){
        bill = 0;
        tip = 0;
    }

    /**
     * Value Constructor
     * @param bill the amount of the bill
     * @param tip the tip percentage
     */
    public TipCalculator(float bill, float tip){
        this.tip = tip;
        this.bill = bill;
    }
    /**
     * calculates the tip
     * @return the tip
     */
    public double calculateTip(){
        return bill * tip * .01;
    }

    /**
     * calculates the total bill
     * @return the total bill
     */
    public double calculateBill(){
        return calculateTip() + bill;
    }

    /**
     * sets the bill amount
     * @param bill the bill amount
     */
    public void setBill(float bill) {
        this.bill = bill;
    }

    /**
     * gets the bill amount
     * @return the bill amount
     */
    public float getBill() {
        return bill;
    }

    /**
     * gets the tip percent
     * @return the tip percent
     */
    public float getTip() {
        return tip;
    }

    /**
     *  Sets the tip to the tip percent
     * @param tip the tip percent
     */
    public void setTip(float tip) {
        this.tip = tip;
    }
}
