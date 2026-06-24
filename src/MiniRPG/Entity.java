package MiniRPG;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JProgressBar;

public class Entity implements IAtacable {
	
	private String name;
	private int currentHealth,attack, defense;
	private double maxHealth;
	private boolean isAlive;
	private JProgressBar healthBar;
	
	public Entity(String name, int attack, int defense, double maxHealth) {
		super();
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.maxHealth = maxHealth;
		currentHealth = (int) maxHealth;
		isAlive = true;
		healthBar = new JProgressBar(0, (int) maxHealth);
		healthBar.setPreferredSize(new Dimension(150, 25));
		setHealth(currentHealth);
		
	}	
		public void setHealth(int health) {
			
			healthBar.setValue(health);
			healthBar.setForeground(Color.RED);
			healthBar.setStringPainted(true);
			healthBar.setString(currentHealth + "/" + (int) maxHealth);
		}
		

		@Override
		public void attack(IAtacable enemy) {
			enemy.beInjured(attack);
		}
		@Override
		public void beInjured(int cuantity) {
			
			if(isAlive) {
				int totalCuantity = cuantity-defense;
				if (totalCuantity <= 0) totalCuantity = 1;
				currentHealth -= totalCuantity;
				if (currentHealth <= 0) {
					isAlive = false;
					currentHealth = 0;
				}
			}
		}
	
		public int getCurrentHealth() {
			return currentHealth;
		}
		public void setCurrentHealth(int currentHealth) {
			this.currentHealth = currentHealth;
		}
		public int getAttack() {
			return attack;
		}
		public void setAttack(int attack) {
			this.attack = attack;
		}
		public int getDefense() {
			return defense;
		}
		public void setDefense(int defense) {
			this.defense = defense;
		}
		public double getMaxHealth() {
			return maxHealth;
		}
		public void setMaxHealth(double maxHealth) {
			this.maxHealth = maxHealth;
		}
		public boolean isAlive() {
			return isAlive;
		}
		public void setAlive(boolean isAlive) {
			this.isAlive = isAlive;
		}
		public String getName() {
			return name;
		}
		public JProgressBar getHealthBar() {
			return healthBar;
		}
		
}
