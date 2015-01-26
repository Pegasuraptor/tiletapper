package com.mygdx.game.tiletapper.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

public class Colours {
	public Array<Color> allColours;
	public Array<Color> gameColours;
	
	public Colours()
	{
		allColours = new Array<Color>(25);
		allColours.add(Color.BLUE);
		allColours.add(Color.RED);
		allColours.add(Color.GREEN);
		allColours.add(Color.YELLOW);
		allColours.add(Color.ORANGE);
		allColours.add(Color.PINK);
		allColours.add(Color.PURPLE);
		allColours.add(Color.MAGENTA);
		allColours.add(Color.CYAN);
		//9
		allColours.add(Color.BLACK);
		allColours.add(Color.DARK_GRAY);
		allColours.add(Color.GRAY);
		allColours.add(Color.LIGHT_GRAY);
		allColours.add(Color.MAROON);
		allColours.add(Color.NAVY);
		allColours.add(Color.OLIVE);
		//16
		allColours.add(Color.TEAL);
		allColours.add(Color.WHITE);
		//25 eventually. Also will come up with better colours after 9.
	}
	
	public void setGameColours(int numTiles)
	{
		gameColours = new Array<Color>(numTiles);
		for(int i = 0; i < numTiles; ++i)
		{
			gameColours.add(allColours.get(i));
		}
	}
}
