package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;
import com.rawad.tetris.entity.movement.Movement;

/**
 * @author Rawad
 *
 */
public class FallingEvent extends Event {
	
	private final int distance;
	private final Movement prevMovement;
	
	/**
	 * @param distance
	 * @param prevMovement
	 */
	public FallingEvent(int distance, Movement prevMovement) {
		super(EventType.TETROMINO_FALL);
		
		this.distance = distance;
		this.prevMovement = prevMovement;
		
	}
	
	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * @return the prevMovement
	 */
	public Movement getPrevMovement() {
		return prevMovement;
	}
	
}
