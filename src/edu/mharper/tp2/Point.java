package edu.mharper.tp2;

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
		return(x == point.getX() && y == point.getY());
	}
}