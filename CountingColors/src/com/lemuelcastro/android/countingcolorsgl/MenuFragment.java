package com.lemuelcastro.android.countingcolorsgl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.lemuelcastro.android.countingcolors.R;

public class MenuFragment extends Fragment {

	private View.OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i;
			switch (v.getId()) {
			case R.id.ngame:
				i = new Intent(getActivity(), MainActivityGL.class);
				startActivity(i);
				break;
			case R.id.hscore:
				i = new Intent(getActivity(), ListActivity.class);
				startActivity(i);
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

}
