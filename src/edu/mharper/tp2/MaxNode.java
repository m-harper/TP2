package edu.mharper.tp2;

public class MaxNode extends Node
{
	public MaxNode(int val, int dep)
	{
		super(val, dep);
	}
	
	public boolean isMax()
	{
		return true;
	}
	
	public boolean isMin()
	{
		return false;
	}
	
	public void addChild(int childVal)
	{
		children.add(new MinNode(childVal, depth + 1));
	}
	
}