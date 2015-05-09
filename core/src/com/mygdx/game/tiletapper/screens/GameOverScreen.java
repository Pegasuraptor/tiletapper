package com.mygdx.game.tiletapper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.mygdx.game.tiletapper.TileTapper;
import com.mygdx.game.tiletapper.misc.Enums.Difficulty;
import com.mygdx.game.tiletapper.misc.GameStats;

public class GameOverScreen implements Screen {
	final TileTapper game;
	private Stage stage;
	private Table table;
	private ShapeRenderer shapeRenderer;
		
	public GameOverScreen(final TileTapper g)
	{
		game = g;
		
		stage = new Stage();
	    Gdx.input.setInputProcessor(stage);

	    table = new Table();
	    table.setFillParent(true);
	    stage.addActor(table);

	    shapeRenderer = new ShapeRenderer();
	    
	    Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	    
	    int oldScore = GameStats.score;
	    
	    //Originally had this inside the button listeners but it's the same every time.
		GameStats.lives = 3;
		GameStats.score = 0;

	    VerticalGroup menu = new VerticalGroup().space(15).pad(5).fill();
	    Label gameOver = new Label("Game Over!", skin);
	    menu.addActor(gameOver);
	    Label score = new Label("Final Score: " + oldScore, skin);
	    menu.addActor(score);
	    Label tryAgain = new Label("Try Again?", skin);
	    menu.addActor(tryAgain);
	    TextButton button = new TextButton("Easy", skin);
	    button.addListener(new InputListener() {
	    	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	    		GameStats.currentDifficulty = Difficulty.EASY;
	    		dispose();
	    		g.setScreen(new GameScreen(g));
	    		return true;
	    	}
	    });
	    menu.addActor(button);
	    button = new TextButton("Normal", skin);
	    button.addListener(new InputListener() {
	    	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	    		GameStats.currentDifficulty = Difficulty.NORMAL;
	    		dispose();
	    		g.setScreen(new GameScreen(g));
	    		return true;
	    	}
	    });
	    menu.addActor(button);
	    button = new TextButton("Hard", skin);
	    button.addListener(new InputListener() {
	    	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	    		GameStats.currentDifficulty = Difficulty.HARD;
	    		dispose();
	    		g.setScreen(new GameScreen(g));
				return true;
	    	}
	    });
	    menu.addActor(button);
	    button = new TextButton("Main Menu", skin);
	    button.addListener(new InputListener() {
	    	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	    		dispose();
	    		g.setScreen(new MenuScreen(g));
				return true;
	    	}
	    });
	    menu.addActor(button);
	    table.addActor(menu);
	    table.pack();
	    menu.setPosition((table.getWidth() - menu.getWidth()) * 0.5f, (table.getHeight() * 0.75f));
	    menu.setScaleX(table.getWidth() / 480f);
	    menu.setScaleY(table.getHeight() / 800f);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.4f, 0f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    stage.act(Gdx.graphics.getDeltaTime());
	    stage.draw();
	    
	    stage.getBatch().begin();
	    table.draw(stage.getBatch(), 0);
	    stage.getBatch().end();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
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
		Gdx.input.setInputProcessor(null);
		
		if(stage != null)
		{
			stage.dispose();
			stage = null;
		}
		
		if(shapeRenderer != null)
		{
			shapeRenderer.dispose();
			shapeRenderer = null;
		}
	}
}
