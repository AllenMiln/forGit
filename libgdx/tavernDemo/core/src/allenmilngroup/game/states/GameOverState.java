package allenmilngroup.game.states;

import allenmilngroup.game.TavernDemo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverState extends State {
    public GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, TavernDemo.WIDTH / 2, TavernDemo.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {
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

            new BitmapFont().draw(spriteBatch, "Game over", 170, 130);

        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
