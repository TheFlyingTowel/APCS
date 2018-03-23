package com.Spoofy.local.States;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Core.gfx.Background;
import com.Spoofy.local.Core.gfx.mapping.TileMap;
import com.Spoofy.local.Utils.Vector2F;
import com.Spoofy.local.objs.entitys.Player;

public class GameState extends State{
	
	
	Player player;
	Background bg;
	Background cloud;
	TileMap tileMap;
	
	public GameState(Handler handler){
		super(handler);
	}

	@Override
	public void init() {
		bg = new Background(handler, "/Background/BKG01(no-cloud).png", 0.5);
		cloud = new Background(handler, "/Background/Clouds.png", 1.00005);
		bg.setPosition(new Vector2F());
		cloud.setPosition(new Vector2F());
		bg.setDirection(new Vector2F(-0.5,0));
		cloud.setDirection(new Vector2F(-0.5,0));
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tiles_Sets/TilesetV2.png");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(0.1);
		player = new Player(handler, 0, 0, 32, 32, tileMap, new Dimension(32, 72));
		player.init();
		tileMap.setTween(0.5);
	}

	@Override
	public void tick(float delta) {
		

		player.setUp(handler.getKeyInput().isUp());
		player.setJumping(handler.getKeyInput().isUp());
		player.setLeft(handler.getKeyInput().isLeft());
		player.setRight(handler.getKeyInput().isRight());
		player.tick(delta);		
		tileMap.setPosition((Game.WIDTH / 4) - player.getPosition().x,(Game.HEIGHT / 4) - player.getPosition().y);
		bg.tick();
		cloud.tick();
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		cloud.draw(g);
		tileMap.draw(g);
		player.draw(g);
		
	}
}
