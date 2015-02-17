package com.mygdx.game.tiletapper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.tiletapper.TileTapper;
import com.mygdx.game.tiletapper.misc.Enums.Difficulty;
import com.mygdx.game.tiletapper.misc.Enums.TouchType;
import com.mygdx.game.tiletapper.tiles.TileGroup;

public class GameScreen implements Screen {
	final TileTapper game;
	private OrthographicCamera camera;
	private TileGroup tiles;
	
	public int score = 0;
	public int lives = 3;
	
	Difficulty currentDifficulty = Difficulty.EASY;
	
	private float timeToTouch;
	private BitmapFont font;
		
	public GameScreen(final TileTapper g)
	{
		game = g;
		camera= new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		font = new BitmapFont(true);
		font.setColor(Color.WHITE);
		font.setScale(Gdx.graphics.getDensity());
	
		timeToTouch = currentDifficulty.time();
		tiles = new TileGroup(game, currentDifficulty.tiles());
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.5f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		//timeToTouch -= delta;
		
		//Stops little graphical glitch where time is a - number.
		if(timeToTouch <= 0.0f)
			timeToTouch = 0.0f;
		
		if(Gdx.input.justTouched())
		{
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			TouchType result = tiles.isTouched(touchPos.x, touchPos.y);
			if(result == TouchType.RIGHT)
			{
				score += timeToTouch * 5;
				timeToTouch = currentDifficulty.time();
			}
			else if(result == TouchType.WRONG)
			{
				lives -= 1;
			}
		}
		
		gameOverChecks();
		
		game.batch.begin();
		tiles.render();
		font.draw(game.batch, "Score: " + score + "     Lives: " + lives + "     Time: " + String.format("%.02f", timeToTouch), 0, Gdx.graphics.getHeight() - font.getLineHeight());
		game.batch.end();
	}

	private void gameOverChecks() {
		if(lives <= 0 || timeToTouch <= 0.0f)
		{
			dispose();
			game.setScreen(new GameOverScreen(game));
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
