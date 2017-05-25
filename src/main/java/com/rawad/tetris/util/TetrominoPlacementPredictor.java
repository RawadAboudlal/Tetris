package com.rawad.tetris.util;

import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.game.GameModel;

/**
 * @author Rawad
 *
 */
public final class TetrominoPlacementPredictor {
	
	/**
	 * 
	 * @param gameModel
	 * @return Y value of where the active tetromino from {@code gameModel} is expected to be if it is moved down as far as 
	 * possible. before colliding with the rest of the board.
	 */
	public static int getPredictedPlacementPosition(GameModel gameModel) {
		
		// If more data is needed, a copy of either the translate component OR the entire entity can be returned instead.
		
		// Use while or something, do some pre-checks in that case though.
		Entity activeTetromino = gameModel.getActiveTetromino();
		
		TilesComponent tilesComp = activeTetromino.getComponent(TilesComponent.class);
		TranslateComponent translateComp = activeTetromino.getComponent(TranslateComponent.class);
		
		int dy = 0;
		
		while(!CollisionChecker.isColliding(gameModel, tilesComp, translateComp, 0, dy)) {
			dy++;
		}
		
		return translateComp.getY() + dy - 1;// -1 moves it back up one because using just dy would make the tetromino collide.
		
	}
	
}
