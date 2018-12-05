package com.artificialmemories.minerals;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import screens.MainMenu;
import utils.MyGestures;

public class Initial extends Game {

	public SpriteBatch batch;
	public static int WIDTH, HEIGHT;
	public static InputMultiplexer mux;
	public static Preferences prefs;
	public static BitmapFont font;
	public static FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	public static FreeTypeFontGenerator generator;

	@Override
	public void create () {


		MyGestures ges = new MyGestures();
		mux = new InputMultiplexer();
		mux.addProcessor(ges);
		mux.addProcessor(ges.gd);
		Gdx.input.setInputProcessor(mux);
		prefs = Gdx.app.getPreferences("My Preferences");
		prefs.putString("item-1","");
		prefs.putString("item-2","");
		prefs.putString("item-3","");
		prefs.flush();
		batch = new SpriteBatch();
		//WIDTH = Gdx.graphics.getWidth();
		//HEIGHT = Gdx.graphics.getHeight();




		WIDTH = 1280;
    	HEIGHT = 720;


		generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 20;

		font = generator.generateFont(parameter);
		font.setColor(Color.WHITE);



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
