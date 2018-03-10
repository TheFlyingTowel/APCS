package com.Spoofy.local.States;

import java.awt.Graphics2D;
import java.util.Stack;

public abstract class GameStates {

	protected static Stack<GameStates> states = new Stack<GameStates>();
	public static GameStates currentState = null;
	
	public GameStates() {
		states.add(currentState = this);
		
	}
	
	public abstract void init();
	public abstract void tick(float delta);
	public abstract void draw(Graphics2D g);
	
	public void setState(GameStates state) {
		currentState = state;
		currentState.init();
	}
	
	
	
}
