package com.Spoofy.local.Utils.GameIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Load {
	private String[] buffer;
	private int size;
	private Path path;
	
	public Load(int size){
		this.size = size;
		buffer = new String[size];
	}
	
	public Load(int size, String path){ 
		this.size = size;
		buffer = new String[size];
		this.path = Paths.get(path);
	}
	
	
	
	public void loadInFile(){
		Charset charset = Charset.forName("US-ASCII");
		
		try(BufferedReader br = Files.newBufferedReader(path,charset)){
			String line = "";
			int i = 0;
			int lastIndex = 0;
			while((line = br.readLine()) != null) {
				
				for(int n = lastIndex; n < line.length(); n++) {
					
					if(n < (line.length() + lastIndex)) {
						
					}
					
					lastIndex++;	
				}
				
				if(i < buffer.length) {
					buffer[i] = line;
				}else {
					setSize(buffer.length + 1);
					buffer[i] = line;
				}
				i++;
			}
		}catch(IOException e) {
			System.err.println(e);
		}finally {
			IO.BUFFER_STREAM.add(buffer);
			
			System.out.println("Added buffer to main stream.");
		}
	}
	
	
	public void setPath(String s){
		path = Paths.get(s);
	}
	public String getPath(){
		return path.toString();
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
