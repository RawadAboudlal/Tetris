package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;


/**
 * @author Rawad
 *
 */
public class MoveEvent extends Event {
	
	public MoveEvent() {
		super(EventType.TETROMINO_MOVE);
	}
	
}
