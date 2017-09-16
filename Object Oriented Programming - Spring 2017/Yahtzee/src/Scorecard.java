import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Erik Fox
 * Homework 3
 * Last Modified:2/24/2017
 * Description: This class creates a 
 * scorecard out of scorecard card.get()s
 * and outputs it in the console
 */
public class Scorecard {
	Rules rules = new Rules();
	Dictionary<Integer, ScorecardLine> card;
	Scanner userInput = new Scanner(System.in);
	private int sizeOfHand = rules.getDie();
	private int numberOfSides = rules.getSides();
	JLabel totalScoreLabel = new JLabel();
	JLabel bonusScoreLabel = new JLabel();
	
	/**
	 * constructor
	 */
	public Scorecard() {
		totalScoreLabel.setFont(totalScoreLabel.getFont().deriveFont(20.0f));
		bonusScoreLabel.setFont(bonusScoreLabel.getFont().deriveFont(20.0f));
		sizeOfHand = rules.getDie();
		numberOfSides = rules.getSides();
		card = new Hashtable<Integer, ScorecardLine>();
	}
	
	/**
	 * creates a new blank scorecard
	 */
	public void createScorecard(JButton rollButton, Hand hand, Scorecard scorecard, YahtzeeUX frame){
		for(int i=1; i<=numberOfSides; i++){
			ScorecardLine newLine = new ScorecardLine(rollButton, hand, scorecard, frame);
			newLine.lineButton.setText(Integer.toString(i)+" Line");
			card.put(i, newLine);
		}
		for(int i=20; i<=27; i++){
			ScorecardLine newLine = new ScorecardLine(rollButton, hand, scorecard, frame);
			card.put(i, newLine);
		}
		card.get(20).lineButton.setText("3 of a Kind");
		card.get(21).lineButton.setText("4 of a Kind");
		card.get(22).lineButton.setText("Full House");
		card.get(23).lineButton.setText("Small Strait");
		card.get(24).lineButton.setText("Large Strait");
		card.get(25).lineButton.setText("Yahtzee");
		card.get(26).lineButton.setText("Chance");
	}
	
	/**
	 *  displays the scorecard between hands and saves the score of each card.get() to a respective
	 *  potential score in the ScoreCard class
	 * @param hand the current hand being scored
	 */
	public void displayScorecard(Hand hand){
		for(int i=1; i<=numberOfSides; i++){
			int currentCount = 0;
			for(int j=0; j<sizeOfHand; j++){
				if(hand.currentHand.get(j).getSideOnTop() == i){
					currentCount++;
				}
			}
			if(card.get(i).getLineIsScored() == 0){
				card.get(i).setPotential(i*currentCount);
				card.get(i).scoreLabel.setText(Integer.toString(i*currentCount));
			}
		}
		if(card.get(20).getLineIsScored() == 0){
			if(hand.maxOfAKindFound() >= 3){
				card.get(20).setPotential(hand.totalAllDice());
				card.get(20).scoreLabel.setText(Integer.toString(hand.totalAllDice()));
			}
			else{
				card.get(20).setPotential(0);
			}
		}
		if(card.get(21).getLineIsScored() == 0){
			if(hand.maxOfAKindFound() >= 4){
				card.get(21).setPotential(hand.totalAllDice());
				card.get(21).scoreLabel.setText(Integer.toString(hand.totalAllDice()));
			}
			else{
				card.get(21).setPotential(0);
			}
		}
		if(card.get(22).getLineIsScored() == 0){
			if(hand.fullHouseFound()){
				card.get(22).setPotential(25);
				card.get(22).scoreLabel.setText("25");
			}
			else{
				card.get(22).setPotential(0);
			}
		}
		if(card.get(23).getLineIsScored() == 0){
			if(hand.maxStraightFound() >= 4){
				card.get(23).setPotential(30);
			}
			else{
				card.get(23).setPotential(0);
			}
		}
		if(card.get(24).getLineIsScored() == 0){
			if(hand.maxStraightFound() >= 5){
				card.get(24).setPotential(40);
			}
			else{
				card.get(24).setPotential(0);
			}
		}
		if(card.get(25).getLineIsScored() == 0){
			if(hand.maxOfAKindFound() >= 5){
				card.get(25).setPotential(50);
				card.get(25).scoreLabel.setText("50");
			}
			else{
			}
		}
		if(card.get(26).getLineIsScored() == 0){
			card.get(26).setPotential(hand.totalAllDice());
		}
	}

	/**
	 * displays the final scorecard with all of the values
	 */	
	public void displayFinalScorecard(){
		for(int i=1; i<=numberOfSides; i++){
			System.out.println("Score " + card.get(i).getScore() + " on "+ i);
		}
		System.out.println("Score " + card.get(20).getScore() + " on  3K");
		System.out.println("Score " + card.get(21).getScore() + " on  4K");
		System.out.println("Score " + card.get(22).getScore() + " on  FH");
		System.out.println("Score " + card.get(23).getScore() + " on  SS");
		System.out.println("Score " + card.get(24).getScore() + " on  LS");
		System.out.println("Score " + card.get(25).getScore() + " on  Y");
		System.out.println("Score " + card.get(26).getScore() + " on  C");
	}
	
	/**
	 * send the score being chosen to the respective ScorecardLine
	 */
	public void scoreValue(){
		String lineChoice = userInput.next();
		//card.get(Integer.valueOf(lineChoice)).setLine();
	}
	/**
	 * calculates the total of the upper scorecard
	 * @return the total score of the upper scorecard
	 */
	public int upperScore(){
		int total = 0;
		for(int i = 1; i<=numberOfSides; i++){
			total += card.get(i).getScore();
		}
		return total;
	}
	/**
	 * sees if the bonus is reached
	 * @return the total of the bonus score
	 */
	public int bonusScore(){
		int total = 0;
		int count = 0;
		for(int i = 1; i<=numberOfSides; i++){
			count += 3*i;
		}
		if(upperScore() >= count)
			total = 35;
		return total;	
	}
	/**
	 * calculates the total of the lower scorecard
	 * @return the total of the lower scorecard
	 */
	public int lowerScore(){
		int total = 0;
		total += card.get(20).getScore();
		total += card.get(21).getScore();
		total += card.get(22).getScore();
		total += card.get(23).getScore();
		total += card.get(24).getScore();
		total += card.get(25).getScore();
		total += card.get(26).getScore();
		return total;
	}

	public JLabel getTotalScoreLabel(){
		totalScoreLabel.setText(Integer.toString(totalScore()));
		totalScoreLabel.setMinimumSize(new Dimension(200, 200));
		totalScoreLabel.setMaximumSize(new Dimension(200, 200));
		return totalScoreLabel;
	}
	
	public JLabel getBonusScoreLabel(){
		bonusScoreLabel.setText(Integer.toString(bonusScore()));
		bonusScoreLabel.setMinimumSize(new Dimension(200, 200));
		bonusScoreLabel.setMaximumSize(new Dimension(200, 200));
		return bonusScoreLabel;
	}
	
	/**
	 * calculates the overall total
	 * @return the total score
	 */
	public int totalScore(){
		return upperScore()+lowerScore()+bonusScore();
	}

}

