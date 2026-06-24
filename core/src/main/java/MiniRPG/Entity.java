package MiniRPG;

public class Entity implements IAtacable {

    private String name;
    private int currentHealth;
    private int attack;
    private int defense;
    private double maxHealth;
    private boolean alive;

    public Entity(String name, int attack, int defense, double maxHealth) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;
        this.currentHealth = (int) maxHealth;
        this.alive = true;
    }

    @Override
    public void attack(IAtacable enemy) {
        enemy.beInjured(attack);
    }

    @Override
    public void beInjured(int quantity) {
        if (!alive) return;

        int totalDamage = quantity - defense;
        if (totalDamage <= 0) totalDamage = 1;

        currentHealth -= totalDamage;
        if (currentHealth <= 0) {
            currentHealth = 0;
            alive = false;
        }
    }

    public void healToFull() {
        currentHealth = (int) maxHealth;
        alive = true;
    }

    public String getName() { return name; }
    public int getCurrentHealth() { return currentHealth; }
    public void setCurrentHealth(int currentHealth) { this.currentHealth = Math.max(0, currentHealth); }
    public int getAttack() { return attack; }
    public void setAttack(int attack) { this.attack = attack; }
    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }
    public double getMaxHealth() { return maxHealth; }
    public void setMaxHealth(double maxHealth) { this.maxHealth = maxHealth; }
    public boolean isAlive() { return alive; }
    public void setAlive(boolean alive) { this.alive = alive; }
}
