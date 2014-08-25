package com.CONE.cted.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bullet {
	
	public float posX;
	public float posY;
	Texture t;
	Sprite s;
	SpriteBatch batch;
	float dx;
	float dy;
	float BulletSpeed = 800;
	float angle;
	public float damage;
	public boolean shouldremove = false;
	float distancetime;
	float timer;
	public Bullet(float x,float y,Vector3 gotovec,SpriteBatch b,float damage){
		this.posX = x;
		this.posY = y;
		this.batch = b;
		this.damage = damage;
		t = new Texture("bullet.png");
		s = new Sprite(t);
		timer = 2f;
		distancetime = 0f;
		angle = MathUtils.atan2(gotovec.y - this.posY, gotovec.x - this.posX);
		s.setRotation(MathUtils.radDeg * angle - 90);
		dx = MathUtils.cosDeg(angle * 180 / MathUtils.PI) * BulletSpeed * Gdx.graphics.getDeltaTime();
		dy = MathUtils.sinDeg(angle * 180 / MathUtils.PI) * BulletSpeed * Gdx.graphics.getDeltaTime();
		
	}
	public void update(){
		if(distancetime > timer){
			shouldremove = true;
		}
		else{
			distancetime+=Gdx.graphics.getDeltaTime();
		}
		posX += dx;
		posY += dy;
		s.setPosition(posX, posY);
		batch.begin();
		s.draw(batch);
		batch.end();
	}
	public Rectangle getBounds(){
		return s.getBoundingRectangle();
	}
	public boolean getshouldremove(){
		if(shouldremove = distancetime > timer){
			shouldremove = true;
			return shouldremove;
		}
			return shouldremove;
	}
	public void shouldremove(boolean b){
		shouldremove = b;
	}

}
