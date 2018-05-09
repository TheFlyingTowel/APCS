package com.Spoofy.local.States;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Robot;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Core.Sound;
import com.Spoofy.local.Core.gfx.Background;
import com.Spoofy.local.Core.gfx.Fade;
import com.Spoofy.local.Utils.Vector2F;

public class StartState extends State{

	Background bg;
	Background cloud;
	Font font;
	public static final String[] MAIN_MENU = {"Start","Options"};
	public static final String[] OPTIONS = {"Optimizer: ", "Music: ","Vol: ", "Back"};
	public static final String[] START_MENU = {"New Game", "Load Save", "Back"};
	private String[] currentMenu = MAIN_MENU.clone();
	private String[] lastMenu = currentMenu;
	private boolean changingMenu = false;
	private boolean isOptimizer = false;
	private Sound music;
	int pos = 0;
	int size = 32, xpos = 128;
	private Graphics2D gfx = null;
	private double currentVol;
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
		music = new Sound("Main.wav");
		Sound.playSound(music, 1);
		currentVol = 1;
		
	}

	@Override
	public void tick(double delta) {
		bg.tick();
		cloud.tick();
		isOptimizer = handler.getOptimizer().isRunning();
		
		
			if(handler.getKeyInput().isUp()) {
				pos --;
				hold();
			}
			if(handler.getKeyInput().isDown()) {
				pos++;
				hold();
			}
			if(pos < 0)pos = currentMenu.length - 1;
			if(!(pos < currentMenu.length))pos = 0;
		

			
			if(handler.getKeyInput().isLeft() || handler.getKeyInput().isRight()) {
				if(isCurrentMenu(OPTIONS)) {
					if(pos == 2) {
						hold();
						if(handler.getKeyInput().isLeft()) currentVol -= 0.25;
						else if(handler.getKeyInput().isRight())currentVol += 0.25;
					}
				}
			}//https://solarianprogrammer.com/2014/12/08/getting-started-jogl-java-opengl-eclipse/#JOGL
			if(currentVol >= Sound.MAX_VOL) currentVol = Sound.MAX_VOL;
			if(currentVol <= 0) currentVol = 0;
			
			if(handler.getKeyInput().isEnter() && !changingMenu) {
				hold();
				if(isCurrentMenu(MAIN_MENU)) {
					switch (pos) {
					case 0:
						Sound.kill();
						Fade fade = new Fade(gfx, 16, .1, 0);
						fade.start();
						//setState(new GameState(handler));
						break;
						
					case 1:
						setCurrentMenu(OPTIONS);
						changingMenu = false;
						break;
					
					}
				}else if(isCurrentMenu(OPTIONS)) {
					switch (pos) {
					case 0:
						if(isOptimizer)handler.getOptimizer().stop();
						else handler.getOptimizer().start();
						break;
					
					case 1:
						if(Sound.MUSIC)Sound.MUSIC = false;
						else Sound.MUSIC = true;
						break;
					
					case 2:
						Sound.setVol(currentVol);
						break;
						
					case 3:
						hold();
						setCurrentMenu(lastMenu);
						changingMenu = false;
						break;
					}
				}
			}
			
		
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		cloud.draw(g);
		gfx = g;
		
	
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("sans", Font.BOLD, size + 1));
		g.drawString("Spoofy", xpos - 3, 100);
		
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString("Spoofy", xpos, 100);
		
		g.setFont(new Font("sans", Font.BOLD,10));
		g.setColor(Color.black);
		
		if(!changingMenu) {
			if(isCurrentMenu(MAIN_MENU)) {
				for(int i = 0; i < currentMenu.length; i++) {
					if(i == pos)g.setColor(Color.RED);
					else g.setColor(Color.black);
					g.drawString(currentMenu[i], xpos + 42, 120 + (i * 16));
				}
			}else if(isCurrentMenu(OPTIONS)) {
				for(int i = 0; i < currentMenu.length; i++) {
				
					currentMenu[0] = String.format("%s%s", OPTIONS[0], (isOptimizer) ? "ON (Recommended)" : "OFF");
					currentMenu[1] = String.format("%s%s", OPTIONS[1], (Sound.MUSIC) ? "ON" : "OFF");
					
					
					int x = 21;
					int y = 7;
					g.setColor(Color.RED);
					g.fillRect((xpos + 42) + x, (120 + (2 * 16)) - y, 32, 8);
					g.setColor(Color.BLUE);
					g.fillRect((xpos + 42) + x, (120 + (2 * 16)) - y, (int) (32 * (currentVol / Sound.MAX_VOL)), 8);
					
					if(i == pos)g.setColor(Color.RED);
					else g.setColor(Color.black);
					
					g.drawString(currentMenu[i], xpos + 42, 120 + (i * 16));
				}
			}
			
			
			
			
		}
		
		

	}
	
	
	private void setCurrentMenu(String[] m) {
		changingMenu = true;
		lastMenu = currentMenu;
		pos = 0;
		currentMenu = m.clone();
	}
	
	private boolean isCurrentMenu(String[] m) {
		if(m.length != currentMenu.length)return false;
		for(int i = 0; i < currentMenu.length; i++) {
			if(i < m.length) {
				if(!currentMenu[i].contains(m[i]))return false;
			}
		}
		
		return true;
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
