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
public final class RegisterScreen extends ScreenBase {

    @Override protected void onCreate(Skin skin, TextureAtlas textureAtlas, Stage stage, Table rootUi) {
    	
        // Username field
        final TextField usernameField = new TextField("", skin);
        usernameField.selectAll();
        stage.setKeyboardFocus(usernameField);

        // e-mail field
        final TextField emailField = new TextField("", skin);
        emailField.selectAll();
        
        // Password field
        final TextField passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        // Reg button
        final TextButton registerButton = new TextButton("Register", skin);
        registerButton.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                registerUser(usernameField.getText(), passwordField.getText(), emailField.getText());
            }
        });

        // Also activate on enter press
        InputListener enterListener = new InputListener() {
            @Override public boolean keyTyped(InputEvent event, char character) {
                if (event.getKeyCode() == Input.Keys.ENTER) {
                    return registerUser(usernameField.getText(), passwordField.getText(), emailField.getText());
                } else {
                    return false;
                }

            }
        };
        
        usernameField.addListener(enterListener);
        passwordField.addListener(enterListener);
        emailField.addListener(enterListener);
        
        // Add the UI components together and layout the UI
        // See https://github.com/libgdx/libgdx/wiki/Table for documentation on laying out UI components with Table.

        rootUi.right();
        rootUi.defaults().pad(GAP).space(SMALL_GAP);
        rootUi.add(new Label("Username -", skin)).right();
        rootUi.add(usernameField).padRight(LARGE_GAP);
        
        rootUi.row();
        
        rootUi.add(new Label("e-Mail -", skin)).right();
        rootUi.add(emailField).padRight(LARGE_GAP);
        
        rootUi.row();
        
        rootUi.add(new Label("Password -", skin)).right();
        rootUi.add(passwordField).padRight(LARGE_GAP);
        rootUi.row();
        rootUi.add(registerButton).colspan(2).width(100).right(); // Span two columns with the button, as we used two columns for the fields above

    }

	private boolean registerUser(String username, String password, String email) {
	    // IMPLEMENT: Call register with network code
	    System.out.println("Registering '" + username + "' pass: '" + password + "'" + "' email: '" + email + "'");
		return false;
	}


}
