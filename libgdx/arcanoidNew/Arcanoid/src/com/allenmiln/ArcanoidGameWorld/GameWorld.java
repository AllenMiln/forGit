package com.allenmiln.ArcanoidGameWorld;

import com.allenmiln.ArcanoidGameObjects.Ball;
import com.allenmiln.ArcanoidGameObjects.Block;
import com.allenmiln.ArcanoidGameObjects.Stick;
import com.allenmiln.ArcanoidHelpers.AssetLoader;
import com.allenmiln.ArcanoidHelpers.BlockHelpers;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {

	private Ball ball;
	private Stick stick;
	private Rectangle ground;
	private BlockHelpers blockHelpers;

	private int score = 0;
	private float runTime = 0;
	private int midPointY;
	private GameRenderer renderer;

	private GameState currentState;


	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
	}

	public GameWorld(int midPointY) {
		currentState = GameState.MENU;
		this.midPointY = midPointY;
		ball = new Ball(33, midPointY - 5, 15, 15);
		stick = new Stick(37, midPointY + 100, 64, 16);

		ground = new Rectangle(0, midPointY + 120, 137, 11);

		blockHelpers = new BlockHelpers();
		blockHelpers.load();
	}

	public void update(float delta) {
		runTime += delta;

		switch (currentState) {
		case READY:
		case MENU:
			updateReady(delta);
			break;

		case RUNNING:
			updateRunning(delta);
			break;
		default:
			break;
		}

	}

	private void updateReady(float delta) {
		ball.updateReady(runTime);
		//stick.updateReady(runTime);

	}

	public void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}

		ball.update(delta);
		stick.update(delta);

		//Пересечение с полом
		if (Intersector.overlaps(ball.getBoundingCircle(), ground)) {

			if (ball.isAlive()) {
				//Звук смерти
				AssetLoader.dead.play();
				//Вспышка перед смертью
				renderer.prepareTransition(255, 255, 255, .3f);

				//Смена состояния мяча.
				ball.die();
				stick.die();
			}

			//Остановка обновления движущихся элементов.
			//scroller.stop();
			//Обнуление ускорения.
			ball.decelerate();
			//Выставление стадии: game over.
			currentState = GameState.GAMEOVER;

			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
			}
		}

		if (Intersector.overlaps(ball.getBoundingCircle(), stick.getRectangle())) {
			ball.crossingWithAStick();
		}

		for (Block block: blockHelpers.getAliveBlockMatrix()) {
			if (Intersector.overlaps(ball.getBoundingCircle(), block.getRectangle())) {
				ball.crossingWithABlock();
				block.die();
				addScore(1);
			}
		}

	}
	public Ball getBall() {
		return ball;
	}

	public Stick getStick() {
		return stick;
	}

	public BlockHelpers getBlockHelpers() {
		return blockHelpers;
	}

	public int getMidPointY() {
		return midPointY;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}

	public void start() {
		currentState = GameState.RUNNING;
		ball.start();
	}

	/**Установка стадии Ready */
	public void ready() {
		currentState = GameState.READY;
		renderer.prepareTransition(0, 0, 0, 1f);
	}

	public void restart() {
		//score = 0;
		ball.onRestart(midPointY - 5);
		stick.onRestart(midPointY + 100);
		ready();
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}

}
