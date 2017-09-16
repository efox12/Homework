import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;



public class YahtzeeUX extends JFrame{
	Rules rules = new Rules();
	JButton rollButton;
	JButton scoreButton;
	int sideCount, dieCount;
	JComboBox numSides;
	JComboBox numDie;
	JButton newGameButton;
	String buttonValue;
	JLabel totalScoreLabel = new JLabel();
	JLabel bonusScoreLabel = new JLabel();

	JPanel northSubPanel = new JPanel();
	JPanel northSubPanelTop = new JPanel(new FlowLayout());
	JPanel northSubPanelBottom = new JPanel(new FlowLayout());
	JPanel centerSubPanelRightMid = new JPanel();
	JPanel centerSubPanelLeftMid = new JPanel();
	JPanel centerSubPanel = new JPanel();
	JPanel centerSubPanelLeft = new JPanel();
	JPanel centerSubPanelRight = new JPanel();
	
	public void displayTurn(int turn){
		if (turn < 4){
			rollButton.setEnabled(true);
			rollButton.setText("Roll: " + Integer.toString(turn));
		}
		else{
			rollButton.setEnabled(false);
			rollButton.setText("Score Hand");		}
	}
	
	/**
	 * Creates a board with general buttons 
	 * such as roll, get scorecard, and change rules
	 */
	public void createBoard(){
		northSubPanel.setLayout(new BoxLayout(northSubPanel, BoxLayout.Y_AXIS));
		
		rollButton = new JButton("Roll: 2");
		northSubPanelTop.add(rollButton);
		newGameButton = new JButton("New Game");
		northSubPanelTop.add(newGameButton);
		
		northSubPanelTop.add(new JLabel("Set Sides:"));
		String[] sides= {"6","8","12"};
		numSides = new JComboBox(sides);
		northSubPanelTop.add(numSides);
		numSides.setVisible(true);
		
		northSubPanelTop.add(new JLabel("Set Dice:"));
		String[] dice= {"5","6","7"};
		numDie = new JComboBox(dice);
		northSubPanelTop.add(numDie);
		numSides.setVisible(true);
		
		northSubPanel.add(northSubPanelTop);
		northSubPanel.add(northSubPanelBottom);
		
		add(northSubPanel, BorderLayout.NORTH);
		
		scoreButton = new JButton("View Score Card");
		add(scoreButton, BorderLayout.SOUTH);
		
	}
	public void resetHand(){
		northSubPanelBottom.removeAll();
	}
	
