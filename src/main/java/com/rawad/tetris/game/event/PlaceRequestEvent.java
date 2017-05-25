package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;


/**
 * @author Rawad
 *
 */
public class PlaceRequestEvent extends Event {
	
	public PlaceRequestEvent() {
		super(EventType.TETROMINO_PLACE_REQUEST);
	}
	
}
