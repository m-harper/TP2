package edu.mharper.tp2;

public class GameBoard
{
	//Stores all pieces, in no particular order
	//private ArrayList<GamePiece> pieces;
	private GamePiece[][] board;
	private int numRows;
	private int numCols;
	
	//Default constructor: constructs a 5x9 board
	public GameBoard()
	{
		//pieces = new ArrayList<GamePieces>();
		numRows = 5;
		numCols = 9;
		board = new GamePiece[numRows][numCols];
	}
	
	//Attempts to add piece to (addRow, addCol)
	public void addPiece(int addRow, int addCol)
	{
	}
	
	//Attempts to remove piece from (pieceRow, pieceCol)
	public void removePiece(int pieceRow, int pieceCol)
	{
	}
	
	//Attempts to move piece at (pieceRow, pieceCol) to location (newRow, newCol)
	//Also checks movement validity
	public void movePiece(int pieceRow, int pieceCol, int newRow, int newCol)
	{
	}
}