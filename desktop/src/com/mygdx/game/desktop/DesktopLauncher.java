package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MainGame;
import com.mygdx.game.MainGame2;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "my-gdx-ga";
		config.useGL30 = false;
		config.width = 600;
		config.height = 400;
		new LwjglApplication(new MainGame(), config);
	}
}
