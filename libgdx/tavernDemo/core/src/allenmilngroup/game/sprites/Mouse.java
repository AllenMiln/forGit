package allenmilngroup.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


import static java.lang.Math.abs;

public class Mouse {
    //Позиция мыши.
    private Vector3 position;
    //Куда ей необходимо придти.
    private Vector3 touchPos;

    //Текстура мыши.
    private Texture texture;

    //Текстура мыши. нужна только для отзеркаливания.
    private TextureRegion textureRegion;
    private TextureRegion textureRegionFlip;

    //Регион мыши.
    private Rectangle bounds;

    //Скорость перемещения.
    private int velocity;

    //Количество жизней
    private Integer live;

    public Mouse(int x, int y) {
        //Позиция мыши.
        position = new Vector3(x, y, 0);
        //Куда ей необходимо придти.
        touchPos = new Vector3(x, y, 0);

        //Текстура.
        texture = new Texture("mouse.png");

        textureRegion = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());

        textureRegionFlip = new TextureRegion(textureRegion);
        textureRegionFlip.flip(true, false);

        //Регион мыши.
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());

        //Количество жизней
        live = 3;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Integer getLive() {
        return live;
    }

    /**
     * Перемещение мыши. Установка новых координат.
     */
    public void update(float dt) {
        if (abs(position.x - touchPos.x) > 1) {
            position.x += velocity * dt;
        }

        if (position.x < 0) {
            position.x = 0;
        }

        if (position.x > 400 - bounds.getWidth()) {
            position.x = 400 - bounds.getWidth();
        }
        bounds.setPosition(position.x, position.y);
    }

    /**
     * Определение направления движения мыши.
     */
    public void move(Vector3 touchPos) {
        this.touchPos.set(touchPos.x - texture.getWidth() / 2, touchPos.y, touchPos.z);

        //Направление движения
        velocity = ((position.x + (texture.getWidth() / 2)) < touchPos.x) ? 100 : -100;
    }

    /**
     * Возвращаем текстуру мыши.
     */
    public TextureRegion getMouse() {
        return velocity < 0 ? textureRegion : textureRegionFlip;
    }

    public void dispose() {
        texture.dispose();
    }

    public void lostLive() {
        live--;
    }
}
