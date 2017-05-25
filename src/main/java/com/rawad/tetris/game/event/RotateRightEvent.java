package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;

/**
 * @author Rawad
 *
 */
public class RotateRightEvent extends Event {
	
	public RotateRightEvent() {
		super(EventType.TETROMINO_ROTATE_RIGHT_REQUEST);
	}
	
}
