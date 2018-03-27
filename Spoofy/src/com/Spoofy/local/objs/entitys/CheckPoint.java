package com.Spoofy.local.objs.entitys;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Sprite;
import com.Spoofy.local.Core.gfx.mapping.TileMap;
import com.Spoofy.local.Utils.Vector2F;
import com.Spoofy.local.objs.GameObject;

public final class CheckPoint extends GameObject{

	public CheckPoint(Handler handler, Sprite spr, int x, int y, int width, int height, TileMap tm,
			Dimension collision) {
		super(handler, spr, x, y, width, height, tm);
		this.collision = collision;
		
	}

	public void tick(double delta) {
		super.tick(delta);
		for(Creature c : Creature.CREATURES) {
			if(c.getClass() == Player.class) {
				if(collisionWithOther(c)) {
					Player p = (Player) c;
					p.setLastPoint(new Vector2F(position.x - (collision.width / 2),position.y - (collision.height / 2)));
				}
			}
		}
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(sprite.getImage(), (int) (position.x + mapPos.x - dimention.width / 2), (int) (position.y + mapPos.y - dimention.height / 2), null);
	}
	


}
