package com.allenmiln.ArcanoidGameObjects;

import com.allenmiln.ArcanoidHelpers.AssetLoader;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Ball {

	//Позиция
	private Vector2 position;
	//Скорость
	private Vector2 velocity;
	//Ускорение
	private Vector2 acceleration;

	//Ширина
	private int width;
	//Высота
	private float height;

	private float originalY;

	private boolean isAlive;

	//Круг для взаимодействия
	private Circle boundingCircle;

	public Ball(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		this.originalY = y;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		//acceleration = new Vector2(-20, -20);
		acceleration = new Vector2(0, 0);
		boundingCircle = new Circle();
		isAlive = true;
	}

	public void crossingWithAStick(){
        if (velocity.y > 0) {
            acceleration.y *= -1;
            velocity.y *= -1;
        }
    }

	public void crossingWithABlock() {
		acceleration.y *= -1;
		velocity.y *= -1;
		velocity.add( velocity.x / 2, velocity.y / 2);
	}

	public void update(float delta) {

		velocity.add(acceleration.cpy().scl(delta));

		//Ограничитель скорости.
		if (velocity.y > 200) {
			velocity.y = 200;
		}

		if (velocity.x > 200) {
			velocity.x = 200;
		}

		// Инвертор при ударении от потолка
		if (position.y < 0 && velocity.y < 0) {
			acceleration.y *= -1;
			velocity.y *= -1;
		}

        // Инвертор при ударении от левой или правой стены
        if ((position.x < 0) && (velocity.x < 0)) {
            acceleration.x *= -1;
            velocity.x *= -1;
        }
        if ((position.x > (136 - width)) && (velocity.x > 0)) {
            acceleration.x *= -1;
            velocity.x *= -1;
        }

		position.add(velocity.cpy().scl(delta));

		// Set the circle's center to be (9, 6) with respect to the ball.
		// Set the circle's radius to be 6.5f;
		boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

	}

	public void updateReady(float runTime) {
		position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
	}

	/**Ускорение при нажатии */
	public void onClick() {
		if (isAlive) {
			AssetLoader.flap.play();
			velocity.y = -140;
		}
	}

	/**Смена состояния на 'false'. Скорость обнуляем по X и Y */
	public void die() {
		isAlive = false;
		velocity.x = 0;
		velocity.y = 0;
	}

	//Обнуление ускорения
	public void decelerate() {
		acceleration.x = 0;
		acceleration.y = 0;
	}

	public void onRestart(int y) {
		position.y = y;
		velocity.x = 0;
		velocity.y = 0;
		acceleration.x = -20;
		acceleration.y = -20;
		isAlive = true;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void start() {
		velocity.add(-20, -20);
	}
}
