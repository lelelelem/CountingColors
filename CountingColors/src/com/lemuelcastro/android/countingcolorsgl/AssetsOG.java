package com.lemuelcastro.android.countingcolorsgl;

import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLGame;

public class AssetsOG {

	public static Texture bg;
	public static TextureRegion bgRegion;
	public static Texture tile;
	public static TextureRegion blueTile;
	public static TextureRegion redTile;

	public static Texture numbers;
	public static Texture numberTiles;

	public static Texture scores;

	public static void loader(GLGame glGame) {

		bg = new Texture(glGame, "background2.png");
		bgRegion = new TextureRegion(bg, 0, 0, 1080, 1920);

		tile = new Texture(glGame, "tileTile.png");
		redTile = new TextureRegion(tile, 0, 0, 268, 400);
		blueTile = new TextureRegion(tile, 268, 0, 268, 400);

		numbers = new Texture(glGame, "numbers.png");

		scores = new Texture(glGame, "numbersscore.png");

	}

	public static void reload() {
		bg.reload();
	}
}
