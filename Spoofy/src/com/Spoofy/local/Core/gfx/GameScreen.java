package com.Spoofy.local.Core.gfx;

import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import com.Spoofy.local.Core.Game;

public class GameScreen extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8475241265061163966L;

	
	public static final int SCALE = 2;
	
	public GameScreen(KeyListener k) {
		setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		setFocusable(true);
		requestFocus();
		addKeyListener(k);
	}

	
	
}
