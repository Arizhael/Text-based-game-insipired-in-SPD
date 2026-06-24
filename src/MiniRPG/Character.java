package MiniRPG;

public class Character extends Entity {
	
	private int level, gold, exp, neededExp;

	public Character(String name, int attack, int defense, double maxHealth) {
		super(name, attack, defense, maxHealth);
		level = 1;
		gold = 0;
		exp = 0;
		neededExp = 10;
	}

	public int getLevel() {
		return level;
	}
	
	public void levelUp() {
		level ++;
		setAttack(getAttack() + 2);
		setDefense(getDefense() + 1);
		setMaxHealth(getMaxHealth() * 1.1);
		getHealthBar().setMaximum((int)getMaxHealth());
		setCurrentHealth((int)getMaxHealth());
		setHealth((int)getMaxHealth());
		neededExp += (neededExp + 5);
	}

	public int getExp() {
		return exp;
	}
	
	public void obtainExp(int cuantity) {
		exp +=cuantity;
		while (exp >= neededExp) levelUp();
	}

	public int getNeededExp() {
		return neededExp;
	}
	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	
	

}
