package com.lemuelcastro.android.countingcolorsgl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONException;

import android.content.Context;

public class ModelSingleton {

	private static final String FILENAME = "crimes.json";

	private ArrayList<ModelClass> mModelClasses;

	private JSONSerializer mJsonSerializer;

	private static ModelSingleton sModelSingleton;

	private ModelSingleton(Context context) {
		mJsonSerializer = new JSONSerializer(context, FILENAME);
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
		return highScores();
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
			e1.printStackTrace();
		}

		Collections.sort(tempModelClasses);

		return tempModelClasses;

	}

}
