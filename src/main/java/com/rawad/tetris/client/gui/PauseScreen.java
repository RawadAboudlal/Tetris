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
public class PauseScreen extends GridPane implements Hideable {
	
	@FXML private Button resumeButton;
	@FXML private Button menuButton;
	
	public PauseScreen() {
		super();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		
		try {
			fxmlLoader.load(GuiLoader.streamLayoutFile(PauseScreen.class));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * @return the resumeButton
	 */
	public Button getResumeButton() {
		return resumeButton;
	}
	
	/**
	 * @return the menuButton
	 */
	public Button getMenuButton() {
		return menuButton;
	}
	
}
