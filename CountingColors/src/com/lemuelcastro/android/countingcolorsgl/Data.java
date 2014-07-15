package com.lemuelcastro.android.countingcolorsgl;

import android.annotation.SuppressLint;
import android.util.SparseIntArray;

//Representation of Tiles
@SuppressLint("UseSparseArrays")
public class Data {

	Data next;
	boolean isHead;
	SparseIntArray buttonPixmapNode = new SparseIntArray();
	int numbers[] = new int[2];

	float CurrentY;
	boolean toMove;
}

class PixmapList {

	Data head, tail, holdInfo, temp;
	int size = 0;
	boolean first = true;

	PixmapList() {
		head = null;
		tail = null;
	}

	public void add(SparseIntArray imgTiles, int coor[][],
			int CurrentY, boolean lock, int tileScore[]) {

		temp = new Data();

		if (size == 0) {
			head = temp;
			tail = temp;
			head.CurrentY = CurrentY;
			head.toMove = lock;
			head.isHead = true;
			head.numbers = tileScore;
			head.buttonPixmapNode = imgTiles;
			holdInfo = head;
			tail.next = null;
		} else {
			tail.next = temp;
			tail = temp;
			tail.next = null;
			head.isHead = false;
			tail.numbers = tileScore;
			tail.CurrentY = CurrentY;
			tail.buttonPixmapNode = imgTiles;
			head.toMove = lock;
		}
		size++;
	}

	public void delete() {
		head = head.next;
	}

	public Data getInfo() {

		if (head.next == null) {
		}

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