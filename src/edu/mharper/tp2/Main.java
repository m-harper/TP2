package edu.mharper.tp2;


public class Main {

	public static String gameTitle = "Fanorona";
	public static int horizontalSpaces = 9;
	public static int verticalSpaces = 5;
	public static int tileSize = 100;
	public static int pieceSize = tileSize / 2;
	public static int displayInfoSize = 150;
	public static int windowHeight = Main.verticalSpaces * Main.tileSize;  
	public static int windowWidth = Main.horizontalSpaces * Main.tileSize; 
	public static int defaultTime = 15000;
	public static int port = 1337;
	public static boolean runAsServer = false;
	public static boolean runAsClient = false;
	public static boolean enableAI= false;
	public static int maxTurns = 10 * verticalSpaces;
	
	public static void main(String[] args) {
		AITest test = new AITest();
		test.testMinMaxTree();
		
		Settings view = new Settings();
		//view.updatePieces(gb.getPieces());
	
	}

}
