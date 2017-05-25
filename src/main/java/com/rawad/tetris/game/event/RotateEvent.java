package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;


/**
 * @author Rawad
 *
 */
public class RotateEvent extends Event {
	
	public RotateEvent() {
		super(EventType.TETROMINO_ROTATE);
	}
	
}
