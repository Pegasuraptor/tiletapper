package com.mygdx.game.tiletapper.misc;

public class Enums {
	public enum TouchType {
		RIGHT, WRONG, OOB, PAUSE
	}
	
	public enum GameState {
		RUNNING, PAUSED, STARTING
	}
	
	public enum Difficulty {
		EASY (3, 3f),
		NORMAL (4, 2.0f),
		HARD (5, 1.5f);
		
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
