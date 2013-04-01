package edu.mharper.tp2;

import java.awt.Color;
import java.util.ArrayList;

import edu.mharper.tp2.Move.MoveType;

public class GameEval {
	
	// Returns utility of the board, scaled between 0 and 1
	// White wins = 1
	// Black wins = 0
	public static double UtilityFunction(GameBoard board)
	{
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
		
		int totalPieces = whiteCount + blackCount;
		
		return (double)whiteCount / totalPieces;
	}
	
	public static ArrayList<Move> getAllValidMoves(GameManager manager)
	{
		GameBoard board = manager.getBoard();
		Color currentPlayer = manager.getCurrentPlayer();
		ArrayList<GamePiece> pieces = board.getPiecesList();
		ArrayList<Move> allMoves = new ArrayList<Move>();
		
		for(GamePiece piece : pieces)
		{
			if(piece == null)
				continue;
			
			if(piece.getColor().equals(currentPlayer))
			{
				//Get all capture/paika moves
				ArrayList<Point> movePoints = manager.getValidMoves(piece);
				for(Point movePoint : movePoints)
				{
					if(manager.isAnyCaptureMove(piece, movePoint))
						allMoves.add(new Move(MoveType.CAPTURE, piece.getPoint(), movePoint));
					else
						allMoves.add(new Move(MoveType.PAIKA, piece.getPoint(), movePoint));
				}
				allMoves.add(new Move(MoveType.SACRIFICE, piece.getPoint()));
			}
		}
		return allMoves;		
	}
	
	//Performs move, altering state of GameManager manager
	public static void makeMove(GameManager manager, Move move)
	{
		GamePiece piece;
		
		if(move == null || move.type == null)
			return;
		
		if(move.startPoint != null)
			piece = manager.getBoard().getPiece(move.startPoint);
		else
			piece = null;
		
		switch(move.type)
		{
		case CAPTURE:
			if(manager.isAdvanceCaptureMove(piece, move.endPoint))
				manager.advanceCapturePieces(piece, move.endPoint);
			else
				manager.withdrawCapturePieces(piece, move.endPoint);
			manager.movePiece(piece, move.endPoint);
			break;
		case PAIKA:
			manager.movePiece(piece, move.endPoint);
			break;
		case END_CHAIN:
			break;
			
		case SACRIFICE:
			manager.sacrificePiece(piece);
		}
	}
	

}