package com.lemuelcastro.android.countingcolors;

import android.support.v4.app.Fragment;

public class GameOver extends SingleFragmentActivity{

	@Override
	protected Fragment setupFragment() {
		return new GameOverFragment();
	}

	@Override
	protected int setupMain() {
		return R.layout.activity_main;
	}

}
