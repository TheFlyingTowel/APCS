package com.Spoofy.local.Core.gfx.mapping;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Utils.Utills;
import com.Spoofy.local.Utils.Vector2F;

public class TileMap {

	private String name = "n/a";
	
	Vector2F position;
	Vector2F min;
	Vector2F max;
	
	private double tween;
	
	private int[][] map;
	private int tileSize, rows, cols, width, height,numTiles;
	
	private BufferedImage tileSet;
	private Tile[][] tiles;
	
	private int rowOffset, colOffset,rowsToDraw, colsToDraw;
	private char[] objSymbols = new char[]{'~','`','!','#','@','$','^','&'};
	
	
	
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		rowsToDraw = Game.HEIGHT / tileSize + 2;
		colsToDraw = Game.WIDTH / tileSize + 2;
		tween = 0.07;
		position = new Vector2F();
		min = new Vector2F();
		max = new Vector2F();
		
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
				
				tiles[0][col] = new Tile(subImage,Tile.NORMAL);
				subImage = tileSet.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
			
				tiles[1][col] = new Tile(subImage, Tile.BLOCKED);
			}
			
	}
	public void loadMap(String path) {
		try {
			
			InputStream in = getClass().getResourceAsStream(path);//Gets input data.
			BufferedReader br = new BufferedReader(new InputStreamReader(in));// Reads input data.
			
			cols = Integer.parseInt(br.readLine());
			rows = Integer.parseInt(br.readLine());
			
			map = new int[rows][cols];
			width = cols * tileSize;
			height = rows * tileSize;
			
			min.x = Game.WIDTH - width;
			min.y = Game.HEIGHT - height;
			max.x = 0;
			max.y = 0;
			
			for(int row = 0; row < rows; row++) {
				String line = br.readLine();
				String[] tokens = line.split("\\s+");
				for(int col = 0; col < cols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}catch(IOException e) {
			System.err.println("ERROR: "+e.getMessage());
		}
	}
	
	
	public void draw(Graphics2D g) {
		for(int row = rowOffset; row < rowOffset + rowsToDraw; row++) {
			if(row >= rows)break;
			
			for(int col = colOffset; col < colOffset + colsToDraw; col++) {
				if(col >= cols)break;
				
				if(map[row][col] == 0)continue;
				
				int rc = map[row][col];
				int r = rc / numTiles;
				int c = rc % numTiles;
				g.drawImage(tiles[r][c].getImage(), (int)position.x + col * tileSize, (int) position.y + row * tileSize, null);
			}
		}
	}
	
	
	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTiles;
		int c = rc % numTiles;
		return tiles[r][c].getType();
	}

	
	
	
	
	
	public void setPosition(double x, double y) {
		

		
		this.position.x += (x - position.x) * tween;
		this.position.y += (y - position.y) * tween;
		

		
		fixedBounds();
		
		colOffset = (int) -(this.position.x / tileSize);
		rowOffset = (int) -(this.position.y / tileSize);
	}
	
    private void fixedBounds(){
    	
        if(position.x < min.x) position.x = min.x;
        if(position.x > max.x) position.x = max.x;
        if(position.y < min.y) position.y = min.y;
        if(position.y > max.y) position.y = max.y;
    }
	
	
    
    
    
    
	public double getTween() {
		return tween;
	}


	public void setTween(double tween) {
		this.tween = tween;
	}


	public int getTileSize() {
		return tileSize;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public Vector2F getPosition() {
		return position;
	}
	
	public Vector2F getMin() {
		return min;
	}
	
	public Vector2F getMax() {
		return max;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
}
