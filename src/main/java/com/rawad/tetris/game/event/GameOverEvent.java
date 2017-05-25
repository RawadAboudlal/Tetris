package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;


/**
 * @author Rawad
 *
 */
public class GameOverEvent extends Event {
	
	public GameOverEvent() {
		super(EventType.GAME_OVER);
	}
	
}
