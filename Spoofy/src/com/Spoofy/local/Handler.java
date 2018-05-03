package com.Spoofy.local;

import java.awt.image.BufferedImage;

import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Core.Optimizer;
import com.Spoofy.local.Core.gfx.GameScreen;
import com.Spoofy.local.Utils.TimeCount;
import com.Spoofy.local.Utils.GameIO.IO;
import com.Spoofy.local.input.KeyboardInput;


public class Handler {
	Game game;
	
	public Handler(Game g) {
		game = g;
	}
	
	public Game getGame() {
		return game;
	}
	
	public IO getIO() {
		return game.getIO();
	}
	
	public KeyboardInput getKeyInput() {
		return game.keys();
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

	public TimeCount getClock() {
		return game.getClock();
	}
	
	public Optimizer getOptimizer() {
		return game.getOptimizer();
	}
	
}
