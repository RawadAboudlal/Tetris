package com.rawad.tetris.game.event;

import com.rawad.gamehelpers.game.event.Event;


/**
 * This event is called at the beginning of each round to generate a given number of tetrominoes.
 * 
 * @author Rawad
 *
 */
public class GenerateTetrominoesEvent extends Event {
	
	private final int amountToGenerate;
	
	/**
	 * @param amountToGenerate
	 */
	public GenerateTetrominoesEvent(int amountToGenerate) {
		super(EventType.GENERATE_TETROMINOES);
		
		this.amountToGenerate = amountToGenerate;
		
	}
	
	/**
	 * @return the amountToGenerate
	 */
	public int getAmountToGenerate() {
		return amountToGenerate;
	}
	
}
