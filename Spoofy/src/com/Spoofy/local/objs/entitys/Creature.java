package com.Spoofy.local.objs.entitys;

import java.awt.Dimension;
import java.util.ArrayList;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Animation;
import com.Spoofy.local.Core.gfx.mapping.TileMap;

public class Creature extends Entity{

	public static final ArrayList<Creature> CREATURES = new ArrayList<Creature>();
	
	protected int health;
	protected int power;
	protected boolean isAlive = true;
	
	public Creature(Handler handler,Animation ani,int x, int y, int width, int height, TileMap tm, Dimension collision) {
		super(handler,ani,x,y,width,height,tm,collision);
		CREATURES.add(this);
	}
	


	public void tick(double delta) {
		super.tick(delta);
	}
	
	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public boolean isAlive() {
		return isAlive;
	}


	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	

}
