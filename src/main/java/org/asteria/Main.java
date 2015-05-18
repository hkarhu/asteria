package org.asteria;


import com.beust.jcommander.JCommander;
import org.asteria.desktop.DesktopClient;
import org.asteria.server.Server;

/**
 *
 */
public class Main {

    public static final String GAME_NAME = "Asteria";
    public static final String GAME_DESCRIPTION = "Co-operative Multiplayer Space Warfare Simulator";
    public static final String GAME_FULL_DESCRIPTION = GAME_NAME + " is a " + GAME_DESCRIPTION.toLowerCase() + ".";
    public static final String START_COMMAND = GAME_NAME.toLowerCase();
    public static final int DEFAULT_PORT = 117711;

    public static void main(String[] args) {
        final CommandLineParameters commandLineParameters = new CommandLineParameters();
        final JCommander jCommander = new JCommander(commandLineParameters, args);

        // Print help if requested
        if (commandLineParameters.isHelpRequested()) {
            jCommander.setProgramName(START_COMMAND);
            System.out.println(GAME_FULL_DESCRIPTION);
            jCommander.usage();
        }
        else {
            if (commandLineParameters.isServer()) {
                // Start server
                Server.start(commandLineParameters.getPort());
            }
            else {
                // Start desktop client
                DesktopClient.start(commandLineParameters.getPort());
            }
        }
    }


}
