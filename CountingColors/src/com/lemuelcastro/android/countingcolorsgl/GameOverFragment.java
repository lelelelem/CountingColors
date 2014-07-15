package com.lemuelcastro.android.countingcolorsgl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lemuelcastro.android.countingcolors.R;

public class GameOverFragment extends Fragment {

	public static final String SCORE = "score";
	
	private View.OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = null;
			switch(v.getId()){
			case R.id.ngame:
				i = new Intent(getActivity(), Menu.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				break;
			case R.id.nG:
				i = new Intent(getActivity(), MainActivityGL.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				break;
			}
			startActivity(i);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.gameover, container, false);


		TextView score;
		Button highScoreButton, newGameButton;
		
		score = (TextView) v.findViewById(R.id.score);

		score.setText(Integer.toString(getActivity().getIntent().getIntExtra(SCORE,0)));

		highScoreButton = (Button) v.findViewById(R.id.ngame);

		highScoreButton.setOnClickListener(mClickListener);
		
		newGameButton = (Button)v.findViewById(R.id.nG);
		newGameButton.setOnClickListener(mClickListener);
		
		return v;
	}
	
	

}
