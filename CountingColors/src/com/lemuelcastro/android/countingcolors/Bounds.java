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
	xBounds4thLeft(804),
	score1st(0),
	score2nd(31),
	score3rd(62),
	score4th(93),
	score5th(124),
	scoreTop(0),
	scoreBottom(42);
	
	
	private final int coordinate;
	
	private Bounds(int value){
		coordinate = value;
	}
	
	public int getCoor (){
		return coordinate;
	}
}
