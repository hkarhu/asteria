package org.asteria.server;

import org.messageduct.server.MessageListenerAdapter;
import org.messageduct.server.UserSession;

/**
 * Listens to clients and handles messages sent from them.
 */
// IMPLEMENT: Actual functionality
public final class ClientListener extends MessageListenerAdapter {

    @Override public void messageReceived(UserSession session, Object message) {
        System.out.println("ClientListener.messageReceived");
        System.out.println("message = " + message);
    }

    @Override public void userCreated(UserSession session) {
        System.out.println("ClientListener.userCreated");
        System.out.println("session.getUserName() = " + session.getUserName());
    }

    @Override public void userDeleted(UserSession session) {
        System.out.println("ClientListener.userDeleted");
        System.out.println("session.getUserName() = " + session.getUserName());
    }

    @Override public void userConnected(UserSession session) {
        System.out.println("ClientListener.userConnected");
        System.out.println("session.getUserName() = " + session.getUserName());
    }

    @Override public void userDisconnected(UserSession session) {
        System.out.println("ClientListener.userDisconnected");
        System.out.println("session.getUserName() = " + session.getUserName());
    }
}
