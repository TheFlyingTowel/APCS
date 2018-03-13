package com.Spoofy.local.States;

import java.awt.Graphics2D;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Background;
import com.Spoofy.local.Utils.Debugger;
import com.Spoofy.local.Utils.Vectors2F;

public class StartState extends State{

	Background bg;
	Debugger debug;
	public StartState(Handler h) {
		super(h);
	}
	
	@Override
	public void init() {
		bg = new Background(handler,"/Background/grassbg1.png", 1.00005);
		bg.setPosition(new Vectors2F());
		bg.setDirection(new Vectors2F(0.5,0));
		debug = new Debugger(handler);
		debug.init();
		debug.addDebugText("FPS: ");
		debug.addDebugText("BG X pos: ");
	}

	@Override
	public void tick(float delta) {
		bg.tick();
		debug.upDateText("FPS: ", "FPS: "+handler.getFPS());
		debug.upDateText("BG X pos: ", "BG X pos: "+bg.getPosition().x);
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
	}

}
