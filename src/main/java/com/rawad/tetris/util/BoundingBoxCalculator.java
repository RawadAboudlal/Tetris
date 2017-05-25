package com.rawad.tetris.util;

import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.game.tiles.Tile;
import com.rawad.tetris.geometry.Rectangle;

/**
 * @author Rawad
 *
 */
public final class BoundingBoxCalculator {
	
	public static final int MAX_TETROMINO_WIDTH = 4;
	public static final int MAX_TETROMINO_HEIGHT = 2;
	
	public static Rectangle getBoundingBox(TilesComponent tilesComp) {
		
		Rectangle boundingBox = new Rectangle(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
		
		for(Tile tile: tilesComp.getTiles()) {
			
			// Don't include the translate here. That is not up for us to decide. Caller can translate final bounding box.
			int x = tile.getX();
			int y = tile.getY();
			
			boundingBox.setX1(Math.min(boundingBox.getX1(), x));
			boundingBox.setY1(Math.min(boundingBox.getY1(), y));
			
			boundingBox.setX2(Math.max(boundingBox.getX2(), x));
			boundingBox.setY2(Math.max(boundingBox.getY2(), y));
			
		}
		
		return boundingBox;
		
	}
	
	public static void centerInArea(TranslateComponent translateComp, Rectangle boundingBox, int width, int height) {
		
		int xCenteringOffset = 0;
		
		if(boundingBox.getWidth() % 2 == 1) xCenteringOffset = -1;
		
		translateComp.setX((width / 2) - (boundingBox.getWidth() / 2) + xCenteringOffset);
		translateComp.setY((height / 2) - (boundingBox.getHeight() / 2));
		
	}
	
}
