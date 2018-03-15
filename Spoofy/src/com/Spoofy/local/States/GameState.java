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
	TileMap tileMap;
	
	public GameState(Handler handler){
		super(handler);
	}

	@Override
	public void init() {
		bg = new Background(handler, "/Background/grassbg1.png", 0.5);
		bg.setPosition(new Vector2F());
		bg.setDirection(new Vector2F());
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tiles_Sets/grasstileset.png");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(0.1);
		player = new Player(handler, 64, 64, 68, 68, tileMap, new Dimension(68, 68));
		player.init();
	}

	@Override
	public void tick(float delta) {
		
		if(handler.getKeyInput().isUp())System.out.println("UP");
		
		bg.tick();		
		player.setUp(handler.getKeyInput().isUp());
		player.setJumping(handler.getKeyInput().isUp());
		player.setLeft(handler.getKeyInput().isLeft());
		player.setRight(handler.getKeyInput().isRight());
		player.tick(delta);
		tileMap.setPosition((Game.WIDTH / 6) - player.getPosition().x,(Game.HEIGHT / 6) - player.getPosition().y);
		
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		tileMap.draw(g);
		player.draw(g);
		
	}
}
