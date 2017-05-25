package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;

/**
 * @author Rawad
 *
 */
public class HardDropEvent extends Event {
	
	private final int distance;
	
	public HardDropEvent(int distance) {
		super(EventType.TETROMINO_HARD_DROP);
		
		this.distance = distance;
		
	}
	
	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	
}
