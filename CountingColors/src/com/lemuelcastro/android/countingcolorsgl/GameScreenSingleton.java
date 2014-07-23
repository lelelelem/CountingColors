package com.lemuelcastro.android.countingcolorsgl;

import com.badlogic.androidgames.framework.Game;

public class GameScreenSingleton {

	private GameScreen mGameScreen;

	private static GameScreenSingleton sGameScreenSingleton;

	private GameScreenSingleton(GameScreen gS) {
		mGameScreen = gS;
	}

	public static GameScreenSingleton get(Game game, ActionResolverAndroid actionResolverAndroid) throws Exception {
		if (sGameScreenSingleton == null) {
			sGameScreenSingleton = new GameScreenSingleton(new GameScreen(game, actionResolverAndroid));
		}
		return sGameScreenSingleton;
	}

	public GameScreen getGameScreen() {
		return mGameScreen;
	}
	
	public static void setNull(){
		sGameScreenSingleton = null;
	}
}
