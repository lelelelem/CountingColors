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

	public float[] getDimension() {
		float dimension[] = {
				mAppContext.getResources().getDisplayMetrics().widthPixels,
				mAppContext.getResources().getDisplayMetrics().heightPixels };
		return dimension;
	}

	public void showGameOver(int score) {
		mAppContext.startActivity(new Intent(mAppContext, GameOver.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).putExtra(
						GameOverFragment.SCORE, score));
	}

	public void showMenu() {
		mAppContext.startActivity(new Intent(mAppContext, MenuActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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