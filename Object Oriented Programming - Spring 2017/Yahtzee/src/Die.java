/** 
 * Erik Fox
 * Homework 2
 * Last Modified:2/15/2017
 * Description: This class creates a Die and to generate a 
 * random number in its range or return the number. 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class Die {
	Rules rules = new Rules();
	private int sideOnTop;
	private int numberOfSides;
	JToggleButton dieButton;
	Icon newImage;
	
	/**
	 * constructor for the class Die
	 */
	public Die(){
		numberOfSides = rules.getSides();
		Random rand = new Random();
		sideOnTop = rand.nextInt(numberOfSides) + 1;
		if (sideOnTop == 1)
			dieButton = new JToggleButton(new ImageIcon("1die.png"));
		else if (sideOnTop == 2)
			dieButton = new JToggleButton(new ImageIcon("2die.png"));
		else if (sideOnTop == 3)
			dieButton = new JToggleButton(new ImageIcon("3die.png"));
		else if (sideOnTop == 4)
			dieButton = new JToggleButton(new ImageIcon("4die.png"));
		else if (sideOnTop == 5)
			dieButton = new JToggleButton(new ImageIcon("5die.png"));
		else if (sideOnTop == 6)
			dieButton = new JToggleButton(new ImageIcon("6die.png"));
		else if (sideOnTop == 7)
			dieButton = new JToggleButton(new ImageIcon("7die.png"));
		else if (sideOnTop == 8)
			dieButton = new JToggleButton(new ImageIcon("8die.png"));
		else if (sideOnTop == 9)
			dieButton = new JToggleButton(new ImageIcon("9die.png"));
		else if (sideOnTop == 10)
			dieButton = new JToggleButton(new ImageIcon("10die.png"));
		else if (sideOnTop == 11)
			dieButton = new JToggleButton(new ImageIcon("11die.png"));
		else if (sideOnTop == 12)
			dieButton = new JToggleButton(new ImageIcon("12die.png"));;
		dieButton.setVisible(true);
		dieButton.validate();
		dieButton.repaint();
	}

	public Icon getImage(JToggleButton dieButton, int sideOnTop){
		if (sideOnTop == 1)
			newImage = new ImageIcon("1die.png");
		else if (sideOnTop == 2)
			newImage = new ImageIcon("2die.png");
		else if (sideOnTop == 3)
			newImage = new ImageIcon("3die.png");
		else if (sideOnTop == 4)
			newImage = new ImageIcon("4die.png");
		else if (sideOnTop == 5)
			newImage = new ImageIcon("5die.png");
		else if (sideOnTop == 6)
			newImage = new ImageIcon("6die.png");
		else if (sideOnTop == 7)
			newImage = new ImageIcon("7die.png");
		return newImage;
	}
	
	public JToggleButton getDieButton(){
			return dieButton;
		}
	/**
	 * rolls the die to get a random value
	 */
	public void rollDie(){
		Random rand = new Random();
		sideOnTop = rand.nextInt(numberOfSides) + 1;
		dieButton.setIcon(getImage(dieButton, sideOnTop));
		dieButton.validate();
		dieButton.repaint();
	}
	/**
	 * gets the value of the die
	 * @return the value of the die
	 */
	public int getSideOnTop(){
		return sideOnTop;
	}
	
	
}


