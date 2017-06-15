package com.rawad.tetris.client.sound;


/**
 * @author Rawad
 *
 */
public enum SoundEffect {
	
	LEVEL_UP("LevelUp"),
	
	PIECE_FALL("PieceFall"),
	PIECE_HARD_DROP("PieceHardDrop"),
	PIECE_HOLD("PieceHold"),
	PIECE_LOCKDOWN("PieceLockdown"),
	PIECE_MOVE_LR("PieceMoveLR"),
	PIECE_ROTATE_FAIL("PieceRotateFail"),
	PIECE_ROTATE_LR("PieceRotateLR"),
	PIECE_SOFT_DROP("PieceSoftDrop"),
	PIECE_TOUCH_DOWN("PieceTouchDown"),
	PIECE_TOUCH_LR("PieceTouchLR"),
	;
	
	private final String name;
	
	/**
	 * @param name
	 */
	private SoundEffect(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
}
