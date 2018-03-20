package com.Spoofy.local.Core.gfx;

import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.Spoofy.local.Core.Game;

public class Display extends JFrame{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8437664347489886827L;
	GameScreen screen;
	KeyListener kl;
	public Display(String title, KeyListener k)
	{
		super(title);
		kl = k;
		createDisplay();
	}
	
	void createDisplay()
	{
		setSize(new Dimension(Game.WIDTH,Game.HEIGHT));
		setMinimumSize(new Dimension(Game.dimention));
		setMaximumSize(Game.dimention);
		setPreferredSize(Game.dimention);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		screen = new GameScreen(kl);
		setContentPane(screen);
		addKeyListener(kl);
		screen.addKeyListener(kl);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		screen.requestFocus();
		pack();
		
	}
	
	public GameScreen getScreen() {
		return screen;
	}
	
	public void setKeyListener(KeyListener l) {
		screen.addKeyListener(l);
	}
}
