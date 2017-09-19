package com.allenmiln.ArcanoidGameObjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.abs;

public class Stick {
    //Позиция
    private Vector2 position;
    //Куда ей необходимо придти.
    private Vector2 touchPos;

    //Скорость
    private Vector2 velocity;

    //Ширина
    private int width;
    //Высота
    private float height;
    private boolean isAlive;

    //Для взаимодействия
    private Rectangle rectangle;

    public Stick(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        touchPos = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        isAlive = true;

        rectangle = new Rectangle(x,y, width, height);
    }

    public void update(float delta) {
        if (abs(position.x - touchPos.x) > 1) {
            position.x += velocity.x * delta;
        }

        if (position.x < 0) {
            position.x = 0;
        }

        if (position.x > 136 - rectangle.getWidth()) {
            position.x = 136 - rectangle.getWidth();
        }
        rectangle.setPosition(position.x, position.y);
    }


    /**Движение */
    public void onClick(int screenX, int screenY) {
        if (isAlive) {
            velocity.x = (position.x < screenX) ? 50 : -50;
        }
        touchPos.set(screenX, screenY);
    }

    /**Смена состояния на 'false'. Скорость обнуляем по X и Y */
    public void die() {
        isAlive = false;
        velocity.x = 0;

    }

    public void onRestart(int y) {
        position.y = y;
        rectangle.y = y;
        velocity.x = 0;
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

    public boolean isAlive() {
        return isAlive;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

}

