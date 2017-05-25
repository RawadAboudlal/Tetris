package com.rawad.tetris.entity;

import com.rawad.gamehelpers.game.entity.Component;
import com.rawad.tetris.game.tiles.Tile;

/**
 * @author Rawad
 *
 */
public class TilesComponent extends Component {
	
	private Tile[] tiles = new Tile[0];
	
	/**
	 * @return the tiles
	 */
	public Tile[] getTiles() {
		return tiles;
	}
	
	/**
	 * @param tiles the tiles to set
	 */
	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.entity.Component#copyData(com.rawad.gamehelpers.game.entity.Component)
	 */
	@Override
	public Component copyData(Component comp) {
		
		if(comp instanceof TilesComponent) {
			
			TilesComponent tilesComp = (TilesComponent) comp;
			
			Tile[] newTiles = new Tile[getTiles().length];
			
			for(int i = 0; i < newTiles.length; i++) {
				
				Tile oldTile = getTiles()[i];
				
				newTiles[i] = new Tile(oldTile);
				
			}
			
			tilesComp.setTiles(newTiles);
			
		}
		
		return comp;
		
	}
	
}
