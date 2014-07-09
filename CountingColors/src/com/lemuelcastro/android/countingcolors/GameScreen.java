package com.lemuelcastro.android.countingcolors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.util.Log;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

class GameScreen extends Screen {

	ActionResolverAndroid actionResolver;
	private ArrayList<Integer> mArrayListx = new ArrayList<Integer>();
	private ArrayList<Integer> mArrayList2x = new ArrayList<Integer>();
	private ArrayList<Integer> mArrayListy = new ArrayList<Integer>();

	private static final int TILE_SPACING = 405;
	private static final int FRAMES_PER_S = 5;

	public static final int INITIAL_SPACE = 20;

	private Data tempData;

	private boolean lock, first, canTouch;

	private static final String TAG = "Whoa";

	private int correctCoor[][] = new int[2][2];

	private int currindex = 0;
	private int mBottom, addhere;
	private PixmapList mPixmaps;

	private static int count;

	public GameScreen(Game game, ActionResolverAndroid aResolverAndroid) {
		super(game);
		count = 1;
		mBottom = 1520;
		canTouch = true;
		mPixmaps = new PixmapList();

		actionResolver= aResolverAndroid;
		Graphics graphics = game.getGraphics();

		Assets.numbers = graphics.newPixmap("numbers.png",
				PixmapFormat.ARGB8888);

	}

	@Override
	public void update(float deltaTime) {

		List<TouchEvent> touches = game.getInput().getTouchEvents();

		game.getInput().getKeyEvents();

		for (int i = 0; i < touches.size(); i++) {
			TouchEvent forTouch = touches.get(i);

			if (forTouch.type == TouchEvent.TOUCH_UP) {
				if (canTouch
						&& (new methods().inBounds(forTouch,
								mArrayListx.get(currindex),
								(int)mPixmaps.head.CurrentY, 245, 400))) {
					Log.i(TAG, "SWWET" + mArrayListx.get(currindex) + " touche"
							+ forTouch.x);
					mArrayListx.remove(0);
					mArrayListy.remove(0);
					tempData = mPixmaps.head;
					mPixmaps.delete();
					addNode(addhere);
					mPixmaps.first = true;
					lock = false;
					

					/*
					 * mPixmaps.delete(); mBottom = 1900; currindex++; toTouch =
					 * false;
					 */
				}

			}

		}

	}

	private void addNode(float CurrY) {

		// create new button order
		Log.i(TAG, "call" + CurrY);

		// used to know if two tiler or 1
		// odds are 1/3
		int odds = new Random().nextInt(3);

		HashMap<String, Integer> hashtemp = generateRandom(odds);
		// create new tile body
		// adds new Pixmap to Model Class
		mPixmaps.add(hashtemp, correctCoor, (int) CurrY, true);

	}

