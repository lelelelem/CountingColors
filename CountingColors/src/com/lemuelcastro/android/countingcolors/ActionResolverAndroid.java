package com.lemuelcastro.android.countingcolors;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

public class ActionResolverAndroid implements ActionResolver {
	Handler uiThread = new Handler();
	Context appContext;

	
	public ActionResolverAndroid(Context appContext){
		uiThread = new Handler();
		this.appContext = appContext;
	}
	
	@Override
	public void showMyList() {
		appContext.startActivity(new Intent(appContext,ListActivity.class));
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

}