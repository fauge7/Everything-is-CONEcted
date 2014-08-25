package com.CONE.cted.enemy;

import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class AngryCone {
	
	public StateMachine<AngryCone> stateMachine;
	Texture ts;
	Texture tl;
	Texture tr;
	Sprite spr;
	public float posX;
	public float posY;
	float playerx;
	float playery;
	public float yvel;
	public float groundy;
	public boolean onground = false;
	boolean right = false;
	boolean left = false;
	SpriteBatch batch;
	public float health = 10;
	public boolean canJump = true;
	public boolean remove = false;
	
	public AngryCone(float x,float y,SpriteBatch batch){
	
	stateMachine = new DefaultStateMachine<AngryCone>(this, AngryConeState.SLEEP);
	this.batch = batch;
	this.posX = x;
	this.posY = y;
	ts = new Texture("angry cone_still.png");
	tl = new Texture("angry cone_left.png");
	tr = new Texture("angry cone_right.png");
	spr = new Sprite(ts);
	yvel = 0;
	float randomscale = MathUtils.random(.9f, 1.1f);
	spr.setScale(randomscale);
	
	
	}
	
	public void update(float playerx,float playery){
		if(!onground){
			posY+=yvel;
			yvel -=.60f;
			}
			else{
				posY = groundy;
				yvel = 0;
			}
		this.playerx = playerx;
		this.playery = playery;
		stateMachine.update();
		if(left && !right){
			spr.setTexture(tl);
			posX -=2;
		}
		else if(!left && right){
			spr.setTexture(tr);
			posX +=2;
		}
		else{
			spr.setTexture(ts);
		}
		
		spr.setPosition(posX, posY);
		batch.begin();
		spr.draw(batch);
		batch.end();
	}
	public Rectangle getBounds(){
		return spr.getBoundingRectangle();
	}

}
