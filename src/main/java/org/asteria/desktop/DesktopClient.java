package org.asteria.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.asteria.Main;
import org.asteria.client.AsteriaClient;
import org.flowutils.LogUtils;

/**
 * Launches the desktop client
 */
public class DesktopClient {

    public static void start(int port) {
        LogUtils.getLogger().info(Main.GAME_NAME + " desktop client starting...");

        final LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title = Main.GAME_NAME;
        configuration.width = 800;
        configuration.height = 600;

        new LwjglApplication(new AsteriaClient(port), configuration);
    }


}
