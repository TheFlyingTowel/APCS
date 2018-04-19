package com.Spoofy.local.Utils.GameIO;

public class Load {
	private String[] buffer;
	private int size;
	private String path;
	
	public Load(int size){
		this.size = size;
		buffer = new String[size];
	}
	
	public Load(int size, String path){
		this.size = size;
		buffer = new String[size];
		this.path = path;
	}
	
	
	
	public void loadInFile(){
		
	}
	
	
	public void setPath(String s){
		path = s;
	}
	public String getPath(){
		return path;
	}
	
	public void setSize(int s){
		if(s < buffer.length){
			System.err.println(String.format("Size %s, is less than buffer size: %a",s,buffer.length));
			return;
		}
		
		String[] tmpBuff = new String[size = s];
		
		
		for(int i = 0; i < tmpBuff.length; i++){
			tmpBuff[i] = buffer[i];
		}
		buffer = tmpBuff.clone();
		
	}
	public int getSize(){
		return size;
	}
	
}
