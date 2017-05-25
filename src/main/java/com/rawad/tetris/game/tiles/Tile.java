package com.rawad.tetris.game.tiles;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Rawad
 *
 */
@XmlRootElement
public class Tile {
	
	private int x;
	private int y;
	
	// Loaded once the blueprint is loaded (by a FileParseEventHandler). Actual value taken from RenderingComponent.
	private int color;
	
	/**
	 * @param x
	 * @param y
	 * @param color
	 */
	public Tile(int x, int y, int color) {
		super();
		
		this.x = x;
		this.y = y;
		
		this.color = color;
		
	}
	
	/**
	 * @param x
	 * @param y
	 */
	public Tile(int x, int y) {
		this(x, y, 0);
	}
	
	public Tile() {
		this(0, 0);
	}
	
	public Tile(Tile other) {
		this(other.getX(), other.getY(), other.getColor());
	}
	
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}
	
	/**
	 * @param color the color to set
	 */
	@XmlTransient
	public void setColor(int color) {
		this.color = color;
	}
	
}
