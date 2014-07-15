package com.lemuelcastro.android.countingcolorsgl;

import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLGame;

public class AssetsOG {

	public static Texture tile;

	public static Texture numbers;
	public static TextureRegion numbersRegion[] = new TextureRegion[4];

	public static Texture numberTiles;
	public static TextureRegion pressedTile, doNotPressTile;

	public static Texture scores;
	public static TextureRegion scoresRegion[] = new TextureRegion[10];

	public static void loader(GLGame glGame) {

		tile = new Texture(glGame, "tileTile.png");
		pressedTile = new TextureRegion(AssetsOG.tile, 268, 0, 268, 400);
		doNotPressTile = new TextureRegion(AssetsOG.tile, 0, 0, 268, 400);

		numbers = new Texture(glGame, "numbers.png");
		for (int i = 0; i < numbersRegion.length; i++) {
			numbersRegion[i] = new TextureRegion(AssetsOG.numbers, i * 268, 0,
					268, 400);
		}

		scores = new Texture(glGame, "numbersscore.png");
		for (int i = 0; i < scoresRegion.length; i++) {
			int row = i < 4 ? 0 : 42;
			scoresRegion[i] = new TextureRegion(AssetsOG.scores, (i%5) * 32, row,
					32, 42);
		}

	}

	public static void reload() {
	}
}
