/*
 * checks coordinate if onbounds or not
 * */

package com.lemuelcastro.android.countingcolorsgl;

import com.badlogic.androidgames.framework.Input.TouchEvent;

public final class TouchUtils {

	private TouchUtils() {
		// left blank
	}

	public static boolean inBounds(TouchEvent event, int x, int y, int width,
			int height, float dimension[]) {
		
		//since coordinates are bases on resolutions 1080x1920
		//touch coordinates is computed using
		//(1080)/nativeResolution * touchPoint
		//for height (1920)/nativeResolution * touchPoint
		return (((int) ((1080.0f / dimension[0]) * event.x)) > x
				&& ((int) ((1080.0f / dimension[0]) * event.x)) < x + width - 1
				&& dimension[1] - event.y > y && dimension[1] - event.y < y
				+ height - 1);

	}
}
