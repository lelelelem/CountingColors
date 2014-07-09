package com.lemuelcastro.android.countingcolors;

import android.support.v4.app.Fragment;

public class ListActivity extends SingleFragmentActivity{

	@Override
	protected Fragment setupFragment() {
		Assets.blueButton.dispose();
		Assets.redButton.dispose();
		Assets.background.dispose();
		return new ListFragment();
	}

}
