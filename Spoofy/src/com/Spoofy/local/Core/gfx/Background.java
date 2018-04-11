package com.Spoofy.local.Core.gfx;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Utils.Vector2F;

public class Background {
	private BufferedImage img;
	private Vector2F position;
	private Vector2F direction;
	
	private double moveScale;
	Handler handler;
	
	public Background(Handler h,String path, double ms) {
		handler = h;
		try {
			img = ImageIO.read(getClass().getResourceAsStream(path));
			moveScale = ms;
		}catch(IOException e) {
			System.err.println("Could not load background: -> \n"+e.getMessage());
		}
		
		position = new Vector2F();
		direction = new Vector2F();
	}
	
	public void setPosition(Vector2F pos) {
		position.setVector((pos.x * moveScale) % Game.WIDTH,(pos.y * moveScale) % Game.HEIGHT);
	}
	
	public void setPosition(double x,double y) {
		position.setVector((x * moveScale) % Game.WIDTH,(y * moveScale) % Game.HEIGHT);
	}
	
	
	public void setDirection(Vector2F dir) {
		direction.setVector(dir.x, dir.y);
	}
	
	
	
	
	public void tick() {
		position.x += ((direction.x % Game.WIDTH) * moveScale);
		position.y += ((direction.y % Game.HEIGHT) * moveScale);
	}
	
	public void draw(Graphics2D g) {
		
		//X
		g.drawImage(img, (int)position.x, (int)position.y,Game.WIDTH / GameScreen.SCALE,Game.HEIGHT / GameScreen.SCALE,null);
		if(position.x < 0){
			g.drawImage(img, ((int)position.x + (Game.WIDTH / GameScreen.SCALE )),(int) position.y,Game.WIDTH / GameScreen.SCALE,Game.HEIGHT / GameScreen.SCALE ,null);
		}
		if(position.x > 0){
			g.drawImage(img, (int)position.x - (Game.WIDTH  / GameScreen.SCALE),(int) position.y, Game.WIDTH / GameScreen.SCALE,Game.HEIGHT / GameScreen.SCALE,null);
		}
		
		//Y
		if(position.y < 0){
			g.drawImage(img, ((int)position.x),(int) position.y + Game.HEIGHT / GameScreen.SCALE, Game.WIDTH / GameScreen.SCALE,Game.HEIGHT / GameScreen.SCALE ,null);
		}
		
		if(position.y > 0){
			g.drawImage(img, ((int)position.x),(int) position.y - Game.HEIGHT / GameScreen.SCALE, Game.WIDTH / GameScreen.SCALE, Game.HEIGHT / GameScreen.SCALE ,null);
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////
		
		//X
		if( position.x > (Game.WIDTH / GameScreen.SCALE)){
			
			setPosition(-(Game.WIDTH / GameScreen.SCALE), 0);
		}
		if( position.x < -(Game.WIDTH / GameScreen.SCALE)){
			
			setPosition(Game.WIDTH / GameScreen.SCALE, 0);
		}
		
		//Y
		if(position.y > (Game.HEIGHT / GameScreen.SCALE)) {
			setPosition(0, -(Game.HEIGHT / GameScreen.SCALE));
		}
		
		if(position.y < -(Game.HEIGHT / GameScreen.SCALE)) {
			setPosition(0, (Game.HEIGHT / GameScreen.SCALE));
		}
		
		
		
		
	} 
	
	public Vector2F getPosition() {
		return position;
	}
	
	public Vector2F getDirection() {
		return direction;
	}
	
	public double getMoveScale() {
		return moveScale;
	}
	
}
