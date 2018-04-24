package com.Spoofy.local.Utils.GameIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


import com.Spoofy.local.Utils.Utills;


public class Load extends IO{
	private byte[] buffer;
	private int size;
	private Path path;
	private String name;
	
	
	public Load(int size, String path){ 
		this.size = size;
		buffer = new byte[size];
		this.path = Paths.get(path);
		name = getFileName(path);
	}
	
	
	
	public void loadBuffer(){
		
		
		readBytes(path.toString());
		BUFFER_STREAM.put(name, buffer);
		System.out.println(String.format("Added buffer %s into main stream.", name));
		checkAndChange();
	}
	
	
	
	private void readBytes(String path) {
		FileInputStream fis = null;
		byte[] fileBuffer = null;
		
		try {
			File file = new File(path);
			fileBuffer = new byte[size = (int)file.length()];
			
			fis = new FileInputStream(file);
			fis.read(fileBuffer);
		}catch(IOException e) {
			System.err.println("ERROR: "+e);
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.err.println("ERROR: "+e);
					e.getLocalizedMessage();
				}
			}
		}
		
		buffer =  fileBuffer;
	}
	
	
	public void setPath(String s){
		path = Paths.get(s);
	}
	public String getPath(){
		return path.toString();
	}
	
	public String[] setStringBufferSize(int s, String[] b){		
		int count  = Utills.ArrayItemCount(b);	
		if(count > s) {
			System.err.println(String.format("ERROR: Size: %s, is less than Item size: %s", s,count));
			return null;
		}
		String[] tmpBuff = new String[size = s];	
		int len = (b.length < tmpBuff.length)? b.length : tmpBuff.length;
		for(int i = 0; i < len; i++){
			tmpBuff[i] = b[i];
		}
		b = tmpBuff.clone();
		
		return b;
	}
	public int getSize(){
		return size;
	}
	
	
	
}
