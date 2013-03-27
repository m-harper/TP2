package edu.mharper.tp2;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class InfoView extends JPanel {
	
	JLabel timeRemaining;
	JPanel remainingPanel;
	JLabel whiteRemaining;
	JLabel blackRemaining;
	
	Timer timer;
	public long time;
	
	int white, black;
	
	public InfoView() {
		white = View.gameView.gameManager.countWhite();
		black = View.gameView.gameManager.countBlack();
		
		setPreferredSize(new Dimension(Main.windowWidth, Main.displayInfoSize));
		
		timeRemaining = new JLabel("" + time / 1000, JLabel.CENTER);
		timeRemaining.setPreferredSize(new Dimension(Main.windowWidth, 25));
		whiteRemaining = new JLabel("White remaining: " + white, JLabel.CENTER);
		blackRemaining = new JLabel("Black remaining: " + black, JLabel.CENTER);
		remainingPanel = new JPanel(new FlowLayout());
		
		time = Main.defaultTime;
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (time > 0) {
					time -= 1000;
					timeRemaining.setText("" + time / 1000);
				}
				else {
					resetTime();
				}
			}
		});
		timer.start();
		
		setLayout(new FlowLayout());
		remainingPanel.add(whiteRemaining);
		remainingPanel.add(blackRemaining);
		add(timeRemaining, BorderLayout.NORTH);
		add(remainingPanel, BorderLayout.SOUTH);
	}
	
	public void resetTime() {
		time = Main.defaultTime;
		timeRemaining.setText("" + time / 1000);
	}
	
	public void updateColors() {
		white = View.gameView.gameManager.countWhite();
		black = View.gameView.gameManager.countBlack();
		
		whiteRemaining.setText("White remaining: " + white);
		blackRemaining.setText("Black remaining: " + black);
	}
}