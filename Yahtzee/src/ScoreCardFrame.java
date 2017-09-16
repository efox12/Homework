import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreCardFrame extends JFrame{
	JPanel centerSubPanelRightMid = new JPanel();
	JPanel centerSubPanelLeftMid = new JPanel();
	JPanel centerSubPanel = new JPanel();
	JPanel centerSubPanelLeft = new JPanel();
	JPanel centerSubPanelRight = new JPanel();
	JLabel totalScoreLabel = new JLabel();
	JLabel bonusScoreLabel = new JLabel();
	Rules rules = new Rules();
	
	public void ScoreCardFrame(){
		setSize(600,500);
		setVisible(true);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void viewScoreCard(Scorecard scorecard){
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
		
		centerSubPanelRight.add(scorecard.getTotalScoreLabel());
		add(centerSubPanelLeft);
		add(centerSubPanelLeftMid);
		add(centerSubPanelRightMid);
		add(centerSubPanelRight);
	}
}
