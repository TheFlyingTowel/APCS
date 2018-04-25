package com.Spoofy.local.States;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Core.gfx.Background;
import com.Spoofy.local.Core.gfx.mapping.TileMap;
import com.Spoofy.local.Utils.Vector2F;
import com.Spoofy.local.objs.KillZone;
import com.Spoofy.local.objs.Lava;
import com.Spoofy.local.objs.NullObj;
import com.Spoofy.local.objs.entitys.CheckPoint;
import com.Spoofy.local.objs.entitys.Player;

public class GameState extends State{
	
	
	Player player;
	Background bg;
	Background cloud;
	TileMap tileMap;
	KillZone killzone;
	public GameState(Handler handler){
		super(handler);
	}

	@Override
	public void init() {
		
		
		
		
		
		
		bg = new Background(handler, "BKG03.png", 0.5);
		cloud = new Background(handler, "Clouds.png", 1.00005);
		bg.setPosition(new Vector2F());
		cloud.setPosition(new Vector2F());
		bg.setDirection(new Vector2F(-0.2,0));
		cloud.setDirection(new Vector2F(-0.5,0));
		tileMap = new TileMap(handler,30);
		tileMap.setMapOBJS(CheckPoint.class,Lava.class,NullObj.class);
		tileMap.loadTiles("grasstileset.png");
		
		
		tileMap.loadMap("level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(0.01);
		

		
		player = new Player(handler, 64, 16, 52, 73, tileMap, new Dimension(32, 73));
		player.init();
		tileMap.setTween(0.5);
		killzone = new KillZone(handler, tileMap, 0, tileMap.getHeight(), new Dimension(tileMap.getWidth(), 32));
	}

	@Override
	public void tick(double delta) {
		
		player.setUp(handler.getKeyInput().isUp());
		player.setJumping(handler.getKeyInput().isUp());
		player.setLeft(handler.getKeyInput().isLeft());
		player.setRight(handler.getKeyInput().isRight());
		player.tick(delta);		
		tileMap.setPosition((Game.WIDTH / 4) - player.getPosition().x,(Game.HEIGHT / 4) - player.getPosition().y);
		tileMap.tick(delta);
		bg.tick();
		cloud.tick();
		//killzone.tick(delta);
		
		if(handler.getKeyInput().isLeft()) {
			bg.setDirection(new Vector2F(0.1,0));
		}else if (handler.getKeyInput().isRight()) {
			bg.setDirection(new Vector2F(-0.1, 0));
		}else {
			bg.setDirection(new Vector2F());
		}
		
		
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		cloud.draw(g);
		tileMap.draw(g);
		player.draw(g);
		
		
		
	}
}
