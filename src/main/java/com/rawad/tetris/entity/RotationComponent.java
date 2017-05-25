package com.rawad.tetris.entity;

import javax.xml.bind.annotation.XmlTransient;

import com.rawad.gamehelpers.game.entity.Component;


/**
 * @author Rawad
 *
 */
public class RotationComponent extends Component {
	
	private double centerX = 0;
	private double centerY = 0;
	
	private int totalRotated = 0;
	
	/**
	 * @return the centerX
	 */
	public double getCenterX() {
		return centerX;
	}
	
	/**
	 * @param centerX the centerX to set
	 */
	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}
	
	/**
	 * @return the centerY
	 */
	public double getCenterY() {
		return centerY;
	}
	
	/**
	 * @param centerY the centerY to set
	 */
	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}
	
	/**
	 * @return the totalRotated
	 */
	public int getTotalRotated() {
		return totalRotated;
	}
	
	/**
	 * @param totalRotated the totalRotated to set
	 */
	@XmlTransient public void setTotalRotated(int totalRotated) {
		this.totalRotated = totalRotated;
	}
	
	/**
	 * @see com.rawad.gamehelpers.game.entity.Component#copyData(com.rawad.gamehelpers.game.entity.Component)
	 */
	@Override
	public Component copyData(Component comp) {
		
		if(comp instanceof RotationComponent) {
			
			RotationComponent rotationComp = (RotationComponent) comp;
			
			rotationComp.setCenterX(getCenterX());
			rotationComp.setCenterY(getCenterY());
			
		}
		
		return comp;
		
	}
	
}
