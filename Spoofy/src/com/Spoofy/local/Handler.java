package com.Spoofy.local;

import java.awt.image.BufferedImage;

import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Core.gfx.GameScreen;
import com.Spoofy.local.Utils.Debug;


public class Handler {
	Game game;
	
	public Handler(Game g) {
		game = g;
	}
	
	public BufferedImage getGameImage() {
		return game.getGameImage();
	}
	
	public GameScreen getGameScreen() {
		return game.getGameScreen();
	}
	
	public int getFPS(){
		return game.getFPS();
	}
	
	public Debug getDebugger() {
		return game.getDebugger();
	}
}
