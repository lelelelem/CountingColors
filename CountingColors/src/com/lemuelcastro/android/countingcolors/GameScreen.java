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

	private ActionResolverAndroid actionResolver;
	private ArrayList<Integer> mArrayListx = new ArrayList<Integer>();
	private ArrayList<Integer> mArrayList2x = new ArrayList<Integer>();
	private ArrayList<Integer> mArrayListy = new ArrayList<Integer>();

	private ArrayList<Integer> mScoreListx = new ArrayList<Integer>();
	private ArrayList<Integer> mScoreList2x = new ArrayList<Integer>();

	private Graphics graphics;

	private static final int TILE_SPACING = 405;
	private static final int FRAMES_PER_S = 2;
	private static final float TICKER = 0.5f;

	private float ticker;

	public static final int INITIAL_SPACE = 20;

	private Data tempData;

	private int score;

	private int points[];
	private boolean lock, first, canTouch;

	private static final String TAG = "Whoa";

	private int correctCoor[][];
	private int arrayNumbers[];

	private int currindex;
	private int mBottom, addhere;
	private PixmapList mPixmaps;

	private List<TouchEvent> touches;

	public GameScreen(Game game, ActionResolverAndroid aResolverAndroid) {

		super(game);
		ticker = 0;
		graphics = game.getGraphics();

		score = 0;
		correctCoor = new int[2][2];
		currindex = 0;

		graphics.clear(Color.BLACK);
		mBottom = 1500;
		canTouch = true;
		mPixmaps = new PixmapList();

		actionResolver = aResolverAndroid;

	}

	@Override
	public void update(float deltaTime) {

		touches = game.getInput().getTouchEvents();

		game.getInput().getKeyEvents();

		for (int i = 0; i < touches.size(); i++) {
			TouchEvent forTouch = touches.get(i);

			if (forTouch.type == TouchEvent.TOUCH_UP) {
				if (canTouch
						&& ((new methods().inBounds(forTouch,
								mArrayListx.get(currindex),
								(int) mPixmaps.head.CurrentY - 50, 245, 550)))) {
					score += mScoreListx.get(currindex);
					ticker = 0;
					setUpNewNode();
				}

				else if (canTouch
						&& ((new methods().inBounds(forTouch,
								mArrayList2x.get(currindex),
								(int) mPixmaps.head.CurrentY - 50, 245, 550)))) {
					score += mScoreList2x.get(currindex);
					ticker = 0;
					setUpNewNode();
				} else {
					try {
						if(score!=0)
							actionResolver.saveScore(score);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					actionResolver.showGameOver(score);
				}

			}

		}

	}

	private void setUpNewNode() {
		graphics.clear(Color.BLACK);
		mArrayListx.remove(0);
		mArrayList2x.remove(0);
		mScoreListx.remove(0);
		mScoreList2x.remove(0);
		mArrayListy.remove(0);
		mPixmaps.delete();
		addNode(addhere);
		mPixmaps.first = true;
		lock = false;
	}

	private void addNode(float CurrY) {

		// create new button order
		Log.i(TAG, "call" + CurrY);

		// used to know if two tiler or single tiler
		// odds are 1/4
		int odds = new Random().nextInt(4);

		// can be replaced to ArrayList
		HashMap<String, Integer> hashtemp = generateRandom(odds);

		// create new tile body
		// adds new integer id to Model Class
		mPixmaps.add(hashtemp, correctCoor, (int) CurrY, true, arrayNumbers);

	}

	@Override
	public void present(float deltaTime) {

		Log.i(TAG, deltaTime + "fps");
		ticker += TICKER;

		if (ticker >= 50) {
			try {
				if(score!=0)
					actionResolver.saveScore(score);
			} catch (Exception e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
			actionResolver.showGameOver(score);
		}

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

			int arrayCtr = 0;

			points = new int[mPixmaps.head.numbers.length];

			// used to not redraw while non correct button is not pressed
			if (lock)
				break;

			Data data = mPixmaps.getInfo();

			if (data == null) {
				break;
			}
			
		
			// turns on lock once at the button is at the end
			// for debug only since on real game once
			// tiles reaches button its gameover
			if (i==5&&mPixmaps.head.CurrentY>=mBottom&& first) {
				Log.i(TAG, "Pstt");
				for (int x = 0; x < points.length; x++) {
					points[x] = mPixmaps.head.numbers[x];
					Log.i(TAG, "Points is" + points[x]);
				}
				tempData= data;
				if(tempData==null)
					Log.i(TAG, "Pstt");
				canTouch = true;
				lock = true;
			}

			CurrY = data.CurrentY += FRAMES_PER_S * deltaTime;
			CurrY -= 9;

			// adds tiles to row buttons
			for (int j = 0, x = 0; j < 4; j++, x += 270) {

				if (data.buttonPixmapNode.get(Integer.toString(j)) == 0) {

					if (actionResolver.obtainPixmap("Colored") != null) {
						graphics.drawPixmap(
								actionResolver.obtainPixmap("Colored"), x,
								(int) CurrY);
					} else {
						actionResolver.placeTo("Colored", graphics.newPixmap(
								"HasNumber.png", PixmapFormat.RGB565));
						graphics.drawPixmap(
								actionResolver.obtainPixmap("Colored"), x,
								(int) CurrY);
					}

					if (actionResolver.obtainPixmap("number") != null) {
						graphics.drawPixmap(
								actionResolver.obtainPixmap("number"), x,
								(int) CurrY, data.numbers[arrayCtr++], 0, 268,
								400);
					} else {
						actionResolver.placeTo("number", graphics.newPixmap(
								"numbers.png", PixmapFormat.RGB565));
						graphics.drawPixmap(
								actionResolver.obtainPixmap("number"), x,
								(int) CurrY, data.numbers[arrayCtr++], 0, 268,
								400);
					}
				}

				else {

					if (actionResolver.obtainPixmap("NotColored") != null) {
						graphics.drawPixmap(
								actionResolver.obtainPixmap("NotColored"), x,
								(int) CurrY);
					} else {
						actionResolver.placeTo("NotColored", graphics
								.newPixmap("HasNoNumber.png",
										PixmapFormat.RGB565));
						graphics.drawPixmap(
								actionResolver.obtainPixmap("NotColored"), x,
								(int) CurrY);
					}

				}

			}

			if (i == 5) {
				addhere = (int) CurrY - 400 + 20;
			}

			if (i == 5 && !first) {
				lock = first = true;
				tempData= mPixmaps.head;
				Log.i(TAG, "denden");
			}

		}
		drawScore();

	}

	// randomly generate button sequence
	private HashMap<String, Integer> generateRandom(int odds) {

		arrayNumbers = new int[2];

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
				if (odds != 1)
					mArrayList2x.add(9999);
				PlaceTileScore(0, odds);

				Log.i(TAG, "ehemPlaced! " + arrayList.get(index));
			} else {
				if (odds == 1 && arrayList.get(index) == 1) {
					hashMap.put(Integer.toString(i), 0);
					PlaceValue(i, 1);
					mArrayList2x.add(correctCoor[1][0]);
					Log.i(TAG, "ehemOdds!! " + i);

					PlaceTileScore(1, odds);
				}

				else {
					hashMap.put(Integer.toString(i), 1);
					Log.i(TAG, "ehemMeh! " + arrayList.get(index));
				}
			}

			arrayList.remove(index);
		}

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
		Log.i(TAG, "ehem" + i);
	}

	private void PlaceTileScore(int row, int odds) {
		arrayNumbers[row] = new Random().nextInt(4);

		if (arrayNumbers[row] == 0) {
			arrayNumbers[row] = Bounds.xBounds1stLeft.getCoor();
		} else if (arrayNumbers[row] == 1) {
			arrayNumbers[row] = Bounds.xBounds2ndLeft.getCoor();
		}
		if (arrayNumbers[row] == 2) {
			arrayNumbers[row] = Bounds.xBounds3rdLeft.getCoor();
		}
		if (arrayNumbers[row] == 3) {
			arrayNumbers[0] = Bounds.xBounds4thLeft.getCoor();
		}

		if (row == 0) {
			mScoreListx
					.add(arrayNumbers[row] != 0 ? (arrayNumbers[row] / 268) + 1
							: 1);
			if (odds != 1) {
				mScoreList2x.add(0);
			}
		} else {
			mScoreList2x
					.add(arrayNumbers[row] != 0 ? (arrayNumbers[row] / 268) + 1 / 268
							: 1);
		}

	}

	private void drawScore() {

		String stScore = Integer.toString(score);

		char stScoreArr[] = stScore.toCharArray();

		if (actionResolver.obtainPixmap("score") == null) {
			actionResolver
					.placeTo("score", graphics.newPixmap("numbersscore.png",
							PixmapFormat.RGB565));
		}

		for (int i = 0, x = 540; i < stScoreArr.length; i++, x += 33) {
			int row = Integer.parseInt(Character.toString(stScoreArr[i])) <= 4 ? 0
					: 42;
			Log.i(TAG, "yay" + row + "sc" + score);
			graphics.drawPixmap(
					actionResolver.obtainPixmap("score"),
					x,
					10,
					(Integer.parseInt(Character.toString(stScoreArr[i])) % 5) * 32,
					row, 32, 42);
		}
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

	}

}
