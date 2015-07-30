package org.asteria;

import com.esotericsoftware.minlog.Log;
import org.messageduct.common.DefaultNetworkConfig;
import org.messageduct.common.NetworkConfig;
import org.messageduct.serverinfo.DefaultServerInfo;
import org.messageduct.serverinfo.ServerInfo;
import org.messageduct.utils.encryption.AsymmetricEncryption;
import org.messageduct.utils.encryption.RsaEncryption;

import java.security.KeyPair;

/**
 *
 */
public final class CommonSettings {

    // The port to use for the server.
    public static final int PORT = Main.DEFAULT_PORT;

    // The address that the server should tell clients that it is running on.
    public static final String SERVER_ADDRESS = "localhost";

    // Network configuration.  Same for client and server
    public static final NetworkConfig NETWORK_CONFIG = createNetworkConfig();

    // Information about the server, sent to clients.  LATER: Move to server / create from server configuration settings.
    public static final ServerInfo SERVER_INFO = new DefaultServerInfo(SERVER_ADDRESS, PORT);

    public static final int MIN_PORT = 1024;
    public static final int MAX_PORT = 65535;

    private static NetworkConfig createNetworkConfig() {
        final NetworkConfig networkConfig = new DefaultNetworkConfig();
        networkConfig.setPort(PORT);
        networkConfig.setCompressionEnabled(false); // No compression for now
        networkConfig.setEncryptionEnabled(false); // Encryption still needs some bugfixing
        networkConfig.setMessageLoggingEnabled(true); // Log the network messages to console

        // Turn on additional logging in serialization layer
        Log.TRACE = false;
        Log.DEBUG = true;
        Log.INFO = true;
        Log.WARN = true;
        Log.ERROR = true;

        // LATER: If we are on the server, load public and private key from somewhere. For now create them on the fly
        networkConfig.setServerKeys(new RsaEncryption().createNewPublicPrivateKey());

        return networkConfig;
    }

    private CommonSettings() {
    }
}
