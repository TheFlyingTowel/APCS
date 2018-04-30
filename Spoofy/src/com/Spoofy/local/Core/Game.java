package com.Spoofy.local.Core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Assets;
import com.Spoofy.local.Core.gfx.Display;
import com.Spoofy.local.Core.gfx.GameScreen;
import com.Spoofy.local.States.State;
import com.Spoofy.local.Utils.TimeCount;
import com.Spoofy.local.Utils.GameIO.IO;
import com.Spoofy.local.States.GameState;
import com.Spoofy.local.States.StartState;
import com.Spoofy.local.input.KeyboardInput;

public class Game implements Runnable {

	public static final int WIDTH = 750, HEIGHT = 540;
	public static final Dimension dimention = new Dimension(WIDTH,HEIGHT);
	
	private Thread thread;
	private boolean isRunning = false;
	private int fps = 0;
	public int FPS = 60;//Target FPS
	private Display display;
	private KeyboardInput keyInput;
	private BufferedImage image;
	private Graphics2D g;
	private long targetTime;
	private Optimizer op;
	private IO io;
	private TimeCount clock;
	
	void init()
	{
		//Initialize 
		
		WindowListener exit = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				for(int i = 0; i < 50; i++)System.out.print("-");
				System.out.println();
				clock.endTimer();
				save();
				pause();
				io.stop();
				op.stop();
				stop();
				System.exit(0);
			}
		};
		Handler handler = new Handler(this);
		//////////////////////////////
		io = new IO();
		io.start();
		op = new Optimizer(this);
		op.start();
		/////////////////////////////
		Assets.init(io); 

		keyInput = new KeyboardInput();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		display = new Display("Spoofy",keyInput);
		display.addWindowListener(exit);
		display.setKeyListener(keyInput);
		new StartState(handler);
		State.currentState.init();
	}
	
	void tick(float delta)
	{
		//Update
		clock.update();
		keyInput.tick();
		State.currentState.tick(delta);

	}
	
	void draw()
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		
		
		
		
		State.currentState.draw(g);
		
		

		Graphics g2 = display.getScreen().getGraphics();
		/*g.setColor(Color.YELLOW);
		g.drawString("FPS: "+fps, 8, 48);*/
		g2.drawImage(image, 0, 0,(WIDTH * GameScreen.SCALE), (HEIGHT * GameScreen.SCALE), null);
		g2.dispose();
	}
	
	@Override
	public void run() {
		init();
		fps = FPS;
		long now = System.nanoTime();
		targetTime = 1000000000 / FPS;
		long loop = now;
		long fpsTime = 0;
		float delta = 0;
		int ticks = 0;
		clock = new TimeCount(System.nanoTime());
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
				Thread.sleep((long) (loop - now + targetTime) / 1000000);
			}catch(InterruptedException e) {
				System.err.println("Game Thread could not sleep: -> \n"+e.getMessage());
			}
			
		}
		
	}
	
	public synchronized void start() {
		if(isRunning) return;
			isRunning = true;
			thread = new Thread(this);
			thread.setName("Game");
			thread.start();
			System.out.println("Game Opened");
	}

	
	public synchronized void stop() {
		if(!isRunning)return;
		if(thread != null) {
			isRunning = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				System.err.println(e);
			}finally {
				thread.interrupt();
				System.out.println("Game closed.");
				System.out.println("Play Time: "+clock.getEndTimeMinutes()+"m");
			}
		}
	}
	
	
	private void save() {
		if(State.currentState.getClass() == GameState.class) {
			GameState gs = (GameState) State.currentState;
			io.saveGame(gs.getSave(), "Name");			
		}
	}
	
	
	public void pause() {
		do {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}while(IO.getCurrentMode() != IO.STALL);
	}
	
	
	public BufferedImage getGameImage(){
		return image;
	}
	
	public GameScreen getGameScreen() {
		return display.getScreen();
	}
	 
	public int getFPS() {
		return fps;
	}

	public KeyboardInput keys() {
		return keyInput;
	}
	
	public Thread getThread() {
		return thread;
	}
	public void setTargetTime(long targetTime) {
		this.targetTime = targetTime;
	}
	
	public IO getIO() {
		return io;
	}
	
	public TimeCount getClock() {
		return clock;
	}
	
}
