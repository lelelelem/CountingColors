package com.lemuelcastro.android.countingcolorsgl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import org.json.JSONException;

import android.content.Context;

public class ModelSingleton {

	private static final String FILENAME = "crimes.json";

	private ArrayList<ModelClass> mModelClasses;

	private TreeMap<Integer, Integer> mHigh;
	private JSONSerializer mJsonSerializer;

	private static ModelSingleton sModelSingleton;
	private Context mContext;

	private ModelSingleton(Context context) {
		mContext = context;
		mJsonSerializer = new JSONSerializer(mContext, FILENAME);

		try {
			mModelClasses = highScores();
		} catch (Exception e) {
			mModelClasses = new ArrayList<ModelClass>();
			e.printStackTrace();
		}
	}

	public static ModelSingleton get(Context c) throws Exception {
		if (sModelSingleton == null) {
			sModelSingleton = new ModelSingleton(c.getApplicationContext());
		}

		return sModelSingleton;

	}

	public ArrayList<ModelClass> getDetails() {
		return mModelClasses;
	}

	public void addDetails(ModelClass c) {
		mModelClasses.add(c);
	}

	public boolean saveDetails() throws JSONException, IOException {
		mJsonSerializer.saveDetails(mModelClasses);
		return true;
	}

	// ordering of scores in highscore
	public ArrayList<ModelClass> highScores() {
		
		ArrayList<ModelClass> tempModelClasses = new ArrayList<ModelClass>();
		try {
			tempModelClasses = mJsonSerializer.loadDetails();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Collections.sort(tempModelClasses);
		
		return tempModelClasses;

	}

}
