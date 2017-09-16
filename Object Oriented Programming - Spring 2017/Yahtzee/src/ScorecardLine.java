/**
 * Erik Fox
 * Homework 3
 * Last Modified:2/24/2017
 * Description: This class creates a 
 * scorecard line and modifies its values
 * for use in scorecard
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class ScorecardLine {
	ArrayList<Integer> lineValues = new ArrayList<Integer>();
	String lineString;
	JButton lineButton;
	JLabel scoreLabel = new JLabel();
	JLabel finalScoreLabel = new JLabel();
	Dimension minSize = new Dimension(100, 50);
	/**
	 * Constructor for ScorecardLine
	 */
	public ScorecardLine(JButton rollButton, Hand hand, Scorecard scorecard, YahtzeeUX frame){
		scoreLabel = new JLabel();
		scoreLabel.setFont(scoreLabel.getFont().deriveFont(20.0f));
		scoreLabel.setText("0");
		finalScoreLabel.setFont(finalScoreLabel.getFont().deriveFont(20.0f));
		finalScoreLabel.setMinimumSize(new Dimension(200, 50));
		finalScoreLabel.setMaximumSize(new Dimension(200, 100));
		finalScoreLabel.setText(" ");
		scoreLabel.setVisible(true);
		scoreLabel.setMinimumSize(new Dimension(200, 50));
		scoreLabel.setMaximumSize(new Dimension(200, 100));
		scoreLabel.setOpaque(true);
		lineButton = new JButton();
		lineButton.setFont(lineButton.getFont().deriveFont(20.0f));
		lineButton.setMinimumSize(new Dimension(200, 50));
		lineButton.setMaximumSize(new Dimension(200, 100));
		lineButton.setVisible(true);
		lineValues.add(0);
		lineValues.add(0);
		lineValues.add(0);
		lineButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setLine(rollButton, hand, scorecard, frame);
			}
		});
	}
	
	public void getScoreButtonPress(JButton rollButton){

	}
	/**
	 * Returns the button for the line
	 * @return The button for the line
	 */
	public JButton getButton(){
		return lineButton;
	}
	/**
	 * Returns the Label for the line
	 * @return The label for the line
	 */
	public JLabel getScoreLabel(){
		return scoreLabel;
	}

	/**
	 * adds a new line to the scorecard and sets its values
	 * @param scoreLineType	the ID of the line
	 * @param isScored	weather a line has been scored on yet
	 * @param score	the current score on the line
	 * @param potentialScore	the current portential score on the line
	 */
	public void changeLine(String scoreLineType, int isScored, int score, int potentialScore){
		lineValues.set(0, isScored);
		lineValues.set(1, score);
		scoreLabel.setText(Integer.toString(score));
		lineValues.set(2, potentialScore);
		scoreLabel.setText(Integer.toString(potentialScore));
		//scoreLabel.paintImmediately(scoreLabel.getVisibleRect());
	}
	
	/**
	 * checks if a line has been assigned a score yet
	 * @param currentLine 	the current line under inspection
	 * @return 		0 if a value hasn't been stored yet
	 */
	public int getLineIsScored(){
		return lineValues.get(0);
	}
	
	/**
	 * sets the score of the current line to the potential score
	 * @param currentLine	the current line under inspection
	 */
	public void setLine(JButton rollButton, Hand hand, Scorecard scorecard, YahtzeeUX frame){
		lineValues.set(0, 1);
		lineValues.set(1, lineValues.get(2));
		finalScoreLabel.setText(Integer.toString(lineValues.get(2)));
		scoreLabel.setText(Integer.toString(lineValues.get(2)));
		lineButton.setEnabled(false);
		rollButton.setEnabled(true);
		rollButton.setText("Roll: 2");
		yahtzee.turn = 2;
		hand.rollHand();
		hand.sortHand();
		scorecard.displayScorecard(hand);
		frame.updateScorecard(scorecard);
	}
	/**
	 * sets to potential score of the current line
	 * @param currentLine	the current line under inspection
	 * @param score	the current potential score of the line
	 */
	public void setPotential(int score){
		lineValues.set(2, score);
		scoreLabel.setText(Integer.toString(score));
	}
	
	/**
	 * gets the score on the current line
	 * @param currentLine	the current line under inspection
	 * @return	in integer for the current score on a line
	 */
	public int getScore(){
		return lineValues.get(1);
	}
	
	public void setLabel(String label){
		scoreLabel.setText(label);
		scoreLabel.paintImmediately(scoreLabel.getVisibleRect());
	}
}
