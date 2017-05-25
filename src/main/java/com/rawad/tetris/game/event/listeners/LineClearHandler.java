package com.rawad.tetris.game.event.listeners;

import com.rawad.gamehelpers.game.event.Event;
import com.rawad.gamehelpers.game.event.Listener;
import com.rawad.tetris.game.GameModel;
import com.rawad.tetris.game.event.EventType;
import com.rawad.tetris.game.event.LineClearEvent;
import com.rawad.tetris.game.tiles.Tile;


/**
 * @author Rawad
 *
 */
public class LineClearHandler implements Listener {
	
	private GameModel gameModel;
	
	/**
	 * @param gameModel
	 */
	public LineClearHandler(GameModel gameModel) {
		super();
		
		this.gameModel = gameModel;
		
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.event.Listener#onEvent(com.rawad.gamehelpers.game.event.Event)
	 */
	@Override
	public void onEvent(Event ev) {
		
		if(ev.getEventType() == EventType.LINE_CLEAR) {
			
			LineClearEvent lineClearEvent = (LineClearEvent) ev;
			
			Tile[][] tiles = gameModel.getTiles();
			
			// This is the offset of each subsequent row, in multi-row tetrises, because lower ones are shifted first.
			int yOffset = 0;
			
			for(Integer fullRowIndex: lineClearEvent.getFullRows()) {
				
				for(int y = fullRowIndex + yOffset; y > 0; y--) {
					
					Tile[] row = tiles[y];
					
					boolean rowEmpty = false;
					
					for(int x = 0; x < row.length; x++) {
						
						Tile tile = null;
						
						if(y > 0) {// So we don't try to get a tile above the top row.
							tile = gameModel.getTile(x, y - 1);// Get tile above the current row.
							
							if(tile != null) tile.setY(y);
							
						}
						
						// If the top-most row is full, tile will be null (as expected).
						gameModel.setTile(tile, x, y);
						
					}
					
					// No need to try shift tiles down anymore, for this full row.
					if(rowEmpty) break;
					
				}
				
				// Shift next row down one.
				yOffset++;
				
			}
			
		}
		
	}
	
}
