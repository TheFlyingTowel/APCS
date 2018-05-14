package com.Spoofy.local.GL.gfx;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class GLWindow extends JFrame implements GLEventListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8678493360591366043L;

	int width = 600;
	int height = 400;
	
	public GLWindow() {
		super("GL");
		GLProfile profile = GLProfile.get(GLProfile.GL4);
		GLCapabilities capabilitites = new GLCapabilities(profile);
		
		GLCanvas glCanvas = new GLCanvas(capabilitites);
		glCanvas.addGLEventListener(this);
		
		this.getContentPane().add(glCanvas);
		this.setSize(new Dimension(width, height));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		glCanvas.requestFocusInWindow();
		
		
	}
	
	public void play() {
		
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL4 gl = drawable.getGL().getGL4();
		gl.glClear(GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT);
		
        gl.glClearColor(0.392f, 0.584f, 0.929f, 1.0f);
		
		gl.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}

}
