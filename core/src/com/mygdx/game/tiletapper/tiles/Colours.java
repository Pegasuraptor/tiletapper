package com.mygdx.game.tiletapper.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

public class Colours {
	public Array<Color> allColours;
	public Array<Color> gameColours;
	
	static String[] colourValues = new String[] { 
        "FF0000FF", "00FF00FF", "0000FFFF", "FFFF00FF", "FF00FFFF", "00FFFFFF", "000000FF", 
        "800000FF", "008000FF", "000080FF", "808000FF", "800080FF", "008080FF", "808080FF", 
        "C00000FF", "00C000FF", "0000C0FF", "C0C000FF", "C000C0FF", "00C0C0FF", "C0C0C0FF", 
        "400000FF", "004000FF", "000040FF", "404000FF", "400040FF", 
    };
	
	public Colours()
	{
		allColours = new Array<Color>(25);
		
		for(int i = 0; i < colourValues.length; ++i)
		{
			allColours.add(Color.valueOf(colourValues[i]));
		}
		
//		allColours.add(Color.BLUE);
//		allColours.add(Color.RED);
//		allColours.add(Color.GREEN);
//		allColours.add(Color.YELLOW);
//		allColours.add(Color.ORANGE);
//		allColours.add(Color.PINK);
//		allColours.add(Color.PURPLE);
//		allColours.add(Color.MAGENTA);
//		allColours.add(Color.CYAN);
//		//9
//		allColours.add(Color.BLACK);
//		allColours.add(Color.DARK_GRAY);
//		allColours.add(Color.GRAY);
//		allColours.add(Color.LIGHT_GRAY);
//		allColours.add(Color.MAROON);
//		allColours.add(Color.NAVY);
//		allColours.add(Color.OLIVE);
//		//16
//		allColours.add(Color.TEAL);
//		allColours.add(Color.WHITE);
//		//25 eventually. Also will come up with better colours after 9.
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
