/*
s * Holds Bounds for Tile Coordinates
 * 
 */

package com.lemuelcastro.android.countingcolorsgl;

public enum Bounds {

	yBoundsTop(1640), yBoundsBottom(1852), xBounds1stLeft(0), xBounds2ndLeft(
			268), xBounds3rdLeft(536), xBounds4thLeft(804), score1st(0), score2nd(
			51), score3rd(102), score4th(153), score5th(206), scoreTop(0), scoreBottom(
			64);

	private final int mCoordinate;

	private Bounds(int value) {
		mCoordinate = value;
	}

	public int getCoor() {
		return mCoordinate;
	}
}
