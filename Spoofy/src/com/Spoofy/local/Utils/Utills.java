package com.Spoofy.local.Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Utills {

	//Image loader
	public static BufferedImage imageLoader(String path){
		try{
			if((new File(path)).exists()) {
				System.out.println("File is there.");
			}else {
				System.out.println("File is not there.");
			}
			
			
			return ImageIO.read(new File(path));
		}catch(IOException e){
			System.exit(1);
		}
		
		return null;
	}
	
	
	//clamps
	public static float clamp(float value, float min ,float max){
		return(Math.min(Math.max(value, min), Math.max(min, max)));
	}

	public static int clamp(int value, int min ,int max){
		return(Math.min(Math.max(value, min), Math.max(min, max)));
	}

	public static long clamp(long value, long min ,long max){
		return(Math.min(Math.max(value, min), Math.max(min, max)));
	}

	public static double clamp(double value, double min ,double max){
		return(Math.min(Math.max(value, min), Math.max(min, max)));
	}

	
}
