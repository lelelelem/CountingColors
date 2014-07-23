package com.lemuelcastro.android.countingcolorsgl;

import android.annotation.SuppressLint;
import android.util.SparseIntArray;

//Representation of Tiles
@SuppressLint("UseSparseArrays")
public class Data {
	Data next;
	SparseIntArray buttonPixmapNode = new SparseIntArray();
	int numbers[] = new int[2];
	float CurrentY;
}

class PixmapList {

	Data head, tail, holdInfo, temp;
	int size = 0;
	boolean first = true;

	public void add(SparseIntArray imgTiles, int CurrentY, boolean lock,
			int tileScore[]) {

		temp = new Data();

		if (size == 0) {
			head = temp;
			tail = temp;
			head.CurrentY = CurrentY;
			head.numbers = tileScore;
			head.buttonPixmapNode = imgTiles;
			holdInfo = head;
			tail.next = null;
		} else {
			tail.next = temp;
			tail = temp;
			tail.next = null;
			tail.numbers = tileScore;
			tail.CurrentY = CurrentY;
			tail.buttonPixmapNode = imgTiles;
		}
		size++;
	}

	public void delete() {
		head = head.next;
	}

	public Data getInfo() {

		if (size == 0 || holdInfo == null && !first) {
			holdInfo = head;
			return null;
		}

		if (first) {
			holdInfo = head;
			first = false;
		}
		temp = holdInfo;
		holdInfo = holdInfo.next;
		return temp;
	}
}