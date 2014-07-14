package com.lemuelcastro.android.countingcolorsgl;

import com.lemuelcastro.android.countingcolors.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;
import android.view.WindowManager;

public abstract class SingleFragmentActivity extends FragmentActivity {

	//set up fragment activity
	protected abstract Fragment setupFragment();
	
	//setups activity layout
	protected abstract int setupMain();
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle arg0) {
	
		
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		super.onCreate(arg0);
		setContentView(setupMain());
		
		FragmentManager fm=getSupportFragmentManager();
		Fragment fragment=fm.findFragmentById(R.id.main);
		
		fragment = setupFragment();
		
		fm.beginTransaction().add(R.id.main,fragment).commit();
		
	}
	
	
}
