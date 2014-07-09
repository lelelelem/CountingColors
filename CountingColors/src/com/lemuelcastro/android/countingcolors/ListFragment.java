package com.lemuelcastro.android.countingcolors;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListFragment extends android.support.v4.app.ListFragment {

	private ArrayList<ModelClass> mModelClass;

	public static final String MODEL_CLASS = "model";

	private static final String Tag = "LISTFRAGMENT";
	private ImageView mImageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("Details..Details..and Details..");

		try {
			mModelClass = ModelSingleton.get(getActivity()).getDetails();
			Log.i(Tag, "Getting Details Done");
		} catch (Exception e) {
			e.printStackTrace();
		}

		ForListAdapter adapter = new ForListAdapter(mModelClass);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		ModelClass modelClass = ((ForListAdapter) getListAdapter())
				.getItem(position);

		// possible needed we never know

		// Intent intent = new Intent(getActivity(), ShowActivity.class);

		// intent.putExtra(ShowFragment.MODEL_CLASS, modelClass);
		// startActivity(intent);

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
			
			if (convertView == null) { convertView =
			 getActivity().getLayoutInflater().inflate( 
			  R.layout.fragment_main, null); } 
			
			ModelClass c = getItem(position); 
			
			return convertView;
		}
	}

}
