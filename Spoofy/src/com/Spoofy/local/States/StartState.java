package com.Spoofy.local.States;

import java.awt.Graphics2D;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Background;
import com.Spoofy.local.Utils.Vectors2F;

public class StartState extends GameStates{

	Background bg;
	public StartState(Handler h) {
		super(h);
	}
	
	@Override
	public void init() {
		bg = new Background(handler,"/Background/grassbg1.png", 1);
		bg.setPosition(new Vectors2F());
		bg.setDirection(new Vectors2F(-.5,0));
	}

	@Override
	public void tick(float delta) {
		bg.tick();
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
	}

}
