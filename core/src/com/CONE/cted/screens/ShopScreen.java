package com.CONE.cted.screens;



import com.CONE.cted.Player.Player;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ShopScreen  implements Screen{
	
	
	SpriteBatch batch;
	BitmapFont font;
	Rectangle item1;
	Rectangle item2;
	Rectangle item3;
	Rectangle item4;
	Rectangle back;
	Texture t;
	Sprite screen;
	Texture comfirmtex;
	Sprite yesno;
	Vector3 touch;
	Vector2 touchpoint;
	Camera cam;
	boolean isconfirming;
	Player p;
	Game gamecone;
	int wave;
	ShapeRenderer rend;
	
	public ShopScreen(Game game,Player player,int wave){
		this.p = player;
		this.gamecone = game;
		this.wave = wave;
		rend = new ShapeRenderer();

	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,0);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		screen.draw(batch);
		batch.end();
		touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		touch = cam.unproject(touch);
		touchpoint.set(touch.x, touch.y);
		if(Gdx.input.justTouched()){
			
			if(item1.contains(touchpoint)){
				
					if(Player.Money >= Player.bulletlevel * 100){
					Player.bulletlevel++;
					Player.firerate -=.10;
					Player.Money -= Player.bulletlevel * 100;
					System.out.println("bulletlevel");
					}
					
				
			}
			else if(item2.contains(touchpoint.x,touchpoint.y)){
				
					if(Player.Money >= Player.damagelevel * 100){
					Player.damagelevel ++;
					Player.Money -= Player.damagelevel * 100;
					System.out.println("damagelevel");
					}
				
			}
			else if(item3.contains(touchpoint.x,touchpoint.y)){
				
					if(Player.Money >= Player.healthlevel * 100){
					Player.MaxHealth += 10;
					Player.Money -= Player.healthlevel * 100;
					System.out.println("healthlevel");
					}
				
			}
			else if(item4.contains(touchpoint.x,touchpoint.y)){
				
					if(Player.Money >= Player.speedlevel * 100){
					Player.speedMultiplier += .125;
					Player.Money -= Player.speedlevel * 100;
					System.out.println("speedlevel");
					}
				
			}
			else if(back.contains(touchpoint)){
					if(Gdx.input.justTouched()){
					gamecone.setScreen(new GameScreen(gamecone,wave+1));
					}
		}
		}
		 
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		t = new Texture("ShopScreen.png");
		comfirmtex = new Texture("Confirm.png");
		screen = new Sprite(t);
		cam = new OrthographicCamera(Gdx.graphics.getWidth()/1,Gdx.graphics.getHeight()/1);
		cam.position.set(1024/2, 728/2, 0);
		
		cam.update();
		Vector3 tempa = new Vector3(0,0, 0);
		tempa = cam.project(tempa);
		//screen.setPosition(tempa.x,tempa.y);
		
		screen.setX( Gdx.graphics.getWidth()/2-t.getWidth()/2);
		screen.setY(Gdx.graphics.getHeight()/2-t.getHeight()/2);
		
		item1 = new Rectangle((int) (45 + screen.getX()),(int) (203 + screen.getY()),120,51);
		item2 = new Rectangle((int) (217 + screen.getX()),(int) (203 + screen.getY()),120,51);
		item3 = new Rectangle((int) (393 + screen.getX()),(int) (203 + screen.getY()),120,51);
		item4 = new Rectangle((int) (555 + screen.getX()),(int) (203 + screen.getY()),120,51);
		back = new Rectangle((int) (574 + screen.getX()),(int) (28 + screen.getY()),120,51);
		
		
		batch = new SpriteBatch();
		touch = new Vector3(0,0,0);
		touchpoint = new Vector2(0,0);
		//Player.Health = Player.MaxHealth;

		
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
	public boolean confirm(){
		batch.begin();
		batch.draw(comfirmtex,
				Gdx.graphics.getWidth()/2 -comfirmtex.getWidth()/2,
				Gdx.graphics.getHeight()/2-comfirmtex.getHeight()/2);
		batch.end();
		
		int confirmx = Gdx.graphics.getWidth()/2 -comfirmtex.getWidth()/2;
		int confirmy = Gdx.graphics.getHeight()/2-comfirmtex.getHeight()/2;
		Rectangle yes = new Rectangle(confirmx+51,confirmy+223,122,51);
		Rectangle no  = new Rectangle(confirmx+307,confirmy+223,122,51);
		touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		touch = cam.unproject(touch);
		do{
			touchpoint.set(touch.x, touch.y);
			if(yes.contains(touchpoint.x,touchpoint.y)){
				isconfirming = false;
				return true;
			}
			else if(no.contains(touchpoint.x,touchpoint.y)){
				isconfirming = false;
				return false;
			}
			else{
				return false;
			}
		} while(!Gdx.input.isTouched());
	}

}
