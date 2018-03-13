package com.Spoofy.local.Utils;

public class Vector2F {

	public static double worldX, worldY;
	public double x = 0,y = 0;
	
	public Vector2F(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2F()
	{
		
	}
	
	public Vector2F zero()
	{
		return new Vector2F();
	}
	
	public Vector2F add(Vector2F v)
	{
		double  nx = this.x += v.x;
		double  ny = this.y += v.y;
		return new Vector2F(nx ,ny);
	}
	
	public Vector2F sub(Vector2F v)
	{
		double nx = this.x -= v.x;
		double ny = this.y -= v.y;
		return new Vector2F(nx ,ny);

	}
	
	public Vector2F div(Vector2F v)
	{
		double nx = this.x /= v.x;
		double ny = this.y /= v.y;
		return new Vector2F(nx ,ny);
	}
	
	public Vector2F mod(Vector2F v) 
	{
		double nx = this.x %= v.x;
		double ny = this.y %= v.y;
		return new Vector2F(nx ,ny);
	}
	
	
	public Vector2F multi(Vector2F v) 
	{
		double nx = this.x *= v.x;
		double ny = this.y *= v.y;
		return new Vector2F(nx ,ny);
	}
	
	public Vector2F multi(double n) 
	{
		return new Vector2F(this.x *= n, this .y *= n);
	}
	
	
	public Vector2F copy(Vector2F v) 
	{
		double nx = v.x;
		double ny = v.y;
		return new Vector2F(nx ,ny);
	}
	
	public boolean equal(Vector2F v) 
	{
		return (this.x == v.x && this.y == v.y);
	}
	
	public Vector2F negate() {
		double nx = x = x * (-1);
		double ny = y = y * (-1);
		return new Vector2F(nx, ny);
	}
	
	public double vecMag() 
	{
		return (Math.sqrt((x * x) + (y * y)));
	}
	
	public Vector2F getPosOnscreen() 
	{
		return new Vector2F(x - worldX, y - worldY);
	}
	
	public double getDistanceBetweenVectors(Vector2F v) 
	{
		double nx = x - v.x;
		double ny = y - v.y;
		return Math.sqrt((nx * nx) + (ny * ny));
	}
	
	public void vecLimit(double n){
		if(n > 0){
			if(this.x > n){
				this.x = n;
			}
			if(this.y > n){
				this.y = n;
			}
		}else if(n < 0){
			if(this.x < n){
				this.x = n;
			}
			if(this.y < n){
				this.y = n;
			}
		}	
	}	
}
