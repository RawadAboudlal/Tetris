package com.rawad.tetris.game.event.listeners;

import java.util.ArrayList;

import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.gamehelpers.game.event.Event;
import com.rawad.gamehelpers.game.event.EventManager;
import com.rawad.gamehelpers.game.event.Listener;
import com.rawad.tetris.entity.ETetrominoes;
import com.rawad.tetris.entity.IdComponent;
import com.rawad.tetris.entity.MovementComponent;
import com.rawad.tetris.entity.TranslateComponent;
import com.rawad.tetris.entity.movement.Movement;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.event.EventType;
import com.rawad.tetris.game.event.LineClearEvent;
import com.rawad.tetris.game.event.LineClearStreakUpdateEvent;
import com.rawad.tetris.game.event.PlaceEvent;
import com.rawad.tetris.game.event.TSpinEvent;
import com.rawad.tetris.game.tiles.Tile;


/**
 * @author Rawad
 *
 */
public class LineClearCheckHandler implements Listener {
	
	/* This is used for calculating a T-spin. The center tile will always have the x,y coords 1,1 (regardless of rotation). 
	 * We can use this to then find at least 3 diagonals around this center (offset by TranslateComponent). If there's
	 * exactly 3 blocks (more would be impossible, less isn't a proper T-spin) then we found a T-spin.
	 */
	private static final int T_CENTER_X = 1;
	private static final int T_CENTER_Y = 1;
	
	private static final int OCCUPIED_DIAGONALS_FOR_T_SPIN = 3;
	
	private EventManager eventManager;
	private GameModel gameModel;
	
	/**
	 * @param eventManager
	 * @param gameModel
	 */
	public LineClearCheckHandler(EventManager eventManager, GameModel gameModel) {
		super();
		
		this.eventManager = eventManager;
		this.gameModel = gameModel;
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.event.Listener#onEvent(com.rawad.gamehelpers.game.event.Event)
	 */
	@Override
	public void onEvent(Event ev) {
		
		if(ev.getEventType() == EventType.TETROMINO_PLACE) {
			
			PlaceEvent placeEvent = (PlaceEvent) ev;
			
			Integer[] fullLines = getFullLines();
			
			boolean isTSpin = isTSpin(placeEvent.getPlacedTetromino());
			
			// If the placed block caused a T spin then it can be given 100% credit for any lines cleared.
			if(isTSpin) eventManager.submitEvent(new TSpinEvent(fullLines.length));
			
			if(fullLines.length > 0) {
				eventManager.submitEvent(new LineClearEvent(fullLines));
				// Increments the streak when lines are cleared.
				gameModel.setLineClearStreak(gameModel.getLineClearStreak() + 1);
				
				eventManager.submitEvent(new LineClearStreakUpdateEvent(gameModel.getLineClearStreak()));
				
			} else {
				// Resets the streak when no lines were cleared as a result of this PlaceEvent.
				gameModel.setLineClearStreak(0);
				
				eventManager.submitEvent(new LineClearStreakUpdateEvent(gameModel.getLineClearStreak()));
				
			}
			
		}
		
	}
	
	private Integer[] getFullLines() {
		
		Tile[][] tiles = gameModel.getTiles();
		
		ArrayList<Integer> fullRows = new ArrayList<Integer>();
		
		rowsLoop:
		for(int y = tiles.length - 1; y > 0; y--) {
			
			Tile[] row = tiles[y];
			
			for(int x = 0; x < row.length; x++) {
				// If an empty tile is in a row, continue to next row up.
				if(gameModel.getTile(x, y) == null) continue rowsLoop;
			}
			
			// If we makee it here, a full row has been found.
			fullRows.add(y);
			
		}
		
		return fullRows.toArray(new Integer[fullRows.size()]);
		
	}
	
	private boolean isTSpin(Entity placedTetromino) {
		
		IdComponent idComp = placedTetromino.getComponent(IdComponent.class);
		MovementComponent movementComp = placedTetromino.getComponent(MovementComponent.class);
		
		// Ensure this is the T tetromino before continuing check. Last move has to also be a rotate.
		if(ETetrominoes.getTetrominoByName(idComp.getId()) != ETetrominoes.T ||
				movementComp.getPrevMovement() != Movement.ROTATE) return false;
		
		TranslateComponent translateComp = placedTetromino.getComponent(TranslateComponent.class);
		
		int tCenterX = translateComp.getX() + T_CENTER_X;
		int tCenterY = translateComp.getY() + T_CENTER_Y;
		
		int[] diagonalOffsets = {
				-1, 1
		};
		
		int occupiedDiagonals = 0;
		
		for(int xOffset: diagonalOffsets) {
			for(int yOffset: diagonalOffsets) {
				
				int newX = tCenterX + xOffset;
				int newY = tCenterY + yOffset;
				
				if(newX < 0 || newX >= gameModel.getWidth() || newY < 0 || newY >= gameModel.getHeight()) {
					// Now that T-spins can only occur after a rotation, both walls and ground are considered occupied tiles.
					occupiedDiagonals++;
					continue;
				}
				
				if(gameModel.getTile(newX, newY) != null) occupiedDiagonals++;
				
			}
		}
		
		if(occupiedDiagonals == OCCUPIED_DIAGONALS_FOR_T_SPIN) return true;
		
		return false;
		
	}
	
}
