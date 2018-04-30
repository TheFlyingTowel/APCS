package com.Spoofy.local.objs.entitys;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Animation;
import com.Spoofy.local.Core.gfx.Sprite;
import com.Spoofy.local.Core.gfx.mapping.TileMap;
import com.Spoofy.local.Utils.Vector2F;


public class Player extends Creature{

	
	Sprite[] jumpSet;
	Sprite[] walkSet;
	Sprite[] stillSet;
	Sprite[] lastAnimation;
	long d = 100;
	private Vector2F lastPoint;
	
	public static boolean isMoving = false;
	public String Name = "";
	
	
	public Player(Handler handler,int x,int y,int width,int height, TileMap tm, Dimension collision) {
		super(handler, new Animation(null,5), x, y, width, height, tm, collision);
		setCollisionPosition(0, 16);
		
	}
	
	
	public void init() {	
		sprite = new Sprite("Player.png");

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
		maxMoveSpeed = 9.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 5.0;
		jumpSpeed = -5.8;
		stopJumpSpeed = 0.3;
		lastPoint = new Vector2F(position.x,position.y);
		health = 10;
		maxHealth = 10;
	}
	
	public void tick(double delta){
		super.tick(delta);
		isMoving = up || left || right || falling || jumping; 
		if(!isAlive) {
			position.setVector(lastPoint.x, lastPoint.y);
			isAlive = true;
			health = 5;
		}
	}
	
	
	public void draw(Graphics2D g) {
		super.draw(g);
		g.setColor(new Color(211, 255, 124));
		switch (currentAni) {
			case JUMP_ANI:
				g.drawString("Current Animation: "+"JUMPING     ----->>"+ currentAni, 8, 32);
				//if(animation.hasPlayed())
				animation.setIndex(4);
				animation.setFrames(jumpSet);
				animation.setIndex(4);
				lastAnimation = jumpSet;
				break;
				
			case FALL_ANI:
				g.drawString("Current Animation: "+"FALLING     ----->>"+ currentAni, 8, 32);
				//if(animation.hasPlayed())
					animation.setIndex(5);
					animation.setFrames(jumpSet);
					animation.setIndex(5);
					lastAnimation = jumpSet;
				
				break;
					
			case MOVE_LEFT_ANI:
				if(jumping)currentAni = JUMP_ANI;
				if(falling)currentAni = FALL_ANI;
				g.drawString("Current Animation: "+"MOVE_LEFT     ----->>"+ currentAni, 8, 32);
				if(!jumping && !falling && animation.hasPlayed()) {
					//animation.setIndex(0);				
					animation.setFrames(walkSet);
					animation.setDelay(100);
					lastAnimation = walkSet;
				}
				break;
				
			case MOVE_RIGHT_ANI:
				if(jumping)currentAni = JUMP_ANI;
				if(falling)currentAni = FALL_ANI;
				g.drawString("Current Animation: "+"MOVE_RIGHT     ----->>"+ currentAni, 8, 32);
				if(!jumping && !falling && animation.hasPlayed()) {
					//animation.setIndex(0);
					animation.setFrames(walkSet);
					animation.setDelay(100);
					lastAnimation = walkSet;
				}
				break;
					
					
			case STILL_ANI:
				g.drawString("Current Animation: "+"IDLE     ----->>"+ currentAni, 8, 32);
				if(!jumping && !falling)
					if(lastAnimation == stillSet) {
						if(animation.hasPlayed()) {
							animation.setFrames(stillSet);
							lastAnimation = stillSet;
						}
					}else {
						animation.setFrames(stillSet);
						lastAnimation = stillSet;
					}
				break;
					
			default:
				break;
				
			}
				
				//Collision box
				//g.setColor(Color.RED);
				//g.fillRect((int)(((position.x) + mapPos.x - collision.width / 2)), (int)(((position.y) + mapPos.y - collision.height / 2)), collision.width, collision.height);

				
		
				
				
				
				g.setColor(Color.RED);
				g.fillRect(4, 64, 64, 8);
				
				g.setColor(Color.GREEN);
				g.fillRect(4, 64,(int) (64 * (health / maxHealth)), 8);
				
				
				g.setColor(new Color(21, 255, 0));

	}


	public Vector2F getLastPoint() {
		return lastPoint;
	}


	public void setLastPoint(Vector2F lastPoint) {
		this.lastPoint = lastPoint;
	}



	public String getSave() {
		String dat = "[PLAYER]\n"
					+ "<X:{%s}>\n"
					+ "<Y:{%s}>\n"
					+ "<Health:{%s}>\n"
					+ "<Name:{%s}>\n"
					+ "<PT:{%s}>\n"
					+ "[<PLAYER>]\n";
		String buffer = String.format(dat, position.x,position.y,health,Name,handler.getClock().getEndTimeMinutes());
		return buffer;
	}
	

}
