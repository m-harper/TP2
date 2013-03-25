package edu.mharper.tp2;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.CharBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

//Responsible for enforcing the rules of the game
public class GameManager
{
	private GameBoard board;
	private int redRemaining;
	private int blackRemaining;
	
	public GameManager()
	{
		board = new GameBoard();
		redRemaining = 22;
		blackRemaining = 22;
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		Date date = new Date();
		String filename = dateFormat.format(date) + ".sav";
		System.out.println(filename);
		
		try {
			FileOutputStream ofs = new FileOutputStream(filename);
			String fileText = "";
			for (GamePiece piece : board.getPiecesList()) {
				if (piece != null) {
					String colorText = "";
					if (piece.getColor() == Color.black)
						colorText = "black";						
					else if (piece.getColor() == Color.white)
						colorText = "white";
					
					fileText += colorText + "\t" + piece.getRow() + "\t" + piece.getColumn() + "\n";
				}
				else {
					fileText += "null\n";
				}
			}
			ofs.write(fileText.getBytes());
			ofs.close();
		} catch (FileNotFoundException e) {
			File file = new File(filename);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			saveGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void loadGame(String fileName) {
		ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
		File file = new File(fileName);
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				pieces.add(stringToPiece(scan.nextLine()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private GamePiece stringToPiece(String string) {
		if (string.equals("null"))
			return null;
		String color = string.substring(0, string.indexOf('\t'));
		string = string.substring(string.indexOf('\t') + 1);
		int row = Integer.parseInt(string.substring(0, string.indexOf('\t')));
		string = string.substring(string.indexOf('\t') + 1);
		int col = Integer.parseInt(string);
		
		int colorInt = 0;
		if (color.equals("white"))
			colorInt = 1;
		
		return new GamePiece(colorInt, row, col);
	}
	
	public ArrayList<GamePiece> getPieces()
	{
		ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
			
		for (int i = 0; i < Main.verticalSpaces; i++) {
			for (int j = 0; j < Main.horizontalSpaces; j++) {
				//System.out.println("Updating with " + gamePieces[i][j]);
				GamePiece piece = board.getPiece(new Point(j,i));
				
				if (piece != null) {
					//System.out.println("Updating " + piece.getColor() + " piece");
					pieces.add(piece);
				}
			}
		}
		return pieces;
	}
	
}