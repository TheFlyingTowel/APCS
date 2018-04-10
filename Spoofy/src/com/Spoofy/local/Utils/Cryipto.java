package com.Spoofy.local.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Cryipto {

	

		final boolean Encrypt = false;
		int timesToEncrypt = 1;
		
		public void start() throws IOException{
			//writeToFile(genKey(64), "E:\\users\\The Flying Towel\\Documents\\jPros\\ZipheTest\\data\\Main.key");
			if(Encrypt) {
				for(int i = 0; i < clampV2(timesToEncrypt,0,45); i++)
					TowCryipto(false);
			}else {
				for(int i = 0; i < clampV2(timesToEncrypt,0,45); i++)
					TowCryipto(true);
			}
			System.out.println("Done!");
		
		}
		
		
		

		static byte clamp(byte value ,int min,int max) {
			return((byte) Math.min(Math.max(value, min), Math.max(min, max)));
		}

		
		static int clampV2(int value ,int min,int max) {
			return(Math.min(Math.max(value, min), Math.max(min, max)));
		}
		
		
		static void writeToFile(String dat,String path) {
			try {
				Files.write(Paths.get(path),dat.getBytes());
				System.out.println("Wrote to File...");
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		static String genKey(int size) {
			String res = "";
			Random rand = new Random();
			
			for(int x = 0 ; x < size; x++) {
				for(int y = 0; y < size; y++){
					res += (char)(rand.nextInt(126)+32);
				}
				res+='\n';	
			}
			return res;
		}
		
		static void createRnadByte(int size) {
			byte[][] key = new byte[size][size];
			byte[] keyLine = new byte[size];
			new Random().nextBytes(keyLine);
			String k = "";
			for(int y = 0; y < size; y++) {
				for(int x = 0; x < size; x++) {
					key[x][y] = keyLine[x];
					k += (char)key[x][y];
				}
				new Random().nextBytes(keyLine);
			}
			
			
			
			
			System.out.println("KEY ->\n"+k);
			
				
			
			
		} 
		
		
		
		
		
		public void TowCryipto(boolean b) throws IOException {
			String tool = getClass().getResource("res/utills/Tow.exe").getPath();
			String key = getClass().getResource("res/libs/ZUZO").getPath();
			String readText = getClass().getResource("res/libs/Read").getPath();
			
			Process p = new ProcessBuilder(tool,key,readText,Integer.toString( (b) ? 1 : 0)).start();
			InputStream is = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			
			//System.out.println("Starting...");
			
			
			while((line = br.readLine()) != null || p.isAlive()) {
				System.out.println(line+"\n");
			}
			if((p.exitValue()) != 0) {
				System.out.printf("Ended loop... Exit value of: %s ",(int)p.exitValue());
				InputStreamReader error = new InputStreamReader(p.getErrorStream());
			
				br = new BufferedReader(error);
				String errorVal = "";
				while((line = br.readLine()) != null) {
					errorVal += line+"\n";
				}
				
				System.out.printf("ERORR: ERROR=>{\n %s \n}",errorVal);
				
			}
			
			//System.out.println("<TowCryipto>: Exit Value: "+p.exitValue());
		}
		
		
		
	
}
