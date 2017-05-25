package com.rawad.tetris.game;

import com.rawad.gamehelpers.game.GameSystem;
import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.gamehelpers.game.event.EventManager;
import com.rawad.tetris.entity.PlacementComponent;
import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.game.event.MoveRequestEvent;


/**
 * @author Rawad
 *
 */
public class FallingSystem extends GameSystem {
	
	private final EventManager eventManager;
	
	private int tickDelay = 0;
	private int tickCount = 0;
	
	public FallingSystem(EventManager eventManager) {
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
		
		if(tickCount <= 0) {// This way, game tickss first then starts counting from delay to 0.
			tickCount = tickDelay;
			
			eventManager.submitEvent(new MoveRequestEvent(false, false, true));
			
		}
		
		tickCount--;
		
	}
	
	/**
	 * @param tickDelay the tickDelay to set
	 */
	public void setTickDelay(int tickDelay) {
		this.tickDelay = tickDelay;
	}
	
	/**
	 * @return the tickDelay
	 */
	public int getTickDelay() {
		return tickDelay;
	}
	
	public void resetTickCount() {
		this.tickCount = 0;
	}
	
}
