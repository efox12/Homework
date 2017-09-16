/**
 * Erik Fox
 * HomeWork 2
 * Last Modified:2/15/2017
 * Description: This class creates a hand and uses that hand to roll dice,
 * determine which dice to keep, and see what spots on the scorecard hand 
 * will fill out. 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Hand {
	Rules rules = new Rules();
	private char[] diceKept;
	private boolean allKept = false;
	private int sizeOfHand;
	private int numberOfSides;
	ArrayList<Die> currentHand = new ArrayList<Die>();
	
	/**
	 * constructor for the class hand
	 */
	public Hand(){
		sizeOfHand = rules.getDie();
		numberOfSides = rules.getSides();
		for(int i = 0; i<sizeOfHand; i++){
			Die newDie = new Die();
			currentHand.add(newDie);
		}
	}
	/**
	 * rolls all of the dice in the hand.
	 */
	public void rollHand(){
		for(int i=0; i<sizeOfHand; i++){
			currentHand.get(i).rollDie();
		}
		return;
	}
	/**
	 * gets the current hand of dice
	 * @return the the current Hand of dice objects
	 */ 
	ArrayList<Die> getHand(){
		return currentHand;
	}
	/**
	 * finds the values of the dice in hand
	 * @return the values of the dice in currentHand
	 */
	public ArrayList<Integer> displayHand(){
		ArrayList<Integer> display = new ArrayList<Integer>();
		for(int i=0; i<sizeOfHand; i++){
			 display.add(currentHand.get(i).getSideOnTop());
		}
		return display;
	}
	/**
	 * finds the total of all the dice
	 * @return the total of all the dice
	 */
	public int totalAllDice(){
		int total = 0;
		for(int i = 0; i<sizeOfHand; i++){
			total += currentHand.get(i).getSideOnTop();
		}
		return total;
	}
	/**
	 * uses bubble sort to sort the dice by their value
	 * @return the sorted current hand
	 */
	public ArrayList<Die> sortHand(){
		Die temp = new Die();
		for(int i=0; i<sizeOfHand; i++){
			for(int j=1; j<(sizeOfHand-i);j++){
				if(currentHand.get(j-1).getSideOnTop() > currentHand.get(j).getSideOnTop()){
					temp = currentHand.get(j-1);
					currentHand.set(j-1, currentHand.get(j));
					currentHand.set(j, temp);
				}
			}
		}
		return currentHand;
	}
	/**
	 * user picks dice to keep and the rest get re-rolled
	 */
	public void pickDice(){
		Scanner userInput = new Scanner(System.in);
		System.out.println(displayHand());
		System.out.print("Pick which dice you want to keep (y or n): ");
		char[] diceKept = userInput.next().toCharArray();
		allKept = true;
		for(int i=0; i<sizeOfHand; i++){
			if(diceKept[i] == 'n'){
				allKept = false;
				currentHand.get(i).rollDie();
			}
		}
		/*
		rollButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i = 0; i<sizeOfHand; i++){
					if(currentHand.get(i).getDieButton().isSelected() == true){
						currentHand.get(i).rollDie();
						currentHand.get(i).getDieButton().setSelected(false);
					}
				}
			}
		});*/
	}
	
	/**
	 * returns which dice are kept
	 * @return the array of which dice to keep
	 */
	char[] getDiceKept(){
		return diceKept;
	}
	/**
	 * checks to see if all dice are kept
	 * @return return true if all dice are kept
	 */
	boolean allDiceKept(){
		return allKept;
	}
	/**
	 * chacks for 3 or more of the same dice and returns that most dice of a kind
	 * @return the largest maxOfAKind
	 */
	public int maxOfAKindFound(){
		int maxCount = 0;
		int currentCount;
		for(int i=0; i<numberOfSides; i++){
			currentCount = 0;
			for(int j = 0; j<sizeOfHand; j++){
				if(currentHand.get(j).getSideOnTop() == i){
					currentCount++;
				}
			}
			if(currentCount>maxCount){
				maxCount = currentCount;
			}
		}
		return maxCount;
	}
	/**
	 * checks for a strait and returns the largest strait
	 * @return the largest strait found
	 */
	public int maxStraightFound(){
		int maxLength = 1;
		int currentLength = 1;
		for(int i = 0; i < 4; i++){
	        if(currentHand.get(i).getSideOnTop() + 1 == currentHand.get(i + 1).getSideOnTop())
	            currentLength++;
	        else if(currentHand.get(i).getSideOnTop() + 1 < currentHand.get(i + 1).getSideOnTop())
	            currentLength = 1;
	        if(currentLength > maxLength)
	            maxLength = currentLength;
	    }
	    return maxLength;
	}
	/**
	 * checks for 5 of a kind or 3 of a kind and 2 of a kind to make full house
	 * @return true if there is a full house, false if there isn't 
	 */
	public Boolean fullHouseFound(){
		Boolean foundFH = false;
		Boolean temp2K = false;
		Boolean temp3K = false;
		Boolean found3K = false;
		Boolean found2K = false;
		int currentCount ;
		for(int i=0; i<numberOfSides; i++){
			currentCount = 0;
		    for(int j = 0; j < sizeOfHand; j++){
		       if (currentHand.get(j).getSideOnTop() == i)
		        	currentCount++;
		       if(currentCount == 2)
		    	   temp2K = true;
		       if(currentCount >= 3)
		    	   temp3K = true;
		    }
		    if(temp2K && !temp3K)
		    	found2K = true;
		    if(temp3K)
		    	if(!found3K)
		    		found3K = true;
		    	if(found3K)
		    		found2K = true;
		    if(currentCount >= 5){
		    	foundFH = true;
		    	break;
		    }
		}
		if (found2K && found3K)
			foundFH = true;
		return foundFH;
	}
}
