package com.lemuelcastro.android.countingcolorsgl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import android.util.SparseIntArray;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.gl.Camera2D;
import com.badlogic.androidgames.framework.gl.SpriteBatcher;
import com.badlogic.androidgames.framework.gl.TextureRegion;
import com.badlogic.androidgames.framework.impl.GLScreen;

public class GameScreen extends GLScreen {

	private boolean paused;
	private boolean first;

	private ActionResolverAndroid mActionResolver;

	private ArrayList<Data> mData = new ArrayList<Data>();

	private ArrayList<Integer> mArrayListx = new ArrayList<Integer>();
	private ArrayList<Integer> mArrayList2x = new ArrayList<Integer>();

	private ArrayList<Integer> arrayList = new ArrayList<Integer>();

	private ArrayList<Integer> mScoreListx = new ArrayList<Integer>();
	private ArrayList<Integer> mScoreList2x = new ArrayList<Integer>();

	private SparseIntArray sparseArray;

	private static final int TILE_SPACING = 405;
	private static final int TILE_WIDTH = 270;
	private static final int SCREEN_BOTTOM = 0;

	private Data temp;
	private float dimension[];
	private float CurrY;

	private int mFramesPerS;
	private int mScore;

	private int mArrayNum[];
	private int addhere;

	private PixmapList mPixmaps;

	private List<TouchEvent> mTouches;

	private Camera2D mGuiCam;
	private SpriteBatcher mBatcher;

	public GameScreen(Game game, ActionResolverAndroid actionResolverAndroid) {
		super(game);

		// will handle activity control
		mActionResolver = actionResolverAndroid;

		// get real dimension
		dimension = mActionResolver.getDimension();

		// sets up height and width of screen
		mGuiCam = new Camera2D(glGraphics, 1080, 1920);
		mBatcher = new SpriteBatcher(glGraphics, 200);

		first = true;

		mFramesPerS = 700;
		mScore = 0;

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
	private void endGame() throws Exception {
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
		// create new tile body
		// adds new integer id to Model Class
		mPixmaps.add(generateRandom(new Random().nextInt(4)), (int) CurrY + 5,
				true, mArrayNum);

	}

	@Override
	public void update(float deltaTime) {

		mTouches = game.getInput().getTouchEvents();

		for (TouchEvent touch : mTouches) {

			if (touch.type == TouchEvent.TOUCH_UP) {
				// if first button is touched

				if (((TouchUtils.inBounds(touch, mArrayListx.get(0),
						(int) temp.CurrentY
								- (int) (205 * (1080 / dimension[1])), 260,
						550, dimension)))) {
					mScore += mScoreListx.get(0);
					first = false;
					setUpNewNode();
					mFramesPerS += 10;
					AssetsOG.playSound(AssetsOG.touch);
				}
				// if second button is touched
				else if (((TouchUtils.inBounds(touch, mArrayList2x.get(0),
						(int) temp.CurrentY
								- (int) (205 * (1080 / dimension[1])), 260,
						550, dimension)))) {
					mScore += mScoreList2x.get(0);

					first = false;
					setUpNewNode();
					mFramesPerS += 10;
					AssetsOG.playSound(AssetsOG.touch);
				}
				// if wrong button is touched
				else {
					try {
						endGame();
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

		if (!paused)
			data.CurrentY = mPixmaps.head.CurrentY <= SCREEN_BOTTOM + 205 ? data.CurrentY
					: data.CurrentY - mFramesPerS * deltaTime;

		CurrY = data.CurrentY;

		if (flag == 0 && CurrY <= SCREEN_BOTTOM + 205 && !first) {
			try {
				endGame();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (int j = 0, x = 0; j < 4; j++, x += TILE_WIDTH) {
			if (data.buttonPixmapNode.get(j) == 0) {
				// draw score
				if (flag == 0) {
					mBatcher.beginBatch(AssetsOG.numbers);
					mBatcher.drawSprite(134 + (float) x, CurrY, 268.0f, 400.0f,
							new TextureRegion(AssetsOG.numbers,
									data.numbers[arrayCtr++], 0, 268f, 400f));
				}
				// draw bg for pressed tile
				else {
					mBatcher.beginBatch(AssetsOG.tile);
					mBatcher.drawSprite(134 + (float) x, CurrY, 268.0f, 400.0f,
							AssetsOG.pressedTile);
				}

				mBatcher.endBatch();
			} else {
				// draw RedTile
				mBatcher.beginBatch(AssetsOG.tile);
				mBatcher.drawSprite(134 + (float) x, CurrY, 268.0f, 400.0f,
						AssetsOG.doNotPressTile);
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
				if (mData.get(i).CurrentY <= -600) {
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
	private SparseIntArray generateRandom(int odds) {
		mArrayNum = new int[2];

		arrayList.clear();
		sparseArray = new SparseIntArray();

		// adds 4 numbers to arraylist
		for (int i = 0; i < 4; i++) {
			arrayList.add(i, i);
		}

		// generate random number then remove it from array list to
		// avoid duplicate
		for (int i = 0; i < 4; i++) {
			int index = new Random().nextInt(arrayList.size());

			if (arrayList.get(index) == 0) {
				sparseArray.put(i, 0);
				mArrayListx.add(placeValue(i));
				if (odds != 1)
					mArrayList2x.add(9999);
				placeTileScore(0, odds);
			} else {
				if (odds == 1 && arrayList.get(index) == 1) {
					sparseArray.put(i, 0);
					mArrayList2x.add(placeValue(i));
					placeTileScore(1, odds);

				} else {
					sparseArray.put(i, 1);
				}
			}
			arrayList.remove(index);
		}

		return sparseArray;
	}

	// places coordinate
	private int placeValue(int i) {
		if (i == 0) {
			return Bounds.xBounds1stLeft.getCoor();
		} else if (i == 1) {
			return Bounds.xBounds2ndLeft.getCoor();
		} else if (i == 2) {
			return Bounds.xBounds3rdLeft.getCoor();
		} else if (i == 3) {
			return Bounds.xBounds4thLeft.getCoor();
		}

		return -1;
	}

	// places tile score
	private void placeTileScore(int row, int odds) {
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
		int x = 540;
		for (char c : stScore.toCharArray()) {
			mBatcher.beginBatch(AssetsOG.scores);
			mBatcher.drawSprite(x, 1800, 32f, 42f,
					AssetsOG.scoresRegion[Integer.parseInt(Character
							.toString(c))]);
			mBatcher.endBatch();
			x += 33;
		}
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	@Override
	public void pause() {
		// need to override even if not used
	}

	@Override
	public void resume() {
		// need to override even if not used
	}

	@Override
	public void dispose() {
		// need to override even if not used
	}

}
