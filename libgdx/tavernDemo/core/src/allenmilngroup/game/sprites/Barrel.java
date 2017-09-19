package allenmilngroup.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Barrel {
    //Константы
    private static final int GRAVITY = -5;

    private int movement;

    //Позиция бочки.
    private Vector3 position;

    //Текстура бочки.
    private Texture texture;

    //Регион бочки.
    private Rectangle bounds;

    //Скорость перемещения бочки.
    private Vector3 velosity;

    public Barrel(int x, int y) {
        //Позиция бочки.
        position = new Vector3(x, y, 0);
        //Скорость бочки.
        velosity = new Vector3(0, 0, 0);

        movement = getRandomMovement();

        //Текстура бочки.
        texture = new Texture("barrelTwo.png");

        //Регион бочки.
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public Barrel() {
        //Текстура бочки.
        texture = new Texture("barrelTwo.png");

        int x = MathUtils.random(0, 400 - texture.getWidth());
        int y = 240;

        //Позиция бочки.
        position = new Vector3(x, y, 0);
        //Скорость бочки.
        velosity = new Vector3(0, 0, 0);

        movement = getRandomMovement();

        //Регион бочки.
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public Vector3 getVelosity() {
        return velosity;
    }

    public void setVelosity(Vector3 velosity) {
        this.velosity = velosity;
    }

    /**
     * Перемещение бочки. Установка новых координат.
     */
    public void update(float dt) {

        move();
        //Изменяем скорость по Y
        velosity.add(0, GRAVITY, 0);

        //Установка скорости за dt.
        velosity.scl(dt);

        //Устанавливаем новую позицию.
        position.add(movement * dt, velosity.y, 0);

        //Ускорение со временем.
        velosity.scl(1 / dt);

        bounds.setPosition(position.x, position.y);
    }

    /**
     * Определение направления движения бочки и её столкновений со стенами.
     */
    public void move() {
        //Инвертируем скорость по X при столкновении со стеной.
        if ((position.x < 0) || (position.x > 400 - bounds.getWidth())) {
            movement *= -1;
        }
    }

    /**
     * Получение рандомного направления при старте бочки
     */
    private int getRandomMovement() {
        return MathUtils.random(-200, 200);
    }

    /**
     * Возвращаем текстуру бочки.
     */
    public Texture getBarrel() {
        return texture;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Проверяем столкновение бочек
     */
    public void collision(Barrel barrel) {
        if (bounds.overlaps(barrel.getBounds())) {
            int xSpeed = movement;
            movement = barrel.getMovement();
            barrel.setMovement(xSpeed);

            float ySpeed = velosity.y;
            velosity.y = barrel.getVelosity().y;
            barrel.setVelosity(new Vector3(0, ySpeed, 0));
        }
    }

    public void mouseCollision(Mouse mouse) {
        if (bounds.overlaps(mouse.getBounds())) {
            if (velosity.y < 0) {
                velosity.set(0, velosity.y * -1, 0);
                mouse.lostLive();
            }
        }
    }

    public void dispose() {
        texture.dispose();
    }

}
