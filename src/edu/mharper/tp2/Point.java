package edu.mharper.tp2;

import java.util.ArrayList;

public class Point {
	private int x, y;
	
	public Point(int _x, int _y) {
		x = _x;
		y = _y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Point point)
	{
		if(point == null)
			return false;
		
		return(x == point.getX() && y == point.getY());
	}
	
	public Point getN()
	{
		return new Point(x, y-1);
	}
	
	public Point getS()
	{
		return new Point(x, y+1);
	}
	
	public Point getE()
	{
		return new Point(x+1, y);
	}
	
	public Point getW()
	{
		return new Point(x-1, y);
	}
	
	public ArrayList<Point> getCardinalNeighbors()
	{
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(getN());
		points.add(getS());
		points.add(getE());
		points.add(getW());
		
		return points;
	}
	
	public Point getNE()
	{
		return new Point(x+1, y-1);
	}
	
	public Point getSE()
	{
		return new Point(x+1, y-1);
	}
	
	public Point getSW()
	{
		return new Point(x+1, y-1);
	}
	
	public Point getNW()
	{
		return new Point(x+1, y-1);
	}
	
	public ArrayList<Point> getDiagonalNeighbors()
	{
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(getNE());
		points.add(getSE());
		points.add(getSW());
		points.add(getNW());
		
		return points;
	}
	
	public ArrayList<Point> getAllNeighbors()
	{
		ArrayList<Point> allNeighbors = new ArrayList<Point>();
		allNeighbors.addAll(getCardinalNeighbors());
		allNeighbors.addAll(getDiagonalNeighbors());
		
		return allNeighbors;
	}
}