package com.allenmiln.ArcanoidGameWorld;

import com.allenmiln.ArcanoidGameObjects.Ball;
import com.allenmiln.ArcanoidGameObjects.Block;
import com.allenmiln.ArcanoidGameObjects.Stick;
import com.allenmiln.ArcanoidHelpers.AssetLoader;
import com.allenmiln.ArcanoidHelpers.BlockHelpers;
import com.allenmiln.ArcanoidHelpers.InputHandler;
import com.allenmiln.ArcanoidTweenAccessors.Value;
import com.allenmiln.ArcanoidTweenAccessors.ValueAccessor;
import com.allenmiln.ArcanoidUi.SimpleButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	private SpriteBatch batcher;

	private int midPointY;

	// Game Objects
	private Ball ball;
	private Stick stick;
	private BlockHelpers blockHelpers;


	// Game Assets
	private TextureRegion bg, ballMid, ready,
			zbLogo, gameOver, highScore, scoreboard, star, noStar, retry, stickTexture, blockOne;
	private Animation birdAnimation;



	// Tween stuff
	private TweenManager manager;
	private Value alpha = new Value();

	// Buttons
	private List<SimpleButton> menuButtons;
	private Color transitionColor;

	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;

		this.midPointY = midPointY;
		this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getMenuButtons();

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		initGameObjects();
		initAssets();

		transitionColor = new Color();
		prepareTransition(255, 255, 255, .5f);
	}

	private void initGameObjects() {
		ball = myWorld.getBall();
		stick = myWorld.getStick();
		blockHelpers = myWorld.getBlockHelpers();
	}

	private void initAssets() {
		bg = AssetLoader.bg;
		birdAnimation = AssetLoader.birdAnimation;
		ballMid = AssetLoader.ball;
        blockOne = AssetLoader.blockOne;
		ready = AssetLoader.ready;
		zbLogo = AssetLoader.zbLogo;
		gameOver = AssetLoader.gameOver;
		highScore = AssetLoader.highScore;
		scoreboard = AssetLoader.scoreboard;
		retry = AssetLoader.retry;
		star = AssetLoader.star;
		noStar = AssetLoader.noStar;
		stickTexture = AssetLoader.stickTexture;
	}

	//Отрисовка птицы для заставки
	private void drawBallCentered(float runTime) {
		batcher.draw(birdAnimation.getKeyFrame(runTime), 59, ball.getY() - 15,
				ball.getWidth() / 2.0f, ball.getHeight() / 2.0f,
				ball.getWidth(), ball.getHeight(), 1, 1, 0);
	}

	//Отрисовка птицы
	private void drawBall(float runTime) {
		batcher.draw(ballMid, ball.getX(), ball.getY());
	}

	private void drawStick(float runTime) {
		batcher.draw(stickTexture, stick.getX(), stick.getY());
	}

	private void drawBlocks(float runTime) {
		for (Block block : blockHelpers.getAliveBlockMatrix()) {
			batcher.draw(blockOne, block.getX(), block.getY());
		}
	}

	private void drawMenuUI() {
		batcher.draw(zbLogo, 136 / 2 - 56, midPointY - 50,
				zbLogo.getRegionWidth() / 1.2f, zbLogo.getRegionHeight() / 1.2f);

		for (SimpleButton button : menuButtons) {
			button.draw(batcher);
		}

	}

	private void drawScoreboard() {
		batcher.draw(scoreboard, 22, midPointY - 30, 97, 37);

		batcher.draw(noStar, 25, midPointY - 15, 10, 10);
		batcher.draw(noStar, 37, midPointY - 15, 10, 10);
		batcher.draw(noStar, 49, midPointY - 15, 10, 10);
		batcher.draw(noStar, 61, midPointY - 15, 10, 10);
		batcher.draw(noStar, 73, midPointY - 15, 10, 10);

		if (myWorld.getScore() > 2) {
			batcher.draw(star, 73, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 17) {
			batcher.draw(star, 61, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 50) {
			batcher.draw(star, 49, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 80) {
			batcher.draw(star, 37, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 120) {
			batcher.draw(star, 25, midPointY - 15, 10, 10);
		}

		int length = ("" + myWorld.getScore()).length();

		AssetLoader.whiteFont.draw(batcher, "" + myWorld.getScore(),
				104 - (2 * length), midPointY - 20);

		int length2 = ("" + AssetLoader.getHighScore()).length();
		AssetLoader.whiteFont.draw(batcher, "" + AssetLoader.getHighScore(),
				104 - (2.5f * length2), midPointY - 3);

	}

	private void drawRetry() {
		batcher.draw(retry, 36, midPointY + 10, 66, 14);
	}

	private void drawReady() {
		batcher.draw(ready, 36, midPointY - 50, 68, 14);
	}

	private void drawGameOver() {
		batcher.draw(gameOver, 24, midPointY - 50, 92, 14);
	}

	private void drawScore() {
		int length = ("" + myWorld.getScore()).length();
		AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
				68 - (3 * length), midPointY - 82);
		AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
				68 - (3 * length), midPointY - 83);
	}

	/**Отрисовка счета*/
	private void drawHighScore() {
		batcher.draw(highScore, 22, midPointY - 50, 96, 14);
	}

	/**Отрисовка */
	public void render(float delta, float runTime) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);


		batcher.begin();
		batcher.disableBlending();

		//Рисуем фон.
		batcher.draw(bg, 0,0, 136, Gdx.graphics.getHeight() /(Gdx.graphics.getWidth() / 136));

		batcher.enableBlending();

		if (myWorld.isRunning()) {
			drawBall(runTime);
			drawStick(runTime);
			drawBlocks(runTime);
			drawScore();
		} else if (myWorld.isReady()) {
			drawBall(runTime);
			drawStick(runTime);
			drawBlocks(runTime);
			drawReady();
		} else if (myWorld.isMenu()) {
			drawBallCentered(runTime);
			drawStick(runTime);
			drawMenuUI();
		} else if (myWorld.isGameOver()) {
			//Вывеска со счётом.
			drawScoreboard();
			//
			drawBall(runTime);
			drawStick(runTime);
			drawBlocks(runTime);
			//Отрисовка фраз.
			drawGameOver();
			drawRetry();
		} else if (myWorld.isHighScore()) {
			drawScoreboard();
			drawBall(runTime);
			drawStick(runTime);
			drawBlocks(runTime);
			drawHighScore();
			drawRetry();
		}

		batcher.end();
		drawTransition(delta);

	}

	public void prepareTransition(int r, int g, int b, float duration) {
		transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
		alpha.setValue(1);
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, duration).target(0)
				.ease(TweenEquations.easeOutQuad).start(manager);
	}

	private void drawTransition(float delta) {
		if (alpha.getValue() > 0) {
			manager.update(delta);
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(transitionColor.r, transitionColor.g,
					transitionColor.b, alpha.getValue());
			shapeRenderer.rect(0, 0, 136, 300);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL10.GL_BLEND);

		}
	}
}
