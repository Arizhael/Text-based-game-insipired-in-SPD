package MiniRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameOverScreen extends ScreenAdapter {

    private final MiniRPGGame game;
    private final GameState gameState;
    private final boolean victory;

    private Stage stage;
    private Skin skin;
    private Texture texture;

    public GameOverScreen(MiniRPGGame game, GameState gameState, boolean victory) {
        this.game = game;
        this.gameState = gameState;
        this.victory = victory;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        skin = SkinFactory.createBasicSkin();
        Gdx.input.setInputProcessor(stage);

        texture = new Texture(Gdx.files.internal(victory ? "Images/Victory.jpg" : "Images/GameOver.png"));

        Character ch = gameState.getPlayer();

        Table root = new Table();
        root.setFillParent(true);
        root.pad(12);
        stage.addActor(root);

        root.add(new Image(texture)).size(520, 260).padBottom(10).row();

        String message;
        if (victory) {
            message = "You won!\n" +
                    "wow.\n" +
                    ch.getName() + "\n" +
                    "Level: " + ch.getLevel() + "\n" +
                    "Gold: " + ch.getGold();
        } else {
            message = "GAME OVER\n" +
                    "It's not that hard.\n" +
                    ch.getName() + "\n" +
                    "Level: " + ch.getLevel() + "\n" +
                    "Gold: " + ch.getGold();
        }

        Label label = new Label(message, skin);
        root.add(label).padBottom(10).row();

        TextButton exit = new TextButton("End Game...", skin);
        exit.addListener(event -> {
            if (!exit.isPressed()) return false;
            Gdx.app.exit();
            return true;
        });
        root.add(exit).width(180);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.getViewport().apply(true);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        if (texture != null) texture.dispose();
        if (skin != null) skin.dispose();
        if (stage != null) stage.dispose();
    }
}
