package edu.mharper.tp2;

import javax.swing.JOptionPane;

public class Main {
	
	

	public static String gameTitle = "Fanorona";
	public static int horizontalSpaces = 9;
	public static int verticalSpaces = 5;
	public static int tileSize = 100;
	public static int pieceSize = tileSize / 2;
	public static int displayInfoSize = 150;
	public static int windowHeight = Main.verticalSpaces * Main.tileSize;  
	public static int windowWidth = Main.horizontalSpaces * Main.tileSize; 
	
	public static void main(String[] args) {
		int choice = JOptionPane.showOptionDialog(null, "Start as server?", "Server setup", JOptionPane.YES_NO_OPTION, 1, null, null, null);
		switch (choice) {
		case 0:
			System.out.println("Started as server");
			break;
		case 1:
			System.out.println("Started as client");
			break;
		}
		View view = new View();
		//view.updatePieces(gb.getPieces());
	}

}
