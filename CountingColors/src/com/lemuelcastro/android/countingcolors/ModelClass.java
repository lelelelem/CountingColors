package com.lemuelcastro.android.countingcolors;
import java.io.Serializable;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


@SuppressWarnings("serial")
public class ModelClass implements Serializable {
	
	private final String TAG="MODELCLASS";
	
	private final String JSON_UID="id",  JSON_SCORE="score",JSON_ID_NAME="name";
	
	private String mScore,mHId;
	
	private UUID mId;
	
	public String uuid(){
		return mHId;
	}
	
	public ModelClass() {		
		mId = UUID.randomUUID();
	}
	
	public ModelClass(JSONObject jsonObject) throws JSONException{
		
		
		Log.i(TAG, "JSON LENGTH "+jsonObject.length());
		Log.i(TAG, "JSON VALUE "+jsonObject.toString());
		
	
		mScore = jsonObject.getString(JSON_SCORE);
		mHId = jsonObject.getString(JSON_UID);
		
		Log.i(TAG, "ID ADDED! "+jsonObject.getString(JSON_UID));
		
	}
	
	
	public JSONObject toJsonObject() throws JSONException{
		
		JSONObject jsonObject = new JSONObject();
	
		if(mId==null)
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
	
}
