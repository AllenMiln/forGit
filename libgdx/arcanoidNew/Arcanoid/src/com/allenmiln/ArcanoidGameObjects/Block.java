package com.allenmiln.ArcanoidGameObjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {
    //Позиция
    private Vector2 position;

    //Ширина
    private int width;
    //Высота
    private float height;

    //Для взаимодействия
    private Rectangle rectangle;

    private boolean isAlive;

    public Block(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        rectangle = new Rectangle(x,y, width, height);
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

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void die() {
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
