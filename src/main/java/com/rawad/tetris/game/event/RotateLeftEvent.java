package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;

/**
 * @author Rawad
 *
 */
public class RotateLeftEvent extends Event {
	
	public RotateLeftEvent() {
		super(EventType.TETROMINO_ROTATE_LEFT_REQUEST);
	}
	
}
