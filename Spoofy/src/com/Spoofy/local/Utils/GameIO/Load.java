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
	private boolean list = false;
	
	public Load(int size, String path){ 
		this.size = size;
		buffer = new byte[size];
		this.path = Paths.get(path);
		name = getFileName(path);
	}
	
	
	
	public void loadBuffer(){

		
		if(list){
			File folder = new File(path.toString());
			File[] files = folder.listFiles();
			
			for(File f : files){
				name = getFileName(f.getAbsolutePath());
				if(!name.contains("."))continue;
				if(name.contains(".map") || name.contains(".sav"))Utills.Tow_exe(f.getAbsolutePath(), true, ENCRYPTION_SIZE);
				readBytes(f.getAbsolutePath());
				BUFFER_STREAM.put(name, buffer);
				System.out.println(String.format("Loadded %s", name));
			}
			hasAdded = true;
			checkAndChange();
			return;
		}
		name =  getFileName(path.toString());
		if(name.contains(".map") || name.contains(".sav"))Utills.Tow_exe(path.toString(), true, ENCRYPTION_SIZE);
		readBytes(path.toString());
		BUFFER_STREAM.put(name, buffer);
		System.out.println(String.format("Added buffer %s into main stream.", name));
		hasAdded = true;
		checkAndChange();
		notify();
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
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
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
	
	public void setList(boolean b){
		list = b;
	}
	
	public boolean isList(){return list;}
	
	
}
