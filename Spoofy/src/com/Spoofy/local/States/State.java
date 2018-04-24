package com.Spoofy.local.States;

import java.awt.Graphics2D;
import java.util.Stack;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Utils.GameIO.IO;

public abstract class State {

	protected static Stack<State> states = new Stack<State>();
	public static State currentState = null;
	protected Handler handler;
	protected static IO io;
	public State(Handler h) {
		states.add(currentState = this);
		handler = h;
		io = handler.getIO();
	}
	
	public abstract void init();
	public abstract void tick(double delta);
	public abstract void draw(Graphics2D g);
	
	public void setState(State state) {
		currentState = state;
		currentState.init();
	}
	
	
	
}
