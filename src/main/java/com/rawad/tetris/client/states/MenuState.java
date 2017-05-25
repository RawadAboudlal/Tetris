package com.rawad.tetris.client.states;

import com.rawad.gamehelpers.client.states.State;
import com.rawad.gamehelpers.client.states.StateChangeRequest;
import com.rawad.jfxengine.gui.GuiRegister;
import com.rawad.jfxengine.gui.Root;
import com.rawad.tetris.client.Client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * @author Rawad
 *
 */
public class MenuState extends State {
	
	private Root root;
	
	@FXML private ImageView backgroundImageView;
	
	@FXML private Button playButton;
	@FXML private Button quitButton;
	
	/**
	 * @see com.rawad.gamehelpers.client.states.State#init()
	 */
	@Override
	public void init() {
		
		root = GuiRegister.loadGui(this);
		
		backgroundImageView.setImage(Client.BACKGROUND);
		
		root.setPrefSize(Client.SCREEN_WIDTH, Client.SCREEN_HEIGHT);
		root.setMinSize(Client.SCREEN_WIDTH, Client.SCREEN_HEIGHT);
		
		playButton.setOnAction((e) -> sm.requestStateChange(StateChangeRequest.instance(GameState.class)));
		quitButton.setOnAction((e) -> game.requestStop());
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.client.states.State#terminate()
	 */
	@Override
	public void terminate() {
	}
	
	/**
	 * @see com.rawad.gamehelpers.client.states.State#onActivate()
	 */
	@Override
	protected void onActivate() {
	}
	
	/**
	 * @see com.rawad.gamehelpers.client.states.State#onDeactivate()
	 */
	@Override
	protected void onDeactivate() {
	}
	
}
