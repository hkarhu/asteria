package org.asteria.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.asteria.Main;
import org.asteria.client.screens.GameScreen;
import org.asteria.client.screens.LoginScreen;
import org.asteria.client.screens.ScreenBase;

/**
 * Main client class, directs screen changes and handles client initialization
 */
public final class AsteriaClient extends Game {

    private static final String SKIN_PATH = Main.ASSET_TARGET_DIR + Main.SKINS_SUBDIR + "/";
    private static final String DEFAULT_SKIN_PATH = SKIN_PATH + "uiskin.json";
    private static final String DEFAULT_SKIN_ATLAS_PATH = SKIN_PATH + "uiskin.atlas";
    private static final String DEFAULT_TEXTURE_ATLAS_PATH = Main.ASSET_TARGET_DIR + Main.TEXTURE_SUBDIR + "/" + Main.TEXTURE_ATLAS_NAME + ".atlas";

    private int port;

    private Skin skin;

    private LoginScreen loginScreen;
    private GameScreen gameScreen;
    private TextureAtlas textureAtlas;

    public AsteriaClient(int port) {
        setPort(port);
    }

    @Override public void create() {

        // Load texture atlas
        textureAtlas = new TextureAtlas(DEFAULT_TEXTURE_ATLAS_PATH);

        // Load skin
        skin = new Skin(Gdx.files.internal(DEFAULT_SKIN_PATH), new TextureAtlas(DEFAULT_SKIN_ATLAS_PATH));

        // Create the screens that we have in the game
        loginScreen = addScreen(new LoginScreen());
        gameScreen = addScreen(new GameScreen());

        // Switch to login screen
        setScreen(loginScreen);
    }

    public LoginScreen getLoginScreen() {
        return loginScreen;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private <T extends ScreenBase> T addScreen(final T screen) {
        screen.create(this, skin, textureAtlas);
        return screen;
    }


}
