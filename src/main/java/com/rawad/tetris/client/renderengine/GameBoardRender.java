package com.rawad.tetris.client.renderengine;

import com.rawad.gamehelpers.client.renderengine.Render;
import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.tetris.entity.PlacementComponent;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.tiles.Tile;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @author Rawad
 *
 */
public class GameBoardRender extends Render {
	
	// Package private for use by TileRender to render ghost tetrominoes' wireframes (for now).
	static final double TILE_ARC_SIZE = 7d;
	
	private TetrominoRender tetrominoRender = new TetrominoRender();
	private TileRender tileRender = new TileRender();
	
	private Timeline flashTimeline = new Timeline();
	
	private SimpleDoubleProperty opacity = new SimpleDoubleProperty(1d);
	
	/**
	 * 
	 */
	public GameBoardRender() {
		super();
		
		// File opacity is 0.2d so that tetromino doesn't go completely away.
		flashTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new KeyValue(opacity, 0.2d)));
		
		flashTimeline.setAutoReverse(true);
		flashTimeline.setCycleCount(Timeline.INDEFINITE);
		
	}
	
	public void render(GraphicsContext g, GameModel gameModel, Image tileTexture) {
		
		Tile[][] tiles = gameModel.getTiles();
		
		for(int rowIndex = 0; rowIndex < tiles.length; rowIndex++) {
			
			Tile[] row = tiles[rowIndex];
			
			for(int colIndex = 0; colIndex < row.length; colIndex++) {
				
				Tile tile = row[colIndex];
				
				if(tile == null) {
					g.setFill(Color.BLACK);
					g.fillRoundRect((double) colIndex * tileTexture.getWidth(), (double) rowIndex * tileTexture.getHeight(), 
							tileTexture.getWidth(), tileTexture.getHeight(), TILE_ARC_SIZE, TILE_ARC_SIZE);
				} else {
					tileRender.render(g, tile, tileTexture);
				}
				
			}
		}
		
		// Rendering this first allows it to be covered when the active tetromino is close enough.
		tetrominoRender.renderGhostTetromino(g, gameModel, tileTexture.getWidth(), tileTexture.getHeight());
		
		Entity activeTetromino = gameModel.getActiveTetromino();
		
		PlacementComponent placementComp = activeTetromino.getComponent(PlacementComponent.class);
		
		double originalAlpha = g.getGlobalAlpha();
		
		double newAlpha = originalAlpha;
		
		if(placementComp.isOnGround()) {
			flashTimeline.play();// Continues flashing
			newAlpha = opacity.get();
		} else {
			flashTimeline.pause();
		}
		
		g.setGlobalAlpha(newAlpha);
		
		tetrominoRender.render(g, gameModel.getActiveTetromino(), tileTexture);
		
		g.setGlobalAlpha(originalAlpha);
		
	}
	
}
