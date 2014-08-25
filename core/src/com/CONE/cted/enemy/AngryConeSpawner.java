package com.CONE.cted.enemy;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class AngryConeSpawner {
	Texture t;
	Sprite spr;
	float spawntimer;
	float spawntime;
	float x;
	float y;
	private SpriteBatch batch;
	int wave;
	public int spawned;
	public int max_spawn;
	public boolean finished = false;
	
	public AngryConeSpawner(int wave,float x,float y,SpriteBatch batch){
		t = new Texture("spawner.png");
		spr = new Sprite(t);
		this.wave = wave;
		max_spawn = wave + MathUtils.random(-3,3);
		if(max_spawn <= 0){
			finished = true;
		}
		this.x = x;
		this.y = y;
		this.batch = batch;
		spawntimer = 1;
		spawntime = 0;
		spr.setPosition(x, y);
	}
	public void spawnCone(ArrayList<AngryCone> conelist){
		spawned++;
		conelist.add(new AngryCone(x,y,batch));
	}
	public void update(ArrayList<AngryCone> conelist){
		spr.rotate(2);
		batch.begin();
		spr.draw(batch);
		batch.end();
		if(spawned >= max_spawn){
			finished = true;
		}
		else{
			if(spawntime > spawntimer){
				spawnCone(conelist);
				spawntime = 0;
			}
			else{
				spawntime+=Gdx.graphics.getDeltaTime();
			}
		}
	}
	

}
