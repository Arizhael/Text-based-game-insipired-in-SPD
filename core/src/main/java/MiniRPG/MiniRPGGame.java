package MiniRPG;

import com.badlogic.gdx.Game;

public class MiniRPGGame extends Game {

    private GameState gameState;

    @Override
    public void create() {
        Character hero = new Character("Ástagos", 6, 2, 80);
        gameState = new GameState(hero);
        setScreen(new MainGameScreen(this, gameState));
    }

    public void showGameOver(boolean victory) {
        setScreen(new GameOverScreen(this, gameState, victory));
    }
}
