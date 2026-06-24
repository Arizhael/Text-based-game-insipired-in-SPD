package MiniRPG;

public class GameState {

    private final Character player;
    private int explorationNum = 0;
    private boolean swordBought = false;
    private boolean shieldBought = false;
    private boolean potionBought = false;
    private boolean mapBought = false;

    public GameState(Character player) {
        this.player = player;
    }

    public Character getPlayer() {
        return player;
    }

    public int rollEncounterDifficulty() {
        int randomNum = (int) (Math.random() * 100) + explorationNum;
        explorationNum++;
        return randomNum;
    }

    public void skipExplorationAhead() {
        explorationNum = 250;
    }

    public void increaseExplorationNum() {
        explorationNum++;
    }

    public int getExplorationNum() { return explorationNum; }

    public boolean isSwordBought() { return swordBought; }
    public void setSwordBought(boolean swordBought) { this.swordBought = swordBought; }

    public boolean isShieldBought() { return shieldBought; }
    public void setShieldBought(boolean shieldBought) { this.shieldBought = shieldBought; }

    public boolean isPotionBought() { return potionBought; }
    public void setPotionBought(boolean potionBought) { this.potionBought = potionBought; }

    public boolean isMapBought() { return mapBought; }
    public void setMapBought(boolean mapBought) { this.mapBought = mapBought; }
}
