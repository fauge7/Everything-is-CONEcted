package com.CONE.cted.main;

import com.CONE.cted.Player.Player;
import com.CONE.cted.screens.GameScreen;
import com.CONE.cted.screens.InstructionScreen;
import com.CONE.cted.screens.ShopScreen;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainCone extends Game implements ApplicationListener {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//this.setScreen(new ShopScreen(this,new Player(5, 5, batch),1));
		this.setScreen(new InstructionScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
