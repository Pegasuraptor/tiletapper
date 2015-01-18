package com.mygdx.game.tiletapper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tiletapper.screens.GameScreen;

public class TileTapper extends Game {
	public SpriteBatch batch;
	private int numTiles;
	private int squareNumTiles;
	public Color currentColour;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		currentColour = new Color();
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	public void dispose() {
		batch.dispose();
	}

	public int getNumTiles() {
		return numTiles;
	}

	public void setNumTiles(int numTiles) {
		this.numTiles = numTiles;
		this.squareNumTiles = (numTiles * numTiles);
	}

	public int getSquareNumTiles() {
		return squareNumTiles;
	}
}
