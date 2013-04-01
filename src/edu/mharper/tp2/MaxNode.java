package edu.mharper.tp2;

public class MaxNode extends Node
{
	public MaxNode(Move move, int val)
	{
		super(move, val, 0);
	}
	public MaxNode(Move move, int val, int dep)
	{
		super(move, val, dep);
	}
	
	public boolean isMax()
	{
		return true;
	}
	
	public boolean isMin()
	{
		return false;
	}
	
	public Node addChild(Move childMove, int childVal)
	{
		Node child = new MinNode(childMove, childVal, depth + 1);
		children.add(child);
		return child;
	}
	
}