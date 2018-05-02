package com.Spoofy.local.Core;

public class Optimizer implements Runnable{

	Game game;
	private boolean isRunning = false;
	private Thread thread;
	
	public static boolean debug = false;
	
	public Optimizer(Game game) {
		this.game = game;
	}
	
	public void run() {
		int lastTarget = 60;
		int hits = 0,highHits = 0, count = 0;
		
		System.out.println("Optimizer started");
		log("Target: "+lastTarget);
		while(isRunning) {

			
			if(game.getFPS() < 30) {
				log("Hitt: "+hits);
				if(hits > 5) {
					lastTarget += 30;
					lastTarget = (lastTarget == 0) ? 1 : lastTarget;
					game.setTargetTime(1000000000 / lastTarget);
					log("Target Change: "+lastTarget);
					hits = 0;
				}
				hits++;
			}
			
			if(game.getFPS() > 60) {
				if(highHits > 5) {
					lastTarget -= 30;
					lastTarget = (lastTarget == 0) ? 1 : lastTarget;
					game.setTargetTime(1000000000 / lastTarget);
					log("Target Change: "+lastTarget);
					highHits = 0;
				}
				highHits++;
			}
			
			if(count > 20) {
				count = 0;
				hits--;
			}
			
			count++;
			
			if(!game.getThread().isAlive()) {
				isRunning = false;
				log("Optimizer stoped.");
			}
			
			///////////////////
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void start() {
		if(isRunning)return;
		isRunning = true;
		thread = new Thread(this);
		thread.setName("Optimizer");
		if(thread != null)thread.start();
	}

	public synchronized void stop() {
		if(!isRunning()) return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Optimizer stoped.");
	}
	
	
	public boolean isRunning() {
		return isRunning;
	}
	
	protected void log(String s) {
		if(debug)System.out.println("[Optimizer]: "+s);
	}
	
	protected void log(String s, Object... obj) {
		if(debug)System.out.println("[Optimizer]: "+String.format(s, obj));
	}
	
}
