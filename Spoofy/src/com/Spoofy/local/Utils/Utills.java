package com.Spoofy.local.Utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.imageio.ImageIO;

public abstract class Utills {
	
	//Image loader <<no longer used> only use if IO stream has failed>
	public static BufferedImage imageLoader(String path){
		try{
			if((new File(path)).exists()) {
				System.out.println("File is there.");
			}else {
				System.out.println("File is not there.");
			}
			
			
			return ImageIO.read(new File(path));
		}catch(IOException e){
			System.exit(1);
		}
		
		return null;
	}
	
	
	
	//Array counter
	public static int ArrayItemCount(Object[] a) {
		int n = 0;
		for(int i = 0; i < a.length; i++) {
			if(a[i] != null)n++;
		}
		
		return n;
	}
	
	//clamps
	public static float clamp(float value, float min ,float max){
		return(Math.min(Math.max(value, min), Math.max(min, max)));
	}

	public static int clamp(int value, int min ,int max){
		return(Math.min(Math.max(value, min), Math.max(min, max)));
	}

	public static long clamp(long value, long min ,long max){
		return(Math.min(Math.max(value, min), Math.max(min, max)));
	}

	public static double clamp(double value, double min ,double max){
		return(Math.min(Math.max(value, min), Math.max(min, max)));
	}

	public static String genKey(int size) {
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
	
	/* Note: to decrypt  the size must be the same as its encryption, if
	 * if the sizes are different on either calls it WILL corrupt the file's 
	 * data, and will be almost impossible to repair.
	 * 
	 *  Never encrypt or decrypt a file more than once, if you chooses to do
	 *  so, have it at your own risk.   
	 *  */  
	public static void Tow_exe(String path, boolean a, int size) {
		String key = (new File("")).getAbsolutePath() + "\\res\\utills\\zip.key";
		String buffer = path;
		if(a) {
			for(int i = 0 ; i < clamp(size, 0, 10); i++) {
				executeTow_exe(key, buffer, a);
			}
		}else {
			for(int i = 0 ; i < clamp(size, 0, 10); i++) {
				executeTow_exe(key, buffer, a);
			}
		}
	}
	
	//Method is used to start Tow.exe
	private static void executeTow_exe(String key,String buffer, boolean a) {
		try {
			Process tow = new ProcessBuilder((new File("")).getAbsolutePath() + "\\res\\utills\\Tow.exe",key, buffer, Integer.toString( (a) ? 1 : 0)).start();
			InputStream is = tow.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
				

					String line;
					try {
						while((line = br.readLine()) != null || tow.isAlive()) {
							System.out.print("[Tow.exe]: "+line+"\n");
						}
						
						if(tow.exitValue() != 0) {
							InputStreamReader error = new InputStreamReader(tow.getErrorStream());
							br = new BufferedReader(error);
							String errorVal = "";
							while((line = br.readLine()) != null || tow.isAlive()) {
								errorVal += line+"\n";
							}
							System.err.println("[Tow.exe]: "+errorVal);
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					System.out.println("Tow.exe done, exit_value = "+tow.exitValue());
				

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
