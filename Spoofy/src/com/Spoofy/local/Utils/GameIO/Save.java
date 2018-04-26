package com.Spoofy.local.Utils.GameIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.Spoofy.local.Utils.Utills;

public class Save extends IO{
	private byte[] buffer;
	private int size;
	private Path path;
	private String name;
	
	public Save(byte[] buffer, String path) {
		this.buffer = buffer;
		size = buffer.length;
		this.path = Paths.get(path);
		name = getFileName(path);
	}
	
	public void saveBuffer() {
		saveBytes();
		
		if(BUFFER_STREAM.containsKey(name)){
			BUFFER_STREAM.replace(name, buffer);
		}else{
			BUFFER_STREAM.put(name, buffer);
		}
		if(BUFFER_STREAM.containsKey(name)) hasAdded = true;
		else saveBuffer();
		
		checkAndChange();
	}
	
	private void saveBytes() {
		FileOutputStream fos = null;
		File file = null;
		
		try {	
			file = new File(path.toString());
			fos = new FileOutputStream(file);
			
			
			
			if(!file.exists())file.createNewFile();
			fos.write(buffer);
			fos.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
				try {
					if(fos != null) {
						fos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				//Encrypt buffer
				Utills.Tow_exe(path.toString(), false, ENCRYPTION_SIZE);
			
		}
		
	}

	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
}
