package com.mygdx.game.tiletapper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.mygdx.game.tiletapper.TileTapper;
import com.mygdx.game.tiletapper.misc.Enums.Difficulty;
import com.mygdx.game.tiletapper.misc.GameStats;
import com.sun.prism.image.ViewPort;

public class MenuScreen implements Screen {
	final TileTapper game;
	private Stage stage;
	private Table table;
	private ShapeRenderer shapeRenderer;
		
	public MenuScreen(final TileTapper g)
	{
		game = g;
		
		stage = new Stage();
	    Gdx.input.setInputProcessor(stage);

	    table = new Table();
	    table.setFillParent(true);
	    stage.addActor(table);

	    shapeRenderer = new ShapeRenderer();
	    
	    final TextureRegion region = new TextureRegion(new Texture("tiletapper.png"));
	    final Actor actor = new Actor() {
	    	public void draw(Batch batch, float parentAlpha) {
	    		Color color = getColor();
				batch.setColor(color.r, color.g, color.b, parentAlpha);
				batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());
	    	}
	    };
	    actor.setOrigin(0, 0);
	    actor.setBounds((stage.getWidth() - region.getTexture().getWidth()) * 0.5f, stage.getHeight() - 50 - region.getTexture().getHeight(), region.getTexture().getWidth(), region.getTexture().getHeight());
	    stage.addActor(actor);
	    
	    Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

	    VerticalGroup menu = new VerticalGroup().space(15).pad(5).fill();
	    TextButton button = new TextButton("Easy", skin);
	    button.addListener(new InputListener() {
	    	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	    		GameStats.currentDifficulty = Difficulty.EASY;
	    		GameStats.lives = 3;
	    		GameStats.score = 0;
	    		g.setScreen(new GameScreen(g));
				return true;
	    	}
	    });
	    menu.addActor(button);
	    button = new TextButton("Normal", skin);
	    button.addListener(new InputListener() {
	    	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	    		GameStats.currentDifficulty = Difficulty.NORMAL;
	    		GameStats.lives = 3;
	    		GameStats.score = 0;
	    		g.setScreen(new GameScreen(g));
				return true;
	    	}
	    });
	    menu.addActor(button);
	    button = new TextButton("Hard", skin);
	    button.addListener(new InputListener() {
	    	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
	    		GameStats.currentDifficulty = Difficulty.HARD;
	    		GameStats.lives = 3;
	    		GameStats.score = 0;
	    		g.setScreen(new GameScreen(g));
				return true;
	    	}
	    });
	    menu.addActor(button);
	    menu.setFillParent(true);
	    table.addActor(menu);
	    menu.setPosition((table.getWidth() - menu.getWidth()) * 0.5f, (table.getHeight() * 0.5f));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.5f, 1, 1);
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
		stage.dispose();
		shapeRenderer.dispose();
	}
}
