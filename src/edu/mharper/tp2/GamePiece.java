package edu.mharper.tp2;

public class GamePiece
{
	public enum Player
	{
		Black,
		White
	}
	
	private Player player;
	private int row;
	private int col;
	
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

	public Player getPlayer() 
	{
		return player;
	}

	public int getRow() 
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
}