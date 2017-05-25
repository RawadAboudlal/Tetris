package com.rawad.tetris.entity;

import com.rawad.gamehelpers.game.entity.Component;


/**
 * @author Rawad
 *
 */
public class RenderingComponent extends Component {
	
	private int color;
	
	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}
	
	/**
	 * @param color the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.entity.Component#copyData(com.rawad.gamehelpers.game.entity.Component)
	 */
	@Override
	public Component copyData(Component comp) {
		
		if(comp instanceof RenderingComponent) {
			
			RenderingComponent renderingComp = (RenderingComponent) comp;
			
			renderingComp.setColor(getColor());
			
		}
		
		return comp;
		
	}
	
}
