package com.Spoofy.local.objs;

import com.Spoofy.local.Utils.Vector2F;

public class MapObjID {

	private char id;
	private GameObject obj;
	private Vector2F location;
	public MapObjID(char id, GameObject obj) {
		this.id = id;
		this.obj = obj;
		location = new Vector2F();
	}
	
	public void setLocation(int x, int y) {
		this.location.setVector(x, y);
	}
	
	public Vector2F getLocation() {
		return location;
	}
	
	public char getId() {
		return id;
	}
	public GameObject getObj() {
		return obj;
	}

}
