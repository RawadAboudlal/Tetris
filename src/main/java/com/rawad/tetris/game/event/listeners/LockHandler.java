package com.rawad.tetris.game.event.listeners;

import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.gamehelpers.game.event.Event;
import com.rawad.gamehelpers.game.event.Listener;
import com.rawad.tetris.entity.PlacementComponent;
import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.event.EventType;
import com.rawad.tetris.util.CollisionChecker;

/**
 * @author Rawad
 *
 */
public class LockHandler implements Listener {
	
	private static final int INITIAL_LOCK_DELAY = 20;
	
	private final GameModel gameModel;
	
	/**
	 * @param gameModel
	 */
	public LockHandler(GameModel gameModel) {
		super();
		
		this.gameModel = gameModel;
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.event.Listener#onEvent(com.rawad.gamehelpers.game.event.Event)
	 */
	@Override
	public void onEvent(Event ev) {
		
		Entity activeTetromino = gameModel.getActiveTetromino();
		
		PlacementComponent placementComp = activeTetromino.getComponent(PlacementComponent.class);
		
		if(ev.getEventType() == EventType.TETROMINO_FALL || ev.getEventType() == EventType.TETROMINO_MOVE || 
				ev.getEventType() == EventType.TETROMINO_ROTATE) {
			
			TilesComponent tilesComp = activeTetromino.getComponent(TilesComponent.class);
			TranslateComponent translateComp = activeTetromino.getComponent(TranslateComponent.class);
			
			placementComp.setOnGround(CollisionChecker.isColliding(gameModel, tilesComp, translateComp, 0, 1));
			
			if(placementComp.isOnGround()) {
				placementComp.setLockDelay(INITIAL_LOCK_DELAY);
			}
			
		}
		
	}
	
}
