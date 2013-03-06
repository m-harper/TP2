package edu.mharper.tp2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class View extends Canvas {
	
	JFrame frame;
	
	public View() {
		frame = new JFrame(Main.gameTitle);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(Main.windowWidth, Main.windowHeight));
		// Add the canvas
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void paint(Graphics g) {
		drawBackground(g);
		drawSpaces(g);
	}
	
	private void drawBackground(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, Main.windowWidth, Main.windowHeight);
	}
	
	private void drawSpaces(Graphics g) {
		int spaceWidth = 50;
		double horizontalSpacing = Main.windowWidth / Main.horizontalSpaces;
		double verticalSpacing = Main.windowHeight / Main.verticalSpaces;
		
		g.setColor(Color.white);
		for (int i = 0; i < Main.horizontalSpaces; i++) {
			for (int j = 0; j < Main.verticalSpaces; j++) {
				g.fillOval((int) (i * horizontalSpacing + spaceWidth / 2), (int) (j * verticalSpacing + spaceWidth / 2), spaceWidth, spaceWidth);
			}
		}
	}
	
	
}
