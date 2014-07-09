/*
 * checks coordinate if onbounds or not
 * */

package com.lemuelcastro.android.countingcolors;

import com.badlogic.androidgames.framework.Input.TouchEvent;

public class methods {
	public boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}
}
