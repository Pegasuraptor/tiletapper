package com.mygdx.game.tiletapper.tiles;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.tiletapper.TileTapper;
import com.mygdx.game.tiletapper.misc.Enums.GameState;
import com.mygdx.game.tiletapper.misc.Enums.TouchType;

public class TileGroup {
	private int numTiles;
	private int squareNumTiles;
	public Color currentColour;
	public Colours colours;
	
	public Array<Tile> tiles;
	public Tile indicatorTile;
	public Tile pauseTile;
	final TileTapper game;
	Texture img;
	Texture pause;
	Texture play;
	Random rand;
	float tileSize; 
	
	public TileGroup(final TileTapper g, int nT) 
	{
		game = g;

		currentColour = new Color();
		colours = new Colours();
		setNumTiles(nT);
		img = new Texture("tile.png");
		pause = new Texture("pause.png");
		play = new Texture("play.png");
		rand = new Random();
		tiles = new Array<Tile>(getSquareNumTiles());
		tileSize = (float)(Gdx.graphics.getWidth()/getNumTiles());
		indicatorTile = new Tile(img, (Gdx.graphics.getWidth() - (Gdx.graphics.getHeight() * 0.2f)) * 0.5f, Gdx.graphics.getWidth() * 1.13f, Gdx.graphics.getHeight() * 0.2f, Gdx.graphics.getHeight() * 0.2f);
		pauseTile = new Tile(pause, (Gdx.graphics.getWidth() * 0.85f) - ((Gdx.graphics.getWidth() * 0.125f) * 0.5f), (Gdx.graphics.getHeight() * 0.8f) - ((Gdx.graphics.getWidth() * 0.125f) * 0.5f), Gdx.graphics.getWidth() * 0.125f, Gdx.graphics.getWidth() * 0.125f);
		for(int i = 0; i < getNumTiles(); ++i)
		{
			for(int j = 0; j < getNumTiles(); ++j)
			{
				tiles.add(new Tile(img, i * tileSize, j * tileSize, tileSize, tileSize));
			}
		}
		
		makeRandom();
	}
	
	public void render(GameState state)
	{
		for(Tile tile:tiles)
		{
			game.batch.setColor(tile.getColour());
			game.batch.draw(tile.getSquare(), tile.getxPos(), tile.getyPos(), tile.getWidth(), tile.getHeight());
		}
		game.batch.setColor(Color.WHITE);
		game.batch.draw(state == GameState.PAUSED ? play : pause, pauseTile.getxPos(), pauseTile.getyPos(), pauseTile.getWidth(), pauseTile.getHeight());
		game.batch.setColor(indicatorTile.getColour());
		game.batch.draw(indicatorTile.getSquare(), indicatorTile.getxPos(), indicatorTile.getyPos(), indicatorTile.getWidth(), indicatorTile.getHeight());
	}
	
	public TouchType isTouched(float x, float y)
	{
		for(Tile tile: tiles)
		{
			if(tile.isTouched(x, y) && (tile.getColour() == currentColour))
			{
				makeRandom();
				return TouchType.RIGHT;
			}
			else if(tile.isTouched(x, y) && (tile.getColour() != currentColour))
			{
				return TouchType.WRONG;
			}
		}
		
		if(pauseTile.isTouched(x, y))
		{
			return TouchType.PAUSE;
		}
		
		return TouchType.OOB;
	}
	
	public void makeRandom() {
		colours.gameColours.shuffle();
		for(int i = 0; i < getSquareNumTiles(); ++i)
		{
			tiles.get(i).setColour(colours.gameColours.get(i));
		}
		currentColour = colours.gameColours.get(rand.nextInt(getSquareNumTiles()));
		indicatorTile.setColour(currentColour);
	}
	
	public int getNumTiles() {
		return numTiles;
	}

	public void setNumTiles(int numTiles) {
		this.numTiles = numTiles;
		this.squareNumTiles = (numTiles * numTiles);
		colours.setGameColours(this.squareNumTiles);
	}

	public int getSquareNumTiles() {
		return squareNumTiles;
	}
	
	public void dispose() {
		
		if(rand != null)
		{
			rand = null;
		}

		if(currentColour != null)
		{
			currentColour = null;
		}
		
		if(colours != null)
		{
			colours.dispose();
			colours = null;
		}
		
		if(img != null)
		{
			img.dispose();
			img = null;
		}
		
		if(pause != null)
		{
			pause.dispose();
			pause = null;
		}
		
		if(play != null)
		{
			play.dispose();
			play = null;
		}
		
		if(indicatorTile != null)
		{
			indicatorTile.dispose();
			indicatorTile = null;
		}
		
		for(Tile tile: tiles)
		{
			tile.dispose();
			tile = null;
		}
		
		if(tiles != null)
		{
			tiles.clear();
			tiles = null;
		}
	}
}
