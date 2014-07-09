package com.lemuelcastro.android.countingcolors;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class MainActivity extends AndroidGame {

	private static final String TAG_STRING = "AndroidGame";
	private ActionResolverAndroid mActionResolverAndroid;
	
	
	@Override
	public Screen getStartScreen() {
		mActionResolverAndroid = new ActionResolverAndroid(this);
		return new MainMenu(this, mActionResolverAndroid);
	}
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}
	
}
