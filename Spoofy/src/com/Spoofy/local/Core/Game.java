package com.Spoofy.local.Core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.Spoofy.local.Core.gfx.Display;
import com.Spoofy.local.Core.gfx.GameScreen;
import com.Spoofy.local.States.GameStates;
import com.Spoofy.local.States.StartState;
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
	private Graphics2D g;
	private GameStates state;
	
	
	void init()
	{
		//Initialize 
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		display = new Display("Spoofy");
		keyInput = new KeyboardInput();
		display.addKeyListener(keyInput);
		state = new StartState();
		state.init();
	}
	
	void tick(float delta)
	{
		//Update
		keyInput.tick();
		state.tick(delta);
	}
	
	void draw()
	{
		//Render
		
		//To screen
		Graphics g2 = display.getScreen().getGraphics();
		state.draw(g);
		g2.drawImage(image, 0, 0,WIDTH * GameScreen.SCALE, HEIGHT * GameScreen.SCALE, null);
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
		float delta = 0;
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
				Thread.sleep(500);
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
