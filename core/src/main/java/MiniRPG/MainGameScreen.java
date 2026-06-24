package MiniRPG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainGameScreen extends ScreenAdapter {

    private final MiniRPGGame game;
    private final GameState gameState;
    private final Character player;

    private Stage stage;
    private Skin skin;

    private Label nameLabel;
    private Label levelLabel;
    private Label expLabel;
    private Label goldLabel;
    private Label attributesLabel;
    private ProgressBar hpBar;
    private Label hpBarText;

    private TextArea logArea;
    private ScrollPane logScroll;

    private Image backgroundImage;
    private Image enemyImage;
    private Label enemyNameLabel;
    private ProgressBar enemyHpBar;
    private Label enemyHpText;
    private Table enemyTable;

    private Table bottomButtons;
    private TextButton exploreButton;
    private TextButton shopButton;
    private TextButton closeButton;
    private TextButton attackButton;
    private TextButton escapeButton;

    private Enemy currentEnemy;
    private Texture backgroundTexture;
    private Texture enemyTexture;

    public MainGameScreen(MiniRPGGame game, GameState gameState) {
        this.game = game;
        this.gameState = gameState;
        this.player = gameState.getPlayer();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        skin = SkinFactory.createBasicSkin();
        Gdx.input.setInputProcessor(stage);

        buildUi();
        appendLog("Welcome to the libGDX migration of your first game.");
        appendLog("Explore, shop, and fight like before.");
        refreshPlayerInfo();
        updateBottomButtons(false);
    }

    private void buildUi() {
        Table root = new Table();
        root.setFillParent(true);
        root.pad(10f);
        stage.addActor(root);

        Table top = buildTopTable();
        root.add(top).growX().top().padBottom(10).row();

        Table middle = new Table();
        middle.defaults().pad(6);

        backgroundTexture = new Texture(Gdx.files.internal("Images/sewers.jpg"));
        backgroundImage = new Image(backgroundTexture);
        middle.add(wrapPanel(backgroundImage, 320, 260)).width(340).growY();

        logArea = new TextArea("", skin);
        logArea.setDisabled(true);
        logArea.setPrefRows(16);
        logArea.setMaxLength(10_000);
        logArea.getStyle().disabledFontColor = Color.WHITE;
        logScroll = new ScrollPane(logArea, skin);
        logScroll.setFadeScrollBars(false);
        middle.add(logScroll).grow().minWidth(320);

        enemyTable = buildEnemyPanel();
        middle.add(enemyTable).width(250).growY();

        root.add(middle).grow().row();

        bottomButtons = new Table();
        bottomButtons.defaults().pad(4).minWidth(140);
        root.add(bottomButtons).growX().bottom();
    }

    private Table buildTopTable() {
        Table top = new Table();

        nameLabel = new Label("", skin);
        levelLabel = new Label("", skin);
        expLabel = new Label("", skin);
        goldLabel = new Label("", skin);
        attributesLabel = new Label("", skin);

        ProgressBar.ProgressBarStyle hpStyle = new ProgressBar.ProgressBarStyle();
        hpStyle.background = skin.newDrawable("white", Color.DARK_GRAY);
        hpStyle.knobBefore = skin.newDrawable("white", Color.RED);
        hpBar = new ProgressBar(0, (float) player.getMaxHealth(), 1f, false, hpStyle);
        hpBar.setAnimateDuration(0.15f);
        hpBarText = new Label("", skin);

        top.add(nameLabel).left().padRight(14);
        top.add(levelLabel).left().padRight(14);
        top.add(expLabel).left().padRight(14);
        top.add(goldLabel).left().padRight(14);
        top.add(attributesLabel).left().padRight(12);

        Table hpTable = new Table();
        hpTable.add(hpBar).width(180).padRight(6);
        hpTable.add(hpBarText);
        top.add(hpTable).left();

        return top;
    }

    private Table buildEnemyPanel() {
        Table table = new Table(skin);
        table.defaults().pad(6).growX();

        enemyNameLabel = new Label("No enemy", skin);
        enemyNameLabel.setAlignment(Align.center);

        ProgressBar.ProgressBarStyle enemyHpStyle = new ProgressBar.ProgressBarStyle();
        enemyHpStyle.background = skin.newDrawable("white", Color.DARK_GRAY);
        enemyHpStyle.knobBefore = skin.newDrawable("white", Color.FOREST);
        enemyHpBar = new ProgressBar(0, 100, 1f, false, enemyHpStyle);
        enemyHpText = new Label("", skin);
        enemyImage = new Image();

        table.add(enemyNameLabel).row();
        table.add(enemyImage).size(96).row();
        table.add(enemyHpBar).width(180).row();
        table.add(enemyHpText).row();
        return table;
    }

    private Container<Image> wrapPanel(Image image, float width, float height) {
        Container<Image> container = new Container<>(image);
        container.fill();
        container.size(width, height);
        return container;
    }

    private void updateBottomButtons(boolean exploring) {
        bottomButtons.clear();

        if (!exploring) {
            exploreButton = new TextButton("Explore", skin);
            shopButton = new TextButton("Shop", skin);
            closeButton = new TextButton("Close", skin);

            exploreButton.addListener(event -> {
                if (!exploreButton.isPressed()) return false;
                beginExploration();
                return true;
            });
            shopButton.addListener(event -> {
                if (!shopButton.isPressed()) return false;
                openShop();
                return true;
            });
            closeButton.addListener(event -> {
                if (!closeButton.isPressed()) return false;
                Gdx.app.exit();
                return true;
            });

            bottomButtons.add(exploreButton);
            bottomButtons.add(shopButton);
            bottomButtons.add(closeButton);
        } else {
            attackButton = new TextButton("Attack", skin);
            escapeButton = new TextButton("Escape", skin);
            escapeButton.setDisabled(currentEnemy != null && "Gu".equals(currentEnemy.getName()));

            attackButton.addListener(event -> {
                if (!attackButton.isPressed()) return false;
                attackEnemy();
                return true;
            });
            escapeButton.addListener(event -> {
                if (!escapeButton.isPressed() || escapeButton.isDisabled()) return false;
                appendLog("You escaped the encounter.");
                gameState.increaseExplorationNum();
                currentEnemy = null;
                updateEnemyPanel();
                updateBottomButtons(false);
                return true;
            });

            bottomButtons.add(attackButton);
            bottomButtons.add(escapeButton);
        }
    }

    private void beginExploration() {
        int encounterScore = gameState.rollEncounterDifficulty();
        currentEnemy = Enemy.generateEnemy(encounterScore);

        appendLog("A wild " + currentEnemy.getName() + " appears!");
        if ("Gu".equals(currentEnemy.getName())) {
            appendLog("Boss encounter! Escape disabled.");
        }

        updateEnemyPanel();
        updateBottomButtons(true);
    }

    private void attackEnemy() {
        if (currentEnemy == null) return;

        int beforeEnemyHp = currentEnemy.getCurrentHealth();
        player.attack(currentEnemy);
        int damageToEnemy = beforeEnemyHp - currentEnemy.getCurrentHealth();

        appendLog(player.getName() + " attacks with a power of " + player.getAttack() + ".");
        appendLog(currentEnemy.getName() + " takes " + damageToEnemy + " damage because of its defense.");

        updateEnemyPanel();

        if (!currentEnemy.isAlive()) {
            handleDefeatEnemy();
            return;
        }

        int beforeHeroHp = player.getCurrentHealth();
        currentEnemy.attack(player);
        int damageToHero = beforeHeroHp - player.getCurrentHealth();

        appendLog(currentEnemy.getName() + " attacks with a power of " + currentEnemy.getAttack() + ".");
        appendLog(player.getName() + " takes " + damageToHero + " damage because of its defense.");

        refreshPlayerInfo();

        if (!player.isAlive()) {
            game.showGameOver(false);
        }
    }

    private void handleDefeatEnemy() {
        appendLog(player.getName() + " defeated " + currentEnemy.getName() + ".");
        appendLog("You got " + currentEnemy.getGoldReward() + " gold.");
        appendLog("+ " + currentEnemy.getExpReward() + " Exp.");

        player.obtainExp(currentEnemy.getExpReward());
        player.setGold(player.getGold() + currentEnemy.getGoldReward());
        refreshPlayerInfo();

        boolean victory = "Gu".equals(currentEnemy.getName());
        currentEnemy = null;
        updateEnemyPanel();
        updateBottomButtons(false);

        if (victory) {
            game.showGameOver(true);
        }
    }

    private void openShop() {
        final Window window = new Window("Shop", skin);
        window.setModal(true);
        window.setMovable(false);
        window.pad(10);

        Table content = new Table();
        content.defaults().pad(8).growX();

        addShopRow(content, "Sword - 100g", "Images/sword.png", !gameState.isSwordBought(), () -> {
            if (player.getGold() >= 100 && !gameState.isSwordBought()) {
                player.setAttack(player.getAttack() + 3);
                player.setGold(player.getGold() - 100);
                gameState.setSwordBought(true);
                refreshPlayerInfo();
                appendLog("You bought a Sword. Attack +3.");
                window.remove();
            } else {
                appendLog("Not enough gold for Sword.");
            }
        });

        addShopRow(content, "Shield - 100g", "Images/Round Shield.png", !gameState.isShieldBought(), () -> {
            if (player.getGold() >= 100 && !gameState.isShieldBought()) {
                player.setDefense(player.getDefense() + 1);
                player.setGold(player.getGold() - 100);
                gameState.setShieldBought(true);
                refreshPlayerInfo();
                appendLog("You bought a Shield. Defense +1.");
                window.remove();
            } else {
                appendLog("Not enough gold for Shield.");
            }
        });

        addShopRow(content, "Health potion - 50g", "Images/Potion.png", !gameState.isPotionBought(), () -> {
            if (player.getGold() >= 50 && !gameState.isPotionBought()) {
                player.healToFull();
                player.setGold(player.getGold() - 50);
                gameState.setPotionBought(true);
                refreshPlayerInfo();
                appendLog("You bought a Potion. HP restored.");
                window.remove();
            } else {
                appendLog("Not enough gold for Potion.");
            }
        });

        addShopRow(content, "Map - 10g", "Images/Map.png", !gameState.isMapBought(), () -> {
            if (player.getGold() >= 10 && !gameState.isMapBought()) {
                gameState.skipExplorationAhead();
                player.setGold(player.getGold() - 10);
                gameState.setMapBought(true);
                refreshPlayerInfo();
                appendLog("You bought a Map. Boss path revealed.");
                window.remove();
            } else {
                appendLog("Not enough gold for Map.");
            }
        });

        TextButton close = new TextButton("Exit", skin);
        close.addListener(event -> {
            if (!close.isPressed()) return false;
            window.remove();
            return true;
        });

        window.add(content).row();
        window.add(close).padTop(10);
        window.pack();
        window.setPosition(
            (stage.getWidth() - window.getWidth()) / 2f,
            (stage.getHeight() - window.getHeight()) / 2f
        );

        stage.addActor(window);
    }

    private void addShopRow(Table content, String description, String imagePath, boolean canBuy, Runnable action) {
        Texture texture = new Texture(Gdx.files.internal(imagePath));
        Image image = new Image(texture);
        Label label = new Label(description, skin);
        TextButton buy = new TextButton("Buy", skin);
        buy.setDisabled(!canBuy);

        buy.addListener(event -> {
            if (!buy.isPressed() || buy.isDisabled()) return false;
            action.run();
            return true;
        });

        Table row = new Table();
        row.defaults().pad(4);
        row.add(image).size(48, 48);
        row.add(label).left().growX();
        row.add(buy).width(90);

        content.add(row).growX().row();
    }

    private void updateEnemyPanel() {
        if (enemyTexture != null) {
            enemyTexture.dispose();
            enemyTexture = null;
        }

        if (currentEnemy == null) {
            enemyNameLabel.setText("No enemy");
            enemyNameLabel.setColor(Color.WHITE);
            enemyHpBar.setRange(0, 1);
            enemyHpBar.setValue(0);
            enemyHpText.setText("");
            enemyImage.setDrawable(null);
            return;
        }

        enemyNameLabel.setText(currentEnemy.getName());
        enemyNameLabel.setColor(currentEnemy.getNameColor());

        enemyTexture = new Texture(Gdx.files.internal(currentEnemy.getImagePath()));
        enemyImage.setDrawable(new TextureRegionDrawable(enemyTexture));

        enemyHpBar.setRange(0, (float) currentEnemy.getMaxHealth());
        enemyHpBar.setValue(currentEnemy.getCurrentHealth());
        enemyHpText.setText(currentEnemy.getCurrentHealth() + "/" + (int) currentEnemy.getMaxHealth());
    }

    private void refreshPlayerInfo() {
        nameLabel.setText(player.getName());
        levelLabel.setText("Lvl: " + player.getLevel());
        expLabel.setText("Exp: " + player.getExp() + "/" + player.getNeededExp());
        goldLabel.setText("Gold: " + player.getGold());
        attributesLabel.setText("Atk: " + player.getAttack() + " | Def: " + player.getDefense() + " | HP:");
        hpBar.setRange(0, (float) player.getMaxHealth());
        hpBar.setValue(player.getCurrentHealth());
        hpBarText.setText(player.getCurrentHealth() + "/" + (int) player.getMaxHealth());
    }

    private void appendLog(String text) {
        String current = logArea.getText();
        if (current == null || current.isEmpty()) {
            logArea.setText(text);
        } else {
            logArea.setText(current + "\n\n" + text);
        }
        logScroll.layout();
        logScroll.setScrollPercentY(1f);
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
        if (enemyTexture != null) enemyTexture.dispose();
        if (backgroundTexture != null) backgroundTexture.dispose();
        if (skin != null) skin.dispose();
        if (stage != null) stage.dispose();
    }
}
