package edu.mharper.tp2;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class InfoView extends JPanel {
	
	JLabel timeRemaining;
	Timer timer;
	public long time;
	
	public InfoView() {
		setPreferredSize(new Dimension(Main.windowWidth, Main.displayInfoSize));
		time = Main.defaultTime;
		timeRemaining = new JLabel("" + time / 1000);
		timer = new Timer(1000, new ActionListener() {
			
			@Override
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
		setLayout(new BorderLayout());
		add(timeRemaining, BorderLayout.NORTH);
	}
	
	public void resetTime() {
		time = Main.defaultTime;
		timeRemaining.setText("" + time / 1000);
	}
	
	/*public void paint(Graphics g) {
		drawBackground(g);
	}
	
	private void drawBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}*/
}