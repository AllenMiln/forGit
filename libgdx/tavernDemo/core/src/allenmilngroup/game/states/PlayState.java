package allenmilngroup.game.states;

import allenmilngroup.game.BarrelManager;
import allenmilngroup.game.sprites.Barrel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import allenmilngroup.game.sprites.Mouse;
import allenmilngroup.game.TavernDemo;
import com.badlogic.gdx.utils.TimeUtils;


public class PlayState extends State {

    //Мышь.
    private Mouse mouse;
    //Координаты места прикосновения.
    private Vector3 touchPos;
    //Создание менеджера бочек.
    private BarrelManager barrelManager;

    //Текстура пола.
    Texture texture;

    //Время начала
    private long time;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        //Создание новой мыши.
        mouse = new Mouse(200, 20);
        //Создание менеджера бочек.
        barrelManager = new BarrelManager();


        texture = new Texture("s_ground.png");
        time = TimeUtils.millis();

        touchPos = new Vector3();
        camera.setToOrtho(false, TavernDemo.WIDTH / 2, TavernDemo.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            //Перевод координат из экраных в мировые координаты.
            camera.unproject(touchPos);
            //Определение направления движения мыши.
            mouse.move(touchPos);
        }
    }

    @Override
    public void update(float dt) {
        //Проверяем на нажатие.
        handleInput();

        //Перемещение мыши. Установка новых координат.
        mouse.update(dt);

        //Перемещениие бочек.
        barrelManager.update(dt);

        //Проверка на столкновение с мышью.
        barrelManager.checkMouseCollisions(mouse);

        if (mouse.getLive() == 0) {
            mouse.dispose();
            barrelManager.dispose();
            gameStateManager.set(new GameOverState(gameStateManager));
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        //Сообщаем spriteBatch о системе координат визуализации указанных для камеры.
        spriteBatch.setProjectionMatrix(camera.combined);

        //Блок отрисовки.
        spriteBatch.begin();
            //Отрисовываем мышь.
            spriteBatch.draw(mouse.getMouse(), mouse.getPosition().x, mouse.getPosition().y);

            //отрисовка бочек.
            for (Barrel barrel: barrelManager.getBarrelArrayList()) {
                spriteBatch.draw(barrel.getBarrel(), barrel.getPosition().x, barrel.getPosition().y);
            }

            spriteBatch.draw(texture, 0, -85);
            spriteBatch.draw(texture, texture.getWidth(), -85);

            //Не самое лучшее решение.
            new BitmapFont().draw(spriteBatch, "Live: " + mouse.getLive(), 10, 20);

            new BitmapFont().draw(spriteBatch, "Time: " + (TimeUtils.millis() - time) / 1000, 10, 230);

        //Конец блока отрисовки.
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        mouse.dispose();
        barrelManager.dispose();
    }
}
