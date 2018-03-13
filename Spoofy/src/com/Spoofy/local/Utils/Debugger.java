package com.Spoofy.local.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.Spoofy.local.Handler;

public class Debugger extends Debug {

	private ArrayList<String> text;
	
	public static final int HOR = 0x01A;
	public static final int VER = 0x1A2;
	
	public static int MODE = VER;
	
	
	public Debugger(Handler handler) {
		super(handler);
	}

	@Override
	public void init() {
		text = new ArrayList<String>();
	}

	@Override
	public void tick(float delta) {
		
		
	}

	@Override
	public void draw(Graphics2D g) {
		for(int i = 0; i < text.size(); i++) {
			switch (MODE) {
			case HOR:
				
				break;
			case VER:
				g.setFont(new Font("sans", Font.PLAIN, 9));
				g.setColor(Color.white);
				g.drawString(text.get(i), 4, (i * 10) + 10);
				break;
				
			default:
				break;
			}
		}
		
	}

	@Override
	public void addDebugText(String... t) {
		for(String s : t) {
			text.add(s);
		}
		
	}
	
	public void upDateText(String oldText, String updtae) {
		int i = 0;
		for(String s : text) {
			if(s.contains(oldText)) {
				text.set(i, updtae);
			}
			i++;
		}
	}
	
	public Handler getHandler() {
		return handler;
	}
	
}
