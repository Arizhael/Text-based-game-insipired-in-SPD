package MiniRPG;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class Enemy extends Entity{
	
	private int goldReward;
	private int expReward;
	
	private JLabel nameLabel, imageLabel;
	
	private static String[] easyNames = {"Rat", "Snake", "Ghost"};
	private static String[] mediumNames = {"Gnoll", "IceElemental", "ElectricElemental"};
	private static String[] hardNames = {"Scorpion", "FireElemental", "CaoticElemental"};

	public Enemy(String name, int attack, int defense, double maxHealth, String difficulty) {
		super(name, attack, defense, maxHealth);
		
		nameLabel = new JLabel(name);
		nameLabel.setFont(new Font("Roboto", Font.BOLD, 20));
		String imageRoute = "./Enemies/" + name + ".png";
		imageLabel = new JLabel();
		imageLabel.setIcon(new ImageIcon(imageRoute));
		
		switch(difficulty) {
		
		    case "easy":
		    	expReward = (int) (Math.random() * 2 + 1);
		    	goldReward = (int) (Math.random() * 5 + 1);
		    	nameLabel.setForeground(Color.green);
		    	break;
		    	
		    case "medium":
		    	expReward = (int) (Math.random() * 6 + 2);
		    	goldReward = (int) (Math.random() * 20 + 1);
		    	nameLabel.setForeground(Color.orange);
		    	break;
		
		    case "hard":
		    	expReward = (int) (Math.random() * 16 + 5);
		    	goldReward = (int) (Math.random() * 50 + 1);
		    	nameLabel.setForeground(Color.red);
		    	break;
		    	
		    default:
		    	expReward = 500;
		    	goldReward = 1000;
		    	nameLabel.setForeground(Color.black);
		    	break;
		    	
		}
		
	}
	
	public static Enemy generateEnemy(int i) {
		
		Enemy e;
		
		int enemyN = (int) (Math.random() * 3);
		int healthN = (int) (Math.random() * 30);
		int attackN = (int) (Math.random() * 5);
		int defenseN = (int) (Math.random() * 2);
		
		if (i < 80) {
			e = new Enemy(easyNames[enemyN], attackN+1, defenseN, healthN+15, "easy");
		}else if (i < 140) {
			e = new Enemy(mediumNames[enemyN], attackN+4, defenseN+2, healthN+30, "medium");
		}else if (i < 200) {
			e = new Enemy(hardNames[enemyN], attackN+8, defenseN+5, healthN+80, "hard");
		}else {
			e = new Enemy("Gu", attackN+15, defenseN+10, healthN+150, "Boss");
		}
		
		return e;
	}

	public int getGoldReward() {
		return goldReward;
	}

	public int getExpReward() {
		return expReward;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public JLabel getImageLabel() {
		return imageLabel;
	}
	
}
