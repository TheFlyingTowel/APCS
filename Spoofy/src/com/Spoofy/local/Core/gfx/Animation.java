package com.Spoofy.local.Core.gfx;

import com.Spoofy.local.Utils.Utills;

public class Animation {

	private int index;
	float speed;
	private long delay, startTime;
	private Sprite[] frames;
	private boolean hasPlayed = false;
	
	public Animation(Sprite[] frames,long delay) {
	
		this.frames = frames;
		this.delay = delay;
		
		/*	this.frames = frames;
		this.speed = speed;
		timer = 0;
		lastTime = System.currentTimeMillis();*/
	}
	
	public void tick() {
	/*	timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if((timer / speed) > speed) {
			index++;
			timer = 0;
			if(index >= frames.length) {
				index = 0;
				hasPlayed = true;
			}
		}*/
		
		
		
		
		
		if(delay == -1) return;
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > delay) {
			index++;
			startTime = System.nanoTime();
		}
		if(index == frames.length) {
			index = 0;
			hasPlayed = true;
		}
		
		
		
		
		
	}
	
	
	public Sprite[] getFrames() {
		return frames;
	}
	
	public Sprite getCurrentFrame() {
		return frames[index];
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setIndex(int index) {
		this.index = Utills.clamp(index, 0, frames.length - 1);
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setFrames(Sprite[] frames) {
		this.frames = frames;
		index = 0;
		startTime = System.nanoTime();
		hasPlayed = false;
	}
	
	public boolean hasPlayed() {
		return hasPlayed;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
	
}

