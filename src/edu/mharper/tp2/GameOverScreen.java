package edu.mharper.tp2;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameOverScreen {
	
	JFrame frame;
	
	public GameOverScreen() {
		frame = new JFrame("Game over");
		frame.setPreferredSize(new Dimension(400, 300));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}