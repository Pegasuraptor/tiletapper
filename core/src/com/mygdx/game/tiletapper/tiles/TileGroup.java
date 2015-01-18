package com.mygdx.game.tiletapper.tiles;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.tiletapper.TileTapper;

public class TileGroup {
	public Array<Tile> tiles;
	final TileTapper game;
	Texture img;
	Random rand;
	float swont; 
	
	public TileGroup(final TileTapper g) 
	{
		game = g;
		img = new Texture("tile.png");
		rand = new Random();
		tiles = new Array<Tile>(game.getSquareNumTiles());
		swont = (float)(Gdx.graphics.getWidth()/game.getNumTiles());
				
		for(int i = 0; i < game.getNumTiles(); ++i)
		{
			for(int j = 0; j < game.getNumTiles(); ++j)
			{
				tiles.add(new Tile(img, i * swont, j * swont, swont, swont));
			}
		}
		
		for(Tile tile:tiles)
		{
			makeRandom(tile);
		}
	}
	
	public void render()
	{
		for(Tile tile:tiles)
		{
			game.batch.setColor(tile.getColour());
			game.batch.draw(tile.getSquare(), tile.getxPos(), tile.getyPos(), tile.getWidth(), tile.getHeight());
		}
	}
	
	public void isTouched(float x, float y)
	{
		for(Tile tile: tiles)
		{
			if(tile.isTouched(x, y))
			{
				makeRandom(tile);
			}
		}
	}
	
	public void makeRandom(Tile tile) {
		tile.setColour(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1));
	}
}
