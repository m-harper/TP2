package edu.mharper.tp2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class InfoView extends Canvas {
	
	public InfoView() {
		setPreferredSize(new Dimension(Main.windowWidth, Main.displayInfoSize));
	}
	
	public void paint(Graphics g) {
		drawBackground(g);
	}
	
	private void drawBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}