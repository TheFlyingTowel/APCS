package com.Spoofy.local.Utils;

public class Vectors2F {

	
	double x = 0,y = 0;
	
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
		return new Vectors2F(nx,ny);
	}
	
	public Vectors2F sub(Vectors2F v)
	{
		double nx = this.x -= v.x;
		double ny = this.y -= v.y;
		return new Vectors2F(nx,ny);

	}
	
	public Vectors2F div(Vectors2F v)
	{
		double nx = this.x /= v.x;
		double ny = this.y /= v.y;
		return new Vectors2F(nx,ny);
	}
	
	
	

	
	
	
	
}
