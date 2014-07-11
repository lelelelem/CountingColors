package com.lemuelcastro.android.countingcolors;

import android.support.v4.app.Fragment;

public class ListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment setupFragment() {
		return new ListFragment();
	}

	@Override
	protected int setupMain() {
		return R.layout.activity_main_list;
	}

}
