package com.lemuelcastro.android.countingcolors;

import java.util.HashMap;

//Representation of Tiles

public class Data {

	Data next;
	boolean isHead;
	HashMap<String, Integer> buttonPixmapNode = new HashMap<String, Integer>();
	int numbers[] = new int[2];

	float CurrentY;
	boolean toMove;
}

class PixmapList {

	Data head, tail, holdInfo,temp;
	int size = 0;
	boolean first = true;

	PixmapList() {
		head = null;
		tail = null;
	}

	public void add(HashMap<String, Integer> imgTiles, int coor[][],
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