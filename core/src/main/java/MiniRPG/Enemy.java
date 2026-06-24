package MiniRPG;

import com.badlogic.gdx.graphics.Color;

public class Enemy extends Entity {

    private int goldReward;
    private int expReward;
    private String difficulty;
    private String imagePath;
    private Color nameColor;

    private static final String[] EASY_NAMES = {"Rat", "Snake", "Ghost"};
    private static final String[] MEDIUM_NAMES = {"Gnoll", "IceElemental", "ElectricElemental"};
    private static final String[] HARD_NAMES = {"Scorpion", "FireElemental", "CaoticElemental"};

    public Enemy(String name, int attack, int defense, double maxHealth, String difficulty) {
        super(name, attack, defense, maxHealth);
        this.difficulty = difficulty;
        this.imagePath = "Enemies/" + name + ".png";

        switch (difficulty) {
            case "easy":
                expReward = randomBetween(1, 2);
                goldReward = randomBetween(1, 5);
                nameColor = Color.GREEN;
                break;
            case "medium":
                expReward = randomBetween(2, 7);
                goldReward = randomBetween(1, 20);
                nameColor = Color.ORANGE;
                break;
            case "hard":
                expReward = randomBetween(5, 20);
                goldReward = randomBetween(1, 50);
                nameColor = Color.RED;
                break;
            default:
                expReward = 500;
                goldReward = 1000;
                nameColor = Color.BLACK;
                break;
        }
    }

    public static Enemy generateEnemy(int encounterScore) {
        int enemyIndex = randomBetween(0, 2);
        int healthRoll = randomBetween(0, 29);
        int attackRoll = randomBetween(0, 4);
        int defenseRoll = randomBetween(0, 1);

        if (encounterScore < 80) {
            return new Enemy(EASY_NAMES[enemyIndex], attackRoll + 1, defenseRoll, healthRoll + 15, "easy");
        } else if (encounterScore < 140) {
            return new Enemy(MEDIUM_NAMES[enemyIndex], attackRoll + 4, defenseRoll + 2, healthRoll + 30, "medium");
        } else if (encounterScore < 200) {
            return new Enemy(HARD_NAMES[enemyIndex], attackRoll + 8, defenseRoll + 5, healthRoll + 80, "hard");
        } else {
            return new Enemy("Gu", attackRoll + 15, defenseRoll + 10, healthRoll + 150, "boss");
        }
    }

    private static int randomBetween(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }

    public int getGoldReward() { return goldReward; }
    public int getExpReward() { return expReward; }
    public String getDifficulty() { return difficulty; }
    public String getImagePath() { return imagePath; }
    public Color getNameColor() { return nameColor; }
}
