package com.Spoofy.local.Core.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Utils.Vectors2F;

public class Background {
	private BufferedImage img;
	private Vectors2F position;
	private Vectors2F direction;
	
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
		
	}
	
	public void setPosition(Vectors2F pos) {
		position = new Vectors2F((pos.x * moveScale) % Game.WIDTH,(pos.y * moveScale) % Game.HEIGHT);
	}
	
	public void setDirection(Vectors2F dir) {
		this.direction = dir;
	}
	
	
	
	
	public void tick() {
		position.x += (direction.x % Game.WIDTH) * moveScale;
		position.y += (direction.y % Game.HEIGHT) * moveScale;
		//position.add(direction.mod(new Vectors2F(Game.WIDTH,Game.HEIGHT).multi(moveScale)));
		//position.add(direction);
		
	}
	
	public void draw(Graphics2D g) {
		
		//X
		g.drawImage(img, (int)position.x, (int)position.y,Game.WIDTH / GameScreen.SCALE,Game.HEIGHT / GameScreen.SCALE,null);
		if(position.x < 0){
			g.drawImage(img, ((int)position.x + Game.WIDTH / GameScreen.SCALE ),(int) position.y,Game.WIDTH / GameScreen.SCALE,Game.HEIGHT / GameScreen.SCALE ,null);
		}
		if(position.x > 0){
			g.drawImage(img, (int)position.x - Game.WIDTH  / GameScreen.SCALE,(int) position.y, Game.WIDTH / GameScreen.SCALE,Game.HEIGHT / GameScreen.SCALE,null);
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
			setPosition(new Vectors2F(-(Game.WIDTH / GameScreen.SCALE), 0));
		}
		if( position.x < -(Game.WIDTH + img.getWidth())){
			setPosition(new Vectors2F(Game.WIDTH + img.getWidth(), 0));
		}
		
		//Y
		if(position.y > (Game.HEIGHT / GameScreen.SCALE)) {
			setPosition(new Vectors2F(0, -(Game.HEIGHT / GameScreen.SCALE)));
		}
		
		if(position.y < -(Game.HEIGHT / GameScreen.SCALE)) {
			setPosition(new Vectors2F(0, (Game.HEIGHT / GameScreen.SCALE)));
		}
		
		
		
		
	} 
	
	public Vectors2F getPosition() {
		return position;
	}
	
	public Vectors2F getDirection() {
		return direction;
	}
	
	public double getMoveScale() {
		return moveScale;
	}
	
}
