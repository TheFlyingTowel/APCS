package com.Spoofy.local.objs.entitys;

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
	
	
	public Player(Handler handler,int x,int y,int width,int height, TileMap tm, Dimension collision) {
		super(handler, new Animation(null, 0.5f), x, y, width, height, tm, collision);

	}
	
	
	public void init() {	
		sprite = new Sprite("/Sprites/xeonsheet.png");

		stillSet = new Sprite[3];
		walkSet = new Sprite[4];
		jumpSet = new Sprite[5];
		
		
		//Still animation
		stillSet[0] = new Sprite(sprite.crop(0, 0, dimention.width, dimention.height));
		stillSet[1] = new Sprite(sprite.crop(dimention.width, 0, dimention.width, dimention.height));
		stillSet[2] = new Sprite(sprite.crop(dimention.width * 2, 0, dimention.width, dimention.height));
		
		//Walking animation
		walkSet[0] = new Sprite(sprite.crop(0, dimention.height, dimention.width, dimention.height));
		walkSet[1] = new Sprite(sprite.crop(dimention.width, dimention.height, dimention.width, dimention.height));
		walkSet[2] = new Sprite(sprite.crop(dimention.width * 2, dimention.height, dimention.width, dimention.height));
		walkSet[3] = new Sprite(sprite.crop(dimention.width * 3, dimention.height, dimention.width, dimention.height));
		
		//Jump animation
		jumpSet[0] = new Sprite(sprite.crop(0, dimention.height * 2, dimention.width, dimention.height));
		jumpSet[1] = new Sprite(sprite.crop(dimention.width, dimention.height * 2, dimention.width, dimention.height));
		jumpSet[2] = new Sprite(sprite.crop(dimention.width * 2, dimention.height * 2, dimention.width, dimention.height));
		jumpSet[3] = new Sprite(sprite.crop(dimention.width * 3, dimention.height * 2, dimention.width, dimention.height));
		jumpSet[4] = new Sprite(sprite.crop(dimention.width * 4, dimention.height * 2, dimention.width, dimention.height));
		
		
		setAnimation(new Animation(stillSet, 0.5f));
		
		moveSpeed = 0.3;
		maxMoveSpeed = 5.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpSpeed = -5.8;
		stopJumpSpeed = 0.3;
	}
	
	
	public void draw(Graphics2D g) {
		//g.setColor(Color.darkGray);
		super.draw(g);
				switch (currentAni) {
					case JUMP_ANI:
					//	g.drawString("Current Animation: "+"JUMPING     ----->>"+ currentAni, 0, 100);
						setAnimation(new Animation(jumpSet, 0.5f));
						break;
				
					case FALL_ANI:
					//	g.drawString("Current Animation: "+"FALLING     ----->>"+ currentAni, 0, 100);
						break;
					
					case MOVE_LEFT_ANI:
					//	g.drawString("Current Animation: "+"MOVE_LEFT     ----->>"+ currentAni, 0, 100);
						setAnimation(new Animation(walkSet, 0.5f));
						break;
				
					case MOVE_RIGHT_ANI:
					//	g.drawString("Current Animation: "+"MOVE_RIGHT     ----->>"+ currentAni, 0, 100);
						setAnimation(new Animation(walkSet, 0.5f));
						break;
					
					case STOP_LEFT_ANI:
					//	g.drawString("Current Animation: "+"STOP_LEFT     ----->>"+ currentAni, 0, 100);
						break;
				
					case STOP_RIGHT_ANI:
					//	g.drawString("Current Animation: "+"STOP_RIGHT     ----->>"+ currentAni, 0, 100);
						break;
					
					case STILL_ANI:
					//	g.drawString("Current Animation: "+"IDLE     ----->>"+ currentAni, 0, 100);
						setAnimation(new Animation(stillSet, 0.5f));
						break;
					
					default:
						break;
				}
				
				
				
				
	}
	

}
