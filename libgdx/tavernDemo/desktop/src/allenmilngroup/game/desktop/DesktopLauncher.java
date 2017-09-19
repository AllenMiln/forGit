package allenmilngroup.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import allenmilngroup.game.TavernDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = TavernDemo.WIDTH;
		config.height = TavernDemo.HEIGHT;
		config.title = TavernDemo.TITLE;
		new LwjglApplication(new TavernDemo(), config);

	}
}
