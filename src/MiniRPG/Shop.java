package MiniRPG;

import java.awt.*;

import javax.swing.*;

public class Shop {
	
	private JDialog frame;
	
	private JPanel mainPanel, topPanel, bottomPanel, shopPanel;
	private JPanel swordPanel, shieldPanel, potionPanel, mapPanel;
	
	private JLabel swordImage, shieldImage, potionImage, mapImage;
	private JLabel swordDes, shieldDes, potionDes, mapDes;
	private static JButton swordButton,shieldButton, potionButton, mapButton;
	private static boolean noSword=false, noShield=false, noPotion=false, noMap=false;
	
	private JButton exitButton;
	
	private MainWindow mw;
	private Character ch;
	
	public Shop(MainWindow mw) {
		
		this.mw = mw; 
		
		ch = mw.getCh();
		
		frame = new JDialog();
		
		mainPanel = new JPanel(new BorderLayout());
		topPanel = mw.getTopPanel();
		bottomPanel = new JPanel();
		shopPanel = new JPanel(new GridLayout(2,2));
		
		swordPanel = new JPanel();
		shieldPanel = new JPanel();
		potionPanel = new JPanel();
		mapPanel = new JPanel();
		
		swordImage = new JLabel(new ImageIcon("./images/sword.png"));
		shieldImage = new JLabel(new ImageIcon("./images/Round Shield.png"));
		potionImage = new JLabel(new ImageIcon("./images/Potion.png"));
		mapImage = new JLabel(new ImageIcon("./images/Map.png"));
		
		swordDes = new JLabel("Sword - 100g");
		shieldDes = new JLabel("Shield - 100g");
		potionDes = new JLabel("Health potion - 50g");
		mapDes = new JLabel("Map - 10g");
		
		swordButton = new JButton("Buy");
		shieldButton = new JButton("Buy");
		potionButton = new JButton("Buy");
		mapButton = new JButton("Buy");
		
		exitButton = new JButton("Exit");
		
	}
	
	public void openShop() {
		
		setScene();
		frame.setUndecorated(true);
		frame.setVisible(true);
		
	}

	private void setScene() {

		// Top panel
		mainPanel.add(topPanel, BorderLayout.NORTH	);
		
		// Center zone
		addObject(swordPanel, swordImage, swordDes, swordButton, "Sword", noSword);
		addObject(shieldPanel, shieldImage, shieldDes, shieldButton, "Shield", noShield);
		addObject(potionPanel, potionImage, potionDes, potionButton, "Potion", noPotion);
		addObject(mapPanel, mapImage, mapDes, mapButton, "Map", noMap);
		
		mainPanel.add(shopPanel, BorderLayout.CENTER);
		
		// Bottom panel
		exitButton.addActionListener(e-> frame.dispose());
		bottomPanel.add(exitButton);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setModal(true);
		frame.add(mainPanel);
		
	}

	private void addObject(JPanel objectPanel, JLabel image, JLabel description, JButton button, String name, boolean noStock) {
		
		image.setAlignmentX(Component.CENTER_ALIGNMENT);
		description.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		if(noStock) button.setEnabled(false);
		
		button.addActionListener(e-> buyObject(button, name));
		
		objectPanel.setLayout(new BoxLayout(objectPanel, BoxLayout.Y_AXIS));
		objectPanel.add(image);
		objectPanel.add(description);
		objectPanel.add(button);
		
		shopPanel.add(objectPanel);
		
	}

	private void buyObject(JButton button, String name) {
		
		switch(name) {
		
			case "Sword":
				if(ch.getGold() >= 100) {
					ch.setAttack(ch.getAttack() + 3);
					mw.getAttributesLabel().setText(" Atk: " + ch.getAttack() + " | Def: " + ch.getDefense() + "  Vida: ");	
					ch.setGold(ch.getGold() - 100);
					mw.getGoldLabel().setText(" Gold: " + ch.getGold());
					button.setEnabled(false);
					noSword = true;
				}
				break;
				
			case "Shield":
				if(ch.getGold() >= 100) {
					ch.setDefense(ch.getDefense() + 1);
					mw.getAttributesLabel().setText(" Atk: " + ch.getAttack() + " | Def: " + ch.getDefense() + "  Vida: ");	
					ch.setGold(ch.getGold() - 100);
					mw.getGoldLabel().setText(" Gold: " + ch.getGold());
					button.setEnabled(false);
					noShield = true;
				}
					break;
					
			case "Potion":
				if(ch.getGold() >= 50) {
					ch.setCurrentHealth((int)ch.getMaxHealth());
					ch.setHealth(ch.getCurrentHealth());	
					ch.setGold(ch.getGold() - 50);
					mw.getGoldLabel().setText(" Gold: " + ch.getGold());
					button.setEnabled(false);
					noPotion = true;
				}
					break;
						
			case "Map":
				if(ch.getGold() >= 10) {
					Exploration.setExplorationNum(250);
					ch.setGold(ch.getGold() - 10);
					mw.getGoldLabel().setText(" Gold: " + ch.getGold());
					button.setEnabled(false);
					noMap = true;
				}
					break;
		
		}
		
	}

}
