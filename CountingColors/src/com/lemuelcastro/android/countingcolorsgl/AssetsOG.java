package com.lemuelcastro.android.countingcolorsgl;

import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.impl.GLGame;

public class AssetsOG {

	public static Texture tile;

	public static Texture numbers;
	public static Texture numberTiles;

	public static Texture scores;

	public static void loader(GLGame glGame) {

		
		tile = new Texture(glGame, "tileTile.png");
		
		numbers = new Texture(glGame, "numbers.png");

		scores = new Texture(glGame, "numbersscore.png");

	}

	public static void reload() {
	}
}
