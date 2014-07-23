package com.lemuelcastro.android.countingcolorsgl;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.lemuelcastro.android.countingcolors.R;

public class MenuFragment extends Fragment {
	private MediaPlayer mP = null;

	private View.OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ngame:
				mP = MediaPlayer.create(getActivity(), R.raw.click);
				mP.start();
				startActivity(new Intent(getActivity(), MainActivityGL.class));
				break;
			case R.id.hscore:
				mP = MediaPlayer.create(getActivity(), R.raw.click);
				mP.start();
				startActivity(new Intent(getActivity(), ListActivity.class));
				break;
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Button scoreButton, newButton;

		View v = inflater.inflate(R.layout.mmenu, container, false);
		scoreButton = (Button) v.findViewById(R.id.ngame);
		newButton = (Button) v.findViewById(R.id.hscore);

		scoreButton.setOnClickListener(mClickListener);
		newButton.setOnClickListener(mClickListener);

		return v;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mP.stop();
		mP.release();
		mP = null;
	}

}
