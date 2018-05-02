package com.Spoofy.local;


import java.io.File;

import com.Spoofy.local.Core.Game;
import com.Spoofy.local.Utils.Utills;
import com.Spoofy.local.Utils.GameIO.IO;



public class Main {
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		
		/*System.out.println((new File("")).getAbsolutePath());
		File folder  = new File((new File("")).getAbsolutePath() + "\\res\\Maps\\");
		File[] files = folder.listFiles();
		
		
		for(File f : files) {
			Utills.Tow_exe(f.getAbsolutePath(), false, IO.ENCRYPTION_SIZE);
		}*/
		
		
	}
	
}
