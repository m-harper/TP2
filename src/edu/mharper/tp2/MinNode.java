package edu.mharper.tp2;

public class MinNode extends Node
{
	public MinNode(int val, int dep)
	{
		super(val, dep);
	}
	
	public boolean isMax()
	{
		return false;
	}
	
	public boolean isMin()
	{
		return true;
	}
	
	public void addChild(int childVal)
	{
		children.add(new MaxNode(childVal, depth + 1));
	}
	
}