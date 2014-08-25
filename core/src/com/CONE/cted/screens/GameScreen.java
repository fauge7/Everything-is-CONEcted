package com.CONE.cted.screens;

import com.CONE.cted.Player.Player;
import com.CONE.cted.map.Map;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen{
	
	SpriteBatch batch;
	SpriteBatch bgsb;
	Texture bg;
	Game conegame;
	Map m;
	OrthographicCamera cam;
	public Player player;
	public static final int WIDTH = Gdx.graphics.getWidth()/2;
	public static final int HEIGHT = Gdx.graphics.getHeight()/2;
	BitmapFont font;
	private int wave;
	
	public GameScreen(Game conegame, int wave){
		this.conegame = conegame;
		this.wave = wave;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		batch.setProjectionMatrix(cam.combined);
		handleInput();
		BasicDraw();
		m.draw(player);
		player.update();
		drawfont();
		
	}

	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		cam = new OrthographicCamera(WIDTH,HEIGHT);
		cam.position.set(WIDTH/2,HEIGHT/2,0);
		// TODO Auto-generated method stub
		bgsb = new SpriteBatch();
		batch = new SpriteBatch();
		bg = new Texture("backround.png");
		font = new BitmapFont();
		m = new Map(MathUtils.random(1, 1),batch);
		player = new Player(2048, 96, batch);
		player.Health = player.MaxHealth;
		
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
	private void BasicDraw(){
		// TODO Auto-generated method stub
				Gdx.gl.glClearColor(1, 0, 0, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				bgsb.begin();
				bgsb.draw(bg, 0, 0);
				bgsb.end();
				cam.position.x = 32 + player.posX;
				cam.zoom = 1.2f;
	}
	public void handleInput(){
		if(Gdx.input.isKeyPressed(Keys.A)){
			//if (cam.position.x > 4096 / 2){
				 cam.translate(-4 * Player.speedMultiplier, 0, 0);
				 player.move(-4 * Player.speedMultiplier, 0);
			//}
               
		}
		else if(Gdx.input.isKeyPressed(Keys.D)){
			// if (cam.position.x < 4096 - WIDTH / 2){
				 cam.translate(4 * Player.speedMultiplier, 0, 0);
				 player.move(4 * Player.speedMultiplier, 0);
			 //}
		}
		else{
			player.move(0, 0);
		}
		player.setjumptime();
		if(Gdx.input.isKeyJustPressed(Keys.W)){
			if(player.getjumptime()){
			player.onground = false;
			player.yvel = 13.5f;
			}
		}
		
		if(player.posY > HEIGHT/2){
			cam.position.y = player.posY;
		}
		player.onground = false;
		m.applyGravity();
	    cam.update();
	    if(player.guntimer > player.gun){
	    	if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
	    		player.guntimer = 0f;
	    		Vector3 touchpoint = cam.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
	    		m.fire(player.posX+16,player.posY + player.getBounds().height/2,touchpoint);
	    	}
	    }
	    else{
	    	System.out.println(player.guntimer);
	    	player.guntimer+=Gdx.graphics.getDeltaTime();
	    }
	    if(m.conesleft ==-1){
	    	conegame.setScreen(new ShopScreen(conegame,player,wave));
	    }
	    if(player.posY < -100){
	    	player.Health -=50;
	    	player.posX = 2048;
	    	player.posY = 96;
	    }
	}
	private void drawfont() {
		// TODO Auto-generated method stub
		Vector3 text = new Vector3(64,64,0);
		text = cam.unproject(text);
		font.setColor(Color.ORANGE);
		font.setScale(.8f);
		batch.begin();
		font.draw(batch, "HEALTH: " 	 + Player.Health,		text.x + 128 * 0, text.y);
		font.draw(batch, "Money" 		 + Player.Money, 		text.x + 128 * 1, text.y);
		font.draw(batch, "Cones Killed: "+ Player.killCount, 	text.x + 128 * 2, text.y);
		font.draw(batch, "Cones left: "  + m.conesleft,			text.x + 128 * 3, text.y);
		font.draw(batch, "Wave: " 		 + wave,				text.x + 128 * 4, text.y);
		batch.end();
	}

}
