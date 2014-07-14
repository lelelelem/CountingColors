package com.lemuelcastro.android.countingcolorsgl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.GLGame;

public class MainActivityGL extends GLGame {
	boolean firstTimeCreate = true;
	private ActionResolverAndroid mActionResolverAndroid;

	public Screen getStartScreen() {
		mActionResolverAndroid = new ActionResolverAndroid(getApplication());
		return new GameScreen(this,mActionResolverAndroid);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		if (firstTimeCreate) {
			AssetsOG.loader(this);
			firstTimeCreate = false;
		} else {
			AssetsOG.reload();
		}
	}

	@Override
	public void onPause() {
		super.onPause();

	}
}
