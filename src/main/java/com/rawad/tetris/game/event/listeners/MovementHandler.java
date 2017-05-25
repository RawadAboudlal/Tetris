package com.rawad.tetris.game.event.listeners;

import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.gamehelpers.game.event.Event;
import com.rawad.gamehelpers.game.event.EventManager;
import com.rawad.gamehelpers.game.event.Listener;
import com.rawad.tetris.entity.MovementComponent;
import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.entity.movement.Movement;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.event.EventType;
import com.rawad.tetris.game.event.FallingEvent;
import com.rawad.tetris.game.event.MoveEvent;
import com.rawad.tetris.game.event.MoveRequestEvent;
import com.rawad.tetris.util.CollisionChecker;


/**
 * @author Rawad
 *
 */
public class MovementHandler implements Listener {
	
	private final EventManager eventManager;
	private final GameModel gameModel;
	
	/**
	 * @param eventManager
	 * @param gameModel
	 */
	public MovementHandler(EventManager eventManager, GameModel gameModel) {
		super();
		
		this.eventManager = eventManager;
		this.gameModel = gameModel;
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.event.Listener#onEvent(com.rawad.gamehelpers.game.event.Event)
	 */
	@Override
	public void onEvent(Event ev) {
		
		if(ev.getEventType() == EventType.TETROMINO_MOVE_REQUEST) {
			
			MoveRequestEvent movementEvent = (MoveRequestEvent) ev;
			
			Entity activeTetromino = gameModel.getActiveTetromino();
			
			TilesComponent tilesComp = activeTetromino.getComponent(TilesComponent.class);
			TranslateComponent translateComp = activeTetromino.getComponent(TranslateComponent.class);
			
			int dx = 0;
			int dy = 0;
			
			if(movementEvent.isRight()) dx = 1;
			else if(movementEvent.isLeft()) dx = -1;
			
			if(movementEvent.isDown()) dy = 1;
			
			boolean colliding = CollisionChecker.isColliding(gameModel, tilesComp, translateComp, dx, dy);
			
			// Do NOT submit GroundHitEvent here, will be delayed by one frame (long time early in game).
			if(!colliding) {
				
				translateComp.setX(translateComp.getX() + dx);
				translateComp.setY(translateComp.getY() + dy);
				
				MovementComponent movementComp = activeTetromino.getComponent(MovementComponent.class);
				
				if(dx != 0) {
					
					movementComp.setPrevMovement(Movement.SHIFT);
					
					eventManager.submitEvent(new MoveEvent());
					
				}
				
				if(dy != 0) {
					
					if(gameModel.isSoftDrop()) {
						movementComp.setPrevMovement(Movement.SOFT_DROP);
					} else {
						movementComp.setPrevMovement(Movement.FALL);
					}
					
					eventManager.submitEvent(new FallingEvent(dy));
					
				}
				
			} else {
				
				// For when tetrmino is blocked after spawning (columns cover entire field).
				eventManager.submitEvent(new FallingEvent(0));
				
			}
			
		}
		
	}
	
}
