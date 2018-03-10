package com.Spoofy.local.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener{

	
	private boolean up, down, left, right, btn1, btn2;
	private boolean key[] = new boolean[256];
	
	int btn1KeyCode = KeyEvent.VK_Z;
	int btn2KeyCode = KeyEvent.VK_X;
	int keyCodes[] = new int[6];
	
	
	
	public KeyboardInput() 
	{
		keyCodes[0]  = KeyEvent.VK_UP;
		keyCodes[1]  = KeyEvent.VK_DOWN;
		keyCodes[2]  = KeyEvent.VK_LEFT;
		keyCodes[3]  = KeyEvent.VK_RIGHT;
		keyCodes[4]  = KeyEvent.VK_Z;
		keyCodes[5]  = KeyEvent.VK_X;
	}
	
	
	//Used for custom keys
	public KeyboardInput(int... keyCode) 
	{
		for(short i = 0; i < keyCode.length; i++) {
			keyCodes[i] = keyCode[i];
		}
	}
	
	public void tick() 
	{
		up = key[keyCodes[0]];
		down = key[keyCodes[1]];
		left = key[keyCodes[2]];
		right = key[keyCodes[3]];
		btn1 = key[keyCodes[4]];
		btn2 = key[keyCodes[5]];
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		key[e.getKeyCode()] = true;
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		key[e.getKeyCode()] = false;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isUp() {return up;}
	public boolean isDown() {return down;}
	public boolean isLeft() {return left;}
	public boolean isRight() {return right;}
	public boolean isBtn1() {return btn1;}
	public boolean isBtn2() {return btn2;}
	
	
	
	
}
