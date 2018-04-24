package com.Spoofy.local.Core.gfx.mapping;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.rmi.CORBA.Util;

import com.Spoofy.local.Handler;
import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Core.gfx.Animation;
import com.Spoofy.local.Core.gfx.Assets;
import com.Spoofy.local.Core.gfx.Sprite;
import com.Spoofy.local.Utils.Vector2F;
import com.Spoofy.local.Utils.GameIO.IO;
import com.Spoofy.local.objs.GameObject;
import com.Spoofy.local.objs.KillZone;
import com.Spoofy.local.objs.Lava;
import com.Spoofy.local.objs.NullObj;
import com.Spoofy.local.objs.entitys.CheckPoint;

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
	
	protected IO io;
	protected Handler handler;
	
	private int rowOffset, colOffset,rowsToDraw, colsToDraw;
	private char[] objSymbols = new char[]{'A','a','B','b','C','c','D','d','E','e','F','f','G','g','H','h','I','i','J','j',
										   'K','k','L','l','M','m','N','n','O','o','P','p','Q','q','R','r','S','s','T','t'
										   ,'U','u','V','v','W','w','X','x','Y','y','Z','z','~','`','!','#','@','$','^','&',
										   '(',')','-','+','='};
	private Class<?>[] levelObjs;
	private ArrayList<GameObject> OBJS = new ArrayList<GameObject>();
	
	public TileMap(Handler handler,int tileSize) {
		this.tileSize = tileSize;
		rowsToDraw = Game.HEIGHT / tileSize + 2;
		colsToDraw = Game.WIDTH / tileSize + 2;
		tween = 0.07;
		position = new Vector2F();
		min = new Vector2F();
		max = new Vector2F();
		this.handler = handler;
		io = handler.getIO();
	}
	
	
	public void loadTiles(String path) {
		
			try {
				tileSet = ImageIO.read(getClass().getResourceAsStream(path));
			} catch (IOException e) {
				System.err.println("ERROR: "+e.getMessage());
			}
			//io.load(path, false);
			
			
			
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
			String lastLine = "";
			for(int row = 0; row < rows; row++) {
				String line = br.readLine();
				String[] tokens = line.split("\\s+");
				String[] lastTokens = lastLine.split("\\s+");
				for(int col = 0; col < cols; col++) {
					
					for(int i = 0; i < objSymbols.length; i++) { 
						if(tokens[col].contentEquals(Character.toString(objSymbols[i]))) {
							
							if(((levelObjs != null) && ((levelObjs.length) > 0))) {
								
								GameObject obj = null;
								try {
									if(levelObjs[i] == CheckPoint.class) {
										obj = new CheckPoint(null, null, 0, 0, tileSize, tileSize, this);
									}else if (levelObjs[i] == KillZone.class) {
										obj = new KillZone(null, this, 0, 0, new Dimension(tileSize, tileSize));
									}else if (levelObjs[i] == Lava.class) {
										Sprite[] lava;
										if(!lastTokens[col].contentEquals(Character.toString(objSymbols[i]))){
											lava = new Sprite[Assets.lavaTop.length];
											for(int z = 0; z < Assets.lavaTop.length; z++)lava[z] = new Sprite(Assets.lavaTop[z]);
										}else {
											lava = new Sprite[Assets.lava.length];
											for(int z = 0; z < Assets.lava.length; z++)lava[z] = new Sprite(Assets.lava[z]);
										}
										
										obj  = new Lava(null, new Animation(lava, 150), 0, 0, 32, 32, this);
									}//Add GameObject classes here
									
								}catch(Exception e) {
									e.printStackTrace();
									System.exit(1);
								}
		
								if(obj!= null) {
									obj.setMapPosition();
									obj.setPosition(( position.x + col * tileSize),(position.y + row * tileSize));	
									OBJS.add(obj);
								}
							}
							tokens[col] = "0";
						}
						
					}
					map[row][col] = Integer.parseInt(tokens[col]);
				}
				lastLine = line;
			}
			
		}catch(IOException e) {
			System.err.println("ERROR: "+e.getMessage());
		}
	}
	

	public void tick(double delta) {
		for(GameObject m : OBJS) {
			m.tick(delta);
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
				
				int posX = (int)position.x + col * tileSize;
				int posY = (int) position.y + row * tileSize;
				
				if(isItemOnScreen(posX, posY, tileSize, tileSize))g.drawImage(tiles[r][c].getImage(), posX, posY, null);
			}
		}
		
		
		for(GameObject m : OBJS) {
			m.draw(g);
		}
		
	}
	
	
	public boolean isItemOnScreen(int x, int y, int width, int height) {
		return (x + width) > -1 && (y + height) > -1 &&
				(x + width) < Game.WIDTH - (Game.WIDTH / 2) + 225 && (y + height) < Game.HEIGHT - (Game.HEIGHT / 2) + 225;
	}
	
	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTiles;
		int c = rc % numTiles;
		return tiles[r][c].getType();
	}

	
	
	
	
	
	public void setPosition(double x, double y) {
		this.position.x += ((x - position.x) * tween);
		this.position.y += ((y - position.y) * tween);
		

		
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
	
	
    
    
    
    
    public void setMapOBJS(Class<?>...gameObjects ) {
    	levelObjs = new Class<?>[gameObjects.length];
    	for(int i = 0; i < gameObjects.length; i++) {
    		levelObjs[i] = gameObjects[i];
    	}
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
