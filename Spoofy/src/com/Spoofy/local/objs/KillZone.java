package com.Spoofy.local.objs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.mapping.TileMap;
import com.Spoofy.local.Utils.Vector2F;
import com.Spoofy.local.objs.entitys.Creature;

public class KillZone extends GameObject{

	public KillZone(Handler handler, TileMap tm, int x, int y, Dimension size) {
		super(handler,tm);
		this.position = new Vector2F(x,y);
		this.collision = size;
		dimention = collision;
	}
	
	public void tick(double delta) {
		super.tick(delta);
		for(Creature c : Creature.CREATURES) {
			if(this.collisionWithOther(c)) {
				c.setAlive(false);
			}
		}
	}
	

	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.drawString("{KillZone}isOnScreen() = "+(!isOFFscreen()), 8, 200);
		if(!isOFFscreen()) {
			g.setColor(Color.WHITE);
			//g.fillRect((int)(position.x ), (int)(position.y ), collision.width, collision.height);
			g.fillRect((int)((position.x + mapPos.x - collision.width / 2)), (int)((position.y + mapPos.y - collision.height / 2)), collision.width, collision.height);
		}
	}
	
}
