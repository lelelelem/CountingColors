package com.lemuelcastro.android.countingcolors;

import java.util.HashMap;

import android.util.Log;

import com.badlogic.androidgames.framework.Pixmap;

//Representation of Tiles


public class Data {
	
	Data next;
	int imageCtr;
	HashMap<String, Integer> buttonPixmapNode = new HashMap<String, Integer>();
	int CorrectXCoorLeft,CorrectXCoorRight, CorrectYCoorTop,CorrectYCoorBottom;
	
	float CurrentY;
	boolean toMove;
}

class PixmapList{
	
	String Tag = "DATANODE";
	Data head, tail,holdInfo;
	int size = 0;
	boolean first=true;
	
	public static final String TAG="Datanode";

	PixmapList() {
		head = null;
		tail = null;
	}

	public void add(HashMap<String,Integer> imgTiles,int coor[][], int CurrentY, boolean lock) {
		Data temp = new Data();
		
		if (size == 0) {
			head = temp;
			tail = temp;
			head.CurrentY = CurrentY;
			head.toMove = lock;
			head.buttonPixmapNode = imgTiles;
			holdInfo=head;
			tail.next = null;
		} else {
			tail.next = temp;
			tail = temp;
			tail.next = null;
			tail.CurrentY = CurrentY;
			tail.buttonPixmapNode = imgTiles;
			head.toMove = lock;
		}
		size++;
	}
	
	public void delete(){
		head = head.next;
	}

	public Data getInfo() {
		Data temp;
		
		if(size==0||holdInfo==null&&!first){
			Log.i(TAG, "NULL ALREADY");
			holdInfo=head;
			return null;
		}
		
		if (first){
			Log.i(TAG, "FIRST TIME");
			holdInfo=head;
			first=false;
		}
		
		temp=holdInfo;
		
		holdInfo=holdInfo.next;
		
		return temp;
	}
}