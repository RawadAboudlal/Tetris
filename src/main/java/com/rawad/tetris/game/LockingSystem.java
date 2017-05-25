package com.rawad.tetris.game;

import com.rawad.gamehelpers.game.GameSystem;
import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.gamehelpers.game.event.EventManager;
import com.rawad.tetris.entity.PlacementComponent;
import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.game.event.PlaceRequestEvent;


/**
 * @author Rawad
 *
 */
public class LockingSystem extends GameSystem {
	
	private final EventManager eventManager;
	
	public LockingSystem(EventManager eventManager) {
		super();
		
		this.eventManager = eventManager;
		
		compatibleComponentTypes.add(PlacementComponent.class);
		compatibleComponentTypes.add(TilesComponent.class);
		compatibleComponentTypes.add(TranslateComponent.class);
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.GameSystem#tick(com.rawad.gamehelpers.game.entity.Entity)
	 */
	@Override
	public void tick(Entity e) {
		
		PlacementComponent placementComp = e.getComponent(PlacementComponent.class);
		
		// Only attempt to lock tetromino when it is on the ground.
		if(placementComp.isOnGround()) {
			
			if(placementComp.getLockDelay() > 0) placementComp.setLockDelay(placementComp.getLockDelay() - 1);
			else eventManager.submitEvent(new PlaceRequestEvent());
			
		}
		
	}
	
}
