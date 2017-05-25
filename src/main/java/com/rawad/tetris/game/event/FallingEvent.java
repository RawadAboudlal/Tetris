package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;

/**
 * @author Rawad
 *
 */
public class FallingEvent extends Event {
	
	private final int distance;
	
	/**
	 * @param distance
	 */
	public FallingEvent(int distance) {
		super(EventType.TETROMINO_FALL);
		
		this.distance = distance;
		
	}
	
	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	
}
