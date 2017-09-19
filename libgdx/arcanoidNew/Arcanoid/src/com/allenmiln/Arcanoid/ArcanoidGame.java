package com.allenmiln.Arcanoid;

import com.allenmiln.ArcanoidScreens.SplashScreen;
import com.allenmiln.ArcanoidHelpers.AssetLoader;
import com.badlogic.gdx.Game;

public class ArcanoidGame extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}