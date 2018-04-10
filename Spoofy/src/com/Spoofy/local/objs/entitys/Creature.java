package com.Spoofy.local.objs.entitys;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Animation;
import com.Spoofy.local.Core.gfx.mapping.TileMap;

public class Creature extends Entity{

	public static final ArrayList<Creature> CREATURES = new ArrayList<Creature>();
	
	protected float health;
	protected float maxHealth;
	protected int power;
	protected boolean isAlive = true;
	
	public Creature(Handler handler,Animation ani,int x, int y, int width, int height, TileMap tm, Dimension collision) {
		super(handler,ani,x,y,width,height,tm,collision);
		CREATURES.add(this);
	}
	


	public void tick(double delta) {
		super.tick(delta);
		if(health <= 0) {
			setAlive(false);
		}
	}
	
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
	public float getHealth() {
		return health;
	}


	public void setHealth(float health) {
		this.health = health;
	}


	public boolean isAlive() {
		return isAlive;
	}


	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}



	public float getMaxHealth() {
		return maxHealth;
	}



	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}
	

}
