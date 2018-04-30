package com.Spoofy.local.Core.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Spoofy.local.Utils.GameIO.IO;


public class Sprite {

	private BufferedImage sprite;
	public Sprite(String key) {
		try {
			sprite = IO.readByteBufferToImage(IO.BUFFER_STREAM.get(key));
		} catch (IOException e) {
			e.printStackTrace();
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
