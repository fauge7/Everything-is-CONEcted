package com.CONE.cted.enemy;

import com.CONE.cted.Player.Player;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

public enum AngryConeState implements State<AngryCone>{
	
	SLEEP(){
		@Override
		public void update(AngryCone entity) {
			// TODO Auto-generated method stub
			entity.stateMachine.changeState(FOLLOW);
		}
		public boolean onMessage (AngryCone a ,Telegram telegram) {
			return false; // send message to global message handler
		}
			
		
	},
	FOLLOW(){
		@Override
		public void update(AngryCone entity) {
			// TODO Auto-generated method stub
		/*if(distancetoplayer > somedistance){
		 * 
		 * }
		 * else{
		 * entity.stateMachine.changeState(ATTACK);
		 * }
		 */
			if(entity.playerx > entity.posX){
				entity.right = true;
				entity.left = false;
			}
			else{
				entity.right = false;
				entity.left = true;
			}
			if(entity.canJump && entity.yvel < 0 && Math.abs(entity.posY - entity.playery) > 60){
				entity.yvel = 13;
				entity.canJump = false;
			}
			
			if(entity.posY < -100){
				entity.remove = true;
			}
		}
		public boolean onMessage (AngryCone a, Telegram telegram) {
			return false; // send message to global message handler
		}
	},
	ATTACK(){
		@Override
		public void update(AngryCone entity) {
			// TODO Auto-generated method stub
			Player.Health -=4;
			entity.remove = true;
		}
		public boolean onMessage(AngryCone a,Telegram telegram) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	

	@Override
	public void enter(AngryCone entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AngryCone entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit(AngryCone entity) {
		// TODO Auto-generated method stub
		
	}


	public boolean onMessage(Telegram telegram) {
		// TODO Auto-generated method stub
		return false;
	}
}
