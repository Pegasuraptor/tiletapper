package com.mygdx.game.tiletapper.tiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Tile {
	private Texture square;
	private Color colour;
	private float xPos;
	private float yPos;
	private float width;
	private float height;
	
	public Tile(Texture img, float x, float y, float w, float h) {
		setSquare(img);
		setxPos(x);
		setyPos(y);
		setWidth(w);
		setHeight(h);
		colour = new Color();
	}
	
	public boolean isTouched(float x, float y)
	{
		if(x < (xPos + width) && x > xPos && y > yPos && y < (yPos + height))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void setSquare(Texture img) {
		square = img;	
	}

	public Texture getSquare() {
		return square;
	}

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}
}
