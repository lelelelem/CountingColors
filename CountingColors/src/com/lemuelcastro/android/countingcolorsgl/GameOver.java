package com.lemuelcastro.android.countingcolorsgl;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.lemuelcastro.android.countingcolors.R;

public class GameOver extends SingleFragmentActivity{

	@Override
	protected Fragment setupFragment() {
		return new GameOverFragment();
	}

	@Override
	protected int setupMain() {
		return R.layout.activity_main;
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(this,Menu.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}
	

}
