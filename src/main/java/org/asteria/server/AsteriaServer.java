package org.asteria.server;

import org.asteria.CommonSettings;
import org.asteria.Main;
import org.flowutils.LogUtils;
import org.flowutils.service.ServiceProviderBase;
import org.messageduct.account.DefaultAccountService;
import org.messageduct.account.persistence.StorageAccountPersistence;
import org.messageduct.server.ServerNetworking;
import org.messageduct.server.netty.NettyServerNetworking;

import java.io.File;

/**
 * Server
 */
public class AsteriaServer extends ServiceProviderBase {

    private static final File ACCOUNT_STORAGE_FILE = new File("./asteria_account_database");

    private final DefaultAccountService accountService;
    private final ServerNetworking serverNetworking;

    public static void start(int port) {
        LogUtils.getLogger().info(Main.GAME_NAME + " server starting on port " + port + ".");

        AsteriaServer server = new AsteriaServer();
        server.init();
        LogUtils.getLogger().info(Main.GAME_NAME + " Initialized");
    }

    public AsteriaServer() {

        // Listener that will be told about client actions and should forward them to the rest of the system
        final ClientListener clientListener = new ClientListener();

        // Account service that keeps track of registered users and their passwords
        accountService = addService(new DefaultAccountService(new StorageAccountPersistence(ACCOUNT_STORAGE_FILE)));

        // Server side networking service
        serverNetworking = addService(new NettyServerNetworking(CommonSettings.NETWORK_CONFIG,
                                                                accountService,
                                                                CommonSettings.SERVER_INFO,
                                                                clientListener));
    }

}
