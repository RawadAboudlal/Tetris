package com.rawad.tetris.client.gui;

import com.rawad.jfxengine.gui.Hideable;
import com.rawad.jfxengine.loader.GuiLoader;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * @author Rawad
 *
 */
public class GameOverScreen extends GridPane implements Hideable {
	
	@FXML private Button playAgainButton;
	@FXML private Button exitButton;
	
	public GameOverScreen() {
		super();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		
		try {
			fxmlLoader.load(GuiLoader.streamLayoutFile(GameOverScreen.class));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * @return the playAgainButton
	 */
	public Button getPlayAgainButton() {
		return playAgainButton;
	}
	
	/**
	 * @return the exitButton
	 */
	public Button getExitButton() {
		return exitButton;
	}
	
}
