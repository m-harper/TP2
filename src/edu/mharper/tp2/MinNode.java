package edu.mharper.tp2;

public class MinNode extends Node
{
	public MinNode(Move move)
	{
		super(move);
	}
	
	public boolean isMax()
	{
		return false;
	}
	
	public boolean isMin()
	{
		return true;
	}
	
	public Node addChild(Move childMove)
	{
		Node child = new MaxNode(childMove);
		child.setParent(this);
		children.add(child);
		return child;
	}
	
}