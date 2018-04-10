package com.Spoofy.local.objs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Core.gfx.Animation;
import com.Spoofy.local.Core.gfx.Sprite;
import com.Spoofy.local.Core.gfx.mapping.Tile;
import com.Spoofy.local.Core.gfx.mapping.TileMap;
import com.Spoofy.local.Utils.Debugger;
import com.Spoofy.local.Utils.Vector2F;

public abstract class GameObject {

	protected Vector2F position, direction, mapPos;
	protected int cx = 0,cy = 0;
	protected Dimension dimention, collision;
	protected Sprite sprite;
	protected Animation animation;
	protected boolean solid = false;
	protected Handler handler;
	protected TileMap tm;
	protected boolean faceingRight;
	protected int tileSize;
	protected Debugger debug;
    //movement
    protected boolean up,down,left,right,jumping,falling;
	
    //Collision
    protected int currRow,currCol;
    protected double xdest,ydest,xtemp,ytemp;
    protected boolean topLeft,topRight,bottomLeft,bottomRight;
    
	public GameObject(Handler handler, TileMap tm) {
		this.handler = handler;
		position = new Vector2F();
		dimention = new Dimension();
		sprite = null;
		this.tm = tm;
		tileSize = tm.getTileSize();
		direction = new Vector2F();
		mapPos = new Vector2F();
		
	}
	
	
	
	public GameObject (Handler handler, Sprite spr, int x, int y, int width, int height,TileMap tm) {
		this.handler = handler;
		sprite = spr;
		position = new Vector2F(x, y);
		dimention = new Dimension(width, height);
		this.tm = tm;
		tileSize = tm.getTileSize();
		direction = new Vector2F();
		mapPos = new Vector2F();
	}
	
	public GameObject (Handler handler, Animation animation, int x, int y, int width, int height,TileMap tm) {
		this.handler = handler;
		this.animation = animation;
		position = new Vector2F(x, y);
		dimention = new Dimension(width, height);
		this.tm = tm;
		tileSize = (tm != null) ? tm.getTileSize() : 0;
		direction = new Vector2F();
		mapPos = new Vector2F();
		
	}
	
	public void tick(double delta) {
		if(animation != null)animation.tick();
		checkMapCollision();
		setMapPosition();
		setPosition(xtemp, ytemp);
	}
	
	public void draw(Graphics2D g) {
		if(sprite != null) {
			g.drawImage(sprite.getImage(),(int)(position.x + mapPos.x),(int) (position.y + mapPos.y ),(int) collision.width,(int) collision.height,null);
		}else {
			g.setColor(Color.GREEN);
			g.fillRect(((int)((position.x + mapPos.x - collision.width / 2))), (int)((position.y + mapPos.y - collision.height / 2)), collision.width, collision.height);
		}
	}
	
	public void setMapPosition() {
		mapPos.copy(tm.getPosition());
	}
	
	public void setDirection(double x, double y) {
		direction = new Vector2F(x, y);
	}
	
