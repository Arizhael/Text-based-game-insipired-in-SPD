package MiniRPG.lwjgl3;

import MiniRPG.MiniRPGGame;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Lwjgl3Launcher {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Mini RPG");
        config.setWindowedMode(1000, 700);
        config.useVsync(true);
        config.setForegroundFPS(60);
        new Lwjgl3Application(new MiniRPGGame(), config);
    }
}
