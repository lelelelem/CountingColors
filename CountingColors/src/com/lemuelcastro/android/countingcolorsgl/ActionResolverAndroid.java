package com.lemuelcastro.android.countingcolorsgl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

@SuppressLint("InlinedApi")
public class ActionResolverAndroid {

	private Context mAppContext;

	public ActionResolverAndroid(Context appContext) {
		this.mAppContext = appContext;
	}

	public void showMyList() {
		mAppContext.startActivity(new Intent(mAppContext, ListActivity.class));
	}

	public void showGameOver(int score) {
		Intent i = new Intent(mAppContext, GameOver.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.putExtra(GameOverFragment.SCORE, score);
		mAppContext.startActivity(i);
	}

	public void showMenu() {
		Intent i = new Intent(mAppContext, Menu.class);

		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		mAppContext.startActivity(i);
	}

	public void saveScore(int score) throws Exception {
		if (score == 0)
			return;
		ModelClass mClass = new ModelClass();
		mClass.setScore(Integer.toString(score));

		ModelSingleton.get(mAppContext).addDetails(mClass);
		ModelSingleton.get(mAppContext).saveDetails();
	}

}