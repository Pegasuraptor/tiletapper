package com.mygdx.game.tiletapper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.tiletapper.TileTapper;
import com.mygdx.game.tiletapper.misc.Enums.GameState;
import com.mygdx.game.tiletapper.misc.Enums.TouchType;
import com.mygdx.game.tiletapper.misc.GameStats;
import com.mygdx.game.tiletapper.tiles.TileGroup;

public class GameScreen implements Screen {
	final TileTapper game;
	private OrthographicCamera camera;
	private TileGroup tiles;
	
	private BitmapFont font;
	private TextureRegion lowerPanelTex;
	private GameState gameState;
		
	public GameScreen(final TileTapper g)
	{
		game = g;
		camera= new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		font = new BitmapFont(true);
		font.setColor(Color.BLACK);
		font.setScale(Gdx.graphics.getWidth() / 480f, Gdx.graphics.getHeight() / 800f);
				
		lowerPanelTex = new TextureRegion(new Texture("lowerpanel.png"));
		lowerPanelTex.flip(false, true);

		GameStats.timeToTouch = 10f; //GameStats.currentDifficulty.time();
		tiles = new TileGroup(game, GameStats.currentDifficulty.tiles());
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		gameState = GameState.RUNNING;
	}

	@Override
	public void render(float delta) {
		if(gameState == GameState.RUNNING)
		{
			Gdx.gl.glClearColor(0, 0.5f, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			camera.update();
			game.batch.setProjectionMatrix(camera.combined);
			
			GameStats.timeToTouch -= delta;
			
			//Stops little graphical glitch where time is a - number.
			if(GameStats.timeToTouch <= 0.0f)
				GameStats.timeToTouch = 0.0f;
			
			if(Gdx.input.justTouched())
			{
				Vector3 touchPos = new Vector3();
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPos);
				TouchType result = tiles.isTouched(touchPos.x, touchPos.y);
				if(result == TouchType.RIGHT)
				{
					GameStats.score += GameStats.timeToTouch * 5;
					GameStats.timeToTouch = GameStats.currentDifficulty.time();
				}
				else if(result == TouchType.WRONG)
				{
					GameStats.lives -= 1;
				}
				else if(result == TouchType.PAUSE)
				{
					pause();
				}
			}
					
			game.batch.begin();
			game.batch.setColor(Color.WHITE);
			game.batch.draw(lowerPanelTex, 0, Gdx.graphics.getWidth(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.4f);
			tiles.render(gameState);
			font.draw(game.batch, "Score: " + GameStats.score + "     Lives: " + GameStats.lives + "     Time: " + String.format("%.02f", GameStats.timeToTouch), (Gdx.graphics.getWidth() - font.getBounds("Score: " + GameStats.score + "     Lives: " + GameStats.lives + "     Time: " + String.format("%.02f", GameStats.timeToTouch)).width) * 0.5f, Gdx.graphics.getHeight() - (font.getLineHeight() * 2.5f));
			game.batch.end();
			
			gameOverChecks();
		}
		else
		{
			if(Gdx.input.justTouched())
			{
				Vector3 touchPos = new Vector3();
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPos);
				TouchType result = tiles.isTouched(touchPos.x, touchPos.y);
				if(result == TouchType.PAUSE)
				{
					gameState = GameState.RUNNING;
					return;
				}
			}
			
			game.batch.begin();
			game.batch.setColor(Color.GRAY);
			game.batch.draw(lowerPanelTex, 0, Gdx.graphics.getWidth(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 0.4f);
			tiles.render(gameState);
			font.draw(game.batch, "Score: " + GameStats.score + "     Lives: " + GameStats.lives + "     Time: " + String.format("%.02f", GameStats.timeToTouch), (Gdx.graphics.getWidth() - font.getBounds("Score: " + GameStats.score + "     Lives: " + GameStats.lives + "     Time: " + String.format("%.02f", GameStats.timeToTouch)).width) * 0.5f, Gdx.graphics.getHeight() - (font.getLineHeight() * 2.5f));
			game.batch.end();
		}
	}

	private void gameOverChecks() {
		if(GameStats.lives <= 0 || GameStats.timeToTouch <= 0.0f)
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
		gameState = GameState.PAUSED;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		gameState = GameState.PAUSED;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		if(tiles != null)
		{
			tiles.dispose();
			tiles = null;
		}
		
		if(font != null)
		{
			font.dispose();
			font = null;
		}

		if(lowerPanelTex != null)
		{
			lowerPanelTex.getTexture().dispose();
			lowerPanelTex = null;
		}
	}
}
