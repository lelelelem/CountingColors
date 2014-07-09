/*
 * Holds Bounds for Tile Coordinates
 * 
 */


package com.lemuelcastro.android.countingcolors;

public enum Bounds {

	yBoundsTop(1640),
	yBoundsBottom(1852),
	xBounds1stLeft(0),
	xBounds2ndLeft(268),
	xBounds3rdLeft(536),
	xBounds4thLeft(804);
	
	private final int coordinate;
	
	private Bounds(int value){
		coordinate = value;
	}
	
	public int getCoor (){
		return coordinate;
	}
}
