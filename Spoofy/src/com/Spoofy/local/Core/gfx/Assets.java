package com.Spoofy.local.Core.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.WatchKey;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Utils.GameIO.IO;

public abstract class Assets {

	
	//Animations
	public static BufferedImage[] lava;
	public static BufferedImage[] lavaTop;
	public static BufferedImage[] waterTop;
	public static BufferedImage[] water;
	//Animations

	
	public static void init(IO io) {
	


		io.load("Sprites/PixelPack03_Free/", false,true);
		pause();
		io.load("Sprites/", false,true);
		pause();
		io.load("Maps/", false, true);
		pause();
		io.load("Background/", false, true);
		pause();
		io.load("Tiles_Sets/", false, true);
		pause();
		
		BufferedImage sheet = null;
		try {
			sheet = IO.readByteBufferToImage(IO.BUFFER_STREAM.get("lava_water_tiles.png"));
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
		int width, height;
		
		
		
		//Lava & Water
			
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
			
			
			
		
		
		
	}
	
	private static void pause() {
		do {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}while(IO.getCurrentMode() != IO.STALL);
	}

	

}
