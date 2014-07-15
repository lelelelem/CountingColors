package com.lemuelcastro.android.countingcolorsgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLScreen;

public class GameScreen extends GLScreen {

	private ActionResolverAndroid mActionResolver;

	private ArrayList<Data> mData = new ArrayList<Data>();
	private ArrayList<Integer> mArrayListx = new ArrayList<Integer>();
	private ArrayList<Integer> mArrayList2x = new ArrayList<Integer>();

	private ArrayList<Integer> arrayList = new ArrayList<Integer>();
	private HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
	private ArrayList<Integer> mScoreListx = new ArrayList<Integer>();
	private ArrayList<Integer> mScoreList2x = new ArrayList<Integer>();

	private static final int TILE_SPACING = 405;
	private static final int TILE_WIDTH = 270;
	private static final int SCREEN_BOTTOM = 0;

	private int mFramesPerS;

	private Data temp;
	private float CurrY;

	private boolean first;

	private int mTicker;
	private int mScore;
	private int mCorrectCorrdinate[][];
	private int mArrayNum[];
	private int mCurrindex;
	private int addhere;

	private PixmapList mPixmaps;

	private List<TouchEvent> mTouches;

	private Camera2D mGuiCam;
	private SpriteBatcher mBatcher;

	public GameScreen(Game game, ActionResolverAndroid actionResolverAndroid) {
		super(game);

		// will handle activity control
		mActionResolver = actionResolverAndroid;

		// sets up height and width of screen
		mGuiCam = new Camera2D(glGraphics, 1080, 1920);
		mBatcher = new SpriteBatcher(glGraphics, 200);

		mTicker = 0;
		first = true;

		mFramesPerS = 500;
		mScore = 0;
		mCorrectCorrdinate = new int[2][2];
		mCurrindex = 0;

		CurrY = SCREEN_BOTTOM;

		// initializes tile model class
		mPixmaps = new PixmapList();

		initializeGame();
	}

	// setup newNode when correct tile is pressed
	private void setUpNewNode() {
		mArrayListx.remove(0);
		mArrayList2x.remove(0);
		mScoreListx.remove(0);
		mScoreList2x.remove(0);
		mData.add(mPixmaps.head);
		temp = temp.next;
		mPixmaps.delete();
		addNode(addhere);
		mPixmaps.first = true;
	}

	// start intent for gameoverActivity
	private void GOver() throws Exception {
		if (mScore != 0)
			mActionResolver.saveScore(mScore);

		mActionResolver.showGameOver(mScore);
	}

	// game initializer
	private void initializeGame() {
		for (int i = 0; i < 6; i++) {
			if (i == 0)
				CurrY += 200;
			addNode(CurrY);
			CurrY += TILE_SPACING;
		}
		temp = mPixmaps.head;
	}

	// tile creator
	private void addNode(float CurrY) {
		// used to know if two tiler or single tiler
		// odds are 1/4
		int odds = new Random().nextInt(4);
		// can be replaced by ArrayList
		HashMap<String, Integer> hashtemp = generateRandom(odds);

		// create new tile body
		// adds new integer id to Model Class
		mPixmaps.add(hashtemp, mCorrectCorrdinate, (int) CurrY + 5, true,
				mArrayNum);
		mTicker++;
		if (mTicker % 10 == 0) {
			mFramesPerS += 100;
		}

	}

	@Override
	public void update(float deltaTime) {

		mTouches = game.getInput().getTouchEvents();

		game.getInput().getKeyEvents();

		for (int i = 0; i < mTouches.size(); i++) {
			TouchEvent forTouch = mTouches.get(i);

			if (forTouch.type == TouchEvent.TOUCH_UP) {
				// if first button is touched
				if (((new methodsForDraw().inBounds(forTouch,
						mArrayListx.get(mCurrindex), (int) temp.CurrentY - 205,
						245, 550)))) {
					mScore += mScoreListx.get(mCurrindex);
					first = false;
					setUpNewNode();
				}
				// if second button is touched
				else if (((new methodsForDraw().inBounds(forTouch,
						mArrayList2x.get(mCurrindex),
						(int) temp.CurrentY - 205, 245, 550)))) {
					mScore += mScoreList2x.get(mCurrindex);

					first = false;
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

	// draw tiles
	public void draw(float deltaTime, Data data, int flag) {
		int arrayCtr = 0;
		data.CurrentY = mPixmaps.head.CurrentY <= SCREEN_BOTTOM + 205 ? data.CurrentY
				: data.CurrentY - mFramesPerS * deltaTime;
		CurrY = data.CurrentY;

		if (flag == 0 && CurrY <= SCREEN_BOTTOM + 205 && !first) {
			try {
				GOver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (int j = 0, x = 0; j < 4; j++, x += TILE_WIDTH) {

			if (data.buttonPixmapNode.get(Integer.toString(j)) == 0) {
				// draw score
				if (flag == 0) {
					mBatcher.beginBatch(AssetsOG.numbers);
					mBatcher.drawSprite(134 + (float) x, CurrY, 268.0f, 400.0f,
							new TextureRegion(AssetsOG.numbers,
									data.numbers[arrayCtr++], 0, 268, 400));
				}
				// draw bg for pressed tile
				else {
					mBatcher.beginBatch(AssetsOG.tile);
					mBatcher.drawSprite(134 + (float) x, CurrY, 268.0f, 400.0f,
							AssetsOG.blueTile);
				}

				mBatcher.endBatch();
			} else {
				// draw RedTile
				mBatcher.beginBatch(AssetsOG.tile);
				mBatcher.drawSprite(134 + (float) x, CurrY, 268.0f, 400.0f,
						AssetsOG.redTile);
				mBatcher.endBatch();
			}

		}
	}

	// main game loop
	@Override
	public void present(float deltaTime) {

		GL10 gl = glGraphics.getGL();

		mGuiCam.setViewportAndMatrices();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glClearColor(0f, 0f, 0f, 1);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		CurrY = SCREEN_BOTTOM;

		// draw pressed tiles;
		if (mData.size() != 0) {
			for (int i = 0; i < mData.size(); i++) {
				if (mData.get(i).CurrentY <= -450) {
					mData.remove(i);
					continue;
				}
				draw(deltaTime, mData.get(i), 1);
			}

		}
		// draw unpressed tiles
		for (int i = 0; i < 6; i++) {
			draw(deltaTime, mPixmaps.getInfo(), 0);
		}

		addhere = (int) CurrY + 405;

		mPixmaps.getInfo();

		drawScore();
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

		return hashMap;
	}

	// places coordinate
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

	// places tile score
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

	// draw score box
	private void drawScore() {
		String stScore = Integer.toString(mScore);
		char stScoreArr[] = stScore.toCharArray();

		for (int i = 0, x = 540; i < stScoreArr.length; i++, x += 33) {
			int row = Integer.parseInt(Character.toString(stScoreArr[i])) <= 4 ? 0
					: 42;

			mBatcher.beginBatch(AssetsOG.scores);
			mBatcher.drawSprite(
					x,
					1800,
					32f,
					42f,
					new TextureRegion(
							AssetsOG.scores,
							(Integer.parseInt(Character.toString(stScoreArr[i])) % 5) * 32,
							row, 32, 42));
			mBatcher.endBatch();
		}
	}

	@Override
	public void pause() {
		// not used
	}

	@Override
	public void resume() {
		// not used
	}

	@Override
	public void dispose() {
		this.dispose();
	}

}
