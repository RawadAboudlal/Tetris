package com.rawad.tetris.client.renderengine;

import com.rawad.gamehelpers.client.renderengine.LayerRender;
import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.tetris.game.GameModel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * @author Rawad
 *
 */
public class GameRender extends LayerRender {
	
	private GameModel gameModel;
	
	private GameBoardRender gameBoardRender = new GameBoardRender();
	private TetrominoRender tetrominoRender = new TetrominoRender();
	
	private Image tileTexture;
	
	private Canvas gameBoardCanvas;
	private Canvas holdCanvas;
	private Canvas[] nextCanvases;
	
	private boolean hideTetrominoes = false;
	
	/**
	 * @param gameModel
	 * @param tileTexture The texture of a single tile that makes up a tetromino.
	 * @param gameModelCanvas
	 * @param holdCanvas
	 * @param nextCanvases
	 */
	public GameRender(GameModel gameModel, Image tileTexture, Canvas gameModelCanvas, Canvas holdCanvas, 
			Canvas... nextCanvases) {
		super();
		
		this.gameModel = gameModel;
		
		this.tileTexture = tileTexture;
		
		this.gameBoardCanvas = gameModelCanvas;
		this.holdCanvas = holdCanvas;
		this.nextCanvases = nextCanvases;
		
	}

	/**
	 * @see com.rawad.gamehelpers.client.renderengine.Renderable#render()
	 */
	@Override
	public void render() {
		
		fillCanvasBackgrounds(Color.BLACK, nextCanvases);// Could use Util.append() but there is no need in this case.
		fillCanvasBackgrounds(Color.BLACK, gameBoardCanvas, holdCanvas);
		
		if(hideTetrominoes) return;
		
		GraphicsContext g = gameBoardCanvas.getGraphicsContext2D();
		
		fillCanvasBackgrounds(Color.GRAY, gameBoardCanvas);// Needed for that tiled look.
		
		gameBoardRender.render(g, gameModel, tileTexture);
		
		Entity holdTetromino = gameModel.getHoldTetromino();
		
		if(holdTetromino != null)
			tetrominoRender.render(holdCanvas.getGraphicsContext2D(), holdTetromino, tileTexture, holdCanvas.getWidth());
		
		Entity[] nextTetrominoes = new Entity[gameModel.getNextTetrominoes().size()];
		
		gameModel.getNextTetrominoes().toArray(nextTetrominoes);
		
		for(int i = 0; i < nextCanvases.length; i++) {
			
			Canvas nextCanvas = nextCanvases[i];
			Entity nextTetromino = nextTetrominoes[i];
			
			if(nextTetromino == null) break;
			
			GraphicsContext nextG = nextCanvas.getGraphicsContext2D();
			
			tetrominoRender.render(nextG, nextTetromino, tileTexture, nextCanvas.getWidth());
			
		}
		
	}
	
	private void fillCanvasBackgrounds(Color fill, Canvas... canvases) {
		
		for(Canvas canvas: canvases) {
			
			GraphicsContext g = canvas.getGraphicsContext2D();
			
			g.setFill(fill);
			g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			
		}
		
	}
	
	/**
	 * @param hideTetrominoes the hideTetrominoes to set
	 */
	public void setHideTetrominoes(boolean hideTetrominoes) {
		this.hideTetrominoes = hideTetrominoes;
	}
	
}
