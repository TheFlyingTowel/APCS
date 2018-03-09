package com.Spoofy.local.Core.gfx;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.Spoofy.local.Core.Game;

public class Display extends JFrame{

	
	public Display(String title)
	{
		super(title);
	}
	
	void createDisplay()
	{
		setSize(new Dimension(Game.WIDTH,Game.HEIGHT));
		setMinimumSize(new Dimension(Game.dimention));
		setMaximumSize(Game.dimention);
		
		
	}
	
}
