package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.gamehelpers.game.event.Event;

/**
 * @author Rawad
 *
 */
public class PlaceEvent extends Event {
	
	private final Entity placedTetromino;
	
	/**
	 * @param placedTetromino
	 */
	public PlaceEvent(Entity placedTetromino) {
		super(EventType.TETROMINO_PLACE);
		
		this.placedTetromino = placedTetromino;
		
	}
	
	/**
	 * @return the placedTetromino
	 */
	public Entity getPlacedTetromino() {
		return placedTetromino;
	}
	
}
