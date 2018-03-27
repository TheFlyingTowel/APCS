package com.Spoofy.local.objs.entitys;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Animation;
import com.Spoofy.local.Core.gfx.mapping.TileMap;
import com.Spoofy.local.objs.DamageBox;

public class Enemy extends Creature{

	Player player;
	DamageBox attack;
	
	public Enemy(Handler handler, Animation animation, int x, int y, int width, int height, TileMap tm, Dimension collision, Player player) {
		super(handler, animation, x, y, width, height, tm, collision);
		this.player = player;
		power = 0;
	}
	
	public void tick(double delta) {
		super.tick(delta);
		
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
	
}
