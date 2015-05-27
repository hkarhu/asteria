package org.asteria.client.screens;

import static org.flowutils.Check.notNull;

import org.asteria.client.AsteriaClient;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import org.flowutils.time.RealTime;
/**
 * Provides a 2D scenegraph to work with
 */
public abstract class ScreenBase extends ScreenAdapter {

    public static final int SMALL_GAP = 8;
    public static final int GAP = 10;
    public static final int LARGE_GAP = 24;

	protected BitmapFont font;

    private Stage stage;
    private Skin skin;
    private TextureAtlas textureAtlas;

    private Table rootUi;
    private AsteriaClient asteriaClient;
    
    private RealTime realTime;

    /**
     * Called once to allow creating the scenegraph.
     * @param skin loaded default skin.
     * @param textureAtlas loaded textures.
     */
    public final void create(AsteriaClient asteriaClient, Skin skin, TextureAtlas textureAtlas) {
        // Check and store provided parameters
        notNull(asteriaClient, "asteriaClient");
        notNull(skin, "skin");
        notNull(textureAtlas, "textureAtlas");
        this.asteriaClient = asteriaClient;
        this.skin = skin;
        this.textureAtlas = textureAtlas;

        this.font = new BitmapFont(); //Default font used for now
        
        this.realTime = new RealTime();
        
        // Create 2D Scenegraph
        stage = new Stage();

        // Create UI table that fills screen, add it to scenegraph
        rootUi = new Table(skin);
        rootUi.setFillParent(true);
        stage.addActor(rootUi);

        // Let overriding classes do things
        onCreate(skin, textureAtlas, stage, rootUi);
    }

    @Override final public void render(float delta) {
        // Clear screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update scenegraph (e.g. animations)
        stage.act(Gdx.graphics.getDeltaTime());

        // Render scenegraph
        stage.draw();

        onRender(realTime);
        realTime.nextStep();
    }

    @Override final public void show() {
        Gdx.input.setInputProcessor(stage);
        onVisibilityChanged(true);
    }

    @Override final public void hide() {
        Gdx.input.setInputProcessor(null);
        onVisibilityChanged(false);
    }

    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        onResize(width, height);
    }

    @Override final public void dispose() {
        stage.dispose();
        onDispose();
    }

    /**
     * @return the game client class, can be used e.g. for switching the current screen.
     */
    public AsteriaClient getAsteriaClient() {
        return asteriaClient;
    }

    /**
     * @return UI skin, available after the screen is created the first time.
     */
    public final Skin getSkin() {
        return skin;
    }

    /**
     * @return texture atlas with textures from the asset directory
     */
    public final TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    /**
     * @return UI table that fills screen, can be used as host for other UI components.
     */
    public final Table getRootUi() {
        return rootUi;
    }

    /**
     * @return 2D scenegraph for this screen.
     */
    public final Stage getStage() {
        return stage;
    }

    /**
     * @param skin UI skin to use (provides the look of the UI components)
     * @param textureAtlas texture atlas with custom images.
     * @param stage 2D scenegraph, contains the UI, can also contain other 2D actors.
     * @param rootUi root UI table, fills the screen.  Other UI components can be added to this (or directly to stage).
     */
    protected abstract void onCreate(Skin skin, TextureAtlas textureAtlas, Stage stage, Table rootUi);

    protected void onRender(RealTime realTime) {}
    protected void onVisibilityChanged(boolean screenVisible) {}
    protected void onResize(int width, int height) {}
    protected void onDispose() {}
}
