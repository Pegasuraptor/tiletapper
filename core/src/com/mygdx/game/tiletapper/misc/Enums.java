package com.mygdx.game.tiletapper.misc;

public class Enums {
	public enum TouchType {
		RIGHT, WRONG, OOB
	}
	
	public enum Difficulty {
		EASY (3, 3f),
		NORMAL (4, 1.5f),
		HARD (5, 0.75f);
		
		private final int tiles;
		private final float time; 
		Difficulty(int tiles, float time)
		{
			this.tiles = tiles;
			this.time = time;
		}
		
		public int tiles()
		{
			return tiles;
		}
		
		public float time()
		{
			return time;
		}
	}
}
