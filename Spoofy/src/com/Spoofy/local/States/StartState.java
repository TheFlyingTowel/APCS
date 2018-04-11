package com.Spoofy.local.States;

import java.awt.Graphics2D;
import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Background;
import com.Spoofy.local.Utils.Vector2F;

public class StartState extends State{

	Background bg;
	Background cloud;
	public StartState(Handler h) {
		super(h);
	}
	
	@Override
	public void init() {
		bg = new Background(handler,"/Background/BKG01(no-cloud).png", 1.00005);
		cloud = new Background(handler, "/Background/Clouds.png", 1.00005);
		bg.setPosition(new Vector2F());
		cloud.setPosition(new Vector2F());
		bg.setDirection(new Vector2F(-0.5,0));
		cloud.setDirection(new Vector2F(-0.9,0));

	}

	@Override
	public void tick(double delta) {
		bg.tick();
		cloud.tick();

	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		cloud.draw(g);
	}

}
