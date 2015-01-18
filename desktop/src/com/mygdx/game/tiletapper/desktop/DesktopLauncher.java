package com.mygdx.game.tiletapper.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.tiletapper.TileTapper;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "TileTapper";
		config.width = 480;
		config.height = 800;
		new LwjglApplication(new TileTapper(), config);
	}
}
