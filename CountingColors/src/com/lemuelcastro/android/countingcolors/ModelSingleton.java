package com.lemuelcastro.android.countingcolors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONException;

import android.content.Context;
import android.util.Log;

public class ModelSingleton {

	private static final String FILENAME = "crimes.json";

	private ArrayList<ModelClass> mModelClasses;

	private JSONSerializer mJsonSerializer;

	private static ModelSingleton sModelSingleton;
	private Context mContext;
	static final String Tag = "MODELSINGLETON";

	public ModelSingleton(Context context) {
		mContext = context;
		mJsonSerializer = new JSONSerializer(mContext, FILENAME);

		try {
			Log.i(Tag, "Calling Loader");
			mModelClasses = mJsonSerializer.loadDetails();
			Log.i(Tag, mModelClasses.toString());
		} catch (Exception e) {
			Log.i(Tag, "aint doing it");
			mModelClasses = new ArrayList<ModelClass>();
			e.printStackTrace();
		}
	}

	public static ModelSingleton get(Context c) throws Exception {
		if (sModelSingleton == null) {
			sModelSingleton = new ModelSingleton(c.getApplicationContext());
			Log.i(Tag, sModelSingleton.toString());
		}

		return sModelSingleton;

	}

	public void deleteDetails(int position) throws Exception {
		mJsonSerializer = new JSONSerializer(mContext, FILENAME);
		mModelClasses = mJsonSerializer.deleteDetails(position);
	}

	public ArrayList<ModelClass> getDetails() {
		return mModelClasses;
	}

	public void addDetails(ModelClass c) {
		mModelClasses.add(c);
		Log.i(Tag, "RESULTING CLASSES " + mModelClasses.toString());
	}

	public ModelClass getDetail(UUID id) {
		return null;
	}

	public boolean saveDetails() throws JSONException, IOException {
		Log.i(Tag, "SIZE NOW IS " + mModelClasses.size());
		mJsonSerializer.saveDetails(mModelClasses);
		return true;
	}
	
	

}
