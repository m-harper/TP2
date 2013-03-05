package edu.mharper.tp2;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	
	public static int windowHeight = 600;
	public static int windowWidth = 800;
	public static String gameTitle = "Fanorona";

	public static void main(String[] args) {

		// Construct a new window
		JFrame frame = new JFrame(gameTitle);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(windowWidth, windowHeight));
		frame.pack();
		frame.setVisible(true);
	}

}
