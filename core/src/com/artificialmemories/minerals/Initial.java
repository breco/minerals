package com.artificialmemories.minerals;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.MainMenu;
import utils.MyGestures;

public class Initial extends Game {

	public SpriteBatch batch;
	public static int WIDTH, HEIGHT;
	public static InputMultiplexer mux;
	public static Preferences prefs;

	@Override
	public void create () {


		MyGestures ges = new MyGestures();
		mux = new InputMultiplexer();
		mux.addProcessor(ges);
		mux.addProcessor(ges.gd);
		Gdx.input.setInputProcessor(mux);
		prefs = Gdx.app.getPreferences("My Preferences");

		batch = new SpriteBatch();
		//WIDTH = Gdx.graphics.getWidth();
		//HEIGHT = Gdx.graphics.getHeight();

		WIDTH = 1280;
    	HEIGHT = 720;

		this.setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
		MyGestures.update();
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
	public static void setInputProcessor(){
		Gdx.input.setInputProcessor(mux);
	}

}
