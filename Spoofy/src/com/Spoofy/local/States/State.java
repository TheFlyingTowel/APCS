package com.Spoofy.local.States;

import java.awt.Graphics2D;
import java.util.Stack;

import com.Spoofy.local.Handler;

public abstract class State {

	protected static Stack<State> states = new Stack<State>();
	public static State currentState = null;
	protected Handler handler;
	public State(Handler h) {
		states.add(currentState = this);
		handler = h;
	}
	
	public abstract void init();
	public abstract void tick(double delta);
	public abstract void draw(Graphics2D g);
	
	public void setState(State state) {
		currentState = state;
		currentState.init();
	}
	
	
	
}
