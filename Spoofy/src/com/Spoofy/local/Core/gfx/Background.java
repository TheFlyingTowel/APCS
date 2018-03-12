package com.Spoofy.local.Core.gfx;

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
	private Handler handler;
	
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
		position.x += (direction.x % Game.WIDTH);
		position.y += (direction.y % Game.HEIGHT);
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(img, (int)position.x, (int)position.y, (Game.WIDTH / 2) + 8  , Game.HEIGHT / 2 ,null);
		
		if(position.x < 0) {
			g.drawImage(img, (int) position.x + Game.WIDTH, (int) position.y, null);
			setPosition(new Vectors2F());
		}
		
		if(position.x > 0) {
			g.drawImage(img, (int)position.x - Game.WIDTH , (int) position.y, null);
		}
		
		
	} 
	
}
