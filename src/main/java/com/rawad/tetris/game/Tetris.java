package com.rawad.tetris.game;

import java.util.concurrent.TimeUnit;

import com.rawad.gamehelpers.game.FixedRateGame;


/**
 * Official guidelines website: {@link http://tetris.wikia.com/wiki/Tetris_Guideline}
 * 
 * @author Rawad
 *
 */
public class Tetris extends FixedRateGame {
	
	/**
	 * @see com.rawad.gamehelpers.game.Game#init()
	 */
	@Override
	protected void init() {
		super.init();
		
		setTickTime(TimeUnit.MILLISECONDS.toNanos(40));
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.Game#getName()
	 */
	@Override
	public String getName() {
		return "Tetris";
	}
	
}
