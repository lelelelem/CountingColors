package com.lemuelcastro.android.countingcolorsgl;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class ModelClass implements Serializable, Comparable<ModelClass> {

	private static final String JSON_SCORE = "score";

	private String mScore;

	// thinking of using static factory instead
	public ModelClass() {
		// used for initialization
	}

	public ModelClass(JSONObject jsonObject) throws JSONException {

		mScore = jsonObject.getString(JSON_SCORE);
	}

	public JSONObject toJsonObject() throws JSONException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(JSON_SCORE, mScore);

		return jsonObject;
	}

	public String getScore() {
		return mScore;
	}

	public void setScore(String score) {
		mScore = score;
	}

	@Override
	public int compareTo(ModelClass another) {
		return Integer.parseInt(another.mScore) - Integer.parseInt(this.mScore);
	}

}
