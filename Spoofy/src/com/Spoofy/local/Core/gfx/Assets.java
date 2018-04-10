package com.Spoofy.local.Core.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {

	
	//Animations
	public static BufferedImage[] lava;
	public static BufferedImage[] lavaTop;
	public static BufferedImage[] waterTop;
	public static BufferedImage[] water;
	//Animations
	
	
	public void init() {
		BufferedImage sheet;
		int width, height;
		
		//Lava & Water
		try {
			sheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/lava_water tiles.png"));
			
			lavaTop = new BufferedImage[4];
			lava = new BufferedImage[4];
			waterTop = new BufferedImage[4];
			water = new BufferedImage[4];
			width = 16;
			height = 16;
			for(int i = 0; i < 4; i++) {
				lavaTop[i] = sheet.getSubimage(width * i, 0, width, height);
				lava[i] = sheet.getSubimage(width * i, height, width, height);
				waterTop[i] = sheet.getSubimage(width * i, height * 2, width, height);
				water[i] = sheet.getSubimage(width * i, height * 3, width, height);
			}
			
			
			
			
		} catch (IOException e) {System.err.println("ERROR: "+e.getLocalizedMessage());}
		
		
		
		
	}

}
