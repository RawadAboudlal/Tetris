package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;

/**
 * @author Rawad
 *
 */
public class MoveRequestEvent extends Event {
	
	private final boolean right;
	private final boolean left;
	private final boolean down;
	
	/**
	 * @param right
	 * @param left
	 * @param down
	 */
	public MoveRequestEvent(boolean right, boolean left, boolean down) {
		super(EventType.TETROMINO_MOVE_REQUEST);
		
		this.right = right;
		this.left = left;
		this.down = down;
		
	}
	
	/**
	 * @return the right
	 */
	public boolean isRight() {
		return right;
	}
	
	/**
	 * @return the left
	 */
	public boolean isLeft() {
		return left;
	}
	
	/**
	 * @return the down
	 */
	public boolean isDown() {
		return down;
	}
	
}
