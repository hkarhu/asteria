package org.asteria;


import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.beust.jcommander.JCommander;
import org.asteria.desktop.DesktopClient;
import org.asteria.server.AsteriaServer;
import org.flowutils.LogUtils;

/**
 * Main entrypoint for both the server and desktop client.
 */
public class Main {

    // TODO: Move constants to CommonSettings.

    public static final String GAME_NAME = "Asteria";
    public static final String GAME_DESCRIPTION = "Co-operative Multiplayer Space Warfare Simulator";
    public static final String GAME_FULL_DESCRIPTION = GAME_NAME + " is a " + GAME_DESCRIPTION.toLowerCase() + ".";
    public static final String START_COMMAND = GAME_NAME.toLowerCase();
    public static final int DEFAULT_PORT = 45714;

    public static final String ASSET_SOURCE_DIR = "asset-sources/";
    public static final String ASSET_TARGET_DIR = "assets/";
    public static final String TEXTURE_SUBDIR = "textures";
    public static final String SKINS_SUBDIR = "skins";
    public static final String TEXTURE_ATLAS_NAME = "TextureAtlas";

    private static final int MAX_TEXTURE_SIZE = 512;

    public static void main(String[] args) {
        // Parse command line parameters with JCommander, the parameters will be saved in a CommandLineParameters instance.
        final CommandLineParameters commandLineParameters = new CommandLineParameters();
        final JCommander jCommander = new JCommander(commandLineParameters, args);

        // Print help if requested
        if (commandLineParameters.isHelpRequested()) {
            jCommander.setProgramName(START_COMMAND);

            System.out.println(GAME_FULL_DESCRIPTION);
            jCommander.usage();

            return;
        }

        // Check parameters
        verifyParameterInRange("The port", commandLineParameters.getPort(), CommonSettings.MIN_PORT, CommonSettings.MAX_PORT);

        // Regenerate textures if requested
        if (commandLineParameters.isUpdateTextures()) {
            packTextures();
        }

        if (commandLineParameters.isServer()) {
            // Start server
            AsteriaServer.start(commandLineParameters.getPort());
        }
        else {
            // Start desktop client
            DesktopClient.start(commandLineParameters.getPort());
        }
    }

    /**
     * Ensures the parameter is in the range, if not, quits the program with error code.
     */
    private static void verifyParameterInRange(final String parameterName,
                                               final int value,
                                               final int min,
                                               final int max) {
        if (value < min || value > max) {
            System.err.print(parameterName +" should be in the range " + min + " to " + max + ", but it was " + value);
            System.exit(1);
        }
    }


    /**
     * This will load textures from the asset-sources/textures directory and merge them into bigger textures
     * to the assets/textures directory, along with an atlas file that contains the coordinates of the subtextures
     * inside the new combined texture.
     */
    private static void packTextures() {
        LogUtils.getLogger().info("Updating textures.");

        final TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = MAX_TEXTURE_SIZE;
        settings.maxHeight = MAX_TEXTURE_SIZE;
        settings.paddingX = 2;
        settings.paddingY = 2;
        TexturePacker.process(settings,
                              ASSET_SOURCE_DIR + TEXTURE_SUBDIR,
                              ASSET_TARGET_DIR + TEXTURE_SUBDIR,
                              TEXTURE_ATLAS_NAME);

        LogUtils.getLogger().debug("Textures updated.");
    }

}
