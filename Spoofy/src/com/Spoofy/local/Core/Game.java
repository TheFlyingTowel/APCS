package com.Spoofy.local.Core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.Spoofy.local.Core.gfx.Display;
import com.Spoofy.local.input.KeyboardInput;

public class Game implements Runnable {

	public static final int WIDTH = 1080, HEIGHT = 720;
	public static final Dimension dimention = new Dimension(WIDTH,HEIGHT);
	
	private Thread thread;
	private boolean isRunning = false;
	private int fps = 0;
	private int FPS = 60;//Target FPS
	private Display display;
	private KeyboardInput keyInput;
	private BufferedImage image;
	private Graphics g;
	
	
	void init()
	{
		//Initialize 
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		display = new Display("Spoofy");
		keyInput = new KeyboardInput();
		display.addKeyListener(keyInput);
	}
	
	void tick(double delta)
	{
		//Update
		keyInput.tick();
	}
	
	void draw()
	{
		//Render
		
		//To screen
		Graphics g2 = display.getScreen().getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	@Override
	public void run() {
		init();
		fps = FPS;
		long now = System.nanoTime();
		long targetTime = 1000000000 / FPS;
		long loop = now;
		long fpsTime = 0;
		double delta = 0;
		int ticks = 0;
		
		while(isRunning){
			now = System.nanoTime();
			delta += (now - loop) / targetTime;
			fpsTime += (now - loop);
			loop = now;
			
			if(delta >= 1) {
				tick(delta);
				draw();
				ticks++;
				delta--;
			}
			
			if(fpsTime >= 1000000000) {
				fps = ticks;
				ticks = 0;
				fpsTime = 0;
			}
			
			try {
				Thread.sleep(targetTime - loop / 1000000);
			}catch(InterruptedException e) {
				System.err.println("Game Thread could not sleep: -> \n"+e.getMessage());
			}
			
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
