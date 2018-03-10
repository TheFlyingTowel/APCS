package com.Spoofy.local.Core.gfx;

import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.Spoofy.local.Core.Game;

public class Display extends JFrame{

	GameScreen screen;
	
	public Display(String title)
	{
		super(title);
		createDisplay();
	}
	
	void createDisplay()
	{
		setSize(new Dimension(Game.WIDTH,Game.HEIGHT));
		setMinimumSize(new Dimension(Game.dimention));
		setMaximumSize(Game.dimention);
		setPreferredSize(Game.dimention);
		screen = new GameScreen(null);
		add(screen);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}
	
	public GameScreen getScreen() {
		return screen;
	}
	
	public void setKeyListener(KeyListener l) {
		screen.addKeyListener(l);
	}
}
