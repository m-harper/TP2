package edu.mharper.tp2;

import java.awt.Color;
import java.util.ArrayList;

public class GameEval {
	
	// Counts the number of pieces on the board
	public static int UtilityFunction(GameBoard board, boolean isWhite) {
		ArrayList<GamePiece> pieces = board.getPiecesList();
		
		int whiteCount = 0;
		int blackCount = 0;
		
		for (GamePiece piece : pieces) {
			if (piece != null) {
				if (piece.getColor().equals(Color.white))
					whiteCount++;
				else
					blackCount++;
			}
		}
		
		if (isWhite)
			return whiteCount;
		else
			return blackCount;
	}
}