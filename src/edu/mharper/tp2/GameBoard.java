package edu.mharper.tp2;

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
		numRows = 5;
		numCols = 9;
		board = new GamePiece[numRows][numCols];
		
		//Add all pieces
		initPieces();
	}
	
	//Add pieces in standard starting positions
	//Undefined when there are an even number of rows or cols
	private void initPieces()
	{
		int middleRow = numRows / 2;
		int middleCol = numCols / 2;
				
		for(int r = 0; r < numRows; r++)
		{
			for(int c = 0; c < numCols; c++)
			{
				if(r < middleRow)
					board[r][c] = new GamePiece(GamePiece.Player.Black, r, c);
				else if(r > middleRow)
					board[r][c] = new GamePiece(GamePiece.Player.White, r, c);
				else
				{
					if(c == middleCol)
						continue;
					else if(c % 2 == 0)
						board[r][c] = new GamePiece(GamePiece.Player.Black, r, c);
					else
						board[r][c] = new GamePiece(GamePiece.Player.White, r, c);
				}
			}
		}
	}
	
	//Attempts to remove piece from (pieceRow, pieceCol)
	//Returns false if remove is invalid
	public boolean removePiece(int pieceRow, int pieceCol)
	{
		if(board[pieceRow][pieceCol] == null)
			return false;
		
		board[pieceRow][pieceCol] = null;
		return true;
	}
	
	//Attempts to move piece at (pieceRow, pieceCol) to location (newRow, newCol)
	//Returns false and does nothing if move is invalid
	public boolean movePiece(int pieceRow, int pieceCol, int newRow, int newCol)
	{
		//Check if there is a piece to be moved
		if(hasPiece(pieceRow, pieceCol))
			return false;
		
		//Check if move is on the board
		if(newRow < 0 || newCol < 0)
			return false;
		if(newRow >= numRows || newCol >= numCols)
			return false;
		
		//Check if move is to an adjacent spot
		boolean isAdjacentMove = (Math.abs(newRow - pieceRow) <= 1) && 
									(Math.abs(newCol - pieceCol) <= 1);
		if(!isAdjacentMove)
			return false;
		
		boolean isDiagonalMove = (newRow - pieceRow) % 2 == 1 && 
									(newCol - pieceCol) % 2 == 1;
		
		//If move is diagonal, check if spot allows diagonal moves 
		if(isDiagonalMove && (pieceRow + pieceCol) % 2 == 0)
			return false;
		
		//Check if spot being moved to is empty
		if(hasPiece(newRow, newCol))
			return false;
		
		//If all these tests pass, update piece location
		board[newRow][newCol] = board[pieceRow][pieceCol];
		board[pieceRow][pieceCol] = null;		
		board[pieceRow][pieceCol].moveTo(newRow, newCol);
		
		return true;
	}
	
	//Returns true if (row,col) contains a piece, false otherwise
	public boolean hasPiece(int row, int col)
	{
		return (board[row][col] != null);
	}
	
}