	public boolean collisionWithOther(GameObject obj) {
		return this.getBounds().intersects(obj.getBounds());
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) ((position.x)),(int) ((position.y)), collision.width, collision.height);
	}
	
	
	public void calculateCorners(double x, double y){
	     int leftTile = (int)(x - collision.width / 2) / tileSize;
	     int rightTile = (int)(x + collision.width / 2 - 1) / tileSize;
	     int topTile = (int)(y - collision.height / 2) / tileSize;
	     int bottomTile = (int)(y + collision.height / 2 - 1) / tileSize;
	     if(topTile < 0 || bottomTile >= tm.getRows() || leftTile < 0 || rightTile >= tm.getCols()) {
	              topLeft = topRight = bottomLeft = bottomRight = false;
	              return;
	     }
	     int tl = tm.getType(topTile, leftTile);
	     int tr = tm.getType(topTile, rightTile);
	     int bl = tm.getType(bottomTile, leftTile);
	     int br = tm.getType(bottomTile, rightTile);
	     topLeft = tl == Tile.BLOCKED;
	     topRight = tr == Tile.BLOCKED;
	     bottomLeft = bl == Tile.BLOCKED;
	     bottomRight = br == Tile.BLOCKED;
	}
	   
	public void checkMapCollision(){
	     currCol = (int) position.x / tileSize;
	     currRow = (int) position.y / tileSize;
	       
	     xdest = (position.x)  + direction.x;
	     ydest = (position.y)  + direction.y;
	       
	       
	       
	     xtemp = position.x ;
	     ytemp = position.y ;
	     calculateCorners(position.x,ydest);
	       
	     if(direction.y < 0){
	         if(topLeft || topRight){
	      	   direction.y = 0;
	           ytemp = (currRow * tileSize - 32)  + collision.height/ 2;
	         }else {
	           ytemp += direction.y;
	           
	         }
	       }
	       
	       if(direction.y > 0){
	       
	           if(bottomLeft || bottomRight){   
	        	   direction.y = 0;
	               falling = false;
	               ytemp = (currRow + 2) * tileSize - collision.height / 2;
	           }else{
	               ytemp += direction.y;
	           }
	        }
	       calculateCorners(xdest, position.y);
	       
	       if(direction.x < 0){
	           if(topLeft || bottomLeft){
	        	   direction.x = 0;
	               xtemp = (currCol * tileSize) + collision.width / 2;
	           }else{
	               xtemp += direction.x;
	           }
	       }
	       
	       if(direction.x > 0){
	           if(topRight || bottomRight){
	        	   direction.x = 0;
	               xtemp = (currCol + 1.016099) * tileSize - collision.width / 2;
	           }else{
	               xtemp += direction.x;
	           }
	       
	       }
	       
	       if(!falling){
	           calculateCorners(position.x, ydest + 2);
	           if(!bottomLeft && !bottomRight){
	               falling = true;
	           }
	       }
	       
	       
	       
	       
	   }
	   
	

	//////////////////////////////////////////////////////////////////////////////////
	   public void setUp(boolean a){up = a;}
	   public void setDown(boolean a){down = a;}
	   public void setLeft(boolean a){left = a;}
	   public void setRight(boolean a){right = a;}
	   public void setJumping(boolean a){jumping = a;}
	   public void setFalling(boolean a){falling = a;}
	   public boolean getUp(){return up;}
	   public boolean getDown(){return down;}
	   public boolean getLeft(){return left;}
	   public boolean getRight(){return right;}
	   public boolean isJumping(){return jumping;}
	   public boolean isFalling(){return falling;}
	//////////////////////////////////////////////////////////////////////////////////
	
	public boolean isOFFscreen() {
		return(	position.x + mapPos.x + dimention.width < 0 ||
				position.x + mapPos.x - dimention.width > Game.WIDTH ||
				position.y + mapPos.y + dimention.height < 0 ||
				position.y + mapPos.y - dimention.height > Game.HEIGHT
				);
	}
	      
	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	
	public void setFrames(Sprite[] frames) {
		animation.setFrames(frames);
	}

	public void setPosition(double x, double y) {
		position.setVector(x, y);
	}
	
	public Dimension getCollision() {
		return collision;
	}
	
	public void setCollision(int w, int h) {
		collision = new Dimension(w, h);
	}
	   
	public boolean isSolid() {
		return solid;
	}
	
	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public Sprite getSprite() {
		return sprite;
	}
	
	public Sprite getCurrentFrame() {
		if(animation == null)return null;
		return animation.getCurrentFrame();
	}
	
	public Vector2F getPosition() {
		return position;
	}
	
	public Dimension getDimention() {
		return dimention;
	}
	
	public void setCollisionPosition(int x, int y) {
		this.cx = x;
		this.cy = y;
	}



	public Debugger getDebug() {
		return debug;
	}



	public void setDebug(Debugger debug) {
		this.debug = debug;
	}



	public double getXtemp() {
		return xtemp;
	}



	public double getYtemp() {
		return ytemp;
	}

	public Handler getHandler() {
		return handler;
	}
	
}
