package edu.mharper.tp2;

import java.util.ArrayList;


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