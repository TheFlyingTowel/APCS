package com.Spoofy.local.Core.gfx;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import com.Spoofy.local.Core.Game;

public class Fade implements Runnable{

	private double rate;
	private int timing;
	public static final long SECOND = 1000000000L;
	private double state = 1;
	private AlphaComposite ac;
	private long delay;
	
	private Graphics2D g;
	public Fade(Graphics2D g,int timing, double rate,long delay) {
		this.g = g;
		this.rate = rate;
		this.timing = timing;
		this.delay = delay;
		ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) state);
	}
	@Override
	public void run() {
		long now = System.nanoTime();
		long start = now;
		long loop = now - start;
		int time = 0;
		int lastTime = time;
		while(true) {
			now = System.nanoTime();
			loop = now - start;
			time = (int) Math.round(loop / ((((SECOND * .5) * .5) * .5) * .5));
			
			
			if(time > lastTime) {
				state -= rate;
				lastTime = time;
			}
			
			if(time >= timing) {
				ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0);
				g.setComposite(ac);
				break;
			}
			if(state <= 0)state = 0;
			if(state >= 1)state = 1;
			
			ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) state);
			g.setComposite(ac);
			///////////////////
			if(state <= 0) {
				g.clearRect(0, 0, Game.WIDTH, Game.HEIGHT);
				g.dispose();
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1);
		g.setComposite(ac);
	}
	
	public synchronized void start() {
		Thread thread = new Thread(this);
		thread.setName("Fade");
		thread.start();
	}
	

}
