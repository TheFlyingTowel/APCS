package com.Spoofy.local;

import com.Spoofy.local.Core.Game;


public class Main {
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		
		/*System.out.println((new File("")).getAbsolutePath());
		File folder  = new File((new File("")).getAbsolutePath() + "\\res\\Maps\\");
		File[] files = folder.listFiles();
		
		
		for(File f : files) {
			Utills.Tow_exe(f.getAbsolutePath(), false, IO.ENCRYPTION_SIZE);
		}
		*/
		
	}
	
}
