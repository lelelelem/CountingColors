package com.lemuelcastro.android.countingcolors;

///still need some work on memory management

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

	private ActionResolverAndroid mActionResolver;
	private ArrayList<Integer> mArrayListx = new ArrayList<Integer>();
	private ArrayList<Integer> mArrayList2x = new ArrayList<Integer>();
	private ArrayList<Integer> mArrayListy = new ArrayList<Integer>();
	private ArrayList<Integer> arrayList = new ArrayList<Integer>();
	private HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
	private ArrayList<Integer> mScoreListx = new ArrayList<Integer>();
	private ArrayList<Integer> mScoreList2x = new ArrayList<Integer>();

	private Graphics mGraphics;

	private static final int TILE_SPACING = 405;
	private static final int TILE_WIDTH = 270;
	private float mFramesPerS;
	private static final int SCREEN_BOTTOM = 1500;
	private static final String TAG = null;

	private Data data;
	private float mTicker, CurrY;

	private int mScore;

	private int mCorrectCorrdinate[][];
	private int mArrayNum[];

	private int mCurrindex;
	private int addhere;
	private PixmapList mPixmaps;

	private List<TouchEvent> mTouches;

	public GameScreen(Game game, ActionResolverAndroid aResolverAndroid) {

		super(game);
		mTicker = 0;
		mGraphics = game.getGraphics();
		mFramesPerS = 1;
		mScore = 0;
		mCorrectCorrdinate = new int[2][2];
		mCurrindex = 0;
		CurrY = SCREEN_BOTTOM;
		mGraphics.clear(Color.BLACK);

		mPixmaps = new PixmapList();

		mActionResolver = aResolverAndroid;

		initializeGame();
	}

	@Override
	public void update(float deltaTime) {

		mTouches = game.getInput().getTouchEvents();

		game.getInput().getKeyEvents();

		for (int i = 0; i < mTouches.size(); i++) {
			TouchEvent forTouch = mTouches.get(i);
			if (forTouch.type == TouchEvent.TOUCH_UP) {

				// if first button is touched
				if (((new methods().inBounds(forTouch,
						mArrayListx.get(mCurrindex),
						(int) mPixmaps.head.CurrentY - 50, 245, 550)))) {
					mScore += mScoreListx.get(mCurrindex);
					mTicker = 0;
					setUpNewNode();
				}
				// if second button is touched
				else if (((new methods().inBounds(forTouch,
						mArrayList2x.get(mCurrindex),
						(int) mPixmaps.head.CurrentY - 50, 245, 550)))) {
					mScore += mScoreList2x.get(mCurrindex);
					mTicker = 0;
					setUpNewNode();
				}
				// if wrong button is touched
				else {
					try {
						GOver();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		}

	}

	private void setUpNewNode() {
		mGraphics.clear(Color.BLACK);
		mArrayListx.remove(0);
		mArrayList2x.remove(0);
		mScoreListx.remove(0);
		mScoreList2x.remove(0);
		mArrayListy.remove(0);
		mPixmaps.delete();
		addNode(addhere);
		mPixmaps.first = true;
	}

	private void addNode(float CurrY) {

		// used to know if two tiler or single tiler
		// odds are 1/4
		int odds = new Random().nextInt(4);

		// can be replaced to ArrayList
		HashMap<String, Integer> hashtemp = generateRandom(odds);

		// create new tile body
		// adds new integer id to Model Class
		mPixmaps.add(hashtemp, mCorrectCorrdinate, (int) CurrY, true, mArrayNum);
		mTicker++;
	}

	private void GOver() throws Exception {
		if (mScore != 0)
			mActionResolver.saveScore(mScore);

		mActionResolver.showGameOver(mScore);
	}

	private void initializeGame() {
		for (int i = 0; i < 6; i++) {
			addNode(CurrY);
			CurrY -= TILE_SPACING;
		}
	}

	@Override
	public void present(float deltaTime) {
		Log.i(TAG,deltaTime+"fps");
		if (mTicker % 10 == 0) {
			mFramesPerS += 0.5;
		}

		CurrY = SCREEN_BOTTOM;
		for (int i = 0; i < 6; i++) {
			int arrayCtr = 0;
			// obtains tile
			data = mPixmaps.getInfo();
			if (data == null) {
				break;
			}
			// dead once the bottom tile reaches bottom of screen
			if (i == 5 && mPixmaps.head.CurrentY >= SCREEN_BOTTOM + 350) {
				try {
					GOver();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			CurrY = data.CurrentY += mFramesPerS * deltaTime;
			// adds tiles to row buttons
			for (int j = 0, x = 0; j < 4; j++, x += TILE_WIDTH) {
				if (data.buttonPixmapNode.get(Integer.toString(j)) == 0) {
					mGraphics.drawPixmap(obtain("HasNumber.png", "Colored"), x,
							(int) CurrY);
					mGraphics.drawPixmap(obtain("numbers.png", "numbers"), x,
							(int) CurrY, data.numbers[arrayCtr++], 0, 268, 400);
				} else {
					mGraphics.drawPixmap(
							obtain("HasNoNumber.png", "NotColored"), x,
							(int) CurrY);
				}
			}
			if (i == 5) {
				addhere = (int) CurrY - 400 + 20;
			}
		}
		drawScore();

	}

	private Pixmap obtain(String url, String key) {
		// initializes cache if Pixmap from cache if not null
		if (mActionResolver.obtainPixmap(key) == null) {
			mActionResolver.placeTo(key,
					mGraphics.newPixmap(url, PixmapFormat.RGB565));
		}
		return mActionResolver.obtainPixmap(key);
	}

	// randomly generate button sequence
	private HashMap<String, Integer> generateRandom(int odds) {

		mArrayNum = new int[2];

		arrayList.clear();
		hashMap = new HashMap<String, Integer>();

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
				mArrayListx.add(mCorrectCorrdinate[0][0]);
				if (odds != 1)
					mArrayList2x.add(9999);
				PlaceTileScore(0, odds);
			} else {
				if (odds == 1 && arrayList.get(index) == 1) {
					hashMap.put(Integer.toString(i), 0);
					PlaceValue(i, 1);
					mArrayList2x.add(mCorrectCorrdinate[1][0]);

					PlaceTileScore(1, odds);
				}

				else {
					hashMap.put(Integer.toString(i), 1);
				}
			}

			arrayList.remove(index);
		}

		mArrayListy.add(Bounds.yBoundsTop.getCoor());

		return hashMap;
	}

	private void PlaceValue(int i, int row) {
		if (i == 0) {
			mCorrectCorrdinate[row][0] = Bounds.xBounds1stLeft.getCoor();
		} else if (i == 1) {

			mCorrectCorrdinate[row][0] = Bounds.xBounds2ndLeft.getCoor();
		} else if (i == 2) {

			mCorrectCorrdinate[row][0] = Bounds.xBounds3rdLeft.getCoor();
		} else if (i == 3) {

			mCorrectCorrdinate[row][0] = Bounds.xBounds4thLeft.getCoor();
		}
	}

	private void PlaceTileScore(int row, int odds) {
		mArrayNum[row] = new Random().nextInt(4);

		if (mArrayNum[row] == 0) {
			mArrayNum[row] = Bounds.xBounds1stLeft.getCoor();
		} else if (mArrayNum[row] == 1) {
			mArrayNum[row] = Bounds.xBounds2ndLeft.getCoor();
		}
		if (mArrayNum[row] == 2) {
			mArrayNum[row] = Bounds.xBounds3rdLeft.getCoor();
		}
		if (mArrayNum[row] == 3) {
			mArrayNum[0] = Bounds.xBounds4thLeft.getCoor();
		}

		if (row == 0) {
			mScoreListx.add(mArrayNum[row] != 0 ? (mArrayNum[row] / 268) + 1
					: 1);
			if (odds != 1) {
				mScoreList2x.add(0);
			}
		} else {
			mScoreList2x
					.add(mArrayNum[row] != 0 ? (mArrayNum[row] / 268) + 1 / 268
							: 1);
		}

	}

	private void drawScore() {

		String stScore = Integer.toString(mScore);

		char stScoreArr[] = stScore.toCharArray();

		if (mActionResolver.obtainPixmap("score") == null) {
			mActionResolver.placeTo("score", mGraphics.newPixmap(
					"numbersscore.png", PixmapFormat.RGB565));
		}

		for (int i = 0, x = 540; i < stScoreArr.length; i++, x += 33) {
			int row = Integer.parseInt(Character.toString(stScoreArr[i])) <= 4 ? 0
					: 42;
			mGraphics
					.drawPixmap(
							mActionResolver.obtainPixmap("score"),
							x,
							10,
							(Integer.parseInt(Character.toString(stScoreArr[i])) % 5) * 32,
							row, 32, 42);
		}
	}

	@Override
	public void pause() {
		mActionResolver.showGameOver(mScore);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		this.dispose();
	}

}
