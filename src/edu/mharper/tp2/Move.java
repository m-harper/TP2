package edu.mharper.tp2;


public class Move
{
	public enum MoveType
	{
		CAPTURE,
		PAIKA,
		END_CHAIN,
		SACRIFICE
	}
	
	public Point startPoint;
	public Point endPoint;
	public MoveType type;
	
	//For capture and paika moves
	public Move(MoveType tp, Point start, Point end)
	{
		type = tp;
		startPoint = new Point(start.getX(), start.getY());
		endPoint = new Point(end.getX(), end.getY());
	}
	
	//For moves other than capture or paika
	public Move(MoveType tp, Point piecePoint)
	{
		type = tp;
		startPoint = piecePoint;
		endPoint = null;
	}
	
}