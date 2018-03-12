package com.Spoofy.local.Utils;

public class Vectors2F {

	public static double worldX, worldY;
	public double x = 0,y = 0;
	
	public Vectors2F(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vectors2F()
	{
		
	}
	
	public Vectors2F zero()
	{
		return new Vectors2F();
	}
	
	public Vectors2F add(Vectors2F v)
	{
		double  nx = this.x += v.x;
		double  ny = this.y += v.y;
		return new Vectors2F(nx ,ny);
	}
	
	public Vectors2F sub(Vectors2F v)
	{
		double nx = this.x -= v.x;
		double ny = this.y -= v.y;
		return new Vectors2F(nx ,ny);

	}
	
	public Vectors2F div(Vectors2F v)
	{
		double nx = this.x /= v.x;
		double ny = this.y /= v.y;
		return new Vectors2F(nx ,ny);
	}
	
	public Vectors2F mod(Vectors2F v) 
	{
		double nx = this.x %= v.x;
		double ny = this.y %= v.y;
		return new Vectors2F(nx ,ny);
	}
	
	
	public Vectors2F multi(Vectors2F v) 
	{
		double nx = this.x *= v.x;
		double ny = this.y *= v.y;
		return new Vectors2F(nx ,ny);
	}
	
	public Vectors2F multi(double n) 
	{
		return new Vectors2F(this.x *= n, this .y *= n);
	}
	
	
	public Vectors2F copy(Vectors2F v) 
	{
		double nx = v.x;
		double ny = v.y;
		return new Vectors2F(nx ,ny);
	}
	
	public boolean equal(Vectors2F v) 
	{
		return (this.x == v.x && this.y == v.y);
	}
	
	public Vectors2F negate() {
		double nx = x = x * (-1);
		double ny = y = y * (-1);
		return new Vectors2F(nx, ny);
	}
	
	public double vecMag() 
	{
		return (Math.sqrt((x * x) + (y * y)));
	}
	
	public Vectors2F getPosOnscreen() 
	{
		return new Vectors2F(x - worldX, y - worldY);
	}
	
	public double getDistanceBetweenVectors(Vectors2F v) 
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
