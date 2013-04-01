package edu.mharper.tp2;

public class MinNode extends Node
{
	public MinNode(Move move, int val)
	{
		super(move, val, 0);
	}
	public MinNode(Move move, int val, int dep)
	{
		super(move, val, dep);
	}
	
	public boolean isMax()
	{
		return false;
	}
	
	public boolean isMin()
	{
		return true;
	}
	
	public Node addChild(Move childMove, int childVal)
	{
		Node child = new MaxNode(childMove, childVal, depth + 1);
		children.add(child);
		return child;
	}
	
}