package com.Spoofy.local.Core.gfx.mapping;

import java.awt.image.BufferedImage;

public class Tile {
	public static final int NORMAL = 0xA1, BLOCKED = 0xA2;
	
	private BufferedImage image;
	private int type;
	
	public Tile(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getType() {
		return type;
	}
	
}
