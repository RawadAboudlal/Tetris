package com.rawad.tetris.client.renderengine;

import com.rawad.gamehelpers.client.renderengine.Render;
import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.tiles.Tile;
import com.rawad.tetris.geometry.Rectangle;
import com.rawad.tetris.util.BoundingBoxCalculator;
import com.rawad.tetris.util.TetrominoPlacementPredictor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;


/**
 * @author Rawad
 *
 */
public class TetrominoRender extends Render {
	
	private TileRender tileRender = new TileRender();
	
	public void render(GraphicsContext g, Entity tetromino, Image tileTexture) {
		
		Affine transform = g.getTransform();
		
		TilesComponent tilesComp = tetromino.getComponent(TilesComponent.class);
		TranslateComponent translateComp = tetromino.getComponent(TranslateComponent.class);
		
		// Scaling would distort textures.
		g.translate(translateComp.getX() * tileTexture.getWidth(), translateComp.getY() * tileTexture.getHeight());
		
		for(Tile tile: tilesComp.getTiles()) {
			tileRender.render(g, tile, tileTexture);
		}
		
		g.setTransform(transform);
		
	}
	
	/**
	 * Centers tetromino horizontally within the provided width.
	 * 
	 * @param g
	 * @param tetromino
	 * @param tileTexture
	 * @param width
	 */
	public void render(GraphicsContext g, Entity tetromino, Image tileTexture, double width) {
		
		Rectangle boundingBox = BoundingBoxCalculator.getBoundingBox(tetromino.getComponent(TilesComponent.class));
		
		double xOffset = width / 2d - ((double) boundingBox.getWidth() * tileTexture.getWidth() / 2d);
		
		Affine transform = g.getTransform();
		
		g.translate(xOffset, 0);
		
		render(g, tetromino, tileTexture);
		
		g.setTransform(transform);
		
	}
	
	public void renderGhostTetromino(GraphicsContext g, GameModel gameModel, double tileWidth, double tileHeight) {
		
		Entity activeTetromino = gameModel.getActiveTetromino();
		
		TilesComponent tilesComp = activeTetromino.getComponent(TilesComponent.class);
		TranslateComponent translateComp = activeTetromino.getComponent(TranslateComponent.class);
		
		int ghostY = TetrominoPlacementPredictor.getPredictedPlacementPosition(gameModel);
		
		// Don't render ghost when active is on top. Mainly so it won't interfere with flashing animation.
		if(translateComp.getY() == ghostY) return;
		
		Affine transform = g.getTransform();
		
		g.translate(translateComp.getX() * tileWidth, ghostY * tileHeight);
		
		for(Tile tile: tilesComp.getTiles()) {
			tileRender.renderWireFrame(g, tile, tileWidth, tileHeight);
		}
		
		g.setTransform(transform);
		
	}
	
}
