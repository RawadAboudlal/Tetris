package com.rawad.tetris.game;

import java.util.ArrayList;
import java.util.LinkedList;

import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.tetris.game.tiles.Tile;

/**
 * @author Rawad
 *
 */
/**
 * @author Rawad
 *
 */
public class GameModel {
	
	private Entity activeTetromino;
	
	private Entity holdTetromino;
	
	private final LinkedList<Entity> nextTetrominoes = new LinkedList<Entity>();
	
	private final ArrayList<Entity> activeTetrominoList = new ArrayList<Entity>();
	
	private final Tile[][] tiles;
	
	private final int width;
	private final int height;
	
	private int lineClearStreak;
	
	/** Indicates whether the hold operation can be performed or not. */
	private boolean hold;
	
	/** Indicates whether there is a soft-drop going on right now or not. */
	private boolean softDrop;
	
	public GameModel(int width, int height) {
		super();
		
		tiles = new Tile[height][width];
		
		this.width = width;
		this.height = height;
		
	}
	
	public void reset() {
		
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = null;
			}
		}
		
		activeTetromino = null;
		activeTetrominoList.clear();
		
		holdTetromino = null;
		
		nextTetrominoes.clear();
		
		lineClearStreak = 0;
		
		hold = true;
		softDrop = false;
		
	}
	
	/**
	 * @return the activeTetrominoList
	 */
	public ArrayList<Entity> getActiveTetrominoList() {
		return activeTetrominoList;
	}
	
	/**
	 * @param activeTetromino the activeTetromino to set
	 */
	public void setActiveTetromino(Entity activeTetromino) {
		this.activeTetromino = activeTetromino;
		
		/*
		 *  Kind of makes this class not a "pure" model class this is still necessary. This issue could be solved with C/C++ 
		 *  pointers probably since we want to set a new Entity object to be active AND it is the only thing that realistically
		 *  needs to be handled by the game systems. Keeping a list in GameState, for example, wouldn't get updated because
		 *  getEntities() is only called once AND the active tetromino is null at that point (which could be fixed with the
		 *  initial pause but this is probably more robust).
		 */
		activeTetrominoList.clear();
		activeTetrominoList.add(activeTetromino);
		
	}
	
	/**
	 * @return the activeTetromino
	 */
	public Entity getActiveTetromino() {
		return activeTetromino;
	}
	
	/**
	 * @param holdTetromino the holdTetromino to set
	 */
	public void setHoldTetromino(Entity holdTetromino) {
		this.holdTetromino = holdTetromino;
	}
	
	/**
	 * @return the holdTetromino
	 */
	public Entity getHoldTetromino() {
		return holdTetromino;
	}
	
	/**
	 * @return the nextTetrominoes
	 */
	public LinkedList<Entity> getNextTetrominoes() {
		return nextTetrominoes;
	}
	
	public Tile getTile(int x, int y) {
		return tiles[y][x];
	}
	
	public void setTile(Tile tile, int x, int y) {
		tiles[y][x] = tile;
	}
	
	/**
	 * @return the tiles
	 */
	public Tile[][] getTiles() {// Could return some sort an interator object instead of this.
		return tiles;
	}
	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @return the lineClearStreak
	 */
	public int getLineClearStreak() {
		return lineClearStreak;
	}
	
	/**
	 * @param lineClearStreak the lineClearStreak to set
	 */
	public void setLineClearStreak(int lineClearStreak) {
		this.lineClearStreak = lineClearStreak;
	}
	
	/**
	 * @param hold the hold to set
	 */
	public void setHold(boolean hold) {
		this.hold = hold;
	}
	
	/**
	 * @return the hold
	 */
	public boolean canHold() {
		return hold;
	}
	
	/**
	 * @param softDrop the softDrop to set
	 */
	public void setSoftDrop(boolean softDrop) {
		this.softDrop = softDrop;
	}
	
	/**
	 * @return the softDrop
	 */
	public boolean isSoftDrop() {
		return softDrop;
	}
	
}
