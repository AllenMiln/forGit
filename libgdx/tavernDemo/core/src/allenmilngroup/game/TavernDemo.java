package allenmilngroup.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import allenmilngroup.game.states.GameStateManager;
import allenmilngroup.game.states.MenuState;

public class TavernDemo extends ApplicationAdapter {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;

	public static final String TITLE = "tavern DEMO";
	private GameStateManager gameStateManager;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();

		//Устанавливаем цвет фона.
		//Gdx.gl.glClearColor(1, 1, 1, 1);
		//Создание и установка меню. Передача менеджера в конструктор.
		gameStateManager.push(new MenuState(gameStateManager));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);
	}

}
