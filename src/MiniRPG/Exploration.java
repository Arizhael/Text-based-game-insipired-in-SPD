package MiniRPG;

import java.awt.*;

import javax.swing.*;

public class Exploration {

	private JDialog frame;
	
	private JPanel mainPanel, topPanel, bottomPanel, enemyPanel, secEnemyPanel;
	
	private JButton attackButton, scapeButton;
	
	private JTextArea explorationInfo;
	private JScrollPane scrollBar;
	
	private Character ch;
	private Enemy enemy;
	
	private static int explorationNum = 0;
	
	private MainWindow mw;
	
	private boolean isBoss = false;
	
	public Exploration(MainWindow mw) {

		this.mw = mw;
				
		ch = mw.getCh();
		
		frame = new JDialog(); 
		
		mainPanel = new JPanel(new BorderLayout());
		topPanel = mw.getTopPanel();
		bottomPanel = new JPanel();
		enemyPanel = new JPanel();
		secEnemyPanel = new JPanel();
		
		explorationInfo = new JTextArea();
		explorationInfo.setEditable(false);
		scrollBar = new JScrollPane(explorationInfo);
		scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		attackButton = new JButton("¡Attack!");
		scapeButton = new JButton("Scape...");
	}
	
	public void beginExploration() {
		
		decideDifficulty();
		setUpInterface();
	}
	
	private void decideDifficulty() {
		
		int randomNum = (int) (Math.random() * 100)+explorationNum;
		
		explorationNum ++;
		
		// Instance an obj class monster 
		enemy = Enemy.generateEnemy(randomNum);
		
		if(enemy.getName().equals("Gu")) isBoss = true;
	}

	private void setUpInterface() {
		
		// Top-Panel ready
		
		// Text area added to main Panel
		mainPanel.add(scrollBar, BorderLayout.CENTER);
		
		// Add everything about the monster
		secEnemyPanel.add(enemy.getNameLabel());
		secEnemyPanel.add(enemy.getHealthBar());
		
		enemyPanel.setLayout(new BoxLayout(enemyPanel, BoxLayout.Y_AXIS));
		enemyPanel.add(enemy.getImageLabel());
		enemyPanel.add(secEnemyPanel);
		
		// Bottom-panel buttons
		attackButton.addActionListener(e-> attack());
		scapeButton.addActionListener(e-> {
			explorationNum++;
			frame.dispose();
		});
		
		bottomPanel.add(attackButton);
		bottomPanel.add(new JLabel("            "));
		if (isBoss) scapeButton.setEnabled(false);
		bottomPanel.add(scapeButton);
		
		// add secondary panels to main
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		//add monster panel
		mainPanel.add(enemyPanel, BorderLayout.EAST);
	
		frame.add(mainPanel);
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setModal(true);
		frame.setUndecorated(true);
		frame.setVisible(true);

		
	}

	private void attack() {
		
		int damage;
		
		ch.attack(enemy);
		explorationInfo.setText(explorationInfo.getText() + ch.getName() + " attacks with a power of " + ch.getAttack() + ".\n");
		
		damage = ch.getAttack() - enemy.getDefense();
		if(damage <= 0) damage = 1;
		
		explorationInfo.setText(explorationInfo.getText() + enemy.getName() + " takes " + damage + " damage because of its defense.\n\n");
		
		enemy.setHealth(enemy.getCurrentHealth());
		
		if(!enemy.isAlive()) { 
			defeatEnemy();
		}else {
			enemy.attack(ch);
			explorationInfo.setText(explorationInfo.getText() + enemy.getName() + " attacks with a power of " + enemy.getAttack() + ".\n");
			
			damage = enemy.getAttack() - ch.getDefense();
			if(damage <= 0) damage = 1;
			
			explorationInfo.setText(explorationInfo.getText() + ch.getName() + " takes " + damage + " damage because of its defense.\n\n");
			ch.setHealth(ch.getCurrentHealth());
			
			if(!ch.isAlive()) gameOver();
		}
	}

	private void gameOver() {
		
		GameOverWindow gow = new GameOverWindow(GameOverWindow.DEFEAT, ch);
		gow.Open();
		
	}

	private void defeatEnemy() {
		
		attackButton.setEnabled(false);
		scapeButton.setText("Exit");
		
		explorationInfo.setText(explorationInfo.getText() + ch.getName() + " defeated " + enemy.getName() + ".\n"
									+ "You got " + enemy.getGoldReward() + " gold!.\n"
									+ "+ " + enemy.getExpReward() + " Exp.\n\n");
		
		ch.obtainExp(enemy.getExpReward());
		mw.getExpLabel().setText(" Exp: " + ch.getExp() + "/" + ch.getNeededExp());
		mw.getLevelLabel().setText(" Lvl: " + ch.getLevel());
		mw.getAttributesLabel().setText(" Atk: " + ch.getAttack() + " | Def: " + ch.getDefense() + "  Vida: ");
		
		ch.setGold(ch.getGold() + enemy.getGoldReward());
		mw.getGoldLabel().setText(" Gold: " + ch.getGold());
		
		if (isBoss) {
			
			GameOverWindow gow = new GameOverWindow(GameOverWindow.VICTORY, ch);
			gow.Open();
			
		}
		
	}

	public static int getExplorationNum() {
		return explorationNum;
	}

	public static void setExplorationNum(int explorationNum) {
		Exploration.explorationNum = explorationNum;
	}
	
	

}
