package edu.mharper.tp2;

public class Main {
	
	

	public static String gameTitle = "Fanorona";
	public static int horizontalSpaces = 9;
	public static int verticalSpaces = 5;
	public static int tileSize = 100;
	public static int pieceSize = tileSize / 2;
	//public static int menuBarSize = 25;
	public static int displayInfoSize = 150;
	public static int windowHeight = Main.verticalSpaces * Main.tileSize;  
	public static int windowWidth = Main.horizontalSpaces * Main.tileSize; 
	
	public static void main(String[] args) {

		//GameBoard gb = new GameBoard();
		//GameManager manager = new GameManager();
		View view = new View();
		//view.updatePieces(gb.getPieces());
	}

}
