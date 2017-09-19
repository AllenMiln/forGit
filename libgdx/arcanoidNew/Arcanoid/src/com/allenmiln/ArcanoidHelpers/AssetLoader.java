package com.allenmiln.ArcanoidHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture, logoTexture;
	public static TextureRegion logo, zbLogo, bg, grass, ball, skullUp, skullDown, bar, playButtonUp, playButtonDown,
			ready, gameOver, highScore, scoreboard, star, noStar, retry, stickTexture, blockOne;
	public static Animation birdAnimation;
	public static Sound dead, flap, coin, fall;
	public static BitmapFont font, shadow, whiteFont;
	private static Preferences prefs;

	public static Texture newTexture;


	public static void load() {

		logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		//Новая тест
		newTexture = new Texture(Gdx.files.internal("data/map.png"));
		newTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);


		playButtonUp = new TextureRegion(newTexture, 740, 1, 110, 32);
        playButtonDown = new TextureRegion(newTexture, 628, 1, 110, 32);
		playButtonUp.flip(false, true);
		playButtonDown.flip(false, true);

		ready = new TextureRegion(texture, 59, 83, 34, 7);
		ready.flip(false, true);

		retry = new TextureRegion(texture, 59, 110, 33, 7);
		retry.flip(false, true);
		
		gameOver = new TextureRegion(texture, 59, 92, 46, 7);
		gameOver.flip(false, true);

		scoreboard = new TextureRegion(texture, 111, 83, 97, 37);
		scoreboard.flip(false, true);

		star = new TextureRegion(texture, 152, 70, 10, 10);
		noStar = new TextureRegion(texture, 165, 70, 10, 10);

		star.flip(false, true);
		noStar.flip(false, true);

		highScore = new TextureRegion(texture, 59, 101, 48, 7);
		highScore.flip(false, true);

		zbLogo = new TextureRegion(texture, 0, 55, 135, 24);
		zbLogo.flip(false, true);

		//New
		bg = new TextureRegion(newTexture,1,1,240,400);
		//bg = new TextureRegion(texture, 0, 0, 136, 43);
		bg.flip(false, true);

		grass = new TextureRegion(texture, 0, 43, 143, 11);
		grass.flip(false, true);

		//Птица
		//birdDown = new TextureRegion(texture, 136, 0, 17, 12);
		//birdDown.flip(false, true);
		//ball = new TextureRegion(texture, 153, 0, 17, 12);
		//ball.flip(false, true);
		//birdUp = new TextureRegion(texture, 170, 0, 17, 12);
		//birdUp.flip(false, true);

		//birdDown = new TextureRegion(newTexture, 547, 1, 561, 15);
		//birdDown.flip(false, true);
		ball = new TextureRegion(newTexture, 547, 1, 15, 15);
		ball.flip(false, true);
		blockOne = new TextureRegion(newTexture, 483, 1, 15, 15);
		//birdUp = new TextureRegion(newTexture, 547, 1, 561, 15);
		//birdUp.flip(false, true);

		stickTexture = new TextureRegion(newTexture, 563,1,64,16);
		stickTexture.flip(false,true);

		//TextureRegion[] birds = { birdDown, ball, birdUp };
		TextureRegion[] balls = {ball, ball, ball};
		birdAnimation = new Animation(0.06f, balls);
		birdAnimation.setPlayMode(Animation.LOOP_PINGPONG);

		skullUp = new TextureRegion(texture, 192, 0, 24, 14);
		// Create by flipping existing skullUp
		skullDown = new TextureRegion(skullUp);
		skullDown.flip(false, true);

		bar = new TextureRegion(texture, 136, 16, 22, 3);
		bar.flip(false, true);

		dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
		flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
		fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.wav"));

		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.25f, -.25f);

		whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFont.setScale(.1f, -.1f);

		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);

		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("Arcanoid");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
	}

	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture.dispose();

		// Dispose sounds
		dead.dispose();
		flap.dispose();
		coin.dispose();

		font.dispose();
		shadow.dispose();
	}

}