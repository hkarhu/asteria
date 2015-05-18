package org.asteria;

import com.beust.jcommander.Parameter;

/**
 *
 */
public class CommandLineParameters {

    @Parameter(names="--server", description = "Run " + Main.GAME_NAME + " as a server.")
    private boolean server = false;

    @Parameter(names={"-p", "--port"}, description = "If running as server, the port to listen to, if running as client, the default port to use.")
    private int port = Main.DEFAULT_PORT;

    @Parameter(names = {"-h", "--help"}, description = "Print this help message.", help = true)
    private boolean help = false;

    public boolean isServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public boolean isHelpRequested() {
        return help;
    }
}
