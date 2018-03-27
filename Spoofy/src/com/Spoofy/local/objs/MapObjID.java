package com.Spoofy.local.objs;

public class MapObjID {

	private char id;
	private GameObject obj;
	public MapObjID(char id, GameObject obj) {
		this.id = id;
		this.obj = obj;
	}
	
	
	public char getId() {
		return id;
	}
	public GameObject getObj() {
		return obj;
	}

}