	/**
	 * Creates a hand interface with dice that can be selected as toggle buttons
	 * @param hand The hand being used to control the UI hand.
	 */
	public void createHand(Hand hand){
		for(int i = 0; i<rules.getDie(); i++){
			northSubPanelBottom.add(hand.currentHand.get(i).getDieButton());
		}
		revalidate();
		repaint();
	}
	/**
	 * Creates a score card interface with a button  and a label
	 * for each spot on the score card.
	 * @param scorecard The scorecard being used to create and display
	 * the buttons
	 */
	public void createScorecard(Scorecard scorecard, Hand hand, YahtzeeUX frame){
		totalScoreLabel.setFont(totalScoreLabel.getFont().deriveFont(20.0f));
		bonusScoreLabel.setFont(bonusScoreLabel.getFont().deriveFont(20.0f));
		scorecard.createScorecard(rollButton, hand, scorecard, frame);
		centerSubPanel.setLayout(new FlowLayout());
		centerSubPanel.setMinimumSize(new Dimension(600, 500));
		centerSubPanel.setMaximumSize(new Dimension(600, 500));
		centerSubPanel.setMaximumSize(new Dimension(600, 500));
		centerSubPanel.setBackground(Color.lightGray);
		centerSubPanelLeft.setLayout(new BoxLayout(centerSubPanelLeft, BoxLayout.Y_AXIS));
		centerSubPanelLeft.setMinimumSize(new Dimension(200, 500));
		centerSubPanelLeft.setMaximumSize(new Dimension(200, 500));
		centerSubPanelLeft.setPreferredSize(new Dimension(200, 500));
		for(int i = 1; i<=rules.getSides(); i++){
			centerSubPanelLeft.add(scorecard.card.get(i).getButton());
		}
		bonusScoreLabel.setText("Bonus");
		bonusScoreLabel.setMinimumSize(new Dimension(200, 200));
		bonusScoreLabel.setMaximumSize(new Dimension(200, 200));
		centerSubPanelLeft.add(bonusScoreLabel);
		
		centerSubPanelLeftMid.setLayout(new BoxLayout(centerSubPanelLeftMid, BoxLayout.Y_AXIS));
		centerSubPanelLeftMid.setMinimumSize(new Dimension(100, 500));
		centerSubPanelLeftMid.setMaximumSize(new Dimension(100, 500));
		centerSubPanelLeftMid.setPreferredSize(new Dimension(100, 500));
		for(int i = 1; i<=rules.getSides(); i++){
			centerSubPanelLeftMid.add(scorecard.card.get(i).scoreLabel);
		}
		centerSubPanelLeftMid.add(scorecard.getBonusScoreLabel());
		
		centerSubPanelRightMid.setLayout(new BoxLayout(centerSubPanelRightMid, BoxLayout.Y_AXIS));
		centerSubPanelRightMid.setMinimumSize(new Dimension(200, 500));
		centerSubPanelRightMid.setMaximumSize(new Dimension(200, 500));
		centerSubPanelRightMid.setPreferredSize(new Dimension(200, 500));
		for(int i = 20; i<27; i++){
			centerSubPanelRightMid.add(scorecard.card.get(i).getButton());
		}
		totalScoreLabel.setText("Total Score");
		totalScoreLabel.setMinimumSize(new Dimension(200, 200));
		totalScoreLabel.setMaximumSize(new Dimension(200, 200));
		centerSubPanelRightMid.add(totalScoreLabel);
		
		centerSubPanelRight.setLayout(new BoxLayout(centerSubPanelRight, BoxLayout.Y_AXIS));
		centerSubPanelRight.setMinimumSize(new Dimension(100, 500));
		centerSubPanelRight.setMaximumSize(new Dimension(100, 500));
		centerSubPanelRight.setPreferredSize(new Dimension(100, 500));
		for(int i = 20; i<=26; i++){
			centerSubPanelRight.add(scorecard.card.get(i).scoreLabel);
		}
		centerSubPanelRight.add(scorecard.getTotalScoreLabel());
		
		centerSubPanel.add(centerSubPanelLeft);
		centerSubPanel.add(centerSubPanelLeftMid);
		centerSubPanel.add(centerSubPanelRightMid);
		centerSubPanel.add(centerSubPanelRight);
		
		add(centerSubPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	public void updateScorecard(Scorecard scorecard){
		totalScoreLabel.setFont(totalScoreLabel.getFont().deriveFont(20.0f));
		bonusScoreLabel.setFont(bonusScoreLabel.getFont().deriveFont(20.0f));
		centerSubPanelLeft.removeAll();
		centerSubPanelLeftMid.removeAll();
		centerSubPanelRightMid.removeAll();
		centerSubPanelRight.removeAll();
		
		for(int i = 1; i<=rules.getSides(); i++){
			centerSubPanelLeft.add(scorecard.card.get(i).getButton());
		}
		bonusScoreLabel.setText("Bonus");
		bonusScoreLabel.setMinimumSize(new Dimension(200, 200));
		bonusScoreLabel.setMaximumSize(new Dimension(200, 200));
		centerSubPanelLeft.add(bonusScoreLabel);
		
		
		centerSubPanelLeftMid.setLayout(new BoxLayout(centerSubPanelLeftMid, BoxLayout.Y_AXIS));
		for(int i = 1; i<=rules.getSides(); i++){
			centerSubPanelLeftMid.add(scorecard.card.get(i).scoreLabel);
		}
		centerSubPanelLeftMid.add(scorecard.getBonusScoreLabel());
		
		centerSubPanelRightMid.setLayout(new BoxLayout(centerSubPanelRightMid, BoxLayout.Y_AXIS));
		for(int i = 20; i<27; i++){
			centerSubPanelRightMid.add(scorecard.card.get(i).getButton());
		}
		totalScoreLabel.setText("Total Score");
		totalScoreLabel.setFont(totalScoreLabel.getFont().deriveFont(20.0f));
		totalScoreLabel.setMinimumSize(new Dimension(200, 200));
		totalScoreLabel.setMaximumSize(new Dimension(200, 200));
		centerSubPanelRightMid.add(totalScoreLabel);
		
		centerSubPanelRight.setLayout(new BoxLayout(centerSubPanelRight, BoxLayout.Y_AXIS));
		for(int i = 20; i<=26; i++){
			centerSubPanelRight.add(scorecard.card.get(i).scoreLabel);
		}
		centerSubPanelRight.add(scorecard.getTotalScoreLabel());
		revalidate();
		repaint();
	}
	public void viewScoreCard(Scorecard scorecard){
		totalScoreLabel.setFont(totalScoreLabel.getFont().deriveFont(20.0f));
		bonusScoreLabel.setFont(bonusScoreLabel.getFont().deriveFont(20.0f));
		JPanel scoreCard = new JPanel();
		JFrame scoreCardFrame = new JFrame("Score Card");
		scoreCard.setLayout(new FlowLayout());
		scoreCard.setMinimumSize(new Dimension(600, 500));
		scoreCard.setMaximumSize(new Dimension(600, 500));
		scoreCard.setMaximumSize(new Dimension(600, 500));
		scoreCard.setBackground(Color.lightGray);
		JPanel subPanelLeft = new JPanel();
		JPanel subPanelLeftMid = new JPanel();
		JPanel subPanelRightMid = new JPanel();
		JPanel subPanelRight = new JPanel();
		
		subPanelLeft.setLayout(new BoxLayout(subPanelLeft, BoxLayout.Y_AXIS));		
		subPanelLeft.setMinimumSize(new Dimension(200, 500));
		subPanelLeft.setMaximumSize(new Dimension(200, 500));
		subPanelLeft.setPreferredSize(new Dimension(200, 500));
		for(int i = 1; i<=rules.getSides(); i++){
			subPanelLeft.add(scorecard.card.get(i).getButton());
		}
		bonusScoreLabel.setText("Bonus");
		bonusScoreLabel.setMinimumSize(new Dimension(200, 200));
		bonusScoreLabel.setMaximumSize(new Dimension(200, 200));
		subPanelLeft.add(bonusScoreLabel);
		
		subPanelLeftMid.setMinimumSize(new Dimension(100, 500));
		subPanelLeftMid.setMaximumSize(new Dimension(100, 500));
		subPanelLeftMid.setPreferredSize(new Dimension(100, 500));
		subPanelLeftMid.setLayout(new BoxLayout(subPanelLeftMid, BoxLayout.Y_AXIS));
		for(int i = 1; i<=rules.getSides(); i++){
			subPanelLeftMid.add(scorecard.card.get(i).finalScoreLabel);
		}
		subPanelLeftMid.add(scorecard.getBonusScoreLabel());
		
		subPanelRightMid.setMinimumSize(new Dimension(200, 500));
		subPanelRightMid.setMaximumSize(new Dimension(200, 500));
		subPanelRightMid.setPreferredSize(new Dimension(200, 500));
		subPanelRightMid.setLayout(new BoxLayout(subPanelRightMid, BoxLayout.Y_AXIS));
		for(int i = 20; i<27; i++){
			subPanelRightMid.add(scorecard.card.get(i).getButton());
		}
		totalScoreLabel.setText("Total Score");
		totalScoreLabel.setMinimumSize(new Dimension(200, 200));
		totalScoreLabel.setMaximumSize(new Dimension(200, 200));
		subPanelRightMid.add(totalScoreLabel);
		
		subPanelRight.setMinimumSize(new Dimension(100, 500));
		subPanelRight.setMaximumSize(new Dimension(100, 500));
		subPanelRight.setPreferredSize(new Dimension(100, 500));
		subPanelRight.setLayout(new BoxLayout(subPanelRight, BoxLayout.Y_AXIS));
		for(int i = 20; i<=26; i++){
			subPanelRight.add(scorecard.card.get(i).finalScoreLabel);
		}
		subPanelRight.add(scorecard.getTotalScoreLabel());		
		
		subPanelRight.add(scorecard.getTotalScoreLabel());
		scoreCard.add(subPanelLeft);
		scoreCard.add(subPanelLeftMid);
		scoreCard.add(subPanelRightMid);
		scoreCard.add(subPanelRight);
		JOptionPane.showMessageDialog(scoreCardFrame, scoreCard);
	}
}

