package com.rawad.tetris.client.input;

/**
 * @author Rawad
 *
 */
public enum InputAction {
	
	SHIFT_RIGHT,
	SHIFT_LEFT,
	
	SOFT_DROP,// Speeds up a block to move down faster.
	HARD_DROP,// Immediately places block down where it is supposed to go to.
	
	ROTATE_RIGHT,// Make it up key, rotate clockwise 90 degrees.
	ROTATE_LEFT,// Some unrelated/far key (Z).
	
	HOLD,
	
	PAUSE,
	
	DEFAULT;
	
}
