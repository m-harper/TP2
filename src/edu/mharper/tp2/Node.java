package edu.mharper.tp2;

import java.util.ArrayList;

public abstract class Node
{
	protected int value;
	protected int depth;
	protected ArrayList<Node> children;

	public Node(int v, int d)
	{
		value = v;
		depth = d;
		children = new ArrayList<Node>();
	}
	
	public int getVal()
	{
		return value;
	}
	
	public ArrayList<Node> getChildren()
	{
		return children;
	}
	
	public abstract void addChild(int childVal);
	public abstract boolean isMin();
	public abstract boolean isMax();
}