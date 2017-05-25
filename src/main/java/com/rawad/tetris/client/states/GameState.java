package com.rawad.tetris.client.states;

import java.util.TimerTask;

import com.rawad.gamehelpers.client.states.State;
import com.rawad.gamehelpers.client.states.StateChangeRequest;
import com.rawad.gamehelpers.game.GameManager;
import com.rawad.gamehelpers.game.event.Event;
import com.rawad.gamehelpers.game.event.Listener;
import com.rawad.jfxengine.gui.GuiRegister;
import com.rawad.jfxengine.gui.Root;
import com.rawad.tetris.client.Client;
import com.rawad.tetris.client.gui.CountDownScreen;
import com.rawad.tetris.client.gui.FlashingImageView;
import com.rawad.tetris.client.gui.GameOverScreen;
import com.rawad.tetris.client.gui.PauseScreen;
import com.rawad.tetris.client.input.InputAction;
import com.rawad.tetris.client.renderengine.GameRender;
import com.rawad.tetris.game.FallingSystem;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.LockingSystem;
import com.rawad.tetris.game.event.EventType;
import com.rawad.tetris.game.event.GenerateTetrominoesEvent;
import com.rawad.tetris.game.event.HardDropRequestEvent;
import com.rawad.tetris.game.event.HoldEvent;
import com.rawad.tetris.game.event.LineClearStreakUpdateEvent;
import com.rawad.tetris.game.event.MoveRequestEvent;
import com.rawad.tetris.game.event.RotateLeftEvent;
import com.rawad.tetris.game.event.RotateRightEvent;
import com.rawad.tetris.game.event.listeners.ScoreUpdateHandler;
import com.rawad.tetris.loader.Loader;
import com.rawad.tetris.util.BoundingBoxCalculator;
import com.rawad.tetris.util.GameListeners;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * @author Rawad
 *
 */
public class GameState extends State implements Listener {
	
	private static final int GAME_BOARD_WIDTH = 10;
	/**
	 * Note that there are two extra rows on top (indices -1 and -2) that the tetrominoes spawn in and that cause a losing
	 * condition when reached.
	 */
	private static final int GAME_BOARD_HEIGHT = 20;
	
	/** Generate a total of 4 tetrominoes to at the beginning of each game (1 active, 3 in next slots). */
	private static final int COUNT_TETROMINOES_TO_GEN = 4;
	
	/** Initial speed of the game as a measure of delay between actual game ticks (i.e. movement of tetromino). */
	private static final int INITIAL_DELAY = 30;
	private static final int SOFT_DROP_DELAY = 2;
	/** Smallest delay (faster falling) that can be achieved by increasing in level. */
	private static final int MIN_DELAY = 0;
	
	/** Seconds of delay the count down screen waits for. */
	private static final int COUNT_DOWN_DELAY = 3;
	
	private FallingSystem fallingSystem;
	
	private GameRender gameRender;
	
	private GameModel gameModel;
	
	@FXML private ImageView backgroundImageView;
	
	@FXML private Label holdLabel;
	@FXML private Canvas holdCanvas;
	
	@FXML private Label scoreLabel;
	@FXML private Label scoreValueLabel;
	@FXML private Label levelLabel;
	@FXML private Label levelValueLabel;
	@FXML private Label linesLabel;
	@FXML private Label linesValueLabel;
	@FXML private Label lineClearStreakLabel;
	@FXML private Label lineClearStreakValueLabel;
	
	@FXML private FlashingImageView tSpinImageView;
	
	@FXML private Label nextLabel;
	@FXML private Canvas firstNextCanvas;
	@FXML private Canvas secondNextCanvas;
	@FXML private Canvas thirdNextCanvas;
	
	@FXML private Canvas gameBoardCanvas;
	
	@FXML private PauseScreen pauseScreen;
	@FXML private GameOverScreen gameOverScreen;
	@FXML private CountDownScreen countDownScreen;
	
	private ScoreUpdateHandler scoreUpdateHandler;
	
	private int delayBeforeSoftDrop;
	
