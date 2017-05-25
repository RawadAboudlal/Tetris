package com.rawad.tetris.game.event.listeners;

import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.gamehelpers.game.event.Event;
import com.rawad.gamehelpers.game.event.EventManager;
import com.rawad.gamehelpers.game.event.Listener;
import com.rawad.tetris.entity.MovementComponent;
import com.rawad.tetris.entity.movement.Movement;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.event.EventType;
import com.rawad.tetris.game.event.RotateEvent;
import com.rawad.tetris.util.RotationCalculator;


/**
 * @author Rawad
 *
 */
public class RotationHandler implements Listener {
	
	private final EventManager eventManager;
	private final GameModel gameModel;
	
	/**
	 * @param eventManager
	 * @param gameModel
	 */
	public RotationHandler(EventManager eventManager, GameModel gameModel) {
		super();
		
		this.eventManager = eventManager;
		this.gameModel = gameModel;
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.event.Listener#onEvent(com.rawad.gamehelpers.game.event.Event)
	 */
	@Override
	public void onEvent(Event ev) {
		
		int rotateDirection = 0;
		
		if(ev.getEventType() == EventType.TETROMINO_ROTATE_RIGHT_REQUEST) {
			rotateDirection = RotationCalculator.ROTATE_RIGHT;
		} else if(ev.getEventType() == EventType.TETROMINO_ROTATE_LEFT_REQUEST) {
			rotateDirection = RotationCalculator.ROTATE_LEFT;
		}
		
		Entity activeTetromino = gameModel.getActiveTetromino();
		
		if(rotateDirection != 0 && RotationCalculator.rotateTetromino(gameModel, activeTetromino, rotateDirection)) {
			
			MovementComponent movementComp = activeTetromino.getComponent(MovementComponent.class);
			
			movementComp.setPrevMovement(Movement.ROTATE);
			
			eventManager.submitEvent(new RotateEvent());
		}
		
	}
	
}
