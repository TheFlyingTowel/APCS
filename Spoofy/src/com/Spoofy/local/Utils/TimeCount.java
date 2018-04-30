package com.Spoofy.local.Utils;

public class TimeCount {
	
	public static final long MINUTE = 60000000000L;
	
	private long startTime;
	private long currentTime;
	private int endTime;
	private int timeCount;
	private boolean end = false;
	
	public TimeCount(long startTime) {
		this.startTime = startTime;
	}
	
	
	public void update() {
		if(!end) {
			currentTime = System.nanoTime();
			long time = currentTime - startTime;
			timeCount = Math.round(time / MINUTE);
		}
	}
	
	public void endTimer() {
		end = true;
		endTime = timeCount;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public long getCurrentTime() {
		return currentTime;
	}
	public int getEndTimeMinutes() {
		return endTime;
	}
	
	public boolean hasEnded() {
		return end;
	}
	

}
