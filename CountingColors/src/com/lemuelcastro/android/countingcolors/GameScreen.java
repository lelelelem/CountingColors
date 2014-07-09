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


	public GameScreen(Game game) {
		super(game);
		
		Assets.background.dispose();
		Assets.blueButton.dispose();
		Assets.redButton.dispose();

		
	}

	@Override
	public void update(float deltaTime) {
		
		
		List<TouchEvent> touches = game.getInput().getTouchEvents();

		game.getInput().getKeyEvents();

		for (int i = 0; i < touches.size(); i++) {
			TouchEvent forTouch = touches.get(i);

			if (forTouch.type == TouchEvent.TOUCH_UP) {
			

			}

		}

	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clear(Color.BLACK);
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
