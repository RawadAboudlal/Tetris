package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;


/**
 * @author Rawad
 *
 */
public class LevelIncreaseEvent extends Event {
	
	// Could have newLevel variable here but we're using JavaFX so that won't be necessary for now.
	
	public LevelIncreaseEvent() {
		super(EventType.LEVEL_INCREASE);
	}
	
}
