package com.rawad.tetris.client.renderengine;

import com.rawad.gamehelpers.client.renderengine.Render;
import com.rawad.tetris.game.tiles.Tile;
import com.rawad.tetris.util.ColorUtils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


/**
 * @author Rawad
 *
 */
public class TileRender extends Render {
	
	public void render(GraphicsContext g, Tile tile, Image tileTexture) {
		
		double x = tile.getX() * tileTexture.getWidth();
		double y = tile.getY() * tileTexture.getHeight();
		
		Color color = ColorUtils.toColor(tile.getColor());
		
		g.setFill(color);
		g.fillRect(x, y, tileTexture.getWidth(), tileTexture.getHeight());
		
		g.drawImage(tileTexture, x, y);
		
	}
	
	public void renderWireFrame(GraphicsContext g, Tile tile, double tileWidth, double tileHeight) {
		
		double x = tile.getX() * tileWidth;
		double y = tile.getY() * tileHeight;
		
		Color color = ColorUtils.toColor(tile.getColor());
		
		g.setStroke(color);
		g.strokeRoundRect(x, y, tileWidth, tileHeight, GameBoardRender.TILE_ARC_SIZE, GameBoardRender.TILE_ARC_SIZE);
		
	}
	
}
