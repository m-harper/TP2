package edu.mharper.tp2;

import java.util.ArrayList;

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
	public ArrayList<Point> endPoints;
	public MoveType type;
	
	//For capture, chain capture, and paika moves
	public Move(MoveType tp, Point start, Point end)
	{
		type = tp;
		startPoint = new Point(start.getX(), start.getY());
		endPoints = new ArrayList<Point>();
		endPoints.add(end);
	}
	
	//For moves other than capture or paika
	public Move(MoveType tp, Point piecePoint)
	{
		type = tp;
		startPoint = piecePoint;
		endPoints = new ArrayList<Point>();
	}
	
}