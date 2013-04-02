package edu.mharper.tp2;

public class MaxNode extends Node
{
	public MaxNode(Move move)
	{
		super(move);
	}
	
	public boolean isMax()
	{
		return true;
	}
	
	public boolean isMin()
	{
		return false;
	}
	
	public Node addChild(Move childMove)
	{
		Node child = new MinNode(childMove);
		child.setParent(this);
		children.add(child);
		return child;
	}
	
}