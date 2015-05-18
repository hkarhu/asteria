package org.asteria.server;

import org.asteria.Main;
import org.flowutils.LogUtils;

/**
 *
 */
public class Server {

    public static void start(int port) {
        LogUtils.getLogger().info(Main.GAME_NAME + " server starting on port " + port + ".");

    }

}
