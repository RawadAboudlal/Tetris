package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;


/**
 * @author Rawad
 *
 */
public class FailedRotateEvent extends Event {
	
	/**
	 * 
	 */
	public FailedRotateEvent() {
		super(EventType.FAILED_ROTATE);
	}
	
}
