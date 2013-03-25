package edu.mharper.tp2;

import java.util.ArrayList;

public class GameBoard
{
	//Stores all pieces, in no particular order
	//private ArrayList<GamePiece> pieces;
	private GamePiece[][] board;
	private int numRows;
	private int numCols;
	
	//Default constructor: constructs a 5 row by 9 col board
	public GameBoard()
	{
		//pieces = new ArrayList<GamePieces>();
		numRows = Main.verticalSpaces;
		numCols = Main.horizontalSpaces;
		board = new GamePiece[numRows][numCols];
	}
	
	//Add pieces in standard starting positions
	//Undefined when there are an even number of rows or cols
	public void initPieces()
	{
		board = new GamePiece[numRows][numCols];
		
		int middleRow = numRows / 2;
		int middleCol = numCols / 2;
				
		for(int r = 0; r < numRows; r++)
		{
			for(int c = 0; c < numCols; c++)
			{
				if(r < middleRow)
					board[r][c] = new GamePiece(0, r, c);
				else if(r > middleRow)
					board[r][c] = new GamePiece(1, r, c);
				else
				{
					if(c == middleCol)
						continue;
					else if(c % 2 == 0)
						board[r][c] = new GamePiece(0, r, c);
					else
						board[r][c] = new GamePiece(1, r, c);
				}
			}
		}
	}
	
	
	//Attempts to move piece at startPoint to endPoint
	//Returns false and does nothing if move is invalid
	public boolean movePiece(Point startPoint, Point endPoint)
	{
		if(!testMovePiece(startPoint, endPoint))
			return false;
		
		//if movement validity test passes, update board state
		//board[startPoint.getY()][startPoint.getX()].updatePosition(endPoint.getY(), endPoint.getX());
		board[endPoint.getY()][endPoint.getX()] = board[startPoint.getY()][startPoint.getX()];
		board[startPoint.getY()][startPoint.getX()] = null;
		
		return true;
	}
	
	//Removes pieces taken by a valid approaching capture from startPoint to endPoint
	public void capturePiecesApproaching(Point startPoint, Point endPoint)
	{
		int deltaX = endPoint.getX() - startPoint.getX();
		int deltaY = endPoint.getY() - startPoint.getY();
		
		int takeX = endPoint.getX() + deltaX;
		int takeY = endPoint.getY() + deltaY;
		
		while(removePiece(takeX, takeY))
		{
			takeX += deltaX;
			takeY += deltaY;
		}
	}
	
	//Removes pieces taken by a valid retreating take from startPoint to endPoint
	public void capturePiecesRetreating(Point startPoint, Point endPoint)
	{
		capturePiecesApproaching(endPoint, startPoint);
	}
	
	//Attempts to remove piece from (pieceRow, pieceCol)
	//Returns false if remove is invalid
	private boolean removePiece(int x, int y)
	{
		if(board[y][x] == null)
			return false;
		
		board[y][x] = null;
		return true;
	}

	//Checks if represented move is a valid one
	private boolean testMovePiece(Point startPoint, Point endPoint)
	{
		//Check if there is a piece to be moved
		if(hasPiece(startPoint))
			return false;
		
		//Check if move is on the board
		if(endPoint.getX() < 0 || endPoint.getY() < 0)
			return false;
		if(endPoint.getY() >= numRows || endPoint.getX() >= numCols)
			return false;
		
		//Check if move is to an adjacent spot
		boolean isAdjacentMove = (Math.abs(startPoint.getX() - endPoint.getX()) <= 1) && 
									(Math.abs(startPoint.getY() - endPoint.getY()) <= 1);
		if(!isAdjacentMove)
			return false;
		
		boolean isDiagonalMove = (endPoint.getX() - startPoint.getX()) % 2 == 1 && 
									(endPoint.getY() - endPoint.getY()) % 2 == 1;
		
		//If move is diagonal, check if spot allows diagonal moves 
		if(isDiagonalMove && canMoveDiagonal(startPoint))
			return false;
		
		//Check if spot being moved to is empty
		if(hasPiece(startPoint))
			return false;
		
		return true;
	}
	
	public ArrayList<Point> getValidCapturingMoves(Point point)
	{
		ArrayList<Point> validMoves = getValidMoves(point);
		return validMoves;		
	}
	
	public ArrayList<Point> getValidChainMoves(Point point, ArrayList<Point> prevMoves)
	{
		//Get initially valid moves
		ArrayList<Point> validMoves = getValidMoves(point);
		
		//Remove moves to previously visited points
		for(int i = 0; i < validMoves.size(); i++)
		{
			for(Point prevMove: prevMoves)
			{
				if(validMoves.get(i) == prevMove)
				{
					validMoves.remove(i);
					i--;
					break;
				}
			}
		}
		
		return validMoves;
	}
	
	//Given a point with a piece, determines which moves this piece could go to
	//Returns null if there is no piece
	public ArrayList<Point> getValidMoves(Point piecePoint)
	{
		ArrayList<Point> validMoves = new ArrayList<Point>();
		int x = piecePoint.getX();
		int y = piecePoint.getY();
		
		for(int i = x-1; i <= x+1; i++)
		{
			for(int j = y-1; j <= y+1; j++)
			{
				if(i != x || j != y)
				{
					validMoves.add(new Point(i,j));
				}
			}
		}
		
		for(int i = 0; i < validMoves.size(); i++)
		{
			if(!testMovePiece(piecePoint, validMoves.get(i)))
			{
				validMoves.remove(i);
				i--;
			}
		}
		
		return validMoves;
	}
	
	
	//Returns true if point contains a piece, false otherwise
	public boolean hasPiece(Point point)
	{
		return (board[point.getY()][point.getX()] != null);
	}
	
	public GamePiece getPiece(Point point)
	{
		return board[point.getY()][point.getX()];
	}
	
	public int getNumRows()
	{
		return numRows;
	}
	
	public int getNumCols()
	{
		return numCols;
	}
	
	public GamePiece[][] getPieces() {
		return board;
	}
	
	public ArrayList<GamePiece> getPiecesList() {
		ArrayList<GamePiece> pieces = new ArrayList<GamePiece>();
		
		for (int i = 0; i < Main.verticalSpaces; i++) {
			for (int j = 0; j < Main.horizontalSpaces; j++) {
				pieces.add(board[i][j]);
			}
		}
		
		return pieces;
	}
	
	public void update(ArrayList<GamePiece> pieces) {
		for (int i = 0; i < Main.verticalSpaces; i++) {
			for (int j = 0; j < Main.horizontalSpaces; j++) {
				board[i][j] = null;
			}
		}
		for (GamePiece piece : pieces) {
			if (piece != null)
				board[piece.getRow()][piece.getColumn()] = piece;
		}
	}
	
	public ArrayList<Point> getValidMoves(GamePiece piece) {
		return new ArrayList<Point>();
	}
		
	private boolean canMoveDiagonal(Point point)
	{
		return (point.getX() + point.getY()) % 2 == 0;
	}
}