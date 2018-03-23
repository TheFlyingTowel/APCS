package com.Spoofy.local.objs.entitys;

import java.awt.Dimension;
import java.awt.Graphics2D;
import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Animation;
import com.Spoofy.local.Core.gfx.mapping.TileMap;
import com.Spoofy.local.objs.GameObject;

public abstract class Entity extends GameObject{

	
	public static final int STILL_ANI = -0x0;
	public static final int JUMP_ANI = 0x1FF;
	public static final int FALL_ANI = 0x2FF;
	public static final int MOVE_LEFT_ANI = 0x3FF;
	public static final int MOVE_RIGHT_ANI = 0x4FF;
	public static final int ATTACK_ANI = 0x5FF;
	public static final int STOP_LEFT_ANI = 0x6FF;
	public static final int STOP_RIGHT_ANI = 0x7FF;
	
	protected int currentAni = STILL_ANI;
	
	//
	protected int health;
	protected int power;
	
    //movement attributes
    protected double moveSpeed;
    protected double maxMoveSpeed;
    protected double stopSpeed;
    protected double jumpSpeed;
    protected double stopJumpSpeed;
    protected double maxJumpSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    
	public Entity(Handler handler, Animation ani, int x, int y, int width, int height, TileMap tm,Dimension collision) {
		super(handler, ani, x, y, width, height, tm);
		this.collision = collision;
	}

	
	private void getNextPosition() {
		
		// movement
		if(left) {
			faceingRight = false;
			direction.x -= moveSpeed;
			
			if(jumping) {
				currentAni = JUMP_ANI;
			}else
			if(falling) {
				currentAni = FALL_ANI;
			}else {
				setCurrentAni((MOVE_LEFT_ANI));
			}
			if(direction.x < -maxMoveSpeed) {
				direction.x = -maxMoveSpeed;
				
			}
		}
		else if(right) {
			faceingRight = true;
			direction.x += moveSpeed;
			if(jumping) {
				currentAni = JUMP_ANI;
			}else
			if(falling) {
				currentAni = FALL_ANI;
			}else {
				setCurrentAni((MOVE_RIGHT_ANI));
			}
			if(direction.x > maxMoveSpeed) {
				direction.x = maxMoveSpeed;

			}
		}
		else {
			if(direction.x > 0) {
				direction.x -= stopSpeed;
				
			
				
				if(jumping) {
					currentAni = JUMP_ANI;
				}else
				if(falling) {
					currentAni = FALL_ANI;
				}else {
					setCurrentAni((MOVE_LEFT_ANI));
				}
				if(direction.x < 0) {
					direction.x = 0;
				}
			}
			else if(direction.x < 0) {
				direction.x += stopSpeed;
				if(jumping) {
					currentAni = JUMP_ANI;
				}else
				if(falling) {
					currentAni = FALL_ANI;
				}else {
					setCurrentAni((MOVE_RIGHT_ANI));
				}
				if(direction.x > 0) {
					direction.x = 0;
				}
			}
		}

		// jumping
		if(jumping && !falling) {
				setCurrentAni((JUMP_ANI));
					direction.y = jumpSpeed;
					falling = true;
			
		}
		
		if(this.getClass() == Player.class) {
			if(!falling && currentAni == FALL_ANI) {
				animation.setIndex(6);
			}
		}
		
		
		// falling
		if(falling) {
			
			if(direction.y > 0 ) direction.y += fallSpeed * 0.1;
			direction.y += fallSpeed ;
			
			if(direction.y > 0){
				jumping = false;
				setCurrentAni((FALL_ANI));
			}
			if(direction.y < 0 && !jumping) direction.y += stopJumpSpeed;
			
			if(direction.y > maxFallSpeed) direction.y = maxFallSpeed;
			
		}
		
		if(direction.x == 0 && direction.y == 0 && !falling && !jumping && !right && !left){
			 setCurrentAni((STILL_ANI));
		}
		
		
	}

	public void tick(double delta) {
		super.tick(delta);
		getNextPosition();
		checkMapCollision();
		setPosition(xtemp,ytemp);
		
	}
		
	public void draw(Graphics2D g) {
		setMapPosition();
		if(faceingRight){
			g.drawImage(animation.getCurrentFrame().getImage(), (int) (position.x + mapPos.x - dimention.width / 2), (int) (position.y + mapPos.y - dimention.height / 2),null);
		}else{
			g.drawImage(animation.getCurrentFrame().getImage(), (int) (position.x + mapPos.x - dimention.width / 2  + dimention.width)  , (int) (position.y + mapPos.y - dimention.height / 2), -dimention.width, dimention.height,null);
		}
	}
	
	
	

	public int getCurrentAni() {
		return currentAni;
	}

	public void setCurrentAni(int currentAni) {
		this.currentAni = currentAni;
	}
	
	public boolean isFalling() {
		return falling;
	}
	
	public double getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public double getMaxMoveSpeed() {
		return maxMoveSpeed;
	}

	public void setMaxMoveSpeed(double maxMoveSpeed) {
		this.maxMoveSpeed = maxMoveSpeed;
	}

	public double getStopSpeed() {
		return stopSpeed;
	}


	public void setStopSpeed(double stopSpeed) {
		this.stopSpeed = stopSpeed;
	}


	public double getJumpSpeed() {
		return jumpSpeed;
	}


	public void setJumpSpeed(double jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}


	public double getStopJumpSpeed() {
		return stopJumpSpeed;
	}


	public void setStopJumpSpeed(double stopJumpSpeed) {
		this.stopJumpSpeed = stopJumpSpeed;
	}


	public double getMaxJumpSpeed() {
		return maxJumpSpeed;
	}


	public void setMaxJumpSpeed(double maxJumpSpeed) {
		this.maxJumpSpeed = maxJumpSpeed;
	}


	public double getFallSpeed() {
		return fallSpeed;
	}


	public void setFallSpeed(double fallSpeed) {
		this.fallSpeed = fallSpeed;
	}


	public double getMaxFallSpeed() {
		return maxFallSpeed;
	}


	public void setMaxFallSpeed(double maxFallSpeed) {
		this.maxFallSpeed = maxFallSpeed;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}
	
	
	
	
	
	
	
}
