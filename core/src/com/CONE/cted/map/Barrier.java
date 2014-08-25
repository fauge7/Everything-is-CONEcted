package com.CONE.cted.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Barrier {
	
	float posX;
	float posY;
	Texture t;
	Sprite spr;
	boolean evil;
	SpriteBatch batch;
	float distancetoplayer;
	float distancetoplayerx;
	float distancetoplayery;
	
	public Barrier(float x,float y,boolean evil,SpriteBatch batch){
		this.posX = x;
		this.posY = y;
		this.evil = evil;
		this.batch = batch;
		if(evil){
		t = new Texture("platform_2.png");
		}
		else{
		t = new Texture("platform_1.png");
		}
		spr = new Sprite(t);
		distancetoplayer = 0;
		distancetoplayerx = 0;
		distancetoplayery = 0;
		
	}
	public void draw(){
		spr.setPosition(posX, posY);
		batch.begin();
		spr.draw(batch);
		batch.end();
	}
	public void toggleEvil(){
		evil = !evil;
		if(evil){
			t = new Texture("platform_2.png");
		}
		else{
			t = new Texture("platform_1.png");
		}
		spr.setTexture(t);
	}
	public Rectangle getBounds(){
		return spr.getBoundingRectangle();
	}
	

}
