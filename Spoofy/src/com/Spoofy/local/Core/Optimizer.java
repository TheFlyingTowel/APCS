package com.Spoofy.local.Core;

public class Optimizer implements Runnable{

	Game game;
	private boolean isRunning = false;
	private Thread thread;
	public Optimizer(Game game) {
		this.game = game;
	}
	
	public void run() {
		int lastTarget = 60;
		int hits = 0,highHits = 0, count = 0;
		
		System.out.println("Optimizer started");
		System.out.println("Target: "+lastTarget);
		while(isRunning) {

			
			if(game.getFPS() < 30) {
				System.out.println("Hitt: "+hits);
				if(hits > 5) {
					lastTarget += 30;
					game.setTargetTime(1000000000 / lastTarget);
					System.out.println("Target Change: "+lastTarget);
					hits = 0;
				}
				hits++;
			}
			
			if(game.getFPS() > 60) {
				if(highHits > 5) {
					lastTarget -= 30;
					game.setTargetTime(1000000000 / lastTarget);
					System.out.println("Target Change: "+lastTarget);
					highHits = 0;
				}
				highHits++;
			}
			
			if(count > 20) {
				count = 0;
				hits--;
			}
			
			count++;
			
			if(!game.getThread().isAlive()) isRunning = false;
			
			///////////////////
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void start() {
		if(isRunning)return;
		isRunning = true;
		thread = new Thread(this);
		thread.setName("Optimizer");
		if(thread != null)thread.start();
	}

	
	public boolean isRunning() {
		return isRunning;
	}
	
}
