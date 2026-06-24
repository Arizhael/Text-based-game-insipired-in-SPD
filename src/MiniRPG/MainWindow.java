package MiniRPG;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import PersonalisedFrame.PerFrame;

public class MainWindow {
	
	private PerFrame frame;
	private JPanel mainPanel, topPanel, bottomPanel;
	
	private JLabel nameLabel, levelLabel, expLabel, goldLabel, attributesLabel;
	private JLabel imageLabel;
	
	private JButton exploreButton, shopButton, closeButton;
	
	private Character ch;
	
	public MainWindow (Character ch) {
		
		this.ch = ch;
		
		frame = new PerFrame(800,600, "MIni RPG", true);
		
		mainPanel = new JPanel(new BorderLayout());
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		nameLabel = new JLabel(ch.getName() + "     ");
		levelLabel = new JLabel(" Lvl: " + ch.getLevel());
		expLabel = new JLabel(" Exp: " + ch.getExp() + "/" + ch.getNeededExp());
		goldLabel = new JLabel(" Gold: " + ch.getGold());
		attributesLabel = new JLabel(" Atk: " + ch.getAttack() + " | Def: " + ch.getDefense() + "  Vida: ");
		
		imageLabel = new JLabel();
		
		exploreButton = new JButton("¡Explore!");
		shopButton = new JButton ("Shop");
		closeButton = new JButton ("Close");
	}
	
	public void StartGame() {
		
		setScene();
		frame.setUndecorated(true);
		frame.setVisible(true);
	}

	private void setScene() {
		
		// Top-Panel with character data-
		modifyFonts();
		topPanel.add(nameLabel);
		topPanel.add(levelLabel);
		topPanel.add(expLabel);
		topPanel.add(goldLabel);
		topPanel.add(attributesLabel);
		topPanel.add(ch.getHealthBar());
		
		// Central screen image-
		imageLabel.setIcon(new ImageIcon("./Images/sewers.jpg"));
		mainPanel.add(imageLabel, BorderLayout.CENTER);
		
		// Bottom-panel buttons-
		exploreButton.addActionListener(e-> newExploration());
		shopButton.addActionListener(e-> openShop());
		closeButton.addActionListener(e-> System.exit(0));
		bottomPanel.add(exploreButton);
		bottomPanel.add(shopButton);
		bottomPanel.add(closeButton);
		
		// Secondary panels on the main panel
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		// Add main-panel to the frame 
		frame.add(mainPanel);
		
		
		
	}

	private void openShop() {
		
		Shop s = new Shop(this);
		s.openShop();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		frame.repaint();
		
	}

	private void modifyFonts() {
		
		Font myFont = new Font("Roboto", Font.BOLD, 20);
		
		nameLabel.setFont(myFont);
		
	}

	private void newExploration() {
		Exploration ex = new Exploration(this);
		ex.beginExploration();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		frame.repaint();
		
		/*GameOverWindow gow = new GameOverWindow(GameOverWindow.VICTORY, ch);
		gow.Open();*/
		
	}

	public Character getCh() {
		return ch;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public JLabel getLevelLabel() {
		return levelLabel;
	}

	public JLabel getExpLabel() {
		return expLabel;
	}

	public JLabel getGoldLabel() {
		return goldLabel;
	}

	public JLabel getAttributesLabel() {
		return attributesLabel;
	}
	
	
	
}
