package com.Spoofy.local.Utils.GameIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;



public class IO implements Runnable{
	
	public static final int IN = 0xAF;
	public static final int OUT = 0xEF;
	public static final int STALL = 0x0;
	public static final HashMap<String, byte[]> BUFFER_STREAM = new HashMap<String,byte[]>();
	
	public static boolean debug = false;
	
	private static int mode = 0x0;
	protected boolean hasAdded = false;
	
	private boolean running = false;
	private Thread thread;
	
	private String _path;
	private byte[] buffer;
	
	private boolean list;
	
	public void run() {
		System.out.println("IO Stream on.");
		while(running) {
				switch(mode) {
				case IN:
					log("IN");
					Load load = new Load(64,_path);
					load.setList(list);
					load.loadBuffer();
					break;
				case OUT:
					log("OUT");
					Save save = new Save(buffer,_path);
					save.saveBuffer();
					break;
				case STALL:
					log("STALL");
					_path = "";
					break;
					
				}
				
				try {Thread.sleep(500);} catch (InterruptedException e) {System.err.println(e);}
			
		}
	}
	
	public static BufferedImage readByteBufferToImage(byte[] buffer) throws IOException{
		ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
		return ImageIO.read(bais);
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
	
	public void load(String path,boolean e, boolean list) {
		if(mode == STALL) {
			String current = (new File("")).getAbsolutePath();
			this.list = list;
			if(e){
				_path = path;
				mode = IN;
				return;
			}
			_path = current + "/res/"+path;
			mode = IN;
			return;
		}
		
	}
	
	public void save(byte[] buffer,String path, boolean e){
		String current = (new File("")).getAbsolutePath();
		if(e){
			this._path = path;
			this.buffer = buffer;
			mode = OUT;
			return;
		}
		this._path = current + "/res/" + path;
		this.buffer = buffer;
		mode = OUT;
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
			if(path.toCharArray()[n] == '\\' || path.toCharArray()[n] == '/'){
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
	
	protected void log(String s) {
		if(debug)System.out.println("[IO]: "+s);
	}
	
	protected void log(String s, Object... obj) {
		if(debug)System.out.println("[IO]: "+String.format(s, obj));
	}
	
	
}
