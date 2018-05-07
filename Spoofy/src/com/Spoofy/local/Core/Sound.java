package com.Spoofy.local.Core;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.Spoofy.local.Utils.GameIO.IO;

public class Sound {

	public static boolean MUSIC = true;
	
	public static final double MAX_VOL = 0x2;
	public static final double MIX_VOL = 0x0;
	private byte[] buffer;
	private String key;
	private boolean playing = false;
	
	private static double volume = 0;
	private static Clip clip;
	private static boolean kill = false;
	public Sound(String key) {
		this.key = key;
		buffer = IO.BUFFER_STREAM.get(key);
		process();
	}
	
	
	public static synchronized void playSound(Sound sfx,double vol) {
		Thread thread = new Thread() {
			public void run() {
				try {
					AudioInputStream stream = sfx.getAIS();
					clip = AudioSystem.getClip();
					clip.open(stream);
					sfx.setPlaying(true);
					setVol(vol, clip);
					clip.start();
				
					do {
						
					
						
						sfx.setPlaying(clip.isActive());
						try{Thread.sleep(50);}catch(InterruptedException e){e.printStackTrace();}
						sfx.setPlaying(clip.isActive());
						
						if(!MUSIC) {
							clip.stop();
						}else if(MUSIC && !clip.isActive()) {
							clip.setFramePosition(0);
							clip.start();
						}
						
						if(kill)break;
						
					}while(true);
				} catch (IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			}
		};
		
		thread.setName(String.format("Sound: [%s]", sfx.getKey()));
		thread.start();
	}
	
	private static void setVol(double vol, Clip c) {
		volume = vol;
		FloatControl gain = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
		float db = (float) ((Math.log(vol) / Math.log(10)) * 20);
		gain.setValue(db);
	}
	
	public static void setVol(double vol) {
		int frame = clip.getFramePosition();
		clip.stop();
		setVol(vol, clip);
		clip.start();
		clip.setFramePosition(frame);
	}
	
	public static double getVol() {
		return volume;
	}
	
	private void process() {
		String current = (new File("")).getAbsolutePath()+"/res/libs/AudioStream";
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(current));
			out.write(buffer, 0, buffer.length);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public AudioInputStream getAIS() {
		try {
			String dat = new String(buffer);
			PrintWriter w = new PrintWriter((new File("")).getAbsolutePath()+"/res/libs/AudioStream");
			w.write(dat);
			w.close();
			return AudioSystem.getAudioInputStream(new File((new File("")).getAbsolutePath()+"/res/libs/AudioStream"));
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public String getKey() {
		return key;
	}
	
	public static void kill() {
		kill = true;
		if(clip.isActive())clip.close();
		hold();
		kill = false;
	}
	
	
	private static void hold() {
		try {
			Robot bot = new Robot();
			bot.delay(200);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
	public void setPlaying(boolean b) {
		playing = b;
	}
}
