package com.Spoofy.local.objs;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Animation;
import com.Spoofy.local.Core.gfx.Sprite;
import com.Spoofy.local.Core.gfx.mapping.Tile;
import com.Spoofy.local.Core.gfx.mapping.TileMap;
import com.Spoofy.local.Utils.Vector2F;

public class GameObject {

	protected Vector2F position, direction, mapPos;
	protected Dimension dimention, collision;
	protected Sprite sprite;
	protected Animation animation;
	protected boolean solid = false;
	protected Handler handler;
	protected TileMap tm;
	protected boolean faceingRight;
	protected int tileSize;
	
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
		sprite = new Sprite(new BufferedImage(0, 0, BufferedImage.TYPE_INT_RGB));
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
	
	public GameObject (Handler handler, Animation ani, int x, int y, int width, int height,TileMap tm) {
		this.handler = handler;
		animation = ani;
		position = new Vector2F(x, y);
		dimention = new Dimension(width, height);
		this.tm = tm;
		tileSize = tm.getTileSize();
		direction = new Vector2F();
		mapPos = new Vector2F();
	}
	
	public void tick(float delta) {
		if(animation != null)animation.tick();
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
		return new Rectangle((int)position.x - collision.width,(int) position.y - collision.height, collision.width, collision.height);
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
	       
	     xdest = position.x + direction.x;
	     ydest = position.y + direction.y;
	       
	       
	       
	     xtemp = position.x;
	     ytemp = position.y;
	     calculateCorners(position.x,ydest);
	       
	     if(direction.y < 0){
	         if(topLeft || topRight){
	      	   direction.y = 0;
	           ytemp = currRow * tileSize  + collision.height/ 2;
	         }else {
	           ytemp += direction.y;
	           
	         }
	       }
	       
	       if(direction.y > 0){
	       
	           if(bottomLeft || bottomRight){   
	        	   direction.y = 0;
	               falling = false;
	               ytemp = (currRow + 1) * tileSize - collision.height / 2;
	           }else{
	               ytemp += direction.y;
	           }
	        }
	       calculateCorners(xdest, position.y);
	       
	       if(direction.x < 0){
	           if(topLeft || bottomLeft){
	        	   direction.x = 0;
	               xtemp = currCol * tileSize + collision.width / 2;
	           }else{
	               xtemp += direction.x;
	           }
	       }
	       
	       if(direction.x > 0){
	           if(topRight || bottomRight){
	        	   direction.x = 0;
	               xtemp = (currCol + 1) * tileSize - collision.width /2;
	           }else{
	               xtemp += direction.x;
	           }
	       
	       }
	       
	       if(!falling){
	           calculateCorners(position.x, ydest + 1);
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
	
	public boolean isOnscreen() {
		return(position.x + mapPos.x + dimention.width < 0 ||
				position.x + mapPos.x - dimention.width > handler.getGameImage().getWidth() ||
				position.y + mapPos.y + dimention.height < 0 ||
				position.y + mapPos.y - dimention.height > handler.getGameImage().getWidth()
				);
	}
	      
	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public void setPosition(Vector2F pos) {
		position = pos;
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
	
	
}