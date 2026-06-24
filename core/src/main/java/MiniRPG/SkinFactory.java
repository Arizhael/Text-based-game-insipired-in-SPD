package MiniRPG;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public final class SkinFactory {

    private SkinFactory() {}

    public static Skin createBasicSkin() {
        Skin skin = new Skin();
        BitmapFont font = new BitmapFont();
        skin.add("default-font", font);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        Texture white = new Texture(pixmap);
        pixmap.dispose();
        skin.add("white", white);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        skin.add("default", labelStyle);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.WHITE;
        buttonStyle.up = tinted(skin, new Color(0.18f, 0.20f, 0.24f, 1f));
        buttonStyle.over = tinted(skin, new Color(0.26f, 0.30f, 0.34f, 1f));
        buttonStyle.down = tinted(skin, new Color(0.12f, 0.14f, 0.16f, 1f));
        buttonStyle.disabled = tinted(skin, new Color(0.10f, 0.10f, 0.10f, 0.65f));
        buttonStyle.disabledFontColor = Color.LIGHT_GRAY;
        skin.add("default", buttonStyle);

        TextArea.TextAreaStyle textAreaStyle = new TextArea.TextAreaStyle();
        textAreaStyle.font = font;
        textAreaStyle.fontColor = Color.WHITE;
        textAreaStyle.background = tinted(skin, new Color(0.08f, 0.08f, 0.08f, 0.92f));
        textAreaStyle.cursor = tinted(skin, Color.WHITE);
        textAreaStyle.selection = tinted(skin, new Color(0.25f, 0.35f, 0.8f, 0.75f));
        skin.add("default", textAreaStyle);

        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPaneStyle.background = tinted(skin, new Color(0.06f, 0.06f, 0.06f, 0.95f));
        scrollPaneStyle.vScroll = tinted(skin, new Color(0.18f, 0.18f, 0.18f, 1f));
        scrollPaneStyle.vScrollKnob = tinted(skin, new Color(0.35f, 0.35f, 0.35f, 1f));
        skin.add("default", scrollPaneStyle);

        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = font;
        windowStyle.titleFontColor = Color.WHITE;
        windowStyle.background = tinted(skin, new Color(0.10f, 0.10f, 0.10f, 0.98f));
        skin.add("default", windowStyle);

        return skin;
    }

    private static TextureRegionDrawable tinted(Skin skin, Color color) {
        return skin.newDrawable("white", color);
    }
}
