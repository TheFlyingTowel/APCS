package com.Spoofy.local.Core.gfx;

import com.Spoofy.local.Utils.Utills;

public class Animation {

	private int index;
	float speed;
	private long lastTime, timer;
	private Sprite[] frames;
	private boolean hasPlayed = false;
	
	public Animation(Sprite[] frames,float speed) {
		this.frames = frames;
		this.speed = speed;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if((timer / speed) > speed) {
			index++;
			timer = 0;
			if(index >= frames.length) {
				index = 0;
				hasPlayed = true;
			}
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
		this.index = Utills.clamp(index, 0, frames.length);
	}
	
	public void setFrames(Sprite[] frames) {
		this.frames = frames;
	}
	
	public boolean hasPlayed() {
		return hasPlayed;
	}
	
}

