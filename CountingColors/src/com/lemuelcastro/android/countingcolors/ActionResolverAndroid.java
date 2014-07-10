package com.lemuelcastro.android.countingcolors;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.widget.Toast;

import com.badlogic.androidgames.framework.Pixmap;

public class ActionResolverAndroid implements ActionResolver {
	private Handler uiThread = new Handler();
	private Context appContext;
	private LruCache<String, Pixmap> mMemoryCache;
	private ModelClass mClass;

	public ActionResolverAndroid(Context appContext) {

		uiThread = new Handler();
		this.appContext = appContext;
		setupCache();
	}

	@Override
	public void showMyList() {
		appContext.startActivity(new Intent(appContext, ListActivity.class));
	}

	public void showGameOver(int score) {
		Intent i =new Intent(appContext, GameOver.class);
		
		
		appContext.startActivity(i);
	}

	@Override
	public void showToast() {

		uiThread.post(new Runnable() {

			@Override
			public void run() {

				Toast.makeText(appContext, "memememe", Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	public void setupCache() {

		ActivityManager am = (ActivityManager) appContext
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
		
		ModelSingleton.get(appContext).addDetails(mClass);
		ModelSingleton.get(appContext).saveDetails();
	}

}