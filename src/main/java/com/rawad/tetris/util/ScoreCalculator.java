package com.rawad.tetris.util;

/**
 * @author Rawad
 *
 */
public final class ScoreCalculator {
	
	private static final int SOFT_DROP_MULTIPLIER = 1;
	private static final int HARD_DROP_MULTIPLIER = 2;
	private static final int LINE_CLEAR_BASE = 100;
	
	private static final int T_SPIN_BASE = 500;
	/** Represents how much a T-spin increases in worth per each line cleared */
	private static final int T_SPIN_LINE_MULTIPLIER = 300;
	
	/** How much a line clear streak increases the score of a line clear. */
	private static final int STREAK_MULTIPLIER = 50;
	
	public static int getSoftDropPoints(int distance) {
		return distance * SOFT_DROP_MULTIPLIER;
	}
	
	public static int getHardDropPoints(int distance) {
		return distance * HARD_DROP_MULTIPLIER;
	}
	
	public static int getLineClearPoints(int linesCleared, int streak, int level) {
		
		int totalMultiplier = LINE_CLEAR_BASE;
		
		// No pattern here... had to be brute-forced...
		switch(linesCleared) {
		
		case 1:
			// Do nothing, multiplier already the correct value.
			break;
			
		case 2:
			totalMultiplier *= 3;// 300 points for double line clear.
			break;
			
		case 3:
			totalMultiplier *= 4;// 400 points for triple line clear.
			break;
			
		case 4:
			totalMultiplier *= 8;// 800 points for quadruple line clear (Tetris).
			break;
			
		}
		
		return (totalMultiplier + streak * STREAK_MULTIPLIER) * level;
		
	}
	
	public static int getTSpinPoints(int linesCleared, int streak, int level) {
		return (T_SPIN_BASE + (linesCleared * T_SPIN_LINE_MULTIPLIER) + (streak * STREAK_MULTIPLIER)) * level;
	}
	
}
