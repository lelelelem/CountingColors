package com.lemuelcastro.android.countingcolorsgl;

import java.util.ArrayList;

import com.lemuelcastro.android.countingcolors.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GameOverFragment extends Fragment {

	private ArrayList<ModelClass> mModelClass;
	private TextView mScore;
	private Button mButton;

	private View.OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.ngame) {
				Intent i = new Intent(getActivity(), Menu.class);
				startActivity(i);
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			mModelClass = ModelSingleton.get(getActivity()).getDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.gameover, container, false);

		mScore = (TextView) v.findViewById(R.id.score);

		mScore.setText(mModelClass.get(mModelClass.size() - 1).getScore());

		mButton = (Button) v.findViewById(R.id.ngame);

		mButton.setOnClickListener(mClickListener);

		return v;
	}

}
