package com.rawad.tetris.util;

import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.tiles.Tile;
import com.rawad.tetris.geometry.Rectangle;

/**
 * @author Rawad
 *
 */
public class CollisionChecker {
	
	public static boolean isColliding(GameModel gameModel, TilesComponent tilesComp, TranslateComponent translateComp, 
			int dx, int dy) {
		
		// Bounding box of where tetromino is expected to be.
		Rectangle boundingBox = BoundingBoxCalculator.getBoundingBox(tilesComp);
		boundingBox.translate(translateComp.getX() + dx, translateComp.getY() + dy);
		
		/* boundingBox.getY1() < 0 || <- This used to be here v but it makes no sense since tetrominoes only move 
		 * down/right/left and never back up. This being here would interfere would tetromino movement when trying to move
		 * while still above game field (e.g. column being filled to top).*/
		if(boundingBox.getX1() < 0 || boundingBox.getX2() >= gameModel.getWidth() || 
				boundingBox.getY2() >= gameModel.getHeight())// Tetromino hit edge (left, right, bottom respectively).
			return true;
		
		for(Tile tileInTetromino: tilesComp.getTiles()) {
			
			int xOnBoard = tileInTetromino.getX() + translateComp.getX() + dx;
			int yOnBoard = tileInTetromino.getY() + translateComp.getY() + dy;
			
			// Spot to where Tile is being moved to is above board so just pretend like it is free to move there.
			if(yOnBoard < 0) continue;
			
//			yOnBoard = Math.max(yOnBoard, 0);
			
			if(gameModel.getTile(xOnBoard, yOnBoard) != null) return true;
			
		}
		
		/* Look at this first then look at how much nicer that ^ is. /
		for(int x = 0; x < gameModel.getWidth(); x++) {
			
			// Small optimization. Tiles are more likely at bottom.
			for(int y = gameModel.getHeight() - 1; y >= Math.max(boundingBox.getY2(), 0); y--) {
				
				// Tile is empty, can't collide.
				if(gameModel.getTile(x, y) == null) continue;
				
				for(Tile tileInTetromino: tilesComp.getTiles()) {
					
					int newTileX = tileInTetromino.getX() + translateComp.getX() + dx;
					int newTileY = tileInTetromino.getY() + translateComp.getY() + dy;
					
					if(newTileX == x && newTileY == y) return true;
					
				}
				
			}
		}/**/
		
		return false;
		
	}
	
}
