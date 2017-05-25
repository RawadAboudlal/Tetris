package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;


/**
 * @author Rawad
 *
 */
public class HardDropRequestEvent extends Event {
	
	public HardDropRequestEvent() {
		super(EventType.TETROMINO_HARD_DROP_REQUEST);
	}
	
}
