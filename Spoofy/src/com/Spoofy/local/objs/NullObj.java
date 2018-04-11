package com.Spoofy.local.objs;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.mapping.TileMap;

public class NullObj extends GameObject{

	public NullObj(Handler handler, TileMap tm) {
		super(handler, tm);
		collision = new Dimension(0, 0);
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
}
