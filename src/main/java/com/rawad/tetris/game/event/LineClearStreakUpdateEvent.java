package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;

/**
 * @author Rawad
 *
 */
public class LineClearStreakUpdateEvent extends Event {
	
	private final int lineClearStreak;
	
	/**
	 * @param lineClearStreak
	 */
	public LineClearStreakUpdateEvent(int lineClearStreak) {
		super(EventType.LINE_CLEAR_STREAK_UPDATE);
		
		this.lineClearStreak = lineClearStreak;
		
	}
	
	/**
	 * @return the lineClearStreak
	 */
	public int getLineClearStreak() {
		return lineClearStreak;
	}
	
}
