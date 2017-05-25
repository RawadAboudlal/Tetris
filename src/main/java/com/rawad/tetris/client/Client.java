package com.rawad.tetris.client;

import com.rawad.gamehelpers.client.states.StateChangeRequest;
import com.rawad.gamehelpers.fileparser.event.FileParseEventType;
import com.rawad.gamehelpers.fileparser.xml.EntityFileParser;
import com.rawad.gamehelpers.game.Game;
import com.rawad.jfxengine.client.AbstractClient;
import com.rawad.jfxengine.gui.Root;
import com.rawad.tetris.client.input.InputAction;
import com.rawad.tetris.client.states.GameState;
import com.rawad.tetris.client.states.MenuState;
import com.rawad.tetris.fileparser.event.FileParseEventHandler;
import com.rawad.tetris.loader.Loader;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;


/**
 * @author Rawad
 *
 */
public class Client extends AbstractClient {
	
	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;
	
	private static final int TARGET_FPS = 120;
	
	public static Image BACKGROUND;
	
	private SimpleStringProperty gameTitle;
	
	/**
	 * @see com.rawad.jfxengine.client.AbstractClient#initGui()
	 */
	@Override
	protected void initGui() {
		
		Root initialRoot = new Root(new StackPane(new Label("Loading...")), "");
		
		initialRoot.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		
		scene = new Scene(initialRoot);
		
		gameTitle = new SimpleStringProperty(game.getName());
		
		stage.titleProperty().bind(gameTitle);
		
		stage.setOnCloseRequest((e) -> {
			
			game.requestStop();
			
		});
		
		Platform.runLater(() -> {
			
			stage.setScene(scene);
			stage.show();
			
		});
		
	}
	
	/**
	 * @see com.rawad.jfxengine.client.AbstractClient#preInit(com.rawad.gamehelpers.game.Game)
	 */
	@Override
	public void preInit(Game game) {
		super.preInit(game);
		
		Loader loader = new Loader();
		loaders.put(loader);
		
		EntityFileParser entityFileParser = new EntityFileParser();
		fileParsers.put(entityFileParser);
		
		entityFileParser.getEventManager().registerListener(FileParseEventType.FILE_PARSE, new FileParseEventHandler());
		
		loader.loadEntityBlueprints(entityFileParser);
		
		BACKGROUND = loader.loadTexture("tetris-background");
		
		final Image logo = loader.loadTexture("tetris-logo");
		
		Platform.runLater(() -> {
			stage.getIcons().add(logo);
		});
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.Proxy#init()
	 */
	@Override
	public void init() {
		
		sm.addState(new MenuState());
		sm.addState(new GameState());
		
		sm.setState(StateChangeRequest.instance(MenuState.class));
		
		initInputBindings();
		
		update = true;
		
		renderingTimer.start();
		
	}
	
	private void initInputBindings() {
		
		inputBindings.setDefaultAction(InputAction.DEFAULT);
		
		inputBindings.put(InputAction.SHIFT_RIGHT, KeyCode.RIGHT);
		inputBindings.put(InputAction.SHIFT_LEFT, KeyCode.LEFT);
		
		inputBindings.put(InputAction.ROTATE_RIGHT, KeyCode.UP);
		inputBindings.put(InputAction.ROTATE_LEFT, KeyCode.Z);
		
		inputBindings.put(InputAction.HARD_DROP, KeyCode.SPACE);
		inputBindings.put(InputAction.SOFT_DROP, KeyCode.DOWN);
		
		inputBindings.put(InputAction.HOLD, KeyCode.C);
		
		inputBindings.put(InputAction.PAUSE, KeyCode.ESCAPE);
		
	}
	
	/**
	 * @see com.rawad.jfxengine.client.AbstractClient#terminate()
	 */
	@Override
	public void terminate() {
		super.terminate();
		
		Platform.runLater(() -> stage.close());
		
	}
	
	/**
	 * @see com.rawad.jfxengine.client.AbstractClient#getTargetFps()
	 */
	@Override
	protected int getTargetFps() {
		return TARGET_FPS;
	}
	
}
