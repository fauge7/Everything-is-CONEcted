package com.CONE.cted.map;

import java.util.ArrayList;
import java.util.Iterator;

import com.CONE.cted.Player.Bullet;
import com.CONE.cted.Player.Player;
import com.CONE.cted.enemy.AngryCone;
import com.CONE.cted.enemy.AngryConeSpawner;
import com.CONE.cted.enemy.AngryConeState;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;

public class Map {
	
	ArrayList<Barrier> barrierlist;
	ArrayList<AngryCone> conelist;
	ArrayList<AngryConeSpawner> conespawner;
	ArrayList<Bullet> player_bullet;
	SpriteBatch batch;
	public static final int MAPWIDTH = 40;
	float closestdistace = 100000;
	int closest = 0;
	public boolean nextlevel = false;
	public int conesleft;
	
	public Map(int seed,SpriteBatch batch){
		this.batch = batch;
		barrierlist = new ArrayList<Barrier>();
		conelist = new ArrayList<AngryCone>();
		conespawner = new ArrayList<AngryConeSpawner>();
		player_bullet = new ArrayList<Bullet>();
		if(seed == 1){
		for(int i = 0; i < 32; i++){
			barrierlist.add(new Barrier(i * 128,64,false,batch));
		}
		
		/*
		 * the level height refers to the number of jumps it takes to get to that y value
		 */
		
		//level one
		barrierlist.add(new Barrier((MAPWIDTH)*128/2-128*4,180,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2-128*3,180,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2-128*2,180,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2-128*1,180,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2,180+32,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2+128*1,180,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2+128*2,180,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2+128*3,180,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2+128*4,180,true,batch));
		//level two
		barrierlist.add(new Barrier((MAPWIDTH)*128/2-128*6,180*2,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2-128*5,180*2,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2+128*5,180*2,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2+128*6,180*2,true,batch));
		//level three
		barrierlist.add(new Barrier((MAPWIDTH)*128/2-128*8,180*3,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2-128*7,180*3,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2+128*7,180*3,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2+128*8,180*3,true,batch));
		//level four
		barrierlist.add(new Barrier((MAPWIDTH)*128/2-128*9,180*4,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2-128*10,180*4,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2+128*11,180*4,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2+128*12,180*4,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2-128*12,180*4,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2-128*11,180*4,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2+128*10,180*4,true,batch));
		barrierlist.add(new Barrier((MAPWIDTH)*128/2+128*9,180*4,true,batch));
		//cone spawners
		conespawner.add(new AngryConeSpawner(1,16,96,batch));
		conespawner.add(new AngryConeSpawner(1,MAPWIDTH*128-1,96,batch));
		conespawner.add(new AngryConeSpawner(1,MAPWIDTH*128/2-128*12,180*4+1+32,batch));
		conespawner.add(new AngryConeSpawner(1,MAPWIDTH*128/2+128*12,180*4+1+32,batch));
		}
		else if(seed == 1){
			//adding the base layer without 2
			for(int i = 0; i < 32; i++){
				if(i == 19 || i == 20){
				}
				else{
				barrierlist.add(new Barrier(i * 128,64,false,batch));
				}
			}
		//level one
		barrierlist.add(new Barrier((float) ((MAPWIDTH/2-.5)*128),180,true,batch));
		//level two
		barrierlist.add(new Barrier((float) ((MAPWIDTH/2+1.1)*128),180*1.75f,true,batch));	
		//level three
		barrierlist.add(new Barrier((float) ((MAPWIDTH/2+2.25)*128),180*2.5f,true,batch));	
		//level four
		barrierlist.add(new Barrier((float) ((MAPWIDTH/2-.15)*128),180*3.25f,true,batch));	
		//level five
		barrierlist.add(new Barrier((float) ((MAPWIDTH/2-1.75)*128),180*4f,true,batch));	
		barrierlist.add(new Barrier((float) ((MAPWIDTH/2-2.75)*128),180*4f,true,batch));	
		//level six
		barrierlist.add(new Barrier((float) ((MAPWIDTH/2-4.75)*128),180*4.75f,true,batch));	
		barrierlist.add(new Barrier((float) ((MAPWIDTH/2-5.75)*128),180*4.75f,true,batch));	
		//level six spawner
		conespawner.add(new AngryConeSpawner(1,((MAPWIDTH/2-5.85f)*128),180*4.75f+1+32,batch));
		conespawner.add(new AngryConeSpawner(1,16,96,batch));
		conespawner.add(new AngryConeSpawner(1,MAPWIDTH*128-1,96,batch));
		}
		else{
		}	
	}
	
