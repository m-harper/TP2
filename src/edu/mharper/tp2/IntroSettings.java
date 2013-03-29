package edu.mharper.tp2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class IntroSettings extends JFrame implements ActionListener {
	
	public static int settingsWidth = 400;
	public static int settingsHeight = 300;
	
	View view;
	
	public IntroSettings() {
		setPreferredSize(new Dimension(settingsWidth, settingsHeight));
		
		addComponents();
		
		pack();
		setVisible(true);
	}
	
	private void addComponents() {
		
		JPanel one = new JPanel(new BorderLayout());
		JLabel horizontalLabel = new JLabel("Horizontal spaces", JLabel.CENTER);
		one.add(horizontalLabel, BorderLayout.EAST);
		horizontalLabel.setPreferredSize(new Dimension(settingsWidth / 2, settingsHeight / 3 - 5));
		
		JTextField horizontal = new JTextField("" + Main.horizontalSpaces);
		horizontal.setHorizontalAlignment(JTextField.CENTER);
		horizontal.setPreferredSize(new Dimension(settingsWidth / 2, settingsHeight / 3 - 5));
		one.add(horizontal, BorderLayout.WEST);
		
		JPanel two = new JPanel(new BorderLayout());
		JLabel verticalLabel = new JLabel("Vertical", JLabel.CENTER);
		two.add(verticalLabel, BorderLayout.EAST);
		verticalLabel.setPreferredSize(new Dimension(settingsWidth / 2, settingsHeight / 3 - 5));
		
		JTextField vertical = new JTextField("" + Main.verticalSpaces);
		vertical.setHorizontalAlignment(JTextField.CENTER);
		vertical.setPreferredSize(new Dimension(settingsWidth / 2, settingsHeight / 6 - 5));
		two.add(vertical, BorderLayout.WEST);
		
		JPanel three = new JPanel(new BorderLayout());
		JLabel timeLabel = new JLabel("Maximum turn time", JLabel.CENTER);
		three.add(timeLabel, BorderLayout.EAST);
		timeLabel.setPreferredSize(new Dimension(settingsWidth / 2, settingsHeight / 3 - 5));
		
		JTextField time = new JTextField("" + Main.defaultTime / 1000);
		time.setHorizontalAlignment(JTextField.CENTER);
//		time.setPreferredSize(new Dimension(100, 30));
//		time.setMaximumSize(new Dimension(100, 30));
		time.setPreferredSize(new Dimension(settingsWidth / 2, settingsHeight / 3 - 5));
		three.add(time, BorderLayout.WEST);
		
		JPanel buttonPanel = new JPanel(new BorderLayout());		
		JButton button = new JButton("Play game!");
		buttonPanel.add(button);
		
		add(one, BorderLayout.NORTH);
		add(two, BorderLayout.CENTER);
		add(three, BorderLayout.SOUTH);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}