	/**
	 * @see com.rawad.gamehelpers.client.states.State#init()
	 */
	@Override
	public void init() {
		
		gameModel = new GameModel(GAME_BOARD_WIDTH, GAME_BOARD_HEIGHT);
		
		Client client = game.getProxies().get(Client.class);
		Loader loader = client.getLoaders().get(Loader.class);
		
		GameListeners.registerListeners(gameEngine, gameModel);
		eventManager.registerListener(EventType.GAME_OVER, this);
		eventManager.registerListener(EventType.TETROMINO_PLACE, this);
		eventManager.registerListener(EventType.TETROMINO_HOLD, this);
		eventManager.registerListener(EventType.LEVEL_INCREASE, this);
		eventManager.registerListener(EventType.LINE_CLEAR_STREAK_UPDATE, this);
		gameEngine.getEventManager().registerListener(EventType.T_SPIN, this);
		
		scoreUpdateHandler = new ScoreUpdateHandler(gameEngine.getEventManager(), gameModel);
		
		eventManager.registerListener(EventType.TETROMINO_HARD_DROP, scoreUpdateHandler);
		eventManager.registerListener(EventType.TETROMINO_FALL, scoreUpdateHandler);
		eventManager.registerListener(EventType.LINE_CLEAR, scoreUpdateHandler);
		eventManager.registerListener(EventType.T_SPIN, scoreUpdateHandler);
		
		fallingSystem = new FallingSystem(eventManager);
		
		gameEngine.addGameSystem(fallingSystem);
		gameEngine.addGameSystem(new LockingSystem(eventManager));
		
		Root root = GuiRegister.loadGui(this);
		
		scoreValueLabel.textProperty().bind(scoreUpdateHandler.getScore().asString());
		levelValueLabel.textProperty().bind(scoreUpdateHandler.getLevel().asString());
		linesValueLabel.textProperty().bind(scoreUpdateHandler.getLines().asString());
		
		backgroundImageView.setImage(Client.BACKGROUND);
		
		tSpinImageView.setImages(loader.loadTexture("t-spin-disabled-label"), loader.loadTexture("t-spin-label"));
		
		Image tileTexture = loader.loadTexture("Tetromino");
		
		gameRender = new GameRender(gameModel, tileTexture, gameBoardCanvas, holdCanvas, firstNextCanvas, 
				secondNextCanvas, thirdNextCanvas);
		
		masterRender.getRenders().put(gameRender);
//		masterRender.getRenders().put(new DebugRender(gameModel, tileTexture, gameBoardCanvas));
		
		gameBoardCanvas.setWidth(gameModel.getWidth() * tileTexture.getWidth());
		gameBoardCanvas.setHeight(gameModel.getHeight() * tileTexture.getHeight());
		
		double width = BoundingBoxCalculator.MAX_TETROMINO_WIDTH * tileTexture.getWidth();
		double height = BoundingBoxCalculator.MAX_TETROMINO_HEIGHT * tileTexture.getHeight();
		
		holdCanvas.setWidth(width);
		holdCanvas.setHeight(height);
		
		firstNextCanvas.setWidth(width);
		firstNextCanvas.setHeight(height);
		
		secondNextCanvas.setWidth(width);
		secondNextCanvas.setHeight(height);
		
		thirdNextCanvas.setWidth(width);
		thirdNextCanvas.setHeight(height);
		
		root.addEventHandler(KeyEvent.KEY_PRESSED, (e) -> {
			
			if(gameOverScreen.isVisible() || countDownScreen.isVisible()) return;
			
			InputAction action = (InputAction) client.getInputBindings().get(e.getCode());
			
			switch(action) {
			
			case PAUSE:
				pauseScreen.toggleVisible();
				break;
			
			default:
				break;
			
			}
			
			if(pauseScreen.isVisible()) return;
			
			switch(action) {
			
			case HOLD:
				gameEngine.getEventManager().queueEvent(new HoldEvent());
				break;
				
			case SOFT_DROP:
				
				if(gameModel.isSoftDrop()) break;// Already in soft drop. Prevents locking to soft drop.
				
				delayBeforeSoftDrop = fallingSystem.getTickDelay();
				fallingSystem.setTickDelay(SOFT_DROP_DELAY);
				fallingSystem.resetTickCount();
				gameModel.setSoftDrop(true);
				
				break;
			
			case HARD_DROP:
				gameEngine.getEventManager().queueEvent(new HardDropRequestEvent());
				break;
				
			case SHIFT_RIGHT:
				gameEngine.getEventManager().queueEvent(new MoveRequestEvent(true, false, false));
				break;
				
			case SHIFT_LEFT:
				gameEngine.getEventManager().queueEvent(new MoveRequestEvent(false, true, false));
				break;
				
			case ROTATE_RIGHT:
				gameEngine.getEventManager().queueEvent(new RotateRightEvent());
				break;
				
			case ROTATE_LEFT:
				gameEngine.getEventManager().queueEvent(new RotateLeftEvent());
				break;
				
			default:
				break;
				
			}
			
		});
		
		root.addEventHandler(KeyEvent.KEY_RELEASED, (e) -> {
			
			InputAction action = (InputAction) client.getInputBindings().get(e.getCode());
			
			switch(action) {
			
			case SOFT_DROP:
				fallingSystem.setTickDelay(delayBeforeSoftDrop);
				gameModel.setSoftDrop(false);
				break;
			
			default:
				break;
			
			}
			
		});
		
		pauseScreen.getMenuButton().setOnAction((e) -> sm.requestStateChange(StateChangeRequest.instance(MenuState.class)));
		pauseScreen.getResumeButton().setOnAction((e) -> pauseScreen.hide());
		pauseScreen.visibleProperty().addListener((e, oldVisible, newVisible) -> {
			
			// Hide tetrominoes when pause screen visible and when the game over screen isn't showing.
			if(!gameOverScreen.isVisible()) gameRender.setHideTetrominoes(newVisible);
			
			game.setPaused(newVisible);
			
			if(!newVisible) countDownScreen.show();// Show count down screen
			
		});
		
		gameOverScreen.getPlayAgainButton().setOnAction((e) -> {
			
			GameManager.scheduleTask(new TimerTask() {
				
				@Override
				public void run() {
					
					resetGame();
					
					gameOverScreen.hide();
					countDownScreen.show();// Show countdown after a new game was requested to be started.
					
				}
				
			});
			
		});
		gameOverScreen.getExitButton().setOnAction((e) -> sm.requestStateChange(StateChangeRequest.instance(
				MenuState.class)));
		
		countDownScreen.visibleProperty().addListener((e, oldVisible, newVisible) -> {
			
			gameRender.setHideTetrominoes(newVisible);// Hide tetrominoes when count down screen visible.
			game.setPaused(newVisible);
			
		});
		countDownScreen.setDelay(COUNT_DOWN_DELAY);
		
		client.getStage().focusedProperty().addListener((e, oldFocus, newFocus) -> {
			if(!newFocus && !gameOverScreen.isVisible()) {// Only pause when game is not over.
				countDownScreen.hide();// Keep here because of nature of hide(); adds to JavaFX thread. This maintains the
				pauseScreen.show();// order of these two function calls since both add to JavaFX thread.
			}
		});
		
		resetGame();
		
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
		
		game.setPaused(true);
		
		pauseScreen.hide();
		
		if(!gameOverScreen.isVisible()) countDownScreen.show();// Show count down when we come back (and game is running).
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.client.states.State#onDeactivate()
	 */
	@Override
	protected void onDeactivate() {
		countDownScreen.hide();// Not sure this is possible.
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.event.Listener#onEvent(com.rawad.gamehelpers.game.event.Event)
	 */
	@Override
	public void onEvent(Event ev) {
		
		if(ev.getEventType() == EventType.GAME_OVER) {
			
			game.setPaused(true);
			gameOverScreen.show();
			
		// No need to listen for HardDropEvent here because that is taken care of by the TetrominoPlaceEvent.
		} else if(ev.getEventType() == EventType.TETROMINO_PLACE || ev.getEventType() == EventType.TETROMINO_HOLD) {
			
			// Because the HOLD request above is queued, this needs to be here so we're 100% sure tick count is reset on time.
			
			fallingSystem.resetTickCount();// Gives a nice feel after placing a tetromino.
			
		} else if(ev.getEventType() == EventType.LEVEL_INCREASE) {
			
			int tickDelay = fallingSystem.getTickDelay();
			
			if(tickDelay > MIN_DELAY) {
				tickDelay--;
				fallingSystem.setTickDelay(tickDelay);
			}
			
		} else if(ev.getEventType() == EventType.LINE_CLEAR_STREAK_UPDATE) {
			
			LineClearStreakUpdateEvent lineClearStreakUpdateEvent = (LineClearStreakUpdateEvent) ev;
			
			final int lineClearStreak = lineClearStreakUpdateEvent.getLineClearStreak();
			
			Platform.runLater(() -> lineClearStreakValueLabel.setText("" + lineClearStreak));
			
		} else if(ev.getEventType() == EventType.T_SPIN) {
			tSpinImageView.flashImage();
		}
		
	}
	
	private void resetGame() {
		
		// This isn't really necessary but might as well.
		delayBeforeSoftDrop = INITIAL_DELAY;
		
		gameModel.reset();
		
		gameEngine.getEntities().clear();
		
		fallingSystem.setTickDelay(INITIAL_DELAY);
		
		gameEngine.getEventManager().submitEvent(new GenerateTetrominoesEvent(COUNT_TETROMINOES_TO_GEN));
		
		/* CountDownScreen appearing and hiding tetrominoes not fast enough; can flash sometime at BEGINNING of game.
		 * Be sure to call this here, AFTER all tetrominoes (most importantly the active tetromino) have been generated.
		 */
		gameRender.setHideTetrominoes(false);
		
		Platform.runLater(() -> {
			
			scoreUpdateHandler.getScore().set(0);
			scoreUpdateHandler.getLevel().set(1);
			scoreUpdateHandler.getLines().set(0);
			
			lineClearStreakValueLabel.setText("" + gameModel.getLineClearStreak());
			
		});
		
	}
	
}