	@Override
	public void present(float deltaTime) {

		Log.i(TAG, deltaTime + "fps");

		Graphics graphics = game.getGraphics();
		float CurrY = mBottom;

		// if no Instance is created yet
		if (mPixmaps.size == 0) {

			for (int i = 0; i < 6; i++) {
				addNode(CurrY);
				CurrY -= TILE_SPACING;
			}

		}

		CurrY = mBottom;
		for (int i = 0; i < 6; i++) {

			// used to not redraw while non correct button is not pressed
			 if (lock)
				 break;

			Data data = mPixmaps.getInfo();

			// redraw background only on first
			if (i == 0 && data != null) {
				graphics.clear(Color.BLACK);
			}

			if (data == null) {
				break;
			}

			if (i == 5 && mPixmaps.head.CurrentY >= mBottom && first) {
				lock = true;
				canTouch = true;
				Log.i(TAG, "mice fault" + i);
			}

		

			// needed only on animation and if lock is turned off
			CurrY = data.CurrentY += FRAMES_PER_S * deltaTime;
			CurrY-=9;
			// adds tiles to row buttons

			if (tempData != null && tempData.CurrentY != -5000) {
				Log.i(TAG, "whatthe " +tempData.CurrentY);
				for (int j = 0, x = 0; j < 4; j++, x += 270, tempData.CurrentY-=10) {
					if (tempData.buttonPixmapNode.get(Integer.toString(j)) == 0) {

						Pixmap pix = actionResolver.obtainPixmap("Colored");
						
						if (pix == null) {
							Log.i(TAG,"not from cache");
							pix = graphics.newPixmap("HasNumber.png",
									PixmapFormat.RGB565);
							actionResolver.placeTo("Colored", pix);
						}

						graphics.drawPixmap(pix, x, (int) CurrY);
						if (tempData.buttonPixmapNode.get(Integer.toString(j)) == 0) {
							
						}

					}
					
					else{

						Pixmap pix = actionResolver.obtainPixmap("NotColored");
						
						if (pix == null) {
							
							pix = graphics.newPixmap("HasNoNumber.png",
									PixmapFormat.RGB565);
							actionResolver.placeTo("NotColored", pix);
						}

						graphics.drawPixmap(pix, x, (int) CurrY);
						if (data.buttonPixmapNode.get(Integer.toString(j)) == 0) {
							
						}
						
					}

				}
			}
			// adds tiles to row buttons
			for (int j = 0, x = 0; j < 4; j++, x += 270) {
				
				if (data.buttonPixmapNode.get(Integer.toString(j)) == 0) {

					Pixmap pix = actionResolver.obtainPixmap("Colored");
					
					if (pix == null) {
						Log.i(TAG,"not from cache");
						pix = graphics.newPixmap("HasNumber.png",
								PixmapFormat.RGB565);
						actionResolver.placeTo("Colored", pix);
					}

					graphics.drawPixmap(pix, x, (int) CurrY);
					if (data.buttonPixmapNode.get(Integer.toString(j)) == 0) {
						
					}

				}
				
				else{

					Pixmap pix = actionResolver.obtainPixmap("NotColored");
					
					if (pix == null) {
						Log.i(TAG,"not from cache");
						pix = graphics.newPixmap("HasNoNumber.png",
								PixmapFormat.RGB565);
						actionResolver.placeTo("NotColored", pix);
					}

					graphics.drawPixmap(pix, x, (int) CurrY);
					if (data.buttonPixmapNode.get(Integer.toString(j)) == 0) {
						
					}
					
				}

			}

			if (i == 5) {
				addhere = (int) CurrY - 400 + 20;
			}

			if (i == 5 && !first) {
				lock = first = true;
				Log.i(TAG, "me fault" + i);
			}

			Log.i(TAG, "last i is drawn" + i);

		}
	
	}

	// randomly generate button sequence
	private HashMap<String, Integer> generateRandom(int odds) {

		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

		// adds 4 numbers to arraylist
		for (int i = 0; i < 4; i++) {
			arrayList.add(i, i);
		}

		// generate random number then remove it from array list to
		// avoid duplicate
		for (int i = 0; i < 4; i++) {

			int index = new Random().nextInt(arrayList.size());

			if (arrayList.get(index) == 0) {
				hashMap.put(Integer.toString(i), 0);
				PlaceValue(i, 0);
				mArrayListx.add(correctCoor[0][0]);
				Log.i(TAG, "ehemPlaced! " + arrayList.get(index));
			} else {
				if (odds == 1 && arrayList.get(index) == 1) {
					hashMap.put(Integer.toString(i), 0);
					PlaceValue(i, 1);
					mArrayList2x.add(correctCoor[1][0]);
					Log.i(TAG, "ehemOdds!! " + arrayList.get(index));
				}

				else {
					hashMap.put(Integer.toString(i), 1);
					Log.i(TAG, "ehemMeh! " + arrayList.get(index));
				}
			}

			arrayList.remove(index);
		}
		Log.i(TAG, "neg" + hashMap.size());
		mArrayListy.add(Bounds.yBoundsTop.getCoor());

		return hashMap;
	}

	private void PlaceValue(int i, int row) {
		if (i == 0) {
			
			correctCoor[row][0] = Bounds.xBounds1stLeft.getCoor();
		} else if (i == 1) {

			correctCoor[row][0] = Bounds.xBounds2ndLeft.getCoor();
		} else if (i == 2) {

			correctCoor[row][0] = Bounds.xBounds3rdLeft.getCoor();
		} else if (i == 3) {

			correctCoor[row][0] = Bounds.xBounds4thLeft.getCoor();
		}
		Log.i(TAG,"ehem"+i);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
