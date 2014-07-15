package com.lemuelcastro.android.countingcolorsgl;

import java.io.Serializable;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class ModelClass implements Serializable, Comparable<ModelClass> {

	private static final String JSON_UID = "id", JSON_SCORE = "score";

	private String mScore, mHId;

	private UUID mId;

	public String uuid() {
		return mHId;
	}

	public ModelClass() {
		mId = UUID.randomUUID();
	}

	public ModelClass(JSONObject jsonObject) throws JSONException {

		mScore = jsonObject.getString(JSON_SCORE);
		mHId = jsonObject.getString(JSON_UID);

	}

	public JSONObject toJsonObject() throws JSONException {

		JSONObject jsonObject = new JSONObject();

		if (mId == null)
			mId = UUID.randomUUID();

		jsonObject.put(JSON_UID, mId.toString());
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
		return Integer.parseInt(another.mScore)-Integer.parseInt(this.mScore);
	}

}
