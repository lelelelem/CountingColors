package com.lemuelcastro.android.countingcolorsgl;

import com.lemuelcastro.android.countingcolors.R;

import android.support.v4.app.Fragment;

public class MenuActivity extends SingleFragmentActivity {

	@Override
	protected Fragment setupFragment() {
		return new MenuFragment();
	}

	@Override
	protected int setupMain() {
		return R.layout.activity_main;
	}

}
