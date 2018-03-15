package com.Spoofy.local.Core.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Spoofy.local.Utils.Utills;

public class Sprite {

	private BufferedImage sprite;
	
	public Sprite(String path) {
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream(path));
		}catch(IOException e) {
			System.err.println("ERROR: "+e.getMessage());
		}
	}
	
	public Sprite(BufferedImage img) {
		sprite = img;
	}
	
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sprite.getSubimage(x, y, width, height);
	}
	
	public BufferedImage getImage() {
		return sprite;
	}
}
