package MiniRPG;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class GameOverWindow {
	
	private JTextArea textArea;
	private JLabel imageLabel;
	private JButton exitButton;
	private ImageIcon imageRoute;
	
	private JDialog frame;
	private JPanel mainPanel;
	
	private int condition;
	private Character ch;
	
	public static final int VICTORY = 0;
	public static final int DEFEAT = 1;
	
	public GameOverWindow (int condition, Character ch) {
		
		frame = new JDialog();
		mainPanel = new JPanel(new BorderLayout());
		
		
		textArea = new JTextArea();
		exitButton = new JButton("End Game...");
		
		this.condition = condition;
		this.ch = ch;
		
		if(condition == VICTORY) imageRoute = new ImageIcon("./images/Victory.jpg");
		else imageRoute = new ImageIcon("./images/GameOver.png");
		
		imageLabel = new JLabel(imageRoute);
	}
	
	public void Open() {
		
		preparMessage();
		setScene();
		frame.setUndecorated(true);
		frame.setVisible(true);
	}

	private void preparMessage() {
		
		String finalMessage;
		
		if(condition == VICTORY) {
			
			finalMessage = "You won!.\n"
					+ "wow.\n"
					+ ch.getName() + ".\n"
					+ "Level: " + ch.getLevel() + ".\n"
					+ "Gold: " + ch.getGold() + ".\n";
		}else {
			
			finalMessage = "GAME OVER.\n"
					+ "It's not that hard.\n"
					+ ch.getName() + ".\n"
					+ "Level: " + ch.getLevel() + ".\n"
					+ "Gold: " + ch.getGold() + ".\n";
		}
		
		textArea.setText(finalMessage);
		
	}

	private void setScene() {
		
		// Image to NORTH
		mainPanel.add(imageLabel, BorderLayout.NORTH);
		
		// Text area to CENTER
		textArea.setEditable(false);
		mainPanel.add(textArea, BorderLayout.CENTER);
		
		// Button to SOUTH
		exitButton.addActionListener(e-> System.exit(0));
		mainPanel.add(exitButton,BorderLayout.SOUTH);
		
		
		frame.add(mainPanel);
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setModal(true);
		
	}
	
}
