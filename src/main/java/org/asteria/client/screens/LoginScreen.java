package org.asteria.client.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Screen for logging into a game server
 */
public final class LoginScreen extends ScreenBase {

    @Override protected void onCreate(Skin skin, TextureAtlas textureAtlas, Stage stage, Table rootUi) {

        // Create UI widgets for entering username and password.
        // See https://github.com/libgdx/libgdx/wiki/Scene2d.ui for more documentation

        // Username field
        final TextField usernameField = new TextField("", skin);
        usernameField.selectAll();
        stage.setKeyboardFocus(usernameField);

        // Password field
        final TextField passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        // Login button
        final TextButton loginButton = new TextButton("    Login    ", skin);
        loginButton.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                loginToServer(usernameField.getText(), passwordField.getText());
            }
        });

        // Also activate login on enter press
        InputListener enterListener = new InputListener() {
            @Override public boolean keyTyped(InputEvent event, char character) {
                if (event.getKeyCode() == Input.Keys.ENTER) {
                    loginToServer(usernameField.getText(), passwordField.getText());
                    return true;
                }
                else {
                    return false;
                }

            }
        };
        usernameField.addListener(enterListener);
        passwordField.addListener(enterListener);

        // Add the UI components together and layout the UI
        // See https://github.com/libgdx/libgdx/wiki/Table for documentation on laying out UI components with Table.
        rootUi.row().padBottom(GAP);
        rootUi.add(new Label("Username", skin)).padRight(GAP);
        rootUi.add(usernameField);

        rootUi.row().padBottom(GAP);
        rootUi.add(new Label("Password", skin)).padRight(GAP);
        rootUi.add(passwordField);

        rootUi.row();
        rootUi.add(loginButton).colspan(2); // Span two columns with the button, as we used two columns for the fields above

    }

    private void loginToServer(String username, String password) {

        // IMPLEMENT: Call login with network code

        System.out.println("Logging in with username '" + username + "' and pass '" + password + "'");
    }


}
