package com.Spoofy.local.Utils.GameIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.rmi.CORBA.Util;

import com.Spoofy.local.Utils.Utills;


public class Load extends IO{
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
		String[] tokens;
		try(BufferedReader br = Files.newBufferedReader(path,charset)){
			String line = "";
			int i = 0;
			int lastIndex = 0;
			while((line = br.readLine()) != null) {
				
				/*for(int n = lastIndex; n < line.length(); n++) {
					
					if(n < (line.length() + lastIndex)) {
						
					}
					
					lastIndex++;	
				}
				*/
				if(i < buffer.length) {
					tokens = line.split("\\s+");
					if(buffer.length < tokens.length) setSize(tokens.length);
					for(int j = 0; j < tokens.length; j++) {
						buffer[i] = tokens[j];
						i++;
					}
				}else {
					setSize(buffer.length + 1);
					tokens = line.split("\\s+");
					if(buffer.length < tokens.length) setSize(tokens.length);
					for(int j = 0; j < tokens.length; j++) {
						buffer[i] = tokens[j];
						i++;
					}
				}
				
				i++;
			}
		}catch(IOException e) {
			System.err.println(e);
		}finally {
			
			for(int n = 0; n < buffer.length; n++) {
				if(buffer[n] == null) {
					setSize(n);
					break;
				}
			}
			
			int hash = buffer.hashCode();
			IO.BUFFER_STREAM.add(buffer);
			
			STREAM_MAP.put("", hash);
			
			for(String[] b : BUFFER_STREAM) {
				if(b.hashCode() == hash) {
					hasAdded = true;
					System.out.println("Added buffer into main stream.");
					break;
				}
			}
			
			checkAndChange();
		}
	}
	
	
	public void setPath(String s){
		path = Paths.get(s);
	}
	public String getPath(){
		return path.toString();
	}
	
	public void setSize(int s){		
		int count  = Utills.ArrayItemCount(buffer);	
		if(count > s) {
			System.err.println(String.format("ERROR: Size: %s, is less than Item size: %s", s,count));
			return;
		}
		String[] tmpBuff = new String[size = s];	
		int len = (buffer.length < tmpBuff.length)? buffer.length : tmpBuff.length;
		for(int i = 0; i < len; i++){
			tmpBuff[i] = buffer[i];
		}
		buffer = tmpBuff.clone();
	}
	public int getSize(){
		return size;
	}
	
}
