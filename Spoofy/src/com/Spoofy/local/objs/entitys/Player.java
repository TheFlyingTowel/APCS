package com.Spoofy.local.objs.entitys;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Animation;
import com.Spoofy.local.Core.gfx.Sprite;
import com.Spoofy.local.Core.gfx.mapping.TileMap;


public class Player extends Entity{

	
	Sprite[] jumpSet;
	Sprite[] walkSet;
	Sprite[] stillSet;
	long d = 100;
	
	
	public Player(Handler handler,int x,int y,int width,int height, TileMap tm, Dimension collision) {
		super(handler, new Animation(null,5), x, y, width, height, tm, collision);

	}
	
	
	public void init() {	
		sprite = new Sprite("/Sprites/Player.png");

		stillSet = new Sprite[3];
		walkSet = new Sprite[6];
		jumpSet = new Sprite[7];
		
		int width = 52;
		int height  = 73;
		//Still animation
		for(int i = 0; i < stillSet.length; i++) {stillSet[i] = new Sprite(sprite.crop(width * i, 0, width, height));}
		
		//Walking animation
		for(int i = 0; i < walkSet.length; i++) {walkSet[i] = new Sprite(sprite.crop(width * i, height, width, height));}
		
		//Jumping Animation
		for(int i = 0; i < jumpSet.length; i++) {jumpSet[i] = new Sprite(sprite.crop(width * i, height * 2, width, height));}
		
		
		setAnimation(new Animation(stillSet,200));
		
		
		
		moveSpeed = 0.3;
		maxMoveSpeed = 2.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpSpeed = -5.8;
		stopJumpSpeed = 0.3;
	}
	
	public void tick(float delta){
		super.tick(delta);
		
	}
	
	
	public void draw(Graphics2D g) {
		super.draw(g);
		g.setColor(Color.ORANGE);
		switch (currentAni) {
			case JUMP_ANI:
				g.drawString("Current Animation: "+"JUMPING     ----->>"+ currentAni, 0, 32);
				//if(animation.hasPlayed())
				animation.setIndex(4);
				animation.setFrames(jumpSet);
				animation.setIndex(4);
				break;
				
			case FALL_ANI:
				g.drawString("Current Animation: "+"FALLING     ----->>"+ currentAni, 0, 32);
				//if(animation.hasPlayed())
					animation.setIndex(5);
					animation.setFrames(jumpSet);
					animation.setIndex(5);
				
				break;
					
			case MOVE_LEFT_ANI:
				g.drawString("Current Animation: "+"MOVE_LEFT     ----->>"+ currentAni, 0, 32);
				//if(!jumping && !falling && animation.hasPlayed()) {
					//animation.setIndex(0);
				if(animation.hasPlayed())
					animation.setFrames(walkSet);
					animation.setDelay(10);
				
				break;
				
			case MOVE_RIGHT_ANI:
				g.drawString("Current Animation: "+"MOVE_RIGHT     ----->>"+ currentAni, 0, 32);
				//if(!jumping && !falling && animation.hasPlayed()) {
					//animation.setIndex(0);
				if(animation.hasPlayed())
					animation.setFrames(walkSet);
					animation.setDelay(10);
				
				break;
					
					
			case STILL_ANI:
				g.drawString("Current Animation: "+"IDLE     ----->>"+ currentAni, 0, 32);
				if(!jumping && !falling)
					animation.setFrames(stillSet);
				break;
					
			default:
				break;
				
			}
				
				//Collision box
				//g.setColor(Color.RED);
				//g.fillRect((int)((position.x + mapPos.x - collision.width / 2)), (int)((position.y + mapPos.y - collision.height / 2)), collision.width, collision.height);
				
	}



	

}
