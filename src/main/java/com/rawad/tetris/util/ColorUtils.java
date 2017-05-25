package com.rawad.tetris.util;

import javafx.scene.paint.Color;

/**
 * @author Rawad
 *
 */
public final class ColorUtils {
	
	/** Number of bits reserved to represent each color channel. e.g. for the range 0-255, 8 bits are needed. */
	private static final int BITS_PER_CHANNEL = 8;
	
	/** 
	 * Max positive number that can be represented using {@link #BITS_PER_CHANNEL} bits; <code> 2<sup>BITS_PER_CHANNEL</sup>
	 *  - 1</code>.
	 */
	private static final double MAX_FROM_BITS = Math.pow(2, 8) - 1;
	
	public static int toInteger(Color color) {
		
		int red = (int) (color.getRed() * MAX_FROM_BITS);
		int green = (int) (color.getGreen() * MAX_FROM_BITS);
		int blue = (int) (color.getBlue() * MAX_FROM_BITS);
		int alpha = (int) (color.getOpacity() * MAX_FROM_BITS);
		
		if(Integer.bitCount(alpha) < BITS_PER_CHANNEL) alpha = (int) MAX_FROM_BITS;
		
		int rgba = red;
		rgba = (rgba << BITS_PER_CHANNEL) + green;
		rgba = (rgba << BITS_PER_CHANNEL) + blue;
		rgba = (rgba << BITS_PER_CHANNEL) + alpha;
		
		return rgba;
	}
	
	public static Color toColor(int rgba) {
		
		double alpha = (double) (rgba & (int) MAX_FROM_BITS) / MAX_FROM_BITS;
		rgba >>= BITS_PER_CHANNEL;
		double blue = (double) (rgba & (int) MAX_FROM_BITS) / MAX_FROM_BITS;
		rgba >>= BITS_PER_CHANNEL;
		double green = (double) (rgba & (int) MAX_FROM_BITS) / MAX_FROM_BITS;
		rgba >>= BITS_PER_CHANNEL;
		double red = (double) (rgba & (int) MAX_FROM_BITS) / MAX_FROM_BITS;
		
		return new Color(red, green, blue, alpha);
		
	}
	
}
