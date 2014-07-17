package com.lemuelcastro.android.countingcolorsgl;

import com.lemuelcastro.android.countingcolors.R;

import android.support.v4.app.Fragment;

public class Menu extends SingleFragmentActivity {

	@Override
	protected Fragment setupFragment() {
		GameScreenSingleton.setNull();
		return new MenuFragment();
	}

	@Override
	protected int setupMain() {
		return R.layout.activity_main;
	}

}