	public void draw(Player p){
		for(Barrier b : barrierlist){
			for(AngryCone c : conelist){
				if(b.getBounds().overlaps(c.getBounds()) && b.posY - c.posY < 0){
					if(c.yvel > 0){
						c.onground = false;
					}
					else if(c.yvel < 0){
						c.onground = true;
						c.groundy = b.posY + 31;	
						c.canJump = true;
					}
					else{
						if(b.getBounds().contains(c.posX, c.posY - 1)){
							c.onground = false;
						}
						else{
							c.onground = true;
						}
						if(c.onground){
						c.groundy = b.posY + 31;	
						}
					}
				}
			}
			if(b.getBounds().overlaps(p.getBounds()) && b.posY - p.posY < 0){
				if(p.yvel > 0){
					p.onground = false;
				}
				else if(p.yvel < 0){
					p.onground = true;
					p.groundy = b.posY + 31;	
				}
				else{
					if(b.getBounds().contains(p.posX, p.posY - 1)){
						p.onground = false;
					}
					else{
						p.onground = true;
					}
					if(p.onground){
					p.groundy = b.posY + 31;	
					}
				}
			}
		}
		for(Bullet bullet : player_bullet){
			for(AngryCone cone : conelist){
				if(bullet.getBounds().overlaps(cone.getBounds())){
					cone.health -= bullet.damage;
					bullet.shouldremove(true);
				}
			}
		}
		for (Iterator<Bullet> iterator = player_bullet.iterator(); iterator.hasNext();) {
			Bullet bullet = (Bullet) iterator.next();
			if(bullet.shouldremove){
				System.out.println(bullet.shouldremove);
				iterator.remove();
			}
		}
		for(Iterator<AngryCone> iter = conelist.iterator(); iter.hasNext();){
			AngryCone cone = (AngryCone) iter.next();
			if(cone.health <= 0){
				Player.Money += 10;
				Player.killCount++;
				iter.remove();
			}
			else if(cone.posY < -100){
				iter.remove();
			}
			else if(cone.remove){
				iter.remove();
			}
		}
		for(Iterator<AngryConeSpawner> iter = conespawner.iterator(); iter.hasNext();){
			AngryConeSpawner spawner = (AngryConeSpawner) iter.next();
			if(spawner.finished){
				iter.remove();
			}
		}
		for(Barrier b : barrierlist){
			b.draw();
		}
		for(AngryCone cone : conelist){
			if(cone.getBounds().overlaps(p.getBounds())){
				cone.stateMachine.changeState(AngryConeState.ATTACK);
			}
			cone.update(p.posX,p.posY);
		}
		
		conesleft = 0;
		for(AngryConeSpawner s : conespawner){
			conesleft +=s.max_spawn - s.spawned;
			s.update(conelist);
		}
		if(conesleft == 0 && conelist.isEmpty()){
			conesleft = -1;
		}
		for(Bullet bul : player_bullet){
			bul.update();
		}
		if(conelist.isEmpty() && conelist.isEmpty()){
			nextlevel = true;
		}
	}

	public void applyGravity() {
		// TODO Auto-generated method stub
		for(AngryCone cone : conelist){
			cone.onground = false;
		}
	}

	public void fire(float x,float y,Vector3 vec) {
		// TODO Auto-generated method stub
		player_bullet.add(new Bullet(x,y,vec,batch,Player.bulletdamage));
	}
}
