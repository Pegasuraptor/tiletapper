package com.mygdx.game.tiletapper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tiletapper.screens.MenuScreen;

public class TileTapper extends Game {
	public SpriteBatch batch;
		
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	public void dispose() {
		batch.dispose();
	}
}
