package com.Spoofy.local.objs.entitys;

import java.awt.Dimension;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Sprite;
import com.Spoofy.local.Core.gfx.mapping.TileMap;

public class SpawnPosition extends Entity{

	public static int sposx;
	public static int sposy;
	
	public SpawnPosition(Handler handler, int x, int y, int width, int height, TileMap tm,
			Dimension collision) {
		super(handler, new Sprite("Null.png"), x, y, width, height, tm, collision);
		
	}


	public void tick(double delta) {
		super.tick(delta);
	}

	public void setPosition(double x, double y) {
		super.setPosition(x, y);
		sposx  = (int) x;
		sposy = (int) y;
	}
	
}
