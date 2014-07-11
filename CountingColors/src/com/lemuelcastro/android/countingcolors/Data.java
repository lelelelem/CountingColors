package com.lemuelcastro.android.countingcolors;

import java.util.HashMap;

import android.util.Log;

import com.badlogic.androidgames.framework.Pixmap;

//Representation of Tiles


public class Data {
	
	Data next;
	int imageCtr;
	int headCtr;
	HashMap<String, Integer> buttonPixmapNode = new HashMap<String, Integer>();
	
	int CorrectXCoorLeft,CorrectXCoorRight, CorrectYCoorTop,CorrectYCoorBottom;
	int numbers[] = new int[2];
	
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

	public void add(HashMap<String,Integer> imgTiles,int coor[][], int CurrentY, boolean lock, int tileScore[]) {
		Data temp = new Data();
		
		if (size == 0) {
			head = temp;
			tail = temp;
			head.CurrentY = CurrentY;
			head.toMove = lock;
			head.headCtr=1;
			head.numbers=tileScore;
			head.buttonPixmapNode = imgTiles;
			holdInfo=head;
			tail.next = null;
		} else {
			tail.next = temp;
			tail = temp;
			tail.next = null;
			tail.numbers = tileScore;
			tail.headCtr=0;
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