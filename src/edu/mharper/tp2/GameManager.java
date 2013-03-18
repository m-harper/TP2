package edu.mharper.tp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class GameManager
{
	private GameBoard board;
	
	public GameManager()
	{
		board = new GameBoard();
	}
	
	public void startGame()
	{
		board.initPieces();
	}
	
	public GameBoard getBoard()
	{
		return board;
	}
	
	public void saveGame() {
		// Output the current state of the board
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String filename = "/" + dateFormat.format(date) + ".sav";
		System.out.println(filename);
		
		File file = new File(filename);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(file);
			
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	public void loadGame() {
		
	}
	
	public ArrayList<GamePiece> getPieces()
	{
		ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
			
		for (int i = 0; i < Main.verticalSpaces; i++) {
			for (int j = 0; j < Main.horizontalSpaces; j++) {
				//System.out.println("Updating with " + gamePieces[i][j]);
				GamePiece piece = board.getPiece(i,j);
				
				if (piece != null) {
					System.out.println("Updating " + piece.getColor() + " piece");
					pieces.add(piece);
				}
			}
		}
		return pieces;
	}
	
}