package MiniRPG;

public class Character extends Entity {

    private int level;
    private int gold;
    private int exp;
    private int neededExp;

    public Character(String name, int attack, int defense, double maxHealth) {
        super(name, attack, defense, maxHealth);
        level = 1;
        gold = 0;
        exp = 0;
        neededExp = 10;
    }

    public void levelUp() {
        level++;
        setAttack(getAttack() + 2);
        setDefense(getDefense() + 1);
        setMaxHealth(getMaxHealth() * 1.1);
        healToFull();

        exp -= neededExp;
        neededExp += (neededExp + 5);
    }

    public void obtainExp(int quantity) {
        exp += quantity;
        while (exp >= neededExp) {
            levelUp();
        }
    }

    public int getLevel() { return level; }
    public int getGold() { return gold; }
    public void setGold(int gold) { this.gold = Math.max(0, gold); }
    public int getExp() { return exp; }
    public int getNeededExp() { return neededExp; }
}
