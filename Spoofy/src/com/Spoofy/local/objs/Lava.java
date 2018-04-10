package com.Spoofy.local.objs;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.gfx.Animation;
import com.Spoofy.local.Core.gfx.mapping.TileMap;
import com.Spoofy.local.objs.entitys.Creature;

public class Lava extends GameObject{


	
	public Lava(Handler handler, Animation animation, int x, int y, int width, int height, TileMap tm) {
		super(handler, animation, x, y, width, height, tm);
		collision = new Dimension(width, height);
		

	}

	public void tick(double delta) {
		super.tick(delta);
		animation.tick();
		for(Creature c : Creature.CREATURES) {
			if(this.collisionWithOther(c)) {
				c.setHealth(c.getHealth() - .009f);
			}
		}
		

		
	}

	
	public void draw(Graphics2D g) {
		if(!isOFFscreen()) g.drawImage(animation.getCurrentFrame().getImage(), (int)(position.x + mapPos.x),(int) (position.y + mapPos.y ),(int) collision.width,(int) collision.height,  null);
	}
	
	public Lava copy(Lava l) {
		return new Lava(l.getHandler(), l.getAnimation(),((int) (l.position.x)),((int)( l.position.y)),(int) l.collision.getWidth(), (int)l.collision.getHeight(), l.tm);
	}

	
}
