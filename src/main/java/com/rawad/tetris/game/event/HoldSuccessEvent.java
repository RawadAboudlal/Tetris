package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;


/**
 * @author Rawad
 *
 */
public class HoldSuccessEvent extends Event {
	
	/**
	 * 
	 */
	public HoldSuccessEvent() {
		super(EventType.TETROMINO_HOLD_SUCCESS);
	}
	
}
