package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;

/**
 * @author Rawad
 *
 */
public class LineClearEvent extends Event {
	
	private final Integer[] fullRows;

	/**
	 * @param fullRows
	 */
	public LineClearEvent(Integer[] fullRows) {
		super(EventType.LINE_CLEAR);
		
		this.fullRows = fullRows;
		
	}
	
	/**
	 * @return the fullRows
	 */
	public Integer[] getFullRows() {
		return fullRows;
	}
	
}
