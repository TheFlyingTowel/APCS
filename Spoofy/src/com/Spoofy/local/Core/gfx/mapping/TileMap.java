package com.Spoofy.local.Core.gfx.mapping;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Utils.Vector2F;

public class TileMap {

	Vector2F position;
	
	Vector2F min;
	Vector2F max;
	
	private double tween;
	
	private int[][] map;
	private int tileSize, rows, cols, width, height,numTiles;
	
	private BufferedImage tileSet;
	private Tile[][] tiles;
	
	private int rowOffset, colOffset,rowsToDraw, colsToDraw;
	
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		rowsToDraw = Game.WIDTH / tileSize;
		colsToDraw = Game.HEIGHT / tileSize;
		tween = 0.07;
	}
	
	
	public void loadTiles(String path) {
		
			try {
				tileSet = ImageIO.read(getClass().getResourceAsStream(path));
			} catch (IOException e) {
				System.err.println("ERROR: "+e.getMessage());
			}
			numTiles = tileSet.getWidth() / tileSize;
			tiles = new Tile[2][numTiles];
			
			BufferedImage subImage;
			for(int col = 0; col < numTiles; col++) {
				subImage = tileSet.getSubimage(col * tileSize, 0, tileSize, tileSize);
				
				//tiles[0][col] = new Tile()
			}
			
	}
	public void loadMap(String path) {}
	
}
