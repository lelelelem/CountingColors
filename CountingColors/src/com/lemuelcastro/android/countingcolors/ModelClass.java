package com.lemuelcastro.android.countingcolors;
import java.io.Serializable;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


@SuppressWarnings("serial")
public class ModelClass implements Serializable {
	
	private final String TAG="MODELCLASS";
	
	private final String JSON_UID="id", JSON_ID_TEXT="_text", JSON_ID_IMG="img",JSON_ID_NAME="name";
	
	private String mName, mScore;
	
	
	private int seqID = 0;
	private UUID mId;
	
	
	private String mImgPath;
	
	public UUID uuid(){
		return mId;
	}
	
	public ModelClass() {
		
		mId = UUID.randomUUID();
		Log.i(TAG, "NEW UUID CREATED! "+mId.toString());
		
	
	}
	
	public ModelClass(JSONObject jsonObject) throws JSONException{
		
		
		Log.i(TAG, "JSON LENGTH "+jsonObject.length());
		Log.i(TAG, "JSON VALUE "+jsonObject.toString());
		
		
		mImgPath=jsonObject.getString(JSON_ID_IMG);
		mName = jsonObject.getString(JSON_ID_NAME);
		
		Log.i(TAG, "ID ADDED! "+jsonObject.getString(JSON_UID));
		
		//adds the details from the json array
		for (int i = 0; i < jsonObject.length()-1; i++) {
			Log.i(TAG, "IM INSIDE THE FOR LOOP!!!! "+"JSON IS "+jsonObject.toString());
			//catched used if ever a JsonId is not present in the array
			try {
			
				Log.i(TAG, "TEXT ADDED!! "+jsonObject.getString(Integer.toString(seqID)+JSON_ID_TEXT));
			} catch (Exception e) {
				//used to continue loop, breaking may lose some details
				continue;
			}
			seqID++;
		}
		
		Log.i(TAG, "IM OUTSIDE THE FOR LOOP!!!! ");
	}
	
	
	public JSONObject toJsonObject() throws JSONException{
		seqID=0;
		
		JSONObject jsonObject = new JSONObject();
	
		if(mId==null)
			mId = UUID.randomUUID();

		
		jsonObject.put(JSON_ID_NAME, mName);
		jsonObject.put(JSON_UID, mId.toString());
		
		
		return jsonObject;
	}

	public String getImgPath() {
		return mImgPath;
	}

	public void setImgPath(String imgPath) {
		this.mImgPath = imgPath;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}
	
	@Override
	public String toString() {
		return mName;
	}

	public String getScore() {
		return mScore;
	}

	public void setScore(String score) {
		mScore = score;
	}
	
}
