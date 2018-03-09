package com.Spoofy.local.Core;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

	public static final int WIDTH = 1080, HEIGHT = 720;
	public static final Dimension dimention = new Dimension(WIDTH,HEIGHT);
	
	private Thread thread;
	private boolean isRunning = false;
	private int fps = 0;
	private int FPS = 60;//Target FPS
	long targetTime  = 1000000000 / FPS;
	private BufferedImage image;
	
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
		//Render
	}
	
	@Override
	public void run() {
		while(isRunning){
			
		}
		
	}

	
	public synchronized void start() {
		if(isRunning) return;
			isRunning = true;
			thread = new Thread(this);
			thread.start();
		
	}

	public void stop() {
		if(!isRunning)return;
		boolean er = false;
		if(thread != null) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				System.err.println("ERROR: "+e.getLocalizedMessage());
				er = true;
			}finally {
				if(er)thread.interrupt();
			}
		}
	}
	
}
