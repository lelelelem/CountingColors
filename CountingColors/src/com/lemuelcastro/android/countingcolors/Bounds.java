/*
 * Holds Bounds for Tile Coordinates
 * 
 */


package com.lemuelcastro.android.countingcolors;

public enum Bounds {

	yBoundsTop(1640),
	yBoundsBottom(1852),
	xBounds1stLeft(0),
	xBounds1stRight(280),
	xBounds2ndLeft(268),
	xBounds2ndRight(775),
	xBounds3rdLeft(536),
	xBounds3rdRight(1265),
	xBounds4thLeft(804),
	xBounds4thRight(1760);
	
	private final int coordinate;
	
	private Bounds(int value){
		coordinate = value;
	}
	
	public int getCoor (){
		return coordinate;
	}
}
