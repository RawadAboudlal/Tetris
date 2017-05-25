package com.rawad.tetris.entity;

import com.rawad.gamehelpers.game.entity.Component;


/**
 * @author Rawad
 *
 */
public class TranslateComponent extends Component {

	private int x = 0;
	private int y = 0;
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.entity.Component#copyData(com.rawad.gamehelpers.game.entity.Component)
	 */
	@Override
	public Component copyData(Component comp) {
		
		if(comp instanceof TranslateComponent) {
			
			TranslateComponent positionComp = (TranslateComponent) comp;
			
			positionComp.setX(getX());
			positionComp.setY(getY());
			
		}
		
		return comp;
		
	}
	
}
