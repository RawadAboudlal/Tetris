package com.rawad.tetris.fileparser.event;

import com.rawad.gamehelpers.fileparser.event.FileParseEvent;
import com.rawad.gamehelpers.game.entity.Entity;
import com.rawad.gamehelpers.game.event.Event;
import com.rawad.gamehelpers.game.event.Listener;
import com.rawad.tetris.entity.RenderingComponent;
import com.rawad.tetris.entity.TilesComponent;
import com.rawad.tetris.game.tiles.Tile;


/**
 * @author Rawad
 *
 */
public class FileParseEventHandler implements Listener {
	
	/**
	 * @see com.rawad.gamehelpers.game.event.Listener#onEvent(com.rawad.gamehelpers.game.event.Event)
	 */
	@Override
	public void onEvent(Event ev) {
		
		if(ev instanceof FileParseEvent) {
			
			FileParseEvent fileParseEvent = (FileParseEvent) ev;
			
			Entity e = fileParseEvent.getEntity();
			
			RenderingComponent renderingComp = e.getComponent(RenderingComponent.class);
			TilesComponent tilesComp = e.getComponent(TilesComponent.class);
			
			if(tilesComp != null && renderingComp != null) {
				handleColorExchange(tilesComp, renderingComp);
			}
			
		}
		
	}
	
	private void handleColorExchange(TilesComponent tilesComp, RenderingComponent renderingComp) {
		
		for(Tile tile: tilesComp.getTiles()) {
			tile.setColor(renderingComp.getColor());
		}
		
	}
	
}
