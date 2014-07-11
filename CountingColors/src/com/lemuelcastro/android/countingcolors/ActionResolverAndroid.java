package com.lemuelcastro.android.countingcolors;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.util.LruCache;

import com.badlogic.androidgames.framework.Pixmap;

public class ActionResolverAndroid {
	
	private Context mAppContext;
	private LruCache<String, Pixmap> mMemoryCache;
	private ModelClass mClass;

	public ActionResolverAndroid(Context appContext) {

		
		this.mAppContext = appContext;
		setupCache();
	}

	public void showMyList() {
		mAppContext.startActivity(new Intent(mAppContext, ListActivity.class));
	}

	public void showGameOver(int score) {
		Intent i = new Intent(mAppContext, GameOver.class);
		mAppContext.startActivity(i);
	}

	public void setupCache() {

		ActivityManager am = (ActivityManager) mAppContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		int memClassBytes = am.getMemoryClass() * 1024 * 1024;
		int cacheSize = memClassBytes / 8;

		mMemoryCache = new LruCache<String, Pixmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Pixmap bitmap) {
				// The cache size will be measured in kilobytes rather than
				// number of items.
				return bitmap.getWidth() * bitmap.getHeight();

			}
		};
	}

	public Pixmap obtainPixmap(String key) {
		Pixmap pix = mMemoryCache.get(key);

		return pix != null ? pix : null;
	}

	public void placeTo(String key, Pixmap pix) {
		mMemoryCache.put(key, pix);
	}

	public void saveScore(int score) throws Exception {
		mClass = new ModelClass();
		mClass.setScore(Integer.toString(score));

		ModelSingleton.get(mAppContext).addDetails(mClass);
		ModelSingleton.get(mAppContext).saveDetails();
	}

}