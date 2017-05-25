package com.rawad.tetris.game.event;

/**
 * @author Rawad
 *
 */
public enum EventType {
	
	TETROMINO_HOLD,
	TETROMINO_MOVE,
	TETROMINO_MOVE_REQUEST,
	TETROMINO_ROTATE,// Just used for the count down timer reset.
	TETROMINO_ROTATE_RIGHT_REQUEST,
	TETROMINO_ROTATE_LEFT_REQUEST,
	TETROMINO_HARD_DROP,
	TETROMINO_HARD_DROP_REQUEST,
	
	TETROMINO_FALL,
	// Called after a tetromino is placed. Listens to this ensure you have the game model with the tetromino placed in it.
	TETROMINO_PLACE,
	TETROMINO_PLACE_REQUEST,// Called to indicate that a tetromino should be placed.
	GENERATE_TETROMINOES,
	
	T_SPIN,
	
	LINE_CLEAR,
	LINE_CLEAR_STREAK_UPDATE,
	
	LEVEL_INCREASE,
	
	GAME_OVER,;
	
}
