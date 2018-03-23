package com.Spoofy.local.objs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.mapping.TileMap;
import com.Spoofy.local.Utils.Vector2F;
import com.Spoofy.local.objs.entitys.Entity;

public class DamageBox extends GameObject{
	
	private int power = 0;
	private Entity entity;
	private boolean hasHit = false;
	
	
	public DamageBox(Handler handler,Entity e, TileMap tm,int power,int x, int y,int w, int h) {
		super(handler,tm);
		this.power = power;
		this.entity = e;
		this.position = new Vector2F(x, y);
		this.collision = new Dimension(w, h);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect((int)(((position.x) + mapPos.x - collision.width / 2)), (int)(((position.y) + mapPos.y - collision.height / 2)), collision.width, collision.height);
	}
	
	public void hitDetection() {
		hasHit = this.collisionWithOther(entity);
		if(hasHit) entity.setHealth(entity.getHealth() - power);
	}
	
	public boolean isHitting() {
		return hasHit;
	}
	
	
	

}
