package com.lemuelcastro.android.countingcolors;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class MainActivity extends AndroidGame {

	private ActionResolverAndroid mActionResolverAndroid;

	@Override
	public Screen getStartScreen() {
		mActionResolverAndroid = new ActionResolverAndroid(this);
		return new GameScreen(this, mActionResolverAndroid);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}

}
