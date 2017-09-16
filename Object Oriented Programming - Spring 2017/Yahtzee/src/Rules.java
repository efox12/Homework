/**
 * Erik Fox
 * Homework 2
 * Last Modified:2/15/2017
 * Description: This class creates rules that
 * can set the sides per die, amount of die, and 
 * amount of rolls allowed
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JComboBox;

import java.lang.*;

public class Rules {
	private int numberOfSides;
	private int sizeOfHand ;
	private int numberOfRolls;
	/**
	 * Constructor for Rules
	 */
	Rules(){
		numberOfSides = 6;
		sizeOfHand = 5;
		numberOfRolls = 3;
	}
	/**
	 * reads the text from the file and makes them use-able variables
	 * then prints the contents of the file
	 */
	public void readFile(){
        String fileName = "yahtzeeConfig.txt";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader readRules = new BufferedReader(fileReader);
            String line1 = readRules.readLine();
            numberOfSides = Integer.parseInt(line1);
            String line2 = readRules.readLine();
            sizeOfHand = Integer.parseInt(line2);
            String line3 = readRules.readLine();
            numberOfRolls = Integer.parseInt(line3);
            System.out.print("You are playing with " + sizeOfHand + " ");
            System.out.println(numberOfSides + " sided dice");
            System.out.println("You get " + numberOfRolls + " rolls per hand");
            readRules.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex){
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}
	/**
	 * reads the contents of a file and assigns variables
	 */
	public void getRules(){
        String fileName = "yahtzeeConfig.txt";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader readRules = new BufferedReader(fileReader);
            String line1 = readRules.readLine();
            numberOfSides = Integer.parseInt(line1);
            String line2 = readRules.readLine();
            sizeOfHand = Integer.parseInt(line2);
            String line3 = readRules.readLine();
            numberOfRolls = Integer.parseInt(line3);
            readRules.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex){
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}
	/**
	 * writes the current value of the variables to the file
	 */
	public void writeFile(){
        String fileName = "yahtzeeConfig.txt";
        Scanner userInput = new Scanner(System.in);
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter writeRules = new BufferedWriter(fileWriter);
            System.out.print("How many sides per die: ");
			numberOfSides = userInput.nextInt();
			System.out.print("How many dice per hand: ");
			sizeOfHand = userInput.nextInt();
			System.out.print("How many rolls per hand: ");
			numberOfRolls = userInput.nextInt();
			
            writeRules.write(Integer.toString(numberOfSides));
            writeRules.newLine();
            writeRules.write(Integer.toString(sizeOfHand));
            writeRules.newLine();
            writeRules.write(Integer.toString(numberOfRolls));
            writeRules.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
	}
	/**
	 * writes default variables into the file
	 */
	public void defaultHand(){
        String fileName = "yahtzeeConfig.txt";
        Scanner userInput = new Scanner(System.in);
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter writeRules = new BufferedWriter(fileWriter);
            writeRules.write('6');
            numberOfSides = 6;
            writeRules.newLine();
            writeRules.write('5');
            sizeOfHand = 5;
            writeRules.newLine();
            writeRules.write('3');
            numberOfRolls = 3;
            writeRules.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
	}
	
	public void changeRules(int sides, int dice){
        String fileName = "yahtzeeConfig.txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter writeRules = new BufferedWriter(fileWriter);
            writeRules.write(Integer.toString(sides));
            numberOfSides = sides;
            writeRules.newLine();
            writeRules.write(Integer.toString(dice));
            sizeOfHand = dice;
            writeRules.newLine();
            writeRules.write('3');
            numberOfRolls = 3;
            writeRules.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
	}
	/**
	 * gets the variable representing the number of sides per die
	 * @return the number of sides
	 */
	public int getSides(){
		getRules();
		return numberOfSides;
	}
	/**
	 * gets the number of dice per hand
	 * @return the number of dice
	 */
	public int getDie(){
		getRules();
		return sizeOfHand;
	}
	/**
	 * gest the number of rolls per hand
	 * @return the number of rolls
	 */
	public int getRolls(){
		getRules();
		return numberOfRolls;
	}
	/**
	 * Modifies the UI based on the current rules
	 * @param numDie The button for changing the amount of dice
	 * @param numSides The button for changing the amount of sides
	 */
	public void changeDie(JComboBox numDie, JComboBox numSides){
		numDie.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	            JComboBox numDie = (JComboBox) event.getSource();
	            int dieCount = Integer.valueOf((String)numDie.getSelectedItem());
	            //setDie(dieCount);
	        }
	    });
		
		numSides.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	            JComboBox numSides = (JComboBox) event.getSource();
	            int sideCount = Integer.valueOf((String)numDie.getSelectedItem());
	            //setSides(sideCount);
	        }
	    });
	}
}
