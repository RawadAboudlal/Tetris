package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;

/**
 * @author Rawad
 *
 */
public class HoldEvent extends Event {
	
	public HoldEvent() {
		super(EventType.TETROMINO_HOLD);
	}
	
}
