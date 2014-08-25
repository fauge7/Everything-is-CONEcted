package com.CONE.cted.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InstructionScreen  implements Screen{

	BitmapFont font;
	SpriteBatch batch;
	Game game;
	public InstructionScreen(Game game){
		this.game = game;
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		batch.begin();
		font.setScale(5f);
		font.setColor(Color.ORANGE);
		font.draw(batch, "Everything is CONEcted", Gdx.graphics.getWidth()/2-font.getBounds("Everything is CONEcted").width/2, Gdx.graphics.getHeight()/4*3.5f);
		font.setColor(Color.GREEN);
		font.setScale(2f);
		font.draw(batch, "Save the kingdom of Newman!", Gdx.graphics.getWidth()/2-font.getBounds("Save the kingdom of Newman!").width/2, Gdx.graphics.getHeight()/4*2.5f);
		font.draw(batch, "From the Alien cones!", Gdx.graphics.getWidth()/2-font.getBounds("From the Alien cones!").width/2, Gdx.graphics.getHeight()/4*2.25f);
		font.draw(batch, "after defending you get Princess Alea's love and effection!", Gdx.graphics.getWidth()/2-font.getBounds("after defending you get Princess Alea's love and effection!").width/2, Gdx.graphics.getHeight()/4*2f);
		font.draw(batch, "Mouse to Aim and Space to shoot", Gdx.graphics.getWidth()/2-font.getBounds("Mouse to Aim,Space to move").width/2, Gdx.graphics.getHeight()/4*1.25f);
		font.draw(batch, "A (left) D (right) W(jump)", Gdx.graphics.getWidth()/2-font.getBounds("A (left) D (right) W(jump)").width/2, Gdx.graphics.getHeight()/4*1f);
		font.draw(batch, "Click to continue", Gdx.graphics.getWidth()/2-font.getBounds("Click to continue").width/2, Gdx.graphics.getHeight()/4*.75f);
		batch.end();
		if(Gdx.input.justTouched()){
			game.setScreen(new GameScreen(game,1));
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		font = new BitmapFont();
		batch = new SpriteBatch();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
