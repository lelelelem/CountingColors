package com.lemuelcastro.android.countingcolorsgl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.GLGame;

public class MainActivityGL extends GLGame {

	private boolean firstTimeCreate = true;
	private GameScreen gS;

	public Screen getStartScreen() {
		ActionResolverAndroid mActionResolverAndroid = new ActionResolverAndroid(
				this);
		try {
			gS = GameScreenSingleton.get(this, mActionResolverAndroid)
					.getGameScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gS;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		super.onSurfaceCreated(gl, config);
		if (firstTimeCreate) {
			AssetsOG.loader(this);
			firstTimeCreate = false;
		}
	}

	@Override
	public void onBackPressed() {
		gS.setPaused(true);
		// create dialog before exit
		new AlertDialog.Builder(this).setTitle("Exit?")
				.setMessage("Exiting to the Main Menu Wont Save Yer Score")
				.setPositiveButton("Yes", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent i = new Intent(getApplication(),
								MenuActivity.class);
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
					}
				}).setNegativeButton("Continue Game", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						gS.setPaused(false);
					}
				}).create().show();
	}

	@Override
	public void onResume() {
		super.onResume();
		ActionResolverAndroid mActionResolverAndroid = new ActionResolverAndroid(
				this);
		try {
			gS = GameScreenSingleton.get(this, mActionResolverAndroid)
					.getGameScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
		firstTimeCreate = true;
	}
}
