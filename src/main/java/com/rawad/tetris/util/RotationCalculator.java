package com.rawad.tetris.util;

import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.tetris.entity.RotationComponent;
import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.tiles.Tile;
import com.rawad.tetris.geometry.Rectangle;

/**
 * @author Rawad
 *
 */
public final class RotationCalculator {
	
	public static final int ROTATE_RIGHT = 1;
	public static final int ROTATE_LEFT = -1;
	
	/**
	 * 
	 * @param gameModel
	 * @param tetromino
	 * @param direction Direction to rotate
	 * @return {@code true} if the tetromino was rotated.
	 */
	public static boolean rotateTetromino(GameModel gameModel, Entity tetromino, int direction) {
		
		RotationComponent rotationComp = tetromino.getComponent(RotationComponent.class);
		TilesComponent tilesComp = tetromino.getComponent(TilesComponent.class);
		TranslateComponent translateComp = tetromino.getComponent(TranslateComponent.class);
		
		RotationCalculator.rotateTiles(rotationComp, tilesComp, direction);
		
		Rectangle boundingBox = BoundingBoxCalculator.getBoundingBox(tilesComp);
		
		// If the tetromino rotated correctly (not colliding with anything after rotate) then rotation was successful.
		if(!CollisionChecker.isColliding(gameModel, tilesComp, translateComp, 0, 0)) return true;
			
		/* This is specifically for I tetromino so it can do wall kick properly (too wide/tall for just an offset of 1).
		 * So we try with both offset 1 and 2 (if no offset doesn't work) AND we try offset 1 FIRST.
		 */
		int maxOffset = Math.max(boundingBox.getWidth() / 2, boundingBox.getHeight() / 2);
		
		for(int offset = 1; offset <= maxOffset; offset++) {
			
			/* The order in which these are tested IS important. Down first for t-spins, then right/left for wall kicks and
			 * if neither of those work we will shift it up one.
			 */
			
			// Shift one down then test.
			if(!CollisionChecker.isColliding(gameModel, tilesComp, translateComp, 0, +offset)) {
				
				translateComp.setY(translateComp.getY() + offset);
				return true;
				
			// Shift one to right then test.
			} else if(!CollisionChecker.isColliding(gameModel, tilesComp, translateComp, offset, 0)) {
				
				translateComp.setX(translateComp.getX() + offset);
				return true;
				
			// Shift one to left then test.
			} else if(!CollisionChecker.isColliding(gameModel, tilesComp, translateComp, -offset, 0)) {
				
				translateComp.setX(translateComp.getX() - offset);
				return true;
				
			// Shift one up then test.
			} else if(!CollisionChecker.isColliding(gameModel, tilesComp, translateComp, 0, -offset)) {
				
				translateComp.setY(translateComp.getY() - offset);
				return true;
				
			}
			
		}
		
		// No wall kicking was able to allow the tetromino to rotate properly so we undo rotation and return false.
		RotationCalculator.rotateTiles(rotationComp, tilesComp, -direction);
		
		return false;
		
	}
	
	public static void rotateTiles(RotationComponent rotationComp, TilesComponent tilesComp, int direction) {
		
		rotationComp.setTotalRotated(rotationComp.getTotalRotated() + direction);
		
		int sin = (int) Math.signum(direction);
		
		direction = Math.abs(direction);
		
		while(direction > 0) {// For when a direction > 1 is given (e.g. undoing rotation), they can be handled properly.
			
			for(Tile tile: tilesComp.getTiles()) {
				
				// Reposition x/y to be at origin (relative to center of rotation).
				double newX = (double) tile.getX() - rotationComp.getCenterX();
				double newY = (double) tile.getY() - rotationComp.getCenterY();
				
				tile.setX((int) ((-newY * sin) + rotationComp.getCenterX()));
				tile.setY((int) (( newX * sin) + rotationComp.getCenterY()));
				
			}
			
			direction--;
			
		}
		
	}
	
}
