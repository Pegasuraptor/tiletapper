package com.mygdx.game.tiletapper.tiles;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.tiletapper.TileTapper;

public class TileGroup {
	private int numTiles;
	private int squareNumTiles;
	public Color currentColour;
	public Colours colours;
	
	public Array<Tile> tiles;
	public Tile indicatorTile;
	final TileTapper game;
	Texture img;
	Random rand;
	float tileSize; 
	
	public TileGroup(final TileTapper g, int nT) 
	{
		game = g;
		
		currentColour = new Color();
		colours = new Colours();
		setNumTiles(nT);
		img = new Texture("tile.png");
		rand = new Random();
		tiles = new Array<Tile>(getSquareNumTiles());
		tileSize = (float)(Gdx.graphics.getWidth()/getNumTiles());
		indicatorTile = new Tile(img, (Gdx.graphics.getWidth() / 2) - (tileSize/2), Gdx.graphics.getHeight() - tileSize - 30, tileSize, tileSize);
		for(int i = 0; i < getNumTiles(); ++i)
		{
			for(int j = 0; j < getNumTiles(); ++j)
			{
				tiles.add(new Tile(img, i * tileSize, j * tileSize, tileSize, tileSize));
			}
		}
		
		makeRandom();
	}
	
	public void render()
	{
		for(Tile tile:tiles)
		{
			game.batch.setColor(tile.getColour());
			game.batch.draw(tile.getSquare(), tile.getxPos(), tile.getyPos(), tile.getWidth(), tile.getHeight());
		}
		game.batch.setColor(indicatorTile.getColour());
		game.batch.draw(indicatorTile.getSquare(), indicatorTile.getxPos(), indicatorTile.getyPos(), indicatorTile.getWidth(), indicatorTile.getHeight());
	}
	
	public boolean isTouched(float x, float y)
	{
		for(Tile tile: tiles)
		{
			if(tile.isTouched(x, y) && (tile.getColour() == currentColour))
			{
				makeRandom();
				return true;
			}
		}
		
		return false;
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
}
