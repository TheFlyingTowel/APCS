package com.Spoofy.local.Core;

import java.awt.Dimension;
import java.awt.Graphics2D;

public class Game implements Runnable {

	public static final int WIDTH = 1080, HEIGHT = 720;
	public static final Dimension dimention = new Dimension(WIDTH,HEIGHT);
	
	private boolean isRunning = false;
	private int fps = 0;
	
	
	
	void init()
	{
		//Initialize 
	}
	
	void tick(double delta)
	{
		//Update
	}
	
	void draw()
	{
		
	}
	
	@Override
	public void run() {
		while(isRunning){
			
		}
		
	}

}
