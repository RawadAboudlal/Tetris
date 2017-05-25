package com.rawad.tetris.util;

import com.rawad.gamehelpers.game.GameEngine;
import com.rawad.gamehelpers.game.event.EventManager;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.event.EventType;
import com.rawad.tetris.game.event.listeners.LockHandler;
import com.rawad.tetris.game.event.listeners.LineClearHandler;
import com.rawad.tetris.game.event.listeners.MovementHandler;
import com.rawad.tetris.game.event.listeners.RotationHandler;
import com.rawad.tetris.game.event.listeners.LineClearCheckHandler;
import com.rawad.tetris.game.event.listeners.TetrominoHandler;

/**
 * @author Rawad
 *
 */
public final class GameListeners {
	
	/**
	 * Registers all listeners related to gameplay to the given EventManager of the given {@code gameEngine}.
	 * 
	 * @param gameEngine
	 */
	public static void registerListeners(GameEngine gameEngine, GameModel gameModel) {
		
		EventManager eventManager = gameEngine.getEventManager();
		
		LockHandler lockHandler = new LockHandler(gameModel);
		TetrominoHandler tetrominoHandler = new TetrominoHandler(gameEngine, gameModel);
		RotationHandler rotationHandler = new RotationHandler(eventManager, gameModel);
		
		// With all this functionality (which is super clean), TetrominoGenerator should be named something else...
		eventManager.registerListener(EventType.TETROMINO_HOLD, tetrominoHandler);
		eventManager.registerListener(EventType.TETROMINO_PLACE_REQUEST, tetrominoHandler);
		eventManager.registerListener(EventType.TETROMINO_HARD_DROP_REQUEST, tetrominoHandler);
		eventManager.registerListener(EventType.GENERATE_TETROMINOES, tetrominoHandler);
		
		// When a tetromino falls, it is no longer hitting the ground so lock delay can't apply to it.
		eventManager.registerListener(EventType.TETROMINO_FALL, lockHandler);
		// Reset the lock delay upon rotate or move.
		eventManager.registerListener(EventType.TETROMINO_ROTATE, lockHandler);
		eventManager.registerListener(EventType.TETROMINO_MOVE, lockHandler);
		
		eventManager.registerListener(EventType.TETROMINO_ROTATE_RIGHT_REQUEST, rotationHandler);
		eventManager.registerListener(EventType.TETROMINO_ROTATE_LEFT_REQUEST, rotationHandler);
		
		eventManager.registerListener(EventType.TETROMINO_MOVE_REQUEST, new MovementHandler(eventManager, gameModel));
		eventManager.registerListener(EventType.TETROMINO_PLACE, new LineClearCheckHandler(eventManager, gameModel));
		eventManager.registerListener(EventType.LINE_CLEAR, new LineClearHandler(gameModel));
		
	}
	
}
