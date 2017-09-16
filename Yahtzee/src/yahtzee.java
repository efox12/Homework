/**
 * Erik Fox
 * Homework 2
 * Last Modified:2/15/2017
 * Description: This class has the main function and it uses the class Hand 
 * to roll the dice 3 times and choose which dice to keep in between rolls.
 * At the end it displays all of the possible score possibilities and asks 
 * the user if they want to play again.
 */

import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class yahtzee {
	static int turn;
	public static void main(String[] args) { 
		Boolean playAgain = true;
		Scanner userInput = new Scanner(System.in);
		Rules rules = new Rules();
		rules.defaultHand();
		YahtzeeUX frame = new YahtzeeUX();
		ScoreCardFrame scoreFrame;
		
		EventQueue.invokeLater(() ->
		{
			frame.setSize(900,1000);
			frame.setVisible(true);
			frame.setLayout(new BorderLayout());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		});
		
		frame.createBoard();
		frame.validate();
		frame.repaint();
		frame.rollButton.addActionListener( e -> {
		});
		frame.scoreButton.addActionListener( e ->{	
		});
		
		frame.newGameButton.addActionListener( ae -> {
				frame.scoreButton.removeActionListener((frame.scoreButton.getActionListeners()[0]));
				frame.rollButton.removeActionListener((frame.rollButton.getActionListeners()[0]));
			turn = 2;
			frame.displayTurn(turn);
			frame.rollButton.setEnabled(true);
			int dice = 5;
			int sides = 6;
			int diceIndex = frame.numDie.getSelectedIndex();
			int sideIndex = frame.numSides.getSelectedIndex();
			if(diceIndex == 0)
				dice = 5;
			else if(diceIndex == 1)
				dice = 6;
			else if(diceIndex == 2)
				dice = 7;
			if(sideIndex == 0)
				sides = 6;
			else if(sideIndex == 1)
				sides = 8;
			else if(sideIndex == 2)
				sides = 12;
			rules.changeRules(sides, dice);
			
			Hand hand = new Hand();
			Scorecard scorecard = new Scorecard();
			frame.createScorecard(scorecard, hand, frame);
			scorecard.createScorecard(frame.rollButton, hand, scorecard, frame);
			frame.validate();
			frame.repaint();
			
			frame.scoreButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					frame.viewScoreCard(scorecard);
					frame.updateScorecard(scorecard);
					frame.validate();
					frame.repaint();
				}
			});
			
			frame.resetHand();
			frame.createHand(hand);
			hand.sortHand();
			scorecard.displayScorecard(hand);
			frame.updateScorecard(scorecard);
			frame.validate();
			frame.repaint();
			
			frame.rollButton.addActionListener( e -> {
				turn++;
				frame.displayTurn(turn);
				for(int j = 0; j<rules.getDie(); j++){
					if(hand.currentHand.get(j).getDieButton().isSelected() == true){
						hand.currentHand.get(j).rollDie();
						hand.currentHand.get(j).getDieButton().setSelected(false);
					}
				}
				hand.sortHand();
				scorecard.displayScorecard(hand);
				frame.updateScorecard(scorecard);
				frame.validate();
				frame.repaint();
			});	
		});
	}
}
