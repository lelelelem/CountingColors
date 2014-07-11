package com.lemuelcastro.android.countingcolors;

import android.support.v4.app.Fragment;

public class Menu extends SingleFragmentActivity{

	@Override
	protected Fragment setupFragment() {
		// TODO Auto-generated method stub
		return new MenuFragment();
	}

	@Override
	protected int setupMain() {
		// TODO Auto-generated method stub
		return R.layout.activity_main;
	}

}
