package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;

/**
 * @author Rawad
 *
 */
public class FailedMoveEvent extends Event {
	
	/**
	 * 
	 */
	public FailedMoveEvent() {
		super(EventType.FAILED_MOVE);
	}
	
}
