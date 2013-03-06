package edu.mharper.tp2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JFrame;

public class View extends Canvas implements ActionListener {
	
	JFrame frame;
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem newGameMenuItem;
	JMenuItem exitMenuItem;
	
	public View() {
		frame = new JFrame(Main.gameTitle);
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		newGameMenuItem = new JMenuItem("New game");
		exitMenuItem = new JMenuItem("Exit");
		
		// Set up window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(Main.windowWidth, Main.windowHeight));
		
		// Set up menu
		newGameMenuItem.addActionListener(this);
		exitMenuItem.addActionListener(this);
		fileMenu.add(newGameMenuItem);
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		
		// Add the items to the window
		frame.setJMenuBar(menuBar);
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
				g.fillOval((int) (i * horizontalSpacing + spaceWidth / 2 - 15), (int) (j * verticalSpacing + spaceWidth / 2 - 15), spaceWidth, spaceWidth);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("New game")) {
			// New game
			// Tell the game logic to reset the board
			this.update(getGraphics());
		}
		else if (event.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
		
	}
	
	
}
