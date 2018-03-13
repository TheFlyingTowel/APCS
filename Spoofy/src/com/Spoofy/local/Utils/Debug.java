package com.Spoofy.local.Utils;

import java.awt.Graphics2D;

import com.Spoofy.local.Handler;

public abstract class Debug {

	protected static StackV2<Debug> debuggers = new StackV2<Debug>();
	protected static int ids;
	protected Handler handler;
	
	private int id,lastId;

	
	public Debug(Handler handler){
		debuggers.pushBottom(this);
		this.handler = handler;
		id = ids++;
	}
	
	public abstract void init();
	public abstract void tick(float delta);
	public abstract void draw(Graphics2D g);
	public abstract void addDebugText(String... t);
	
	public void mInit() {
		for(int i = 0; i < debuggers.getSize(); i++) {
			debuggers.elementAt(i).init();
		}
		lastId = id;
		
	}
	
	public void mTick(float delta) {
		for(int i = 0; i < debuggers.getSize(); i++) {
			debuggers.elementAt(i).tick(delta);
		}
		
		if(id > lastId) {
			debuggers.elementAt(id).init();
			lastId = id;
		}
		
		
	}
	
	public void mDraw(Graphics2D g) {
		for(int i = 0; i < debuggers.getSize(); i++) {
			debuggers.elementAt(i).draw(g);
		}
	}
	
	
	public int getID() {
		return id;
	}
	
}
