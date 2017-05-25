package com.rawad.tetris.client;

import com.rawad.gamehelpers.game.GameManager;
import com.rawad.tetris.game.Tetris;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Rawad
 *
 */
public class TetrisStart extends Application {
	
	private static final Tetris game = new Tetris();
	
	private static final Client client = new Client();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		game.getProxies().put(client);
		
		Application.launch(args);
		
	}
	
	/**
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		client.setStage(primaryStage);
		
		GameManager.launchGame(game);
		
	}
	
}
