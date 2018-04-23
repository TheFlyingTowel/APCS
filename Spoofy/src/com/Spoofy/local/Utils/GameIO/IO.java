package com.Spoofy.local.Utils.GameIO;

import java.util.ArrayList;
import java.util.HashMap;



public class IO implements Runnable{
	
	public static final int IN = 0xAF;
	public static final int OUT = 0xEF;
	public static final int STALL = 0x0;
	
	public static final HashMap<String, Integer> STREAM_MAP = new HashMap<String,Integer>();
	public static final ArrayList<String[]> BUFFER_STREAM = new ArrayList<String[]>();
	public static int mode = 0x0;
	protected boolean hasAdded = false;
	
	private boolean running = false;
	private Thread thread;
	
	private String _path;
	
	public void run() {
		System.out.println("IO Stream on.");
		while(running) {
			
			
			
			switch(mode) {
			case IN:
				Load load = new Load(64,_path);
				load.loadInFile();
				break;
			case OUT:
				//Save save = new Save();
				checkAndChange();
				break;
			case STALL:
				_path = "";
				break;
				
			}
			
			try {Thread.sleep(500);} catch (InterruptedException e) {System.err.println(e);}
		}
	}
	
	public synchronized void start() {
		if(running)return;
		running = true;
		thread = new Thread(this);
		thread.setName("IO");
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("IO stream ended.");
	}
	
	protected void checkAndChange() {
		if(hasAdded) {
			mode = STALL;
			hasAdded = false;
		}
	}
	
	public void load(String path) {
		this._path = IO.class.getResource(path).getFile();
		mode = IN;
	}
}
