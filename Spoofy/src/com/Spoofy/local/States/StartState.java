package com.Spoofy.local.States;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Robot;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Core.gfx.Background;
import com.Spoofy.local.Utils.Vector2F;

public class StartState extends State{

	Background bg;
	Background cloud;
	Font font;
	String[] choices = {"Start","Options"};
	int pos = 0;
	int size = 32, xpos = 128;
	public StartState(Handler h) {
		super(h);
	}
	
	@Override
	public void init() {
		bg = new Background(handler,"BKG01(no-cloud).png", 1.00005);
		cloud = new Background(handler, "Clouds.png", 1.00005);
		bg.setPosition(new Vector2F());
		cloud.setPosition(new Vector2F());
		bg.setDirection(new Vector2F(-0.5,0));
		cloud.setDirection(new Vector2F(-0.9,0));
		font = new Font("sans", Font.BOLD, size);

	}

	@Override
	public void tick(double delta) {
		bg.tick();
		cloud.tick();
		
			if(handler.getKeyInput().isUp()) {
				pos --;
				hold();
			}
			if(handler.getKeyInput().isDown()) {
				pos++;
				hold();
			}
			if(pos < 0)pos = choices.length - 1;
			if(!(pos < choices.length))pos = 0;
		
			if(handler.getKeyInput().isEnter()) {
				switch (pos) {
				case 0:
					setState(new GameState(handler));
					break;

				default:
					break;
				}
			}
			
		
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		cloud.draw(g);

		g.setColor(Color.WHITE);
		g.setFont(new Font("sans", Font.BOLD, size + 1));
		g.drawString("Spoofy", xpos - 3, 100);
		
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString("Spoofy", xpos, 100);
		
		g.setFont(new Font("sans", Font.BOLD,10));
		g.setColor(Color.black);
		for(int i = 0; i < choices.length; i++) {
			if(i == pos)g.setColor(Color.RED);
			else g.setColor(Color.black);
			g.drawString(choices[i], xpos + 42, 120 + (i * 16));
		}
		
		
		

	}
	
	private void hold() {
		try {
			Robot bot = new Robot();
			bot.delay(150);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
