package com.rawad.tetris.entity;

/**
 * For future proofness, this enum will only declaree entity blueprint keys that are tetrominoes.
 * 
 * @author Rawad
 *
 */
public enum ETetrominoes {
	
	I("I"),
	J("J"),
	L("L"),
	O("O"),
	S("S"),
	T("T"),
	Z("Z");
	
	private final String name;
	
	/**
	 * @param name
	 */
	private ETetrominoes(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	public static ETetrominoes getTetrominoByName(String name) {
		
		for(ETetrominoes tetromino: ETetrominoes.values()) {
			if(tetromino.getName().equals(name)) return tetromino;
		}
		
		return null;
		
	}
	
}
