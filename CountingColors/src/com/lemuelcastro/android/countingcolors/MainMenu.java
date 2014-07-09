/*Main Menu, Design is outdated
 * some coordinates must be revised, specially on update
 * */

package com.lemuelcastro.android.countingcolors;

import java.util.List;

import android.support.v4.util.LruCache;
import android.util.Log;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

public class MainMenu extends Screen {

	ActionResolverAndroid actionResolver;

	private static final String TAG = "Main";
	private boolean loaded;
	

	public MainMenu(Game game, ActionResolverAndroid aResolverAndroid) {
		super(game);
		actionResolver = aResolverAndroid;
		actionResolver.setupCache();
		Graphics g = game.getGraphics();

		Assets.background = g.newPixmap("background2.png", PixmapFormat.RGB565);


		Assets.blueButton = g
				.newPixmap("blueButton.png", PixmapFormat.ARGB4444);
		Assets.redButton = g.newPixmap("pinkbutton.png", PixmapFormat.ARGB4444);
		
	}

	
	@Override
	public void update(float deltaTime) {

		List<TouchEvent> touches = game.getInput().getTouchEvents();

		game.getInput().getKeyEvents();

		for (int i = 0; i < touches.size(); i++) {
			TouchEvent forTouch = touches.get(i);
			Log.i(TAG, forTouch.x + " SizeTouche" + forTouch.y + " ");

			if (forTouch.type == TouchEvent.TOUCH_UP) {
				if (new methods().inBounds(forTouch, 336, 392, 792, 596)) {
					game.setScreen(new GameScreen(game,actionResolver));
				}

				else if (new methods().inBounds(forTouch, 336, 1048, 792, 596)) {
					Log.i(TAG, "AHEHEH");
					actionResolver.showMyList();
				}

			}

		}

	}

	@Override
	public void present(float deltaTime) {

		if (loaded) {
			return;
		}

		Graphics graphics = game.getGraphics();
		graphics.drawPixmap(Assets.background, 0, 0);
		
		graphics.drawPixmap(Assets.redButton, 336, 392);
		graphics.drawPixmap(Assets.blueButton, 336, 1048);
		loaded = true;
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

		Assets.background.dispose();
		Assets.blueButton.dispose();
		Assets.redButton.dispose();

	}

}



