package com.Spoofy.local.Utils.GameIO;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;



public class IO implements Runnable{
	
	public static final int IN = 0xAF;
	public static final int OUT = 0xEF;
	public static final int STALL = 0x0;
	protected static final HashMap<String, byte[]> BUFFER_STREAM = new HashMap<String,byte[]>();
	
	private static int mode = 0x0;
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
				load.loadBuffer();
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
		String current = (new File("")).getAbsolutePath();
		System.out.println(current);
		this._path = current + "\\res\\"+path;
		mode = IN;
	}
	
	public static int getCurrentMode() {
		return mode;
	}
	
	public String getFileName(String path){
		int hits = 0;
		boolean a = false;
		String result = "";
		for(int i = 0; i < path.length(); i++){
			if(path.toCharArray()[i] == '\\'){
				hits++;
			}
		}
		
		int hits2 = 0;
		for(int n = 0; n < path.length(); n++){
			if(path.toCharArray()[n] == '\\'){
				hits2++;
			}
			if(hits2 == hits){
				if(!a){
					a = true;
					continue;
				}
				result += path.toCharArray()[n];
				
			}
		}
		
		return result;
	}
	
	
	public byte[] getBufferData(String key) {
		return BUFFER_STREAM.get(key);
	} 
	
	public void removeBuffer(String key) {
		BUFFER_STREAM.remove(key);
	}
}
