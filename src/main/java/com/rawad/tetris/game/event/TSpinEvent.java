package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;


/**
 * @author Rawad
 *
 */
public class TSpinEvent extends Event {
	
	/** Number of lines that were cleared by this T-spin (can be 0). */
	private final int linesCleared;

	/**
	 * @param linesCleared
	 */
	public TSpinEvent(int linesCleared) {
		super(EventType.T_SPIN);
		this.linesCleared = linesCleared;
	}
	
	/**
	 * @return the linesCleared
	 */
	public int getLinesCleared() {
		return linesCleared;
	}
	
}
