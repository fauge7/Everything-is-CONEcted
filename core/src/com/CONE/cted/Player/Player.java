package com.CONE.cted.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player {
	
	public float posX;
	public float posY;
	SpriteBatch batch;
	Texture ts;
	Texture tr;
	Texture tl;
	Sprite spr;
	public float yvel;
	public boolean onground = true;
	public float groundy;
	public float jumptime;
	public float jumptimer;
	public float gun;
	public float guntimer;
	public static float MaxHealth = 100;
	public static float Health = 100;
	public static float healthlevel = 1;
	public static float Money = 0;
	public static float bulletdamage = 5f;
	public static float damagelevel = 1;
	public static float firerate = .15f;//.75f;
	public static float bulletlevel = 1;
	public static float speedMultiplier = 1;
	public static float speedlevel = 1;
	public static int killCount = 0;
	public Player(float x,float y,SpriteBatch batch){
		this.batch = batch;
		this.posX = x;
		this.posY = y;
		jumptime = 0;
		jumptimer = .7f;
	ts = new Texture("player_still.png");	
	tl = new Texture("player_left.png");
	tr = new Texture("player_right.png");
	spr = new Sprite(ts);
	yvel = 0;
	gun = Player.firerate;
	guntimer = 0.0f;
	}
	
	public void update(){
				
		if(!onground){
		posY+=yvel;
		yvel -=.60f;
		}
		else{
			posY = groundy;
			yvel = 0;
		}
		spr.setPosition(posX, posY);
		batch.begin();
		spr.draw(batch);
		batch.end();
	}
	public void move(float dx,float dy){
		posX += dx;
		posY += dy;
		if(dx > 0){
			spr.setTexture(tr);
		}
		else if(dx < 0){
			spr.setTexture(tl);
		}
		else{
			spr.setTexture(ts);
		}
	}
	public Rectangle getBounds(){
		return spr.getBoundingRectangle();
	}
	public void setjumptime(){
			jumptime+=Gdx.graphics.getDeltaTime();
	}
	public boolean getjumptime(){
		if(jumptime > jumptimer){
			jumptime = 0;
			return true;
		}
		else{
			return false;
		}
	}

}
