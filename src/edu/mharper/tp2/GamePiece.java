package edu.mharper.tp2;

public class GamePiece
{
	public enum Player
	{
		FirstPlayer,
		SecondPlayer
	}
	
	private Player player;
	private int row;
	private int col;
	
	//default constructor (should not be needed)
	public GamePiece()
	{
	}
	
	//Typical constructor
	public GamePiece(Player whichPlayer, int startRow, int startCol)
	{
		player = whichPlayer;
		row = startRow;
		col = startCol;
	}
	
	//Changes piece position
	//Assumed that the move is valid- validity will be checked by GameBoard
	public void moveTo(int newRow, int newCol)
	{
		row = newRow;
		col = newCol;
	}
	
}