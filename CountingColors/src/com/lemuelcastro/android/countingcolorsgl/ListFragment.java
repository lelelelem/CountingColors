package com.lemuelcastro.android.countingcolorsgl;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lemuelcastro.android.countingcolors.R;

public class ListFragment extends android.support.v4.app.ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayList<ModelClass> mModelClass=null;
		
		getActivity().setTitle("Details..Details..and Details..");

		try {
			mModelClass = ModelSingleton.get(getActivity()).getDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ForListAdapter adapter = new ForListAdapter(mModelClass);
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);

		ListView listView = (ListView) view.findViewById(android.R.id.list);

		registerForContextMenu(listView);

		return view;

	}

	private class ForListAdapter extends ArrayAdapter<ModelClass> {

		public ForListAdapter(ArrayList<ModelClass> modelClasses) {
			super(getActivity(), 0, modelClasses);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView scoreTextView;

			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.fragment_main_odd, null);
			}

			ModelClass c = getItem(position);
			scoreTextView = (TextView) convertView.findViewById(R.id.score);
			scoreTextView.setText(c.getScore());

			return convertView;
		}
	}

}
