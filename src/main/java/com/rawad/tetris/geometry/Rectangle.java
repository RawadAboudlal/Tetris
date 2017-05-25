package com.rawad.tetris.geometry;


/**
 * A bounding box defined by the top left and bottom right coordinates.
 * 
 * @author Rawad
 *
 */
public class Rectangle {
	
	private int x1;
	private int y1;
	
	private int x2;
	private int y2;
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Rectangle(int x1, int y1, int x2, int y2) {
		super();
		
		this.x1 = x1;
		this.y1 = y1;
		
		this.x2 = x2;
		this.y2 = y2;
		
	}
	
	/**
	 * No arguments constructor.
	 */
	public Rectangle() {
		this(0, 0, 0, 0);
	}
	
	public Rectangle(Rectangle other) {
		this(other.getX1(), other.getY1(), other.getX2(), other.getY2());
	}
	
	/**
	 * @return the x1
	 */
	public int getX1() {
		return x1;
	}
	
	/**
	 * @param x1 the x1 to set
	 */
	public void setX1(int x1) {
		this.x1 = x1;
	}
	
	/**
	 * @return the y1
	 */
	public int getY1() {
		return y1;
	}
	
	/**
	 * @param y1 the y1 to set
	 */
	public void setY1(int y1) {
		this.y1 = y1;
	}
	
	/**
	 * @return the x2
	 */
	public int getX2() {
		return x2;
	}
	
	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}
	
	/**
	 * @return the y2
	 */
	public int getY2() {
		return y2;
	}
	
	/**
	 * @param y2 the y2 to set
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	/**
	 * Moves the location of top left and bottom right coordinates of this rectangle by the given dx and dy.
	 * 
	 * @param dx
	 * @param dy
	 */
	public void translate(int dx, int dy) {
		
		setX1(getX1() + dx);
		setX2(getX2() + dx);
		
		setY1(getY1() + dy);
		setY2(getY2() + dy);
		
	}
	
	public int getWidth() {
		return (getX2() - getX1()) + 1;// +1 to include the right bound.
	}
	
	public int getHeight() {
		return (getY2() - getY1()) + 1;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s[%s, %s, %s, %s]", super.toString(), getX1(), getY1(), getX2(), getY2());
	}
	
}
