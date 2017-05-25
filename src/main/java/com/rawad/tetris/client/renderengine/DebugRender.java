package com.rawad.tetris.client.renderengine;

import com.rawad.gamehelpers.client.renderengine.LayerRender;
import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.geometry.Rectangle;
import com.rawad.tetris.util.BoundingBoxCalculator;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;


/**
 * @author Rawad
 *
 */
public class DebugRender extends LayerRender {
	
	private GameModel gameModel;
	private Image tileTexture;
	
	private Canvas gameCanvas;
	
	/**
	 * @param gameModel
	 * @param gameCanvas
	 * @param tileTexture
	 */
	public DebugRender(GameModel gameModel, Image tileTexture, Canvas gameCanvas) {
		super();
		
		this.gameModel = gameModel;
		this.tileTexture = tileTexture;
		this.gameCanvas = gameCanvas;
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.client.renderengine.Renderable#render()
	 */
	@Override
	public void render() {
		
		Entity activeTetromino = gameModel.getActiveTetromino();
		
		Rectangle boundingBox = BoundingBoxCalculator.getBoundingBox(activeTetromino.getComponent(TilesComponent.class));
		TranslateComponent translateComp = activeTetromino.getComponent(TranslateComponent.class);
		
		GraphicsContext g = gameCanvas.getGraphicsContext2D();
		
		Affine transform = g.getTransform();
		
		double width = tileTexture.getWidth();
		double height = tileTexture.getHeight();
		
		g.translate(translateComp.getX() * width, translateComp.getY() * height);
		
		g.setStroke(Color.RED);
		g.strokeRect(boundingBox.getX1() * width, boundingBox.getY1() * height, boundingBox.getWidth() * width, 
				boundingBox.getHeight() * height);
		
		g.setTransform(transform);
		
	}
	
}
