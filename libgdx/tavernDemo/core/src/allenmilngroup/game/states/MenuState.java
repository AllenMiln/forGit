package allenmilngroup.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import allenmilngroup.game.TavernDemo;

public class MenuState extends State {

    private Texture playButton;


    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, TavernDemo.WIDTH / 2, TavernDemo.HEIGHT / 2);
        playButton = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        //Если нажато - запускаем игру.
        if (Gdx.input.justTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        //отрисовываем кнопку
        spriteBatch.draw(playButton, camera.position.x - playButton.getWidth() / 2, camera.position.y - playButton.getHeight() / 2);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        playButton.dispose();
    }
}
