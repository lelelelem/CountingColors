package com.lemuelcastro.android.countingcolorsgl;

import com.badlogic.androidgames.framework.Sound;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLGame;

final class AssetsOG {

	public static Texture tile;
	public static Texture numbers;
	public static TextureRegion numbersRegion[] = new TextureRegion[4];

	public static Texture numberTiles;
	public static TextureRegion pressedTile, doNotPressTile;

	public static Texture scores;
	public static TextureRegion scoresRegion[] = new TextureRegion[10];
	public static Sound touch;

	public static void loader(GLGame glGame) {

		tile = new Texture(glGame, "ntileTile.png");
		pressedTile = new TextureRegion(AssetsOG.tile, 256, 0, 256, 256);
		doNotPressTile = new TextureRegion(AssetsOG.tile, 0, 0, 256, 256);

		numbers = new Texture(glGame, "numbers.png");
		for (int i = 0; i < numbersRegion.length; i++) {
			numbersRegion[i] = new TextureRegion(AssetsOG.numbers, i * 268, 0,
					268, 400);
		}

		scores = new Texture(glGame, "numbersscore.png");
		for (int i = 0; i < scoresRegion.length; i++) {
			int row = i < 5 ? 0 : 64;
			scoresRegion[i] = new TextureRegion(AssetsOG.scores, (i % 5) * 51,
					row, 52, 64);
		}

		touch = glGame.getAudio().newSound("touch.wav");

	}

	public static void playSound(Sound sound) {
		sound.play(1);
	}

